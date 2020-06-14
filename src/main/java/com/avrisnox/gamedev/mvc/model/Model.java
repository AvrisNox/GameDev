package com.avrisnox.gamedev.mvc.model;

public abstract class Model {
	// TODO: Rework to make it look less like OpenGL (this will need modifications to both Model and Controller)
	//  Can still use keyEven, mouseMove, etc., just need to remove "long window" and things like that...
	protected boolean running;

	public Model() {
		running = true;
	}

	public boolean isRunning() {
		return running;
	}

	public abstract void keyEvent(long window, int key, int action, int mods);
	public abstract void mouseMove(long window, double xpos, double ypos);
	public abstract void mouseEnter(long window, boolean entered);
	public abstract void mouseEvent(long window, int button, int action, int mods);
	public abstract void mouseScroll(long window, double xoff, double yoff);
	public abstract void joystickConnect(int jid, int event);
	public abstract void joystickUpdate(int jid);
	public abstract void update(long window);
}
