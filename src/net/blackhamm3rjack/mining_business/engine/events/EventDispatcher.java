package net.blackhamm3rjack.mining_business.engine.events;

import java.util.ArrayList;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.events.listeners.BlockDestroyListener;
import net.blackhamm3rjack.mining_business.engine.events.listeners.EventListener;
import net.blackhamm3rjack.mining_business.utils.Logger;
import net.blackhamm3rjack.mining_business.utils.Logger.Tag;

/**
 * This class holds every listener of any kind and manages the call of an event,
 * dispatching it to the right one
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public class EventDispatcher {
	/** Listener for the block destory event */
	private static ArrayList<BlockDestroyListener> blockDestroyListeners;

	static {
		// Initialize every array list
		EventDispatcher.blockDestroyListeners = new ArrayList<>();
	}

	/**
	 * Register a new listener to the dispatcher
	 * 
	 * @param listener
	 *            The listener to register
	 */
	public static void register(EventListener listener) {
		// Get the listener type
		if (listener instanceof BlockDestroyListener) {
			blockDestroyListeners.add((BlockDestroyListener) listener);
			Logger.print(Tag.DEBUG, EventDispatcher.class, "Registered listener %x08x to event dispatcher", listener.hashCode());
			return;
		}
	}

	/**
	 * Remove a previously registered listener from the dispatcher
	 * 
	 * @param listener
	 *            The listener to unregister
	 */
	public static void unregister(EventListener listener) {
		// Get the listener type
		if (listener instanceof BlockDestroyListener) {
			blockDestroyListeners.remove((BlockDestroyListener) listener);
			Logger.print(Tag.DEBUG, EventDispatcher.class, "Unregistered listener %x08x from event dispatcher", listener.hashCode());
			return;
		}
	}

	/**
	 * Call the method to each listener that responds for the right event type
	 * 
	 * @param event
	 *            The generated event
	 */
	public static void call(Event event) {
		// Pass the event to the right listeners
		if (event instanceof BlockDestroyEvent) {
			for (BlockDestroyListener listener : blockDestroyListeners)
				listener.onBlockDestroy((BlockDestroyEvent) event);
			return;
		}
	}
}
