package net.blackhamm3rjack.mining_business.engine.minerals;

import java.awt.Color;

public class MineralUranium extends Mineral {
	public static final byte ID = 9;

	public MineralUranium() {
		super(ID, "uranium", new Color(0x326E55), 3.7f, 67.0f, 0.96f);
	}
}
