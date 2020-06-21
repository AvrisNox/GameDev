package com.avrisnox.gamedev.build.Avalanche;

import com.avrisnox.gamedev.build.Avalanche.utils.LoadManager;
import com.avrisnox.gamedev.mvc.model.Model;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALCapabilities;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Objects;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.openal.EXTThreadLocalContext.alcSetThreadContext;
import static org.lwjgl.system.MemoryUtil.NULL;

@SuppressWarnings({"WeakerAccess", "unused"})
public class AvModel extends Model {
	protected State game;
	protected State menu;
	protected LoadManager manager;
	private final long alcCTX;
	private final long device;

	protected class State {
		HashMap<Integer, State> transitions;
		String id;

		public State() {
			this(null, new HashMap<>());
		}

		public State(String id) {
			this(id, new HashMap<>());
		}

		public State(HashMap<Integer, State> transitions) {
			this(null, transitions);
		}

		public State(String id, HashMap<Integer, State> transitions) {
			this.id = id;
			this.transitions = transitions;
		}

		public String getId() {
			return id;
		}

		@SuppressWarnings("UnusedReturnValue")
		public State addTransition(Integer key, State value) {
			return transitions.put(key, value);
		}

		public State next(Integer key) {
			State next = transitions.get(key);
			if(next == null) next = this;
			return next;
		}
	}

	public AvModel() {
		super();

		/* Menu logic - this allows the game to track where it is in the menus */
		State menu_main = new State("Menu.MainMenu");
		State menu_game = new State("Menu.InGame");
		State menu_qGame = new State("Menu.QuitGame");
		State menu_qMenu = new State("Menu.QuitMenu");
		State menu_close = new State("Menu.Close");

		menu_main.addTransition(GLFW_KEY_ESCAPE, menu_qMenu);
		menu_main.addTransition(GLFW_KEY_ENTER, menu_game);
		menu_game.addTransition(GLFW_KEY_ESCAPE, menu_qGame);
		menu_qGame.addTransition(GLFW_KEY_ESCAPE, menu_game);
		menu_qGame.addTransition(GLFW_KEY_ENTER, menu_main);
		menu_qMenu.addTransition(GLFW_KEY_ESCAPE, menu_main);
		menu_qMenu.addTransition(GLFW_KEY_ENTER, menu_close);

		menu = menu_main;

		/* Polygon loading (outsourced) */
		manager = new LoadManager();

		device = alcOpenDevice((ByteBuffer)null);
		if(device == NULL)
			throw new RuntimeException("Could not get default audio device.");
		ALCCapabilities devCap = ALC.createCapabilities(device);
		if(!devCap.OpenALC10)
			throw new IllegalStateException();

		String defaultDevice = Objects.requireNonNull(alcGetString(NULL, ALC_DEFAULT_DEVICE_SPECIFIER));
		alcCTX = alcCreateContext(device, (IntBuffer)null);
		alcSetThreadContext(alcCTX);
		ALCapabilities alCap = AL.createCapabilities(devCap);
	}

	@Override
	public void load() {
		float[] vertices = {
				// Left bottom triangle
				-0.5f, 0.5f, 0f,
				-0.5f, -0.5f, 0f,
				0.5f, -0.5f, 0f,
				// Right top triangle
				0.5f, -0.5f, 0f,
				0.5f, 0.5f, 0f,
				-0.5f, 0.5f, 0f
		};

		manager.POLY_MANAGER.load(vertices);

		manager.AUDIO_MANAGER.load("src/main/resources/file_example_OOG_1MG.ogg", "Audio.Menu.Main");
	}

	protected final void setGameStart(State start) {
		game = start;
	}

	public LoadManager getManager() {
		return manager;
	}

	@Override
	public void keyEvent(int key, int action, int mods) {
		if (action == GLFW_PRESS || action == GLFW_REPEAT) {
			if(menu != null)
				menu = menu.next(key);
			if(game != null)
				game = game.next(key);
		}
	}

	@Override
	public void mouseMove(double xpos, double ypos) {

	}

	@Override
	public void mouseEnter(boolean entered) {

	}

	@Override
	public void mouseEvent(int button, int action, int mods) {

	}

	@Override
	public void mouseScroll(double xoff, double yoff) {

	}

	@Override
	public void joystickConnect(int jid, int event) {

	}

	@Override
	public void joystickUpdate(int jid) {

	}

	@Override
	public void update() {
		if(menu.getId().equals("Menu.Close"))
			glfwSetWindowShouldClose(getWindow(), true);

		if(menu.getId().equals("Menu.MainMenu"))
			manager.AUDIO_MANAGER.enableSound("Audio.Menu.Main");
		else
			manager.AUDIO_MANAGER.disableSound("Audio.Menu.Main");

		running = !glfwWindowShouldClose(getWindow());
		manager.AUDIO_MANAGER.simAudio();
	}

	@Override
	public void destroy() {
		alcMakeContextCurrent(NULL);
		AL.setCurrentThread(null);
		alcDestroyContext(alcCTX);
		alcCloseDevice(device);
		manager.close();
	}
}
