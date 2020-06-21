package com.avrisnox.gamedev.mvc.controller;

import com.avrisnox.gamedev.mvc.model.Model;

/**
 * Controller type for MV<bold>C</bold>.
 * Should handle input from the user in whatever form the associated model will care about.
 * @param <MType> The class type for the Model
 */
@SuppressWarnings("WeakerAccess")
public abstract class Controller <MType extends Model> {
	protected MType model;
	protected GenericKeyHandler keys;
	protected GenericMouseHandler mouse;
	protected GenericJoystickHandler joys;

	/**
	 * A class for handling a keyboard
	 */
	protected abstract class GenericKeyHandler {
		protected abstract void update();
	}

	/**
	 * A class for handling a mouse
	 */
	protected abstract class GenericMouseHandler {
		protected abstract void update();
	}

	/**
	 * A class for handling a joystick
	 */
	protected abstract class GenericJoystickHandler {
		protected abstract void update();
	}

	/**
	 * The default controller constructor.
	 */
	public Controller() { }

	/**
	 * Performs controller initialization logic.
	 * @param model The model this controller will control
	 */
	public void init(MType model) {
		this.model = model;
	}

	/**
	 * Functionality to perform before updating.
	 */
	protected void preupdate() { }

	/**
	 * Functionality to perform after updating.
	 */
	protected void postupdate() { }

	/**
	 * The update method.
	 * Do not call super.update() if you extend this - this function calls both pre and post update functions.
	 */
	public void update() {
		preupdate();

		/* Various handling */
		keys.update();
		mouse.update();
		joys.update();

		postupdate();
	}
}
