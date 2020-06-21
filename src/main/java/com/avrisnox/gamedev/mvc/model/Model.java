package com.avrisnox.gamedev.mvc.model;

import com.avrisnox.gamedev.build.Engine;

/**
 * Model type for <bold>M</bold>VC.
 * Should keep track of the current game state and help the view decide what to show.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class Model {
	protected boolean running;
	protected Engine engine;

	/**
	 * The default model constructor.
	 */
	public Model() { }

	/**
	 * Tells the engine whether or not the game should continue to run.
	 * @return True if the engine should keep running update/draw loops
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Sets the engine window.
	 * @param window The window to set to
	 */
	public void setWindow(long window) {
		engine.setWindow(window);
	}

	/**
	 * Gets the engine window.
	 * @return The engine window
	 */
	public long getWindow() {
		return engine.getWindow();
	}

	/**
	 * Performs model initialization logic.
	 * @param engine The engine that houses this model
	 */
	public void init(Engine engine) {
		running = true;
		this.engine = engine;
	}

	/**
	 * Performs model loading logic.
	 */
	public void load() {}

	/**
	 * Performs model destruction logic.
	 */
	public void destroy() {}

	// TODO: These are still OpenGL like - that needs a fixin!

	/**
	 * Handles key press events.
	 * @param key The key that was pressed
	 * @param action The action of the key press
	 * @param mods Any mod keys that were pressed
	 */
	public abstract void keyEvent(int key, int action, int mods);

	/**
	 * Handles mouse move events.
	 * @param xpos The x position of the mouse
	 * @param ypos The y position of the mouse
	 */
	public abstract void mouseMove(double xpos, double ypos);

	/**
	 * Handles events for when the mouse enters/leaves the screen.
	 * @param entered True if the mouse entered the screen
	 */
	public abstract void mouseEnter(boolean entered);

	/**
	 * Handles mouse press events.
	 * @param button The button that was pressed
	 * @param action The action of the button press
	 * @param mods Any mod keys that were pressed
	 */
	public abstract void mouseEvent(int button, int action, int mods);

	/**
	 * Handles mouse scroll events.
	 * @param xoff The x distance to scroll
	 * @param yoff The y distance to scroll
	 */
	public abstract void mouseScroll(double xoff, double yoff);

	/**
	 * Handles a joystick connection event.
	 * @param jid The joystick id
	 * @param event The event describing the connection
	 */
	public abstract void joystickConnect(int jid, int event);

	/**
	 * Handles a joystick update.
	 * @param jid The joystick id
	 */
	public abstract void joystickUpdate(int jid);

	/**
	 * The update method.
	 */
	public abstract void update();
}
