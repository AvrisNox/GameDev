package com.avrisnox.gamedev.build;

import com.avrisnox.gamedev.mvc.controller.Controller;
import com.avrisnox.gamedev.mvc.model.Model;
import com.avrisnox.gamedev.mvc.view.View;

/**
 * Engine abstract - all builds must extend "Engine"
 */
public abstract class Engine {
	/* ---CLASS VARS--- */
	protected Model model;
	protected View view;
	protected Controller controller;
	protected long window;

	/**
	 * Primary entrypoint for the application (if main exists, process args if necessary and then call run)
	 */
	public void run() {
		init();
		while(model.isRunning()) {
			update();
			draw();
		}
		destroy();
	}
	protected void init(){ }
	protected void update() {
		controller.update();
		model.update(window);
	}
	protected void draw() {
		view.draw();
	}
	protected void destroy(){ }

	public void setWindow(long window) {
		this.window = window;
	}
}
