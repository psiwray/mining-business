package net.blackhamm3rjack.mining_business.engine.minerals;

import java.awt.Color;

public class MineralAluminium extends Mineral {
	public static final byte ID = 0;

	public MineralAluminium() {
		super(ID, "aluminium", new Color(0xDAE1EB), 2.75f, 16.0f, 0.0f);
	}
}
