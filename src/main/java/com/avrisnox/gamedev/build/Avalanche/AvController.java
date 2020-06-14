package com.avrisnox.gamedev.build.Avalanche;

import com.avrisnox.gamedev.mvc.controller.Controller;
import com.avrisnox.gamedev.mvc.model.Model;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwSetJoystickCallback;

public class AvController extends Controller {
	private class KeyHandler extends GenericKeyHandler {
		private void event(long window, int key, int scancode, int action, int mods) {
			if(model.getWindow() == window)
				model.keyEvent(key, action, mods);
		}

		@Override
		protected void update() { }
	}

	private class MouseHandler extends GenericMouseHandler {
		private void move(long window, double xpos, double ypos) {
			if(model.getWindow() == window)
				model.mouseMove(xpos, ypos);
		}
		private void enter(long window, boolean entered) {
			if(model.getWindow() == window)
				model.mouseEnter(entered);
		}
		private void event(long window, int button, int action, int mods) {
			if(model.getWindow() == window)
				model.mouseEvent(button, action, mods);
		}
		private void scroll(long window, double xoff, double yoff) {
			if(model.getWindow() == window)
				model.mouseScroll(xoff, yoff);
		}

		@Override
		protected void update() { }
	}

	private class JoystickHandler extends GenericJoystickHandler {
		private final int[] joysticks = {
				GLFW_JOYSTICK_1,
				GLFW_JOYSTICK_2,
				GLFW_JOYSTICK_3,
				GLFW_JOYSTICK_4,
				GLFW_JOYSTICK_5,
				GLFW_JOYSTICK_6,
				GLFW_JOYSTICK_7,
				GLFW_JOYSTICK_8,
				GLFW_JOYSTICK_9,
				GLFW_JOYSTICK_10,
				GLFW_JOYSTICK_11,
				GLFW_JOYSTICK_12,
				GLFW_JOYSTICK_13,
				GLFW_JOYSTICK_14,
				GLFW_JOYSTICK_15,
				GLFW_JOYSTICK_16
		};

		private void connect(int jid, int event) {
			model.joystickConnect(jid, event);
		}

		@Override
		protected void update() {
			for(int JOYSTICK : joysticks) {
				if(glfwJoystickPresent(JOYSTICK)) {
					model.joystickUpdate(JOYSTICK);
				}
			}
		}
	}

	public AvController() {
		super();
	}

	@Override
	public void init(Model model) {
		super.init(model);
		long window = this.model.getWindow();

		/* Keyboard handlers */
		keys = new KeyHandler();
		glfwSetKeyCallback(window, ((KeyHandler)keys)::event);

		/* Mouse handlers */
		mouse = new MouseHandler();
		glfwSetCursorPosCallback(window, ((MouseHandler)mouse)::move);
		if(glfwRawMouseMotionSupported())
			glfwSetInputMode(window, GLFW_RAW_MOUSE_MOTION, GLFW_TRUE);
		glfwSetCursorEnterCallback(window, ((MouseHandler)mouse)::enter);
		glfwSetMouseButtonCallback(window, ((MouseHandler)mouse)::event);
		glfwSetScrollCallback(window, ((MouseHandler)mouse)::scroll);

		/* Controller handlers */
		joys = new JoystickHandler();
		glfwSetJoystickCallback(((JoystickHandler)joys)::connect);
	}

	@Override
	protected void preupdate() {
		super.preupdate();
		glfwPollEvents();
	}
}
