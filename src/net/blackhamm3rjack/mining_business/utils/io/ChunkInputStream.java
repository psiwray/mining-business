package net.blackhamm3rjack.mining_business.utils.io;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.world.Chunk;
import net.blackhamm3rjack.mining_business.utils.Logger;
import net.blackhamm3rjack.mining_business.utils.Logger.Tag;

/**
 * Stream utility to read chunks. The format is: position in space, matrix of
 * blocks yes/no, sequential blocks data
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public class ChunkInputStream extends DataInputStream {
	private boolean verbose;

	public ChunkInputStream(InputStream in, boolean verbose) {
		super(in);

		this.verbose = verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public Chunk readChunk() throws IOException {
		boolean old = Logger.shouldOutputDebug();

		Logger.setOutputDebug(verbose);
		Logger.print(Tag.DEBUG, ChunkInputStream.class, "Reading a chunk:");

		BitSet mapping;
		Point position = new Point(readInt(), readInt());
		Logger.print(Tag.DEBUG, ChunkInputStream.class, "└ Position X: %d", position.x);
		Logger.print(Tag.DEBUG, ChunkInputStream.class, "└ Position Y: %d", position.y);

		// Read the mapping
		byte[] input = new byte[Chunk.WIDTH * Chunk.HEIGHT / Byte.SIZE];
		// Read each byte
		for (int i = 0; i < input.length; i++)
			input[i] = readByte();

		// Transform the bit set
		if ((mapping = BitSet.valueOf(input)).size() != Chunk.WIDTH * Chunk.HEIGHT)
			throw new IOException("Wrong mapping");

		try {
			@SuppressWarnings("resource")
			BlockInputStream stream = new BlockInputStream(this, verbose);
			Chunk chunk = new Chunk(position);

			for (int i = 0; i < mapping.size(); i++) {
				// No block, skip
				if (!mapping.get(i))
					continue;

				byte posX = (byte) (i / Chunk.WIDTH);
				byte posY = (byte) (i % Chunk.HEIGHT);

				chunk.setBlock(posX, posY, stream.readBlock());

				// Print out the block info
				Logger.print(Tag.DEBUG, ChunkInputStream.class, "└ Loaded a block:");
				Logger.print(Tag.DEBUG, ChunkInputStream.class, "  └ Chunk position X: %d", posX);
				Logger.print(Tag.DEBUG, ChunkInputStream.class, "  └ Chunk position Y: %d", posY);
			}

			Logger.setOutputDebug(old);
			return chunk;
		} catch (IOException e) {
			Logger.setOutputDebug(old);
			throw e;
		}
	}
}
