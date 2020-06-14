package com.avrisnox.gamedev.build;

import com.avrisnox.gamedev.mvc.controller.Controller;
import com.avrisnox.gamedev.mvc.model.Model;
import com.avrisnox.gamedev.mvc.view.View;

import java.lang.reflect.InvocationTargetException;

/**
 * Engine abstract - all builds must extend "Engine"
 */
public abstract class Engine {
	/* ---CLASS VARS--- */
	protected Model model;
	protected View view;
	protected Controller controller;
	protected long window;

	public Engine(Class<? extends Model> mClass, Class<? extends View> vClass, Class<? extends Controller> cClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		model = mClass.getConstructor().newInstance();
		model.init(this);
		view = vClass.getConstructor().newInstance();
		view.init(model);
		controller = cClass.getConstructor().newInstance();
		controller.init(model);
	}

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
		model.update();
	}
	protected void draw() {
		view.draw();
	}
	protected void destroy(){ }

	public void setWindow(long window) {
		this.window = window;
	}

	public long getWindow() {
		return window;
	}
}
