package net.blackhamm3rjack.mining_business.engine.blocks;

import java.awt.Point;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.minerals.Mineral;
import net.blackhamm3rjack.mining_business.engine.minerals.MineralStone;

/**
 * Stone block
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public class BlockStone extends Block {
	/**
	 * Create a new stone block
	 * 
	 * @param position
	 *            The block position
	 */
	public BlockStone(Point position) {
		super(position, Mineral.getMineral(MineralStone.ID), Background.STONE);
	}
}
