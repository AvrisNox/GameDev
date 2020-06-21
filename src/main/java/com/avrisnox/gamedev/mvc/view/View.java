package com.avrisnox.gamedev.mvc.view;

import com.avrisnox.gamedev.mvc.model.Model;

public abstract class View <MType extends Model> {
	protected MType model;

	public View() { }

	public void init(MType model) {
		this.model = model;
	}

	public abstract void draw();

	public void destroy() { }
}
