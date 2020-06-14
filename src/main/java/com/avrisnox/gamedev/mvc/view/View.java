package com.avrisnox.gamedev.mvc.view;

import com.avrisnox.gamedev.build.Engine;
import com.avrisnox.gamedev.mvc.model.Model;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public abstract class View {
	protected Model model;
	protected long window;

	public View(Model model, Engine engine) {
		this.model = model;
		engine.setWindow(init());
	}

	private long init() {
		GLFWErrorCallback.createPrint(System.err).set();

		if(!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		window = glfwCreateWindow(640, 480, "Hello World!", NULL, NULL);
		if(window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

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

		return window;
	}

	public void draw() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glfwSwapBuffers(window);
	}
}
