package com.avrisnox.gamedev.mvc.model;

import com.avrisnox.gamedev.build.Avalanche.Janus;

public enum Models {
	JANUS(new Janus())
	;
	Model model;

	Models(Model model) {
		this.model = model;
	}

	public Model get() {
		return model;
	}
}
