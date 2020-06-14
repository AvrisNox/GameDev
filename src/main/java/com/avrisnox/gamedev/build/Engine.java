package com.avrisnox.gamedev.build;

import com.avrisnox.gamedev.mvc.controller.Controller;
import com.avrisnox.gamedev.mvc.model.Model;
import com.avrisnox.gamedev.mvc.view.View;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

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
	 * Primary entrypoint for the application (if main exists, process args and then call run)
	 */
	public void run() {
		init();
		while(!glfwWindowShouldClose(window)) {
			update();
			draw();
		}
		destroy();
	}
	protected void init(){ }
	protected abstract void update();
	protected abstract void draw();
	protected void destroy(){ }
}
