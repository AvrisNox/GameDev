package com.avrisnox.gamedev.build.Avalanche;

// Engine imports
import com.avrisnox.gamedev.build.Engine;

// LWJGL imports
import org.lwjgl.glfw.GLFWErrorCallback;

// Static LWJGL imports
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;

public class Avalanche extends Engine {
	public Avalanche() {
		this.model = new Janus();
		this.view = new Quercus(this.model, this);
		this.controller = new Dumbbell(this.model, this.window);
	}

	@Override
	protected void destroy() {
		super.destroy();
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
