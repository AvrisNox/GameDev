package com.avrisnox.gamedev.mvc.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public interface Controller {
	public abstract class KeyListener extends KeyAdapter {
	}
	public abstract class MouseListener extends MouseAdapter {
	}
	/* TODO: This is flawed, but the controller needs to accept:
		1. Mouse input (probably using MouseAdapter)
		2. Keyboard input (probably using KeyAdapter)
		3. Controller input (probably using JXInput)
	 */
}
