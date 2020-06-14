package com.avrisnox.gamedev.build.Avalanche;

import com.avrisnox.gamedev.mvc.model.Model;

import static org.lwjgl.glfw.GLFW.*;

public class Janus extends Model {
	@Override
	public void keyEvent(long window, int key, int action, int mods) {
		if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
			glfwSetWindowShouldClose(window, true);
	}

	@Override
	public void mouseMove(long window, double xpos, double ypos) {

	}

	@Override
	public void mouseEnter(long window, boolean entered) {

	}

	@Override
	public void mouseEvent(long window, int button, int action, int mods) {

	}

	@Override
	public void mouseScroll(long window, double xoff, double yoff) {

	}

	@Override
	public void joystickConnect(int jid, int event) {

	}

	@Override
	public void joystickUpdate(int jid) {

	}

	@Override
	public void update(long window) {
		running = !glfwWindowShouldClose(window);
	}
}
