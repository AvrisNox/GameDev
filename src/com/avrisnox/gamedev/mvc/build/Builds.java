package com.avrisnox.gamedev.mvc.build;

import com.avrisnox.gamedev.mvc.controller.Controllers;
import com.avrisnox.gamedev.mvc.model.Models;
import com.avrisnox.gamedev.mvc.view.Views;

public enum Builds {
	AVALANCHE(Controllers.DUMBELL, Models.JANUS, Views.QUERCUS)
	;
	private Controllers controller;
	private Models model;
	private Views view;

	private Builds(Controllers controller, Models model, Views view) {
		this.controller = controller;
		this.model = model;
		this.view = view;
	}

	public Controllers getController() {
		return controller;
	}

	public Models getModel() {
		return model;
	}

	public Views getView() {
		return view;
	}
}
