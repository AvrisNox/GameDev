package com.avrisnox.gamedev.build.Avalanche;

import com.avrisnox.gamedev.mvc.model.Model;

import static org.lwjgl.glfw.GLFW.*;

public class AvModel extends Model {
	public AvModel() {
		super();
	}

	@Override
	public void keyEvent(int key, int action, int mods) {
		if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
			glfwSetWindowShouldClose(getWindow(), true);
	}

	@Override
	public void mouseMove(double xpos, double ypos) {

	}

	@Override
	public void mouseEnter(boolean entered) {

	}

	@Override
	public void mouseEvent(int button, int action, int mods) {

	}

	@Override
	public void mouseScroll(double xoff, double yoff) {

	}

	@Override
	public void joystickConnect(int jid, int event) {

	}

	@Override
	public void joystickUpdate(int jid) {

	}

	@Override
	public void update() {
		running = !glfwWindowShouldClose(getWindow());
	}
}
