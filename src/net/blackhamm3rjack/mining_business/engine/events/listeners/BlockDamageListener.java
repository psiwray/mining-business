package net.blackhamm3rjack.mining_business.engine.events.listeners;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.events.BlockDamageEvent;
import net.blackhamm3rjack.mining_business.engine.events.Event;
import net.blackhamm3rjack.mining_business.utils.Logger;
import net.blackhamm3rjack.mining_business.utils.Logger.Tag;

/**
 * Listener for the block damage event
 * 
 * @author lucac
 *
 */
@Versioning(minor = 2)
public abstract class BlockDamageListener extends EventListener {
	/**
	 * Create a new block damage event listener
	 * 
	 * @param name
	 *            The listener internal name
	 */
	public BlockDamageListener(String name) {
		super(name);
	}

	@Override
	public void action(Event event) {
		if (event instanceof BlockDamageEvent) {
			// Call only if the listener is enabled
			if (enabled)
				// Pass the event
				onBlockDamage((BlockDamageEvent) event);
		} else
			// Signal the error
			Logger.print(Tag.ERROR, BlockDamageListener.class, "Received wrong event type on listener 0x%08x (%s)", this.hashCode(), this.getName());
	}

	/**
	 * Called when the dispatcher received a block damage event
	 * 
	 * @param event
	 *            The received event
	 */
	public abstract void onBlockDamage(BlockDamageEvent event);
}
