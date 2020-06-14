package com.avrisnox.gamedev.mvc.view;

import com.avrisnox.gamedev.mvc.model.Model;

public abstract class View {
	protected Model model;

	public View() { }

	public void init(Model model) {
		this.model = model;
	}

	public abstract void draw();
}
