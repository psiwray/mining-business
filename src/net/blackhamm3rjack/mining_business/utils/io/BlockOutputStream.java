package net.blackhamm3rjack.mining_business.utils.io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.blocks.Block;
import net.blackhamm3rjack.mining_business.utils.Logger;
import net.blackhamm3rjack.mining_business.utils.Logger.Tag;

/**
 * Stream utility to write blocks
 * 
 * @author lucac
 *
 */
@Versioning(minor = 2, patch = 3, working = true)
public class BlockOutputStream extends DataOutputStream {
	private boolean verbose;

	public BlockOutputStream(OutputStream out, boolean verbose) {
		super(out);

		this.verbose = verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void writeBlock(Block block) throws IOException {
		boolean old = Logger.shouldOutputDebug();

		Logger.setOutputDebug(verbose);
		Logger.print(Tag.DEBUG, BlockInputStream.class, "Writing a block 0x%08x", block.hashCode());

		writeBoolean(block.isDamageable());
		writeBoolean(block.isSolid());
		writeByte(block.getDamage());
		writeByte(block.getId());
		writeByte(block.getForeground().getDumpIndex());
		writeInt(block.getBlockPositionX());
		writeInt(block.getBlockPositionY());

		Logger.setOutputDebug(old);
	}
}
