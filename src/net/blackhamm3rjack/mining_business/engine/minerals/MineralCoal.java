package net.blackhamm3rjack.mining_business.engine.minerals;

import java.awt.Color;

public class MineralCoal extends Mineral {
	public static final byte ID = 1;

	public MineralCoal() {
		super(ID, "coal", new Color(0x17181A), 0.5f, 5.0f, 0.0f);
	}
}
