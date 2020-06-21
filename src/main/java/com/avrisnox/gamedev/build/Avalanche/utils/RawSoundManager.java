package com.avrisnox.gamedev.build.Avalanche.utils;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBVorbisInfo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static org.lwjgl.BufferUtils.createByteBuffer;
import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.stb.STBVorbis.*;
import static org.lwjgl.system.MemoryUtil.NULL;

@SuppressWarnings("WeakerAccess")
public class RawSoundManager {
	private HashMap<String, Boolean> status = new HashMap<>();

	private List<RawSound> sounds = new LinkedList<>();

	public void simAudio() {
		for(RawSound sound : sounds) {
			int state = alGetSourcei(sound.getSource(), AL_SOURCE_STATE);
			if(status.getOrDefault(sound.getId(), false) && (state == AL_PAUSED || state == AL_STOPPED))
				alSourcePlay(sound.getSource());
			if(!status.getOrDefault(sound.getId(), true) && state == AL_PLAYING)
				alSourceStop(sound.getSource());
		}
	}

	public void load(String file, String id) {
		int buffer = alGenBuffers();
		int source = alGenSources();

		try(STBVorbisInfo inf = STBVorbisInfo.malloc()) {
			ShortBuffer pcm = readVorbis(file, 32*1024, inf);
			alBufferData(buffer, inf.channels() == 1? AL_FORMAT_MONO16 : AL_FORMAT_STEREO16, pcm, inf.sample_rate());
		}
		alSourcei(source, AL_BUFFER, buffer);
		sounds.add(new RawSound(id, source, buffer));

		alSourcePlay(source);
		alSourceStop(source);
	}

	public void enableSound(String id) {
		status.put(id, true);
	}

	public void disableSound(String id) {
		status.put(id, false);
	}

	private ShortBuffer readVorbis(String file, @SuppressWarnings("SameParameterValue") int size, STBVorbisInfo info) {
		ByteBuffer vorbis;
		try {
			vorbis = ioResourceToByteBuffer(file, size);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		IntBuffer err = BufferUtils.createIntBuffer(1);
		long decoder = stb_vorbis_open_memory(vorbis, err, null);
		if(decoder == NULL)
			throw new RuntimeException("Failed to open OGG Vorbis file: " + err.get(0));

		stb_vorbis_get_info(decoder, info);

		int channels = info.channels();
		ShortBuffer pcm = BufferUtils.createShortBuffer(stb_vorbis_stream_length_in_samples(decoder) * channels);
		stb_vorbis_get_samples_short_interleaved(decoder, channels, pcm);
		stb_vorbis_close(decoder);
		return pcm;
	}

	private ByteBuffer ioResourceToByteBuffer(String resource, int buffSize) throws IOException {
		ByteBuffer buffer;

		Path path = Paths.get(resource);
		if(Files.isReadable(path)) {
			try(SeekableByteChannel fc = Files.newByteChannel(path)) {
				buffer = createByteBuffer((int)fc.size() + 1);
				//noinspection StatementWithEmptyBody
				while(fc.read(buffer) != -1);
			}
		} else {
			try(
					InputStream source = RawSoundManager.class.getClassLoader().getResourceAsStream(resource);
					ReadableByteChannel rbc = Channels.newChannel(Objects.requireNonNull(source))
			) {
				buffer = createByteBuffer(buffSize);

				while(true) {
					int bytes = rbc.read(buffer);
					if(bytes == -1)
						break;

					if(buffer.remaining() == 0)
						buffer = resizeBuffer(buffer, buffer.capacity() * 3 / 2);
				}
			}
		}

		buffer.flip();
		return buffer;
	}

	private ByteBuffer resizeBuffer(ByteBuffer buffer, int newCap) {
		ByteBuffer newBuff = BufferUtils.createByteBuffer(newCap);
		buffer.flip();
		newBuff.put(buffer);
		return newBuff;
	}

	public void close() {
		for(RawSound wav: sounds) {
			int source = wav.getSource();
			int buffer = wav.getBuffer();

			alSourceStop(source);
			alDeleteSources(source);
			alDeleteBuffers(buffer);
		}
	}
}
