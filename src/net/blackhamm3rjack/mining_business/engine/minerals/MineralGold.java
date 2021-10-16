package net.blackhamm3rjack.mining_business.engine.minerals;

import java.awt.Color;

public class MineralGold extends Mineral {
	public static final byte ID = 4;

	public MineralGold() {
		super(ID, "gold", new Color(0xC3B037), 2.5f, 13.0f, 0.0f);
	}
}
