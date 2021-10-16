package net.blackhamm3rjack.mining_business.engine.world;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import org.apache.commons.math3.util.FastMath;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.blocks.Block;
import net.blackhamm3rjack.mining_business.engine.events.BlockDamageEvent;
import net.blackhamm3rjack.mining_business.engine.events.BlockDestroyEvent;
import net.blackhamm3rjack.mining_business.engine.events.BlockRepairEvent;
import net.blackhamm3rjack.mining_business.engine.events.EventDispatcher;
import net.blackhamm3rjack.mining_business.engine.interfaces.Renderable;
import net.blackhamm3rjack.mining_business.engine.interfaces.Updatable;
import net.blackhamm3rjack.mining_business.engine.minerals.Mineral;
import net.blackhamm3rjack.mining_business.engine.world.settings.GameSettings;
import net.blackhamm3rjack.mining_business.utils.Configuration;
import net.blackhamm3rjack.mining_business.utils.Logger;
import net.blackhamm3rjack.mining_business.utils.Logger.Tag;

/**
 * World class holding every block for the current game & manages the generation
 * of the whole terrain with different algorithms. This class also represents a
 * game that can be loaded from disk
 * 
 * @author lucac
 *
 */
@Versioning(major = 1, minor = 3, patch = 5, working = true)
public class World implements Renderable, Updatable {
	/** Every chunk in the world */
	private Map<Point, Chunk> chunks;
	/** The name of the game */
	private String name;
	/** The world player */
	private Player player;

	/** Default radioactivity factor value */
	private float defaultRadioactivity = 3f;
	/** Default rarity factor value */
	private float defaultRarity = 19.7f;
	/** Default global factor value */
	private float defaultGlobal = 0.88f;

	/** The game settings */
	private GameSettings settings;
	/** The company cash tracker */
	private CompanyCash companyCash;
	/** The company time tracker */
	private CompanyTime companyTime;

	/** The position on screen of the company cash GUI label */
	private static final Point COMPANY_CASH_POSITION = new Point(8, 6);
	/** The position on screen of the company time GUI label */
	private static final Point COMPANY_TIME_POSITION = new Point(15, 26);

	/** Default directory where to save/load the games */
	private static final String DEFAULT_GAME_DIR = "res/games/";
	/** Directory where to save/load the games */
	private static String gameDir = DEFAULT_GAME_DIR;

	/**
	 * Get the directory where to save/load the games
	 * 
	 * @return The directory where to save/load the games
	 */
	public static String getGameDir() {
		return gameDir;
	}

	/**
	 * Set the directory where to save/load the games
	 * 
	 * @param gameDir
	 *            The directory where to save/load the games
	 */
	public static void setGameDir(String gameDir) {
		World.gameDir = gameDir;
	}

	/**
	 * Set the mineral price parameters for the current world
	 * 
	 * @param instance
	 *            The world instance
	 */
	private static void setDefaults(World instance) {
		Mineral.radioactivityAdder = instance.defaultRadioactivity;
		Mineral.rarityAdder = instance.defaultRarity;
		Mineral.globalFactor = instance.defaultGlobal;
	}

	/**
	 * Set the trackers from the given settings
	 * 
	 * @param instance
	 *            The instance of the game world
	 * @param settings
	 *            The game settings for the instance
	 */
	private static void setSettings(World instance, GameSettings settings) {
		instance.settings = settings;
		instance.companyCash = new CompanyCash(COMPANY_CASH_POSITION, settings.getInitialCash(), settings.getFailureCash());
		instance.companyTime = new CompanyTime(COMPANY_TIME_POSITION, settings.getDaysCycleValue(), settings.getCashCycleValue());
	}

	/**
	 * Create a new world with the provided settings
	 * 
	 * @param name
	 *            The world name
	 * @param player
	 *            The world player
	 * @param settings
	 *            The world settings
	 */
	public World(String name, Player player, GameSettings settings) {
		this.chunks = Collections.synchronizedMap(new HashMap<Point, Chunk>());
		this.name = name;
		this.player = player;

		this.defaultRadioactivity = Configuration.factors.getDefaultRadioactivity();
		this.defaultRarity = Configuration.factors.getDefaultRarity();
		this.defaultGlobal = Configuration.factors.getDefaultGlobal();

		setSettings(this, settings);
		setDefaults(this);
	}

	/**
	 * Create a new world with every factor specified
	 * 
	 * @param name
	 *            The world name
	 * @param player
	 *            The world player
	 * @param settings
	 *            The world settings
	 * @param defaultRadioactivity
	 *            The default radioactivity factor value
	 * @param defaultRarity
	 *            The default rarity factor value
	 * @param defaultGlobal
	 *            The default global factor value
	 */
	public World(String name, Player player, GameSettings settings, float defaultRadioactivity, float defaultRarity, float defaultGlobal) {
		this.chunks = Collections.synchronizedMap(new HashMap<Point, Chunk>());
		this.name = name;
		this.player = player;

		this.defaultRadioactivity = defaultRadioactivity;
		this.defaultRarity = defaultRarity;
		this.defaultGlobal = defaultGlobal;

		setSettings(this, settings);
		setDefaults(this);
	}

	/**
	 * Get every world chunk
	 * 
	 * @return Every world chunk
	 */
	public Map<Point, Chunk> getChunks() {
		return chunks;
	}

	/**
	 * Get the world name
	 * 
	 * @return The world name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the default radioactivity factor value
	 * 
	 * @return The default radioactivity factor value
	 */
	public float getDefaultRadioactivity() {
		return defaultRadioactivity;
	}

	/**
	 * Get the default rarity factor value
	 * 
	 * @return The default rarity factor value
	 */
	public float getDefaultRarity() {
		return defaultRarity;
	}

	/**
	 * Get the default global factor value
	 * 
	 * @return The default global factor value
	 */
	public float getDefaultGlobal() {
		return defaultGlobal;
	}

	/**
	 * Set the default radioactivity factor value
	 * 
	 * @param defaultRadioactivity
	 *            The default radioactivity factor value
	 */
	public void setDefaultRadioactivity(float defaultRadioactivity) {
		this.defaultRadioactivity = defaultRadioactivity;
	}

	/**
	 * Set the default rarity factor value
	 * 
	 * @param defaultRarity
	 *            The default rarity factor value
	 */
	public void setDefaultRarity(float defaultRarity) {
		this.defaultRarity = defaultRarity;
	}

	/**
	 * Set the default global factor value
	 * 
	 * @param defaultGlobal
	 *            The default global factor value
	 */
	public void setDefaultGlobal(float defaultGlobal) {
		this.defaultGlobal = defaultGlobal;
	}

	/**
	 * Get a chunk from its position
	 * 
	 * @param position
	 *            The chunk position
	 * @return The chunk or null if there's no chunk at that location
	 */
	public Chunk getChunk(Point position) {
		return chunks.get(position);
	}

	/**
	 * Get a single block from the world, chunk independent
	 * 
	 * @param position
	 *            The block position
	 * @return The block, or null if there's no block at that location
	 */
	public Block getBlock(Point position) {
		return getBlock(position.x, position.y);
	}

	/**
	 * Get a single block from the world, chunk independent
	 * 
	 * @param x
	 *            The block X coordinate
	 * @param y
	 *            The block Y coordinate
	 * @return The block, or null if there's no block at that location
	 */
	public Block getBlock(int x, int y) {
		Point chunkPos = new Point();
		chunkPos.x = FastMath.floorDiv(x, Chunk.WIDTH);
		chunkPos.y = FastMath.floorDiv(y, Chunk.HEIGHT);

		Chunk chunk = chunks.get(chunkPos);

		if (chunk != null) {
			byte posX = (byte) FastMath.abs(FastMath.floorMod(x, Chunk.WIDTH));
			byte posY = (byte) FastMath.abs(FastMath.floorMod(y, Chunk.HEIGHT));

			return chunk.getBlock(posX, posY);
		} else
			return null;
	}

	/**
	 * Add or set a chunk in the current world
	 * 
	 * @param chunk
	 *            The chunk to set/add
	 */
	public void setChunk(Chunk chunk) {
		chunks.put(chunk.getChunkPosition(), chunk);
	}

	/**
	 * Set a block inside an existing chunk from its position
	 * 
	 * @param block
	 *            The block to set
	 */
	public void setBlock(Block block) {
		Point blockPos = new Point();
		blockPos.x = block.getBlockPositionX();
		blockPos.y = block.getBlockPositionY();

		Point chunkPos = new Point();
		chunkPos.x = FastMath.floorDiv(blockPos.x, Chunk.WIDTH);
		chunkPos.y = FastMath.floorDiv(blockPos.y, Chunk.HEIGHT);

		Chunk chunk = chunks.get(chunkPos);

		if (chunk != null) {
			byte posX = (byte) FastMath.abs(FastMath.floorMod(blockPos.x, Chunk.WIDTH));
			byte posY = (byte) FastMath.abs(FastMath.floorMod(blockPos.y, Chunk.HEIGHT));

			chunk.setBlock(posX, posY, block);

			Logger.print(Tag.DEBUG, World.class, "Setting block 0x%08x for world \"%s\"", block.hashCode(), name);
			Logger.print(Tag.DEBUG, World.class, "└ Block position: @[%d, %d]", blockPos.x, blockPos.y);
			Logger.print(Tag.DEBUG, World.class, "└ Chunk position: @[%d, %d]", chunk.getChunkPositionX(), chunk.getChunkPositionY());
		}
	}

	/**
	 * Set a block inside a chunk and create it if there's not one at the given
	 * block position
	 * 
	 * @param block
	 *            The block to set
	 */
	public void setBlockForced(Block block) {
		Point blockPos = new Point();
		blockPos.x = block.getBlockPositionX();
		blockPos.y = block.getBlockPositionY();

		Point chunkPos = new Point();
		chunkPos.x = FastMath.floorDiv(blockPos.x, Chunk.WIDTH);
		chunkPos.y = FastMath.floorDiv(blockPos.y, Chunk.HEIGHT);

		Chunk chunk = chunks.get(chunkPos);

		if (chunk == null)
			chunks.put(chunkPos, chunk = new Chunk(chunkPos));

		byte posX = (byte) FastMath.abs(FastMath.floorMod(blockPos.x, Chunk.WIDTH));
		byte posY = (byte) FastMath.abs(FastMath.floorMod(blockPos.y, Chunk.HEIGHT));

		chunk.setBlock(posX, posY, block);

		Logger.print(Tag.DEBUG, World.class, "Setting block 0x%08x for world \"%s\"", block.hashCode(), name);
		Logger.print(Tag.DEBUG, World.class, "└ Block position: @[%d, %d]", blockPos.x, blockPos.y);
		Logger.print(Tag.DEBUG, World.class, "└ Chunk position: @[%d, %d]", chunk.getChunkPositionX(), chunk.getChunkPositionY());
	}

	/**
	 * Get the current game settings
	 * 
	 * @return The current game settings
	 */
	public GameSettings getSettings() {
		return settings;
	}

	/**
	 * Get the company cash tracker
	 * 
	 * @return The company cash tracker
	 */
	public CompanyCash getCompanyCash() {
		return companyCash;
	}

	/**
	 * Get the company time tracker
	 * 
	 * @return The company time tracker
	 */
	public CompanyTime getCompanyTime() {
		return companyTime;
	}

	@Override
	public void render(Graphics2D graphics) {
		chunks.forEach(new BiConsumer<Point, Chunk>() {
			@Override
			public void accept(Point t, Chunk u) {
				u.render(graphics);
			}
		});

		companyCash.render(graphics);
		companyTime.render(graphics);
	}

	@Override
	public void update(long deltaTime) {
		companyCash.update(deltaTime);
		companyTime.update(deltaTime);
	}

	/**
	 * Damage the desired block by one unit
	 * 
	 * @param position
	 *            The block position
	 */
	public void damageBlock(Point position) {
		damageBlock(position.x, position.y);
	}

	/**
	 * Damage the desired block by one unit
	 * 
	 * @param x
	 *            The block position along X axis
	 * @param y
	 *            The block position along Y axis
	 */
	public void damageBlock(int x, int y) {
		Block block = getBlock(x, y);

		if (block != null) {
			block.damage();
			// TODO: Add block damage event call
			EventDispatcher.call(new BlockDamageEvent(player, block, this));

			if (block.isDestroyed())
				EventDispatcher.call(new BlockDestroyEvent(player, block, this));
		}
	}

	/**
	 * Repair the desired block by one unit
	 * 
	 * @param position
	 *            The block position
	 */
	public void repairBlock(Point position) {
		repairBlock(position.x, position.y);
	}

	/**
	 * Repair the desired block by one unit
	 * 
	 * @param x
	 *            The block position along X axis
	 * @param y
	 *            The block position along Y axis
	 */
	public void repairBlock(int x, int y) {
		Block block = getBlock(x, y);

		if (block != null) {
			block.repair();
			// TODO: Add block repair event call
			EventDispatcher.call(new BlockRepairEvent(player, block, this));
		}
	}

	/**
	 * Load the world & player data from disk
	 * 
	 * @return The operation result
	 */
	public boolean load() {
		return true;
	}

	/**
	 * Save the world & player data to disk
	 * 
	 * @return The operation result
	 */
	public boolean save() {
		return false;
	}
}
