package net.blackhamm3rjack.mining_business.engine.minerals;

import java.awt.Color;

public class MineralIron extends Mineral {
	public static final byte ID = 5;

	public MineralIron() {
		super(ID, "iron", new Color(0xC4C2C7), 4.0f, 10.0f, 0.12f);
	}
}
