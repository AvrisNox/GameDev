package com.avrisnox.gamedev.mvc.model;

import com.avrisnox.gamedev.build.Engine;

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class Model {
	protected boolean running;
	protected Engine engine;

	public Model() { }

	public boolean isRunning() {
		return running;
	}
	public void setWindow(long window) {
		engine.setWindow(window);
	}
	public long getWindow() {
		return engine.getWindow();
	}

	public void init(Engine engine) {
		running = true;
		this.engine = engine;
	}
	public void load() {}
	public void destroy() {}

	public abstract void keyEvent(int key, int action, int mods);
	public abstract void mouseMove(double xpos, double ypos);
	public abstract void mouseEnter(boolean entered);
	public abstract void mouseEvent(int button, int action, int mods);
	public abstract void mouseScroll(double xoff, double yoff);
	public abstract void joystickConnect(int jid, int event);
	public abstract void joystickUpdate(int jid);
	public abstract void update();
}
