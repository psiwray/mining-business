package net.blackhamm3rjack.mining_business.engine.blocks;

import java.awt.Point;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.minerals.Mineral;
import net.blackhamm3rjack.mining_business.engine.minerals.MineralGold;

/**
 * Gold block
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public class BlockGold extends Block {
	/**
	 * Create a new gold block
	 * 
	 * @param position
	 *            The block position
	 */
	public BlockGold(Point position) {
		super(position, Mineral.getMineral(MineralGold.ID), Background.STONE);
	}
}
