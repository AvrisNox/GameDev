package com.avrisnox.gamedev.build;

import com.avrisnox.gamedev.mvc.controller.Controller;
import com.avrisnox.gamedev.mvc.model.Model;
import com.avrisnox.gamedev.mvc.view.View;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.InvocationTargetException;

/**
 * Engine abstract - all builds must extend "Engine"
 */
@SuppressWarnings("WeakerAccess")
public abstract class Engine<MType extends Model, VType extends View<MType>, CType extends Controller<MType>> {
	/* ---CLASS VARS--- */
	protected MType model;
	protected VType view;
	protected CType controller;
	protected long window;

	public Engine(Class<MType> mClass, Class<VType> vClass, Class<CType> cClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		model = mClass.getConstructor().newInstance();
		model.init(this);
		view = vClass.getConstructor().newInstance();
		view.init(model);
		controller = cClass.getConstructor().newInstance();
		controller.init(model);

		model.load();
	}

	@SuppressWarnings("unused")
	public static <MType extends Model, VType extends View<MType>, CType extends Controller<MType>> void start(Class<MType> mClass, Class<VType> vClass, Class<CType> cClass) {
		throw new NotImplementedException();
	}

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
	protected void destroy(){
		model.destroy();
		view.destroy();
	}

	public void setWindow(long window) {
		this.window = window;
	}

	public long getWindow() {
		return window;
	}
}
