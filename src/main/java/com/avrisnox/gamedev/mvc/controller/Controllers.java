package com.avrisnox.gamedev.mvc.controller;

import com.avrisnox.gamedev.build.Avalanche.Dumbbell;

public enum Controllers {
	DUMBBELL(new Dumbbell())
	;
	Controller controller;

	Controllers(Controller controller) {
		this.controller = controller;
	}

	public Controller get() {
		return controller;
	}
}
