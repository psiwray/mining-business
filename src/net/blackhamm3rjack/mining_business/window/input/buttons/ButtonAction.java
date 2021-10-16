package net.blackhamm3rjack.mining_business.window.input.buttons;

import java.awt.event.MouseEvent;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Single action triggered from a button
 * 
 * @author Luca
 *
 */
@Versioning(minor = 2)
public interface ButtonAction {
	public void trigger(MouseEvent e);
}
