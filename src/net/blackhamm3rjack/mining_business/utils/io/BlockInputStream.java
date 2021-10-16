package net.blackhamm3rjack.mining_business.utils.io;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.blocks.Block;
import net.blackhamm3rjack.mining_business.engine.blocks.Block.Background;
import net.blackhamm3rjack.mining_business.engine.minerals.Mineral;
import net.blackhamm3rjack.mining_business.utils.Logger;
import net.blackhamm3rjack.mining_business.utils.Logger.Tag;

/**
 * Stream utility to read blocks
 * 
 * @author lucac
 *
 */
@Versioning(minor = 2, patch = 3, working = true)
public class BlockInputStream extends DataInputStream {
	private boolean verbose;

	public BlockInputStream(InputStream in, boolean verbose) {
		super(in);

		this.verbose = verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public Block readBlock() throws IOException {
		boolean old = Logger.shouldOutputDebug();

		Logger.setOutputDebug(verbose);
		Logger.print(Tag.DEBUG, BlockInputStream.class, "Loading a block:");

		boolean damageable = readBoolean();
		Logger.print(Tag.DEBUG, BlockInputStream.class, "└ Damageable?: %b", damageable);
		boolean solid = readBoolean();
		Logger.print(Tag.DEBUG, BlockInputStream.class, "└ Solid?: %b", solid);

		byte damage = readByte();
		Logger.print(Tag.DEBUG, BlockInputStream.class, "└ Damage: 0x%x", damage);
		byte id = readByte();
		Logger.print(Tag.DEBUG, BlockInputStream.class, "└ ID: 0x%x", id);

		byte background = readByte();
		Logger.print(Tag.DEBUG, BlockInputStream.class, "└ Background: 0x%x", background);

		Point position = new Point(readInt(), readInt());
		Logger.print(Tag.DEBUG, BlockInputStream.class, "└ Position X: %d", position.x);
		Logger.print(Tag.DEBUG, BlockInputStream.class, "└ Position Y: %d", position.y);
		Logger.setOutputDebug(old);

		Block block = new Block(position, Mineral.getMineral(id), Background.getFromDump(background));
		block.setDamage(damage);
		block.setDamageable(damageable);
		block.setSolid(solid);

		return block;
	}
}
