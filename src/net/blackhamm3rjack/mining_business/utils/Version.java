package net.blackhamm3rjack.mining_business.utils;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Game software version tracker
 * 
 * @author Luca
 *
 */
@Versioning(minor = 2)
public class Version {
	private int major;
	private int minor;
	private int patch;

	private String devInfo;
	private String devKey;
	private String copyright;

	public Version(int major, int minor, int patch, String devInfo, String devKey, String copyright) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
		this.devInfo = devInfo;
		this.devKey = devKey;
		this.copyright = copyright;
	}

	public int getMinor() {
		return minor;
	}

	public int getMajor() {
		return major;
	}

	public int getPatch() {
		return patch;
	}

	public String getDevInfo() {
		return devInfo;
	}

	public String getDevKey() {
		return devKey;
	}

	public String getCopyright() {
		return copyright;
	}

	@Override
	public String toString() {
		return String.format("%s(%s) @ %d.%d.%d [%s]", devInfo, devKey, major, minor, patch, copyright);
	}
}
