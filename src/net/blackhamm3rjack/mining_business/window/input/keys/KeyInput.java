package net.blackhamm3rjack.mining_business.window.input.keys;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Manages all the input from the keyboard
 * 
 * @author Luca
 *
 */
@Versioning()
public class KeyInput extends KeyAdapter {
	private boolean[] keys;
	private ArrayList<KeyGroup> groups;

	public KeyInput(int keysCount, int groupsCount) {
		this.keys = new boolean[keysCount];
		this.groups = new ArrayList<>(groupsCount);
	}

	public KeyInput(int keysCount) {
		this.keys = new boolean[keysCount];
		this.groups = new ArrayList<>();
	}

	public KeyInput() {
		this.keys = new boolean[1024];
		this.groups = new ArrayList<>();
	}

	public void addGroup(KeyGroup group) {
		groups.add(group);
	}

	public void addGroups(KeyGroup... groupss) {
		for (KeyGroup group : groupss)
			this.groups.add(group);
	}

	public void removeGroup(KeyGroup group) {
		groups.remove(group);
	}

	public void removeAllGroups() {
		groups.clear();
	}

	public boolean isKeyDown(int code) {
		return keys[code];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;

		// Trigger the groups
		for (KeyGroup group : groups)
			group.action(e);
	}
}
