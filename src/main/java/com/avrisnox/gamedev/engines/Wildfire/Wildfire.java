package com.avrisnox.gamedev.engines.Wildfire;

import com.avrisnox.gamedev.engines.Engine;
import com.avrisnox.gamedev.mvc.controller.Controller;
import com.avrisnox.gamedev.mvc.model.Model;
import com.avrisnox.gamedev.mvc.view.View;

import java.lang.reflect.InvocationTargetException;

public class Wildfire<WfMType extends WfModel, WfVType extends WfView<WfMType>, WfCType extends WfController<WfMType>> extends Engine<WfMType, WfVType, WfCType> {

	public Wildfire(Class<WfMType> mClass, Class<WfVType> vClass, Class<WfCType> cClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		super(mClass, vClass, cClass);
	}

	public static <WfMType extends Model, WfVType extends View<WfMType>, WfCType extends Controller<WfMType>> void start(Class<WfMType> mClass, Class<WfVType> vClass, Class<WfCType> cClass) {
		if (!WfModel.class.isAssignableFrom(mClass) || !WfView.class.isAssignableFrom(vClass) || !WfController.class.isAssignableFrom(cClass)) {
			throw new IllegalArgumentException("Either the model, view, or controller did not extend a wildfire type.");
		}

		try {
			@SuppressWarnings("unchecked")
			Class<WfModel> fixM = (Class<WfModel>) mClass;
			@SuppressWarnings("unchecked")
			Class<WfView<WfModel>> fixV = (Class<WfView<WfModel>>) vClass;
			@SuppressWarnings("unchecked")
			Class<WfController<WfModel>> fixC = (Class<WfController<WfModel>>) cClass;
			Wildfire<WfModel, WfView<WfModel>, WfController<WfModel>> engine = new Wildfire<>(fixM, fixV, fixC);
			engine.run();
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
		}
	}

	public static void main(String... args) {
		//noinspection unchecked
		Wildfire.start(
				WfModel.class,
				WfView.class,
				WfController.class
		);
	}
}
