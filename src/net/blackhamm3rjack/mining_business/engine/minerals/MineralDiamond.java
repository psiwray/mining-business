package net.blackhamm3rjack.mining_business.engine.minerals;

import java.awt.Color;

public class MineralDiamond extends Mineral {
	public static final byte ID = 3;

	public MineralDiamond() {
		super(ID, "diamond", new Color(0xCBE6F1), 10.0f, 85.0f, 0.0f);
	}
}
