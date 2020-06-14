package com.avrisnox.gamedev.Projects.Pacman;

import com.avrisnox.gamedev.build.Avalanche.Avalanche;

import java.lang.reflect.InvocationTargetException;

public class Pacman {
	public static void main(String... args) {
		try {
			Avalanche engine = new Avalanche(
					PmModel.class,
					PmView.class,
					PmController.class
			);
			engine.run();
		} catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
