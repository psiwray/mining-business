package net.blackhamm3rjack.mining_business.engine.events;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.blocks.Block;
import net.blackhamm3rjack.mining_business.engine.world.Player;
import net.blackhamm3rjack.mining_business.engine.world.World;

/**
 * Called when a block is destroyed
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public class BlockDestroyEvent extends Event {
	/** The block that was destroyed */
	private Block block;
	/** The world where the block was */
	private World world;

	/**
	 * Create a new block destroy event
	 * 
	 * @param destroyer
	 *            The player that destroyed the block
	 * @param block
	 *            The block that was destroyed
	 * @param world
	 *            The world where the block was
	 */
	public BlockDestroyEvent(Player destroyer, Block block, World world) {
		super(destroyer);

		this.world = world;
		this.block = block;
	}

	/**
	 * Get the block that was destroyed
	 * 
	 * @return The destroyed block
	 */
	public Block getBlock() {
		return block;
	}

	/**
	 * Get the world where the block was
	 * 
	 * @return The world where the block was
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * Get the source as a player
	 * 
	 * @return The player
	 */
	public Player getPlayer() {
		return (Player) source;
	}
}
