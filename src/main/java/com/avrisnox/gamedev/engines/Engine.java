package com.avrisnox.gamedev.engines;

import com.avrisnox.gamedev.mvc.controller.Controller;
import com.avrisnox.gamedev.mvc.model.Model;
import com.avrisnox.gamedev.mvc.view.View;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.InvocationTargetException;

/**
 * Engine abstract - all builds must extend "Engine".
 * For all internally overloaded functions, first call super.(func) (except run).
 * @param <MType> The class type for the Model
 * @param <VType> The class type for the View
 * @param <CType> The class type for the Controller
 */
@SuppressWarnings("WeakerAccess")
public abstract class Engine<MType extends Model, VType extends View<MType>, CType extends Controller<MType>> {
	/* ---CLASS VARS--- */
	protected MType model;
	protected VType view;
	protected CType controller;
	protected long window;

	/**
	 * The default engine constructor.
	 * @param mClass The class for this engine's Model
	 * @param vClass The class for this engine's View
	 * @param cClass The class for this engine's Controller
	 * @throws NoSuchMethodException Thrown by reflection failure
	 * @throws IllegalAccessException Thrown by reflection failure
	 * @throws InvocationTargetException Thrown by reflection failure
	 * @throws InstantiationException Thrown by reflection failure
	 */
	public Engine(Class<MType> mClass, Class<VType> vClass, Class<CType> cClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		// Perform model instantiation and initialization
		model = mClass.getConstructor().newInstance();
		model.init(this);
		// Perform view instantiation and initialization
		view = vClass.getConstructor().newInstance();
		view.init(model);
		// Perform controller instantiation and initialization
		controller = cClass.getConstructor().newInstance();
		controller.init(model);
	}

	/**
	 * Should statically start the engine without needing to instantiate it and then call run.
	 * @param mClass The class for this engine's Model
	 * @param vClass The class for this engine's View
	 * @param cClass The class for this engine's Controller
	 * @param <MType> The class type for the Model
	 * @param <VType> The class type for the View
	 * @param <CType> The class type for the Controller
	 */
	@SuppressWarnings("unused")
	public static <MType extends Model, VType extends View<MType>, CType extends Controller<MType>> void start(Class<MType> mClass, Class<VType> vClass, Class<CType> cClass) {
		throw new NotImplementedException();
	}

	/**
	 * The basic running loop for any engine:
	 * 	Calls init
	 * 	While model.isRunning(), calls update and draw
	 * 	Calls destroy
	 */
	public void run() {
		init();
		try {
			while (model.isRunning()) {
				preloop();
				update();
				draw();
				postloop();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			destroy();
		}
	}

	/**
	 * Early engine initialization.
	 */
	protected void init(){
		model.load();
	}

	/**
	 * This is for any extra work that should be done before calling update.
	 */
	protected void preloop() { }

	/**
	 * Performs engine updates before drawing.
	 */
	protected void update() {
		controller.update();
		model.update();
	}

	/**
	 * Performs engine drawing.
	 */
	protected void draw() {
		view.draw();
	}

	/**
	 * This is for any extra work that should be done after calling draw.
	 */
	protected void postloop() { }

	/**
	 * Destroys all resources when the engine shuts down.
	 */
	protected void destroy(){
		model.destroy();
		view.destroy();
	}

	/**
	 * Sets the window for the engine to render in.
	 * TODO: Admitted, this is still OpenGL reminiscent and could do with a bit of a change to better reflect the
	 * 	freedom of the abstract classes.
	 * @param window The window this engine will run in
	 */
	public void setWindow(long window) {
		this.window = window;
	}

	/**
	 * Returns the window that the engine is running in.
	 * @return The window
	 */
	public long getWindow() {
		return window;
	}
}
