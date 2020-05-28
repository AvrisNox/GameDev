package com.avrisnox.gamedev.mvc.build;

import com.avrisnox.gamedev.mvc.controller.Controller;
import com.avrisnox.gamedev.mvc.model.Model;
import com.avrisnox.gamedev.mvc.view.View;

public interface Build {
	public Controller getController();
	public Model getModel();
	public View getView();

	public void init();
	public void update();
	public void draw();
}
