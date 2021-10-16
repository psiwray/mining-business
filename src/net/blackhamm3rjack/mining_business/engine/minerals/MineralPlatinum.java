package net.blackhamm3rjack.mining_business.engine.minerals;

import java.awt.Color;

public class MineralPlatinum extends Mineral {
	public static final byte ID = 6;

	public MineralPlatinum() {
		super(ID, "platinum", new Color(0xD3D4CE), 3.5f, 37.0f, 0.0f);
	}
}
