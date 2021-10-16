package net.blackhamm3rjack.mining_business.engine.minerals;

import java.awt.Color;

public class MineralStone extends Mineral {
	public static final byte ID = Byte.MAX_VALUE - 0;

	public MineralStone() {
		super(ID, "stone", new Color(0x0), 1.0f, 0.0f, 0.0f);
	}
}
