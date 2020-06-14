package com.avrisnox.gamedev.build.Avalanche;

import com.avrisnox.gamedev.build.Engine;
import com.avrisnox.gamedev.mvc.controller.Controller;
import com.avrisnox.gamedev.mvc.model.Model;
import com.avrisnox.gamedev.mvc.view.View;
import org.lwjgl.glfw.GLFWErrorCallback;
import java.lang.reflect.InvocationTargetException;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;


public class Avalanche extends Engine {
	public Avalanche(Class<? extends Model> mClass, Class<? extends View> vClass, Class<? extends Controller> cClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		super(mClass, vClass, cClass);
	}

	public static void start(Class<? extends Model> mClass, Class<? extends View> vClass, Class<? extends Controller> cClass) {
		try {
			Avalanche engine = new Avalanche(mClass, vClass, cClass);
			engine.run();
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
		}
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
		Avalanche.start(
				AvModel.class,
				AvView.class,
				AvController.class
		);
	}
}
