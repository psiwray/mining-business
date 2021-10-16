package net.blackhamm3rjack.mining_business.engine.minerals;

import java.awt.Color;

public class MineralDirt extends Mineral {
	public static final byte ID = Byte.MAX_VALUE - 1;

	public MineralDirt() {
		super(ID, "dirt", new Color(0x0), 0.5f, 0.0f, 0.0f);
	}
}
