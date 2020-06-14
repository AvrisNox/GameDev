package com.avrisnox.gamedev.Projects.Pacman;

import com.avrisnox.gamedev.build.Avalanche.AvModel;

import static org.lwjgl.glfw.GLFW.*;

public class PmModel extends AvModel {
	private static final float SPEED = 1.0f;
	private static final GameState state = new GameState();

	private static class GameState {
		State start;
		State current;

		public enum ACTION {
			PAC_UPP,
			PAC_RGT,
			PAC_DWN,
			PAC_LFT,
			GHR_UPP,
			GHR_RGT,
			GHR_DWN,
			GHR_LFT,
			GHP_UPP,
			GHP_RGT,
			GHP_DWN,
			GHP_LFT,
			GHC_UPP,
			GHC_RGT,
			GHC_DWN,
			GHC_LFT,
			GHO_UPP,
			GHO_RGT,
			GHO_DWN,
			GHO_LFT,
			GHA_VLN,
			GHA_WRN,
			GHA_NRM,
		}

		private class State {

			public State accept(ACTION a) {
				// TODO
				return null;
			}
		}

		public GameState(State start) {
			this.start = start;
			current = start;
		}

		public void transition(ACTION action) {
			current = current.accept(action);
		}
	}

	@Override
	public void keyEvent(int key, int action, int mods) {
		if(key == GLFW_KEY_W && (action == GLFW_PRESS || action == GLFW_REPEAT)) {
			GameState.transition(GameState.ACTIONS.UP, 1.0f);
		} else if(key == GLFW_KEY_D && (action == GLFW_PRESS || action == GLFW_REPEAT)) {
			GameState.transition(GameState.ACTIONS.RIGHT, 1.0f);
		} else if(key == GLFW_KEY_S && (action == GLFW_PRESS || action == GLFW_REPEAT)) {
			GameState.transition(GameState.ACTIONS.DOWN, 1.0f);
		} else if(key == GLFW_KEY_A && (action == GLFW_PRESS || action == GLFW_REPEAT)) {
			GameState.transition(GameState.ACTIONS.LEFT, 1.0f);
		}
	}
}
