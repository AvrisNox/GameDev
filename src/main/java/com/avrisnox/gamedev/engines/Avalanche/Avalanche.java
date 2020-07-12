package com.avrisnox.gamedev.engines.Avalanche;

import com.avrisnox.gamedev.engines.Engine;
import com.avrisnox.gamedev.mvc.controller.Controller;
import com.avrisnox.gamedev.mvc.model.Model;
import com.avrisnox.gamedev.mvc.view.View;
import org.lwjgl.glfw.GLFWErrorCallback;

import java.lang.reflect.InvocationTargetException;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;

public class Avalanche<AvMType extends AvModel, AvVType extends AvView<AvMType>, AvCType extends AvController<AvMType>> extends Engine<AvMType, AvVType, AvCType> {
	private double startTime;
	private double endTime;

	public Avalanche(Class<AvMType> mClass, Class<AvVType> vClass, Class<AvCType> cClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		super(mClass, vClass, cClass);
		startTime = 0;
		endTime = 0;
	}

	public static <AvMType extends Model, AvVType extends View<AvMType>, AvCType extends Controller<AvMType>> void start(Class<AvMType> mClass, Class<AvVType> vClass, Class<AvCType> cClass) {
		if (!AvModel.class.isAssignableFrom(mClass) || !AvView.class.isAssignableFrom(vClass) || !AvController.class.isAssignableFrom(cClass)) {
			throw new IllegalArgumentException("Either the model, view, or controller did not extend an avalanche type.");
		}

		try {
			@SuppressWarnings("unchecked")
			Class<AvModel> fixM = (Class<AvModel>) mClass;
			@SuppressWarnings("unchecked")
			Class<AvView<AvModel>> fixV = (Class<AvView<AvModel>>) vClass;
			@SuppressWarnings("unchecked")
			Class<AvController<AvModel>> fixC = (Class<AvController<AvModel>>) cClass;
			Avalanche<AvModel, AvView<AvModel>, AvController<AvModel>> engine = new Avalanche<>(fixM, fixV, fixC);
			engine.run();
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void postloop() {
		endTime = glfwGetTime();
		double delta = endTime - startTime;
		double frames = 1/delta;
		System.out.printf("Delt:%.6f\t\tFps:%.2f\n", delta, frames);
		startTime = endTime;
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
		//noinspection unchecked
		Avalanche.start(
				AvModel.class,
				AvView.class,
				AvController.class
		);
	}
}
