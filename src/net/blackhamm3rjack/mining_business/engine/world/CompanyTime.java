package net.blackhamm3rjack.mining_business.engine.world;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.gui.GuiLabel;
import net.blackhamm3rjack.mining_business.engine.translations.LanguageSet;

/**
 * Company passed time tracker
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public class CompanyTime extends GuiLabel {
	/** The passed time, resets each day */
	private long passedTimeDays;
	/** The passed time, resets each money withdrawal */
	private long passedTimeCash;
	/** The days count */
	private long passedDays;
	/** The days cycle time */
	private long cycleDaysTime;
	/** The cash cycle time */
	private long cycleCashTime;

	/** Location in the translation file of the string */
	private static final String CURRENT_DAY_TRANSLATION = "current_day";
	/** The translation string, to avoid continuous loading */
	private String translation;

	/**
	 * Create a new company time tracker
	 * 
	 * @param position
	 *            The label position
	 * @param passedTimeDays
	 *            The passed time to track the days
	 * @param passedTimeCash
	 *            The passed time to track the cash withdrawal
	 * @param passedDays
	 *            The passed days
	 * @param cycleDaysTime
	 *            The days cycle time
	 * @param cycleCashTime
	 *            The cash cycle time
	 */
	public CompanyTime(Point position, long passedTimeDays, long passedTimeCash, long passedDays, long cycleDaysTime, long cycleCashTime) {
		super(position, null, LanguageSet.get(CURRENT_DAY_TRANSLATION) + passedDays, new Color(128, 128, 128), "Arial", Font.PLAIN, 15, true);

		this.name = String.format("company_time[%x]", this.hashCode());
		this.passedTimeDays = passedTimeDays;
		this.passedTimeCash = passedTimeCash;
		this.passedDays = passedDays;
		this.cycleDaysTime = cycleDaysTime;
		this.cycleCashTime = cycleCashTime;
		this.translation = LanguageSet.get(CURRENT_DAY_TRANSLATION);
	}

	/**
	 * Create a new company time tracker with default values
	 * 
	 * @param position
	 *            The label position
	 * @param cycleDaysTime
	 *            The days cycle time
	 * @param cycleCashTime
	 *            The cash cycle time
	 */
	public CompanyTime(Point position, long cycleDaysTime, long cycleCashTime) {
		super(position, null, LanguageSet.get(CURRENT_DAY_TRANSLATION) + 0, new Color(64, 64, 64), "Arial", Font.PLAIN, 15, true);

		this.name = String.format("company_time[%x]", this.hashCode());
		this.passedTimeDays = 0;
		this.passedTimeCash = 0;
		this.passedDays = 0;
		this.cycleDaysTime = cycleDaysTime;
		this.cycleCashTime = cycleCashTime;
		this.translation = LanguageSet.get(CURRENT_DAY_TRANSLATION);
	}

	@Override
	public void update(long deltaTime) {
		passedTimeDays += deltaTime;
		passedTimeCash += deltaTime;

		// Check days
		if (passedTimeDays >= cycleDaysTime) {
			passedTimeDays = 0;
			setText(translation + ++passedDays);
		}

		// Check cash
		if (passedTimeCash >= cycleCashTime) {
			passedTimeCash = 0;
			// TODO: Remove money
		}
	}

	/**
	 * Save the data to an output stream
	 * 
	 * @param stream
	 *            The output stream
	 * @throws IOException
	 *             Thrown if there was an I/O exception while saving
	 */
	public void save(ByteArrayOutputStream stream) throws IOException {
		DataOutputStream output = new DataOutputStream(stream);

		output.writeLong(passedTimeDays);
		output.writeLong(passedTimeCash);
		output.writeLong(passedDays);
		output.writeLong(cycleDaysTime);
		output.writeLong(cycleCashTime);
		output.close();
	}

	/**
	 * Load the data from an input stream
	 * 
	 * @param stream
	 *            The input stream
	 * @throws IOException
	 *             Thrown if there was an I/O exception while loading
	 */
	public void load(ByteArrayInputStream stream) throws IOException {
		DataInputStream input = new DataInputStream(stream);

		this.passedTimeDays = input.readLong();
		this.passedTimeCash = input.readLong();
		this.passedDays = input.readLong();
		this.cycleDaysTime = input.readLong();
		this.cycleCashTime = input.readLong();
		input.close();
	}
}
