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
import net.blackhamm3rjack.mining_business.utils.Currency;

/**
 * The company cash deposit, as a GUI label
 * 
 * @author lucac
 *
 */
@Versioning(minor = 2, working = true)
public class CompanyCash extends GuiLabel {
	/** Current cash amount */
	private double cash;
	/** Failure debt value */
	private double minValue;

	/**
	 * Create a new company cash deposit
	 * 
	 * @param position
	 *            The label position
	 * @param cash
	 *            The current cash amount
	 * @param minValue
	 *            The failure debt value
	 */
	public CompanyCash(Point position, double cash, double minValue) {
		super(position, null, Currency.formatMoney(cash), new Color(32, 128, 32), "Arial", Font.PLAIN, 15, true);

		this.name = String.format("company_cash[%x]", this.hashCode());
		this.cash = cash;
		this.minValue = minValue;
	}

	/**
	 * Create a new company cash deposit with default values
	 * 
	 * @param position
	 *            The label position
	 * @param minValue
	 *            The failure debt value
	 */
	public CompanyCash(Point position, double minValue) {
		super(position, null, Currency.formatMoney(0), new Color(32, 128, 32), "Arial", Font.PLAIN, 15, true);

		this.name = String.format("company_cash[%x]", this.hashCode());
		this.cash = 0;
		this.minValue = minValue;
	}

	/**
	 * Get the current cash amount
	 * 
	 * @return The current cash amount
	 */
	public synchronized double getCash() {
		return cash;
	}

	/**
	 * Set the current cash amount
	 * 
	 * @param cash
	 *            The current cash amount
	 */
	public synchronized void setCash(double cash) {
		this.cash = cash;
		this.setText(Currency.formatMoney(cash));
	}

	/**
	 * Get the failure debt value
	 * 
	 * @return The failure debt value
	 */
	public double getMinValue() {
		return minValue;
	}

	/**
	 * Is the company in debt?
	 * 
	 * @return The company debt state
	 */
	public boolean isInDebit() {
		return cash < 0;
	}

	/**
	 * Is the company in failure?
	 * 
	 * @return The company failure state
	 */
	public boolean isInFailure() {
		return cash < minValue;
	}

	/**
	 * Get some money
	 * 
	 * @param amount
	 *            The amount of money to get
	 */
	public synchronized void withdraw(double amount) {
		setCash(cash - amount);
	}

	/**
	 * Put some money
	 * 
	 * @param amount
	 *            The amount of money to put
	 */
	public synchronized void bestowal(double amount) {
		setCash(cash + amount);
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

		output.writeDouble(cash);
		output.writeDouble(minValue);
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

		this.cash = input.readDouble();
		this.minValue = input.readDouble();
		input.close();
	}
}
