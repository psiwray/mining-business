package net.blackhamm3rjack.mining_business.engine.blocks;

import java.awt.Point;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.minerals.Mineral;
import net.blackhamm3rjack.mining_business.engine.minerals.MineralCoal;

/**
 * Coal block
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public class BlockCoal extends Block {
	/**
	 * Create a new coal block
	 * 
	 * @param position
	 *            The block position
	 */
	public BlockCoal(Point position) {
		super(position, Mineral.getMineral(MineralCoal.ID), Background.STONE);
	}
}
