package net.blackhamm3rjack.mining_business.engine.world.storage;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.utils.Logger;
import net.blackhamm3rjack.mining_business.utils.Logger.Tag;

/**
 * A stack of a particular mineral
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public class StorageStack {
	private byte id;
	private int amount;
	private int maxAmount;

	public StorageStack(byte id, int amount, int maxAmount) {
		this.id = id;
		this.amount = amount;
		this.maxAmount = maxAmount;

		// Notify
		Logger.print(Tag.DEBUG, StorageStack.class, "Created a new storage stack 0x%08x with %d units", this.hashCode(),
				maxAmount);
	}

	public byte getId() {
		return id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		assert amount >= 0;
		assert amount <= maxAmount;

		this.amount = amount;
	}

	/**
	 * Add a single unit to the storage stack
	 * 
	 * @return The operation result
	 */
	public boolean addUnit() {
		if (amount < maxAmount) {
			amount++;
			return true;
		}

		return false;
	}

	/**
	 * Remove a single unit from the storage stack
	 * 
	 * @return The operation result
	 */
	public boolean removeUnit() {
		if (amount > 0) {
			amount--;
			return true;
		}

		return false;
	}

	/**
	 * Add many units from the storage stack
	 * 
	 * @param amount
	 *            The amount to add
	 * @param force
	 *            If forced, the remaining quantity will be discarded and the
	 *            operation is permitted
	 * @return The operation result
	 */
	public boolean addManyUnits(int amount, boolean force) {
		if (this.amount + amount <= maxAmount) {
			this.amount += amount;
			return true;
		} else if (force) {
			this.amount = 0;
			return true;
		}

		return false;
	}

	/**
	 * Remove many units from the storage stack
	 * 
	 * @param amount
	 *            The amount to remove
	 * @param force
	 *            If forced, the remaining quantity will be discarded and the
	 *            operation is permitted
	 * @return The operation result
	 */
	public boolean removeManyUnits(int amount, boolean force) {
		if (this.amount - amount >= 0) {
			this.amount -= amount;
			return true;
		} else if (force) {
			this.amount = 0;
			return true;
		}

		return false;
	}

	/**
	 * Calculate the remaining amount after a forced add
	 * 
	 * @param amount
	 *            The amount to add
	 * @return The remaining quantity, or -1 if there's enough space
	 */
	public int calculateRemaining(int amount) {
		int remaining = (this.amount + amount) - maxAmount;

		if (remaining >= 0)
			return remaining;
		else
			return -1;
	}

	public int getMaxAmount() {
		return maxAmount;
	}
}
