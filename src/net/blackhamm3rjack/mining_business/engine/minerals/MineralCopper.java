package net.blackhamm3rjack.mining_business.engine.minerals;

import java.awt.Color;

public class MineralCopper extends Mineral {
	public static final byte ID = 2;

	public MineralCopper() {
		super(ID, "copper", new Color(0xBB5C40), 3.0f, 15.0f, 0.0f);
	}
}
