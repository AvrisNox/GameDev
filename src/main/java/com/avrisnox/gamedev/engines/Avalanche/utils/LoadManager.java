package com.avrisnox.gamedev.engines.Avalanche.utils;

public class LoadManager {
	public final RawPolyManager POLY_MANAGER;
	public final RawSoundManager AUDIO_MANAGER;

	public LoadManager() {
		POLY_MANAGER = new RawPolyManager();
		AUDIO_MANAGER = new RawSoundManager();
	}

	public void close() {
		POLY_MANAGER.close();
		AUDIO_MANAGER.close();
	}
}
