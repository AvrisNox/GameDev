package com.avrisnox.gamedev.mvc.view;

import com.avrisnox.gamedev.build.Avalanche.Quercus;

public enum Views {
	QUERCUS(new Quercus())
	;
	View view;

	Views(View view) {
		this.view = view;
	}

	public View get() {
		return view;
	}
}
