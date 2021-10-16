package net.blackhamm3rjack.mining_business.engine.minerals;

import java.awt.Color;

public class MineralSilver extends Mineral {
	public static final byte ID = 7;

	public MineralSilver() {
		super(ID, "silver", new Color(0x71695E), 2.5f, 13.0f, 0.0f);
	}
}
