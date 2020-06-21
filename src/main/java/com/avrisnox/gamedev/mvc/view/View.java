package com.avrisnox.gamedev.mvc.view;

import com.avrisnox.gamedev.mvc.model.Model;

/**
 * Model type for M<bold>V</bold>C.
 * @param <MType> The class type for the Model
 */
public abstract class View <MType extends Model> {
	protected MType model;

	/**
	 * The default view constructor.
	 */
	public View() { }

	/**
	 * Performs view initialization logic.
	 * @param model The model this view will control
	 */
	public void init(MType model) {
		this.model = model;
	}

	/**
	 * Performs view draw logic.
	 */
	public abstract void draw();

	/**
	 * Performs view destruction logic.
	 */
	public void destroy() { }
}
