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
		try {
			Avalanche engine = new Avalanche(
					AvModel.class,
					AvView.class,
					AvController.class);
			engine.run();
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
		}
	}
}
