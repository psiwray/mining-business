package net.blackhamm3rjack.mining_business.engine.events.listeners;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.events.BlockRepairEvent;
import net.blackhamm3rjack.mining_business.engine.events.Event;
import net.blackhamm3rjack.mining_business.utils.Logger;
import net.blackhamm3rjack.mining_business.utils.Logger.Tag;

/**
 * Listener for the block repair event
 * 
 * @author lucac
 *
 */
@Versioning(minor = 2)
public abstract class BlockRepairListener extends EventListener {
	/**
	 * Create a new block repair event listener
	 * 
	 * @param name
	 *            The listener internal name
	 */
	public BlockRepairListener(String name) {
		super(name);
	}

	@Override
	public void action(Event event) {
		if (event instanceof BlockRepairEvent) {
			// Call only if the listener is enabled
			if (enabled)
				// Pass the event
				onBlockRepair((BlockRepairEvent) event);
		} else
			// Signal the error
			Logger.print(Tag.ERROR, BlockRepairListener.class, "Received wrong event type on listener 0x%08x (%s)", this.hashCode(), this.getName());
	}

	/**
	 * Called when the dispatcher received a block repair event
	 * 
	 * @param event
	 *            The received event
	 */
	public abstract void onBlockRepair(BlockRepairEvent event);
}
