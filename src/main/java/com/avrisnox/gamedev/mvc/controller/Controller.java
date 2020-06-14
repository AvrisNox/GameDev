package com.avrisnox.gamedev.mvc.controller;

import com.avrisnox.gamedev.mvc.model.Model;

public abstract class Controller {
	protected Model model;
	protected GenericKeyHandler keys;
	protected GenericMouseHandler mouse;
	protected GenericJoystickHandler joys;

	protected abstract class GenericKeyHandler {
		protected abstract void update();
	}

	protected abstract class GenericMouseHandler {
		protected abstract void update();
	}

	protected abstract class GenericJoystickHandler {
		protected abstract void update();
	}

	public Controller(Model model) {
		this.model = model;
	}

	protected void preupdate() { }

	protected void postupdate() { }

	public void update() {
		preupdate();

		/* Various handling */
		keys.update();
		mouse.update();
		joys.update();

		postupdate();
	}
}
