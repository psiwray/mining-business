package net.blackhamm3rjack.mining_business.window.input.buttons;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * A group of triggerable actions
 * 
 * @author Luca
 *
 */
@Versioning(minor = 3, patch = 2, working = true)
public class ButtonGroup {
	private ArrayList<ButtonAction> actions;
	private ArrayList<Integer> triggers;

	private boolean enabled;
	private boolean onMove;

	public ButtonGroup(boolean onMove) {
		this.actions = new ArrayList<>();
		this.triggers = new ArrayList<>();
		this.enabled = false;
		this.onMove = onMove;
	}

	public boolean isOnMove() {
		return onMove;
	}

	public void setOnMove(boolean onMove) {
		this.onMove = onMove;
	}

	public void addTrigger(int keyCode) {
		triggers.add(keyCode);
	}

	public void addTriggers(int... keyCodes) {
		for (int keyCode : keyCodes)
			triggers.add(keyCode);
	}

	public void addAction(ButtonAction action) {
		actions.add(action);
	}

	public void addActions(ButtonAction... actions) {
		for (ButtonAction action : actions)
			this.actions.add(action);
	}

	public void removeAction(ButtonAction action) {
		actions.remove(action);
	}

	public void removeAllActions() {
		actions.clear();
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void action(MouseEvent e) {
		// If not enabled, skip execution
		if (!enabled)
			return;

		if (onMove) {
			// Propagate the action for a mouse move
			for (ButtonAction action : actions)
				action.trigger(e);

			return;
		}

		int buttonCode = e.getButton();

		for (Integer code : triggers) {
			// Execute only if code matches
			if (code != buttonCode)
				continue;

			// Propagate the action
			for (ButtonAction action : actions)
				action.trigger(e);
		}
	}
}
