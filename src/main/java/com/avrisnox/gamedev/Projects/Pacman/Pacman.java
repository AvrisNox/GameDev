package com.avrisnox.gamedev.Projects.Pacman;

import com.avrisnox.gamedev.build.Avalanche.Avalanche;

import java.lang.reflect.InvocationTargetException;

public class Pacman {
	public static void main(String... args) {
		Avalanche.start(
				PmModel.class,
				PmView.class,
				PmController.class
		);
	}
}
