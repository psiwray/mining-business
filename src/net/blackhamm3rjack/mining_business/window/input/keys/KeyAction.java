package net.blackhamm3rjack.mining_business.window.input.keys;

import java.awt.event.KeyEvent;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Single action triggered from a key
 * 
 * @author Luca
 *
 */
@Versioning(minor = 2)
public interface KeyAction {
	public void trigger(KeyEvent e);
}
