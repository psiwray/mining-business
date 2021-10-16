package net.blackhamm3rjack.mining_business.utils.io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.BitSet;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.world.Chunk;
import net.blackhamm3rjack.mining_business.utils.Logger;
import net.blackhamm3rjack.mining_business.utils.Logger.Tag;

/**
 * Stream utility to write chunks. The format is: position in space, matrix of
 * blocks yes/no, sequential blocks data
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public class ChunkOutputStream extends DataOutputStream {
	private boolean verbose;

	public ChunkOutputStream(OutputStream out, boolean verbose) {
		super(out);

		this.verbose = verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void writeChunk(Chunk chunk) throws IOException {
		boolean old = Logger.shouldOutputDebug();

		Logger.setOutputDebug(verbose);
		Logger.print(Tag.DEBUG, ChunkInputStream.class, "Writing a chunk 0x%08x", chunk.hashCode());

		writeInt(chunk.getChunkPositionX());
		writeInt(chunk.getChunkPositionY());

		BitSet mapping = new BitSet(Chunk.WIDTH * Chunk.HEIGHT);

		// Put the values in the bit set
		for (byte x = 0; x < Chunk.WIDTH; x++)
			for (byte y = 0; y < Chunk.HEIGHT; y++)
				mapping.set(x * Chunk.WIDTH + y, chunk.getBlock(x, y) != null);

		byte[] output = mapping.toByteArray();

		// Write the values byte per byte
		for (int i = 0; i < Chunk.WIDTH * Chunk.HEIGHT / Byte.SIZE; i++)
			if (i >= output.length)
				writeByte(0);
			else
				writeByte(output[i]);

		// Write each block
		try {
			@SuppressWarnings("resource")
			BlockOutputStream stream = new BlockOutputStream(this, verbose);

			for (byte x = 0; x < Chunk.WIDTH; x++)
				for (byte y = 0; y < Chunk.HEIGHT; y++)
					if (mapping.get(x * Chunk.WIDTH + y))
						stream.writeBlock(chunk.getBlock(x, y));

			Logger.setOutputDebug(old);
			return;
		} catch (IOException e) {
			Logger.setOutputDebug(old);
			throw e;
		}
	}
}
