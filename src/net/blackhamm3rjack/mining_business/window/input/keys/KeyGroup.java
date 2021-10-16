package net.blackhamm3rjack.mining_business.window.input.keys;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * A group of triggerable actions
 * 
 * @author Luca
 *
 */
@Versioning(working = true)
public class KeyGroup {
	private ArrayList<KeyAction> actions;
	private ArrayList<Integer> triggers;

	private boolean altDown;
	private boolean controlDown;
	private boolean shiftDown;

	private boolean enabled;

	public KeyGroup(boolean altDown, boolean controlDown, boolean shiftDown) {
		this.actions = new ArrayList<>();
		this.triggers = new ArrayList<>();
		this.altDown = altDown;
		this.controlDown = controlDown;
		this.shiftDown = shiftDown;
		this.enabled = false;
	}

	public void addTrigger(int keyCode) {
		triggers.add(keyCode);
	}

	public void addTriggers(int... keyCodes) {
		for (int keyCode : keyCodes)
			triggers.add(keyCode);
	}

	public void addAction(KeyAction action) {
		actions.add(action);
	}

	public void addActions(KeyAction... actions) {
		for (KeyAction action : actions)
			this.actions.add(action);
	}

	public void removeAction(KeyAction action) {
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

	public void action(KeyEvent e) {
		// If not enabled, skip execution
		if (!enabled)
			return;

		// Skip if invalid modifiers are set or unset
		if (controlDown != e.isControlDown())
			return;
		if (altDown != e.isAltDown())
			return;
		if (shiftDown != e.isShiftDown())
			return;

		int keyCode = e.getKeyCode();

		for (Integer code : triggers) {
			// Execute only if code matches
			if (code != keyCode)
				continue;

			// Propagate the action
			for (KeyAction action : actions)
				action.trigger(e);
		}
	}
}
