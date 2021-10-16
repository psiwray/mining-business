package net.blackhamm3rjack.mining_business.engine.events.listeners;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.events.BlockDestroyEvent;
import net.blackhamm3rjack.mining_business.engine.events.Event;
import net.blackhamm3rjack.mining_business.utils.Logger;
import net.blackhamm3rjack.mining_business.utils.Logger.Tag;

/**
 * Listener for the block destroy event
 * 
 * @author lucac
 *
 */
@Versioning(minor = 2)
public abstract class BlockDestroyListener extends EventListener {
	/**
	 * Create a new block destroy event listener
	 * 
	 * @param name
	 *            The listener internal name
	 */
	public BlockDestroyListener(String name) {
		super(name);
	}

	@Override
	public void action(Event event) {
		if (event instanceof BlockDestroyEvent) {
			// Call only if the listener is enabled
			if (enabled)
				// Pass the event
				onBlockDestroy((BlockDestroyEvent) event);
		} else
			// Signal the error
			Logger.print(Tag.ERROR, BlockDestroyListener.class, "Received wrong event type on listener 0x%08x (%s)", this.hashCode(), this.getName());
	}

	/**
	 * Called when the dispatcher received a block destroy event
	 * 
	 * @param event
	 *            The received event
	 */
	public abstract void onBlockDestroy(BlockDestroyEvent event);
}
