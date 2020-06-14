package com.avrisnox.gamedev.build.Avalanche;

// Engine imports
import com.avrisnox.gamedev.build.Engine;
import com.avrisnox.gamedev.mvc.controller.Controllers;
import com.avrisnox.gamedev.mvc.model.Models;
import com.avrisnox.gamedev.mvc.view.Views;

// Package imports
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

// Nio import
import java.nio.*;
import java.util.Objects;

// Specific package imports
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Avalanche extends Engine {
	public Avalanche() {
		this.model = Models.JANUS.get();
		this.view = Views.QUERCUS.get();
		this.controller = Controllers.DUMBBELL.get();
	}

	@Override
	protected void init() {
		GLFWErrorCallback.createPrint(System.err).set();

		if(!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");

		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		window = glfwCreateWindow(640, 480, "Hello World!", NULL, NULL);
		if(window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
				glfwSetWindowShouldClose(window, true);
		});

		try(MemoryStack stacks = stackPush()) {
			IntBuffer pWidth = stacks.mallocInt(1);
			IntBuffer pHeight = stacks.mallocInt(1);

			glfwGetWindowSize(window, pWidth, pHeight);
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			if(vidmode == null)
				throw new RuntimeException("The video mode was null.");

			int xpos = (vidmode.width() - pWidth.get(0)) / 2;
			int ypos = (vidmode.height() - pHeight.get(0)) / 2;
			glfwSetWindowPos(window, xpos, ypos);
		}

		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);
		glfwShowWindow(window);

		GL.createCapabilities();
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
	}

	@Override
	protected void update() {
		glfwPollEvents();
	}

	@Override
	protected void draw() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glfwSwapBuffers(window);
	}

	@Override
	protected void destroy() {
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

		glfwTerminate();
		GLFWErrorCallback callback = glfwSetErrorCallback(null);

		if(callback != null)
			callback.free();
	}

	public static void main(String... args) {
		Avalanche engine = new Avalanche();
		engine.run();
	}
}
