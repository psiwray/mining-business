package net.blackhamm3rjack.mining_business.window.input.buttons;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Manages all the input from the mouse
 * 
 * @author Luca
 *
 */
@Versioning(minor = 2, patch = 2)
public class ButtonInput extends MouseAdapter implements MouseMotionListener {
	private boolean[] buttons;
	private ArrayList<ButtonGroup> groups;

	public ButtonInput(int buttonsCount, int groupsCount) {
		this.buttons = new boolean[buttonsCount];
		this.groups = new ArrayList<>(groupsCount);
	}

	public ButtonInput(int buttonsCount) {
		this.buttons = new boolean[buttonsCount];
		this.groups = new ArrayList<>();
	}

	public ButtonInput() {
		this.buttons = new boolean[4];
		this.groups = new ArrayList<>();
	}

	public void addGroup(ButtonGroup group) {
		groups.add(group);
	}

	public void addGroups(ButtonGroup... groupss) {
		for (ButtonGroup group : groupss)
			this.groups.add(group);
	}

	public void removeGroup(ButtonGroup group) {
		groups.remove(group);
	}

	public void removeAllGroups() {
		groups.clear();
	}

	public boolean isButtonDown(int code) {
		return buttons[code];
	}

	@Override
	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton()] = false;

		// Trigger the groups
		for (ButtonGroup group : groups)
			if (!group.isOnMove())
				group.action(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// Trigger the move groups
		for (ButtonGroup group : groups)
			if (group.isOnMove())
				group.action(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Trigger the move groups
		for (ButtonGroup group : groups)
			if (group.isOnMove())
				group.action(e);
	}
}
