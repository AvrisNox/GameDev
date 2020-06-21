package com.avrisnox.gamedev.build.Avalanche.utils;

@SuppressWarnings("WeakerAccess")
public class RawSound {
	private int source;
	private int buffer;
	private String id;

	public RawSound(String id, int source, int buffer) {
		this.id = id;
		this.source = source;
		this.buffer = buffer;
	}

	public String getId() {
		return id;
	}

	int getSource() {
		return source;
	}

	int getBuffer() {
		return buffer;
	}
}
