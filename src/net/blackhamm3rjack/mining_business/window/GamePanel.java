package net.blackhamm3rjack.mining_business.window;

import java.awt.AWTException;
import java.awt.BufferCapabilities;
import java.awt.BufferCapabilities.FlipContents;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.ImageCapabilities;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import net.blackhamm3rjack.mining_business.annotations.Debug;
import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.utils.Configuration;
import net.blackhamm3rjack.mining_business.utils.Logger;
import net.blackhamm3rjack.mining_business.utils.Logger.Tag;

/**
 * Simple canvas
 * 
 * @author Luca
 *
 */
@Versioning(patch = 3)
public class GamePanel extends Canvas implements Runnable {
	private static final long serialVersionUID = -3065839922448041828L;

	private Thread thread;
	private GameState state;
	private GameLooper looper;
	private BufferedImage canvas;

	private int buffersCount;
	private long framesTiming;
	private boolean optionalChecks;
	private boolean bufferChecks;

	private Sigar sigar;
	private Mem memory;
	private CpuPerc[] cpus;
	private boolean hwStatistics;

	@Debug()
	private long totalTime;

	public GamePanel(int framesPerSecond, int buffersCount, boolean optionalChecks, boolean bufferChecks,
			boolean hwStatistics) {
		super();

		this.framesTiming = 1_000_000_000 / framesPerSecond;
		this.buffersCount = buffersCount;
		this.state = new GameState(this);
		this.looper = new GameLooper(this);
		this.optionalChecks = optionalChecks;
		this.bufferChecks = bufferChecks;
		this.hwStatistics = hwStatistics;

		if (hwStatistics) {
			this.sigar = new Sigar();
			this.memory = null;
			this.cpus = null;
			this.totalTime = 0l;

			try {
				CpuInfo[] infos = sigar.getCpuInfoList();

				Logger.print(Tag.DEBUG, GamePanel.class, "Total cores: %d", infos[0].getTotalCores());
				Logger.print(Tag.DEBUG, GamePanel.class, "Total sockets: %d", infos[0].getTotalSockets());

				// Output general CPUs information
				for (int i = 0; i < infos.length; i++)
					Logger.print(Tag.DEBUG, GamePanel.class, "CPU%d @ %s (%d) - Cache size: %d, Vendor: %s", i,
							infos[i].getModel(), infos[i].getMhz(), infos[i].getCacheSize(), infos[i].getVendor());
			} catch (SigarException e) {
				Logger.print(Tag.ERROR, GamePanel.class, "Could not get CPUs info");
			}
		}

		setPreferredSize(new Dimension(Configuration.display.getWidth(), Configuration.display.getHeight()));
		setBackground(Color.white);
		setFocusable(true);
		requestFocus();

		addKeyListener(looper.getKeyInput());
		addMouseListener(looper.getButtonInput());
		addMouseMotionListener(looper.getButtonInput());
	}

	private void checkCanvas() {
		if (canvas == null)
			canvas = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
	}

	private void checkBuffer() {
		if (getBufferStrategy() == null) {
			if (bufferChecks) {
				try {
					createBufferStrategy(buffersCount,
							new BufferCapabilities(
									new ImageCapabilities(Configuration.display.isFrontBufferAccelerated()),
									new ImageCapabilities(Configuration.display.isBackBufferAccelerated()),
									FlipContents.BACKGROUND));
				} catch (AWTException e) {
					// Bypass the error stop because of a fatal exception
					Logger.setStopOnError(true);
					Logger.print(Tag.ERROR, GamePanel.class,
							"Cannot set the right buffer capabilities: " + e.getMessage().toLowerCase());
				}

				// Check capabilities
				BufferCapabilities capabilities = getBufferStrategy().getCapabilities();

				if (!capabilities.isPageFlipping() && Configuration.engine.doesRequirePageFlipping())
					Logger.print(Tag.ERROR, GamePanel.class, "The graphics context has no page flipping");
				if (!capabilities.isMultiBufferAvailable() && Configuration.engine.doesRequireMultiBuffer())
					Logger.print(Tag.ERROR, GamePanel.class, "The graphics context has no multi buffer");
			} else
				createBufferStrategy(buffersCount);
		}
	}

	@Override
	public void run() {
		long startTime;
		long endTimePartial = 0;
		long deltaTimePartial = 0;
		long endTimeFull = 0;
		long deltaTimeFull = 0;

		BufferStrategy bufferStrategy;
		Graphics2D graphics;

		while (state.isRunning()) {
			startTime = System.nanoTime();

			checkCanvas();
			checkBuffer();

			bufferStrategy = getBufferStrategy();
			graphics = (Graphics2D) bufferStrategy.getDrawGraphics();

			looper.update(deltaTimeFull);

			graphics.setColor(Color.black);
			graphics.fillRect(0, 0, getWidth(), getHeight());

			looper.render(graphics);

			if (optionalChecks) {
				// Check buffer state
				if (bufferStrategy.contentsLost())
					Logger.print(Tag.WARNING, GamePanel.class, "Contents lost by buffer");
				if (bufferStrategy.contentsRestored())
					Logger.print(Tag.WARNING, GamePanel.class, "Contents restored from buffer");
			}

			bufferStrategy.getDrawGraphics().drawImage(canvas, 0, 0, null);
			bufferStrategy.show();

			endTimePartial = System.nanoTime();
			deltaTimePartial = endTimePartial - startTime;

			if (deltaTimePartial > framesTiming)
				deltaTimePartial = framesTiming;

			try {
				Thread.sleep((framesTiming - deltaTimePartial) / 1_000_000);
			} catch (InterruptedException e) {
				// Bypass the error stop because of a fatal exception
				Logger.setStopOnError(true);
				Logger.print(Tag.ERROR, GamePanel.class, "Interrupted exception: " + e.getMessage().toLowerCase());
			}

			endTimeFull = System.nanoTime();
			deltaTimeFull = endTimeFull - startTime;

			// Print statistics about the RAM and the CPUs
			if (hwStatistics && ((totalTime += deltaTimeFull) >= 1_000_000_000)) {
				try {
					cpus = sigar.getCpuPercList();
					memory = sigar.getMem();

					// Print out memory statistics
					Logger.print(Tag.DEBUG, GamePanel.class,
							"Actual free memory: %d (%f%%), actual used memory: %d (%f%%)", memory.getActualFree(),
							memory.getFreePercent(), memory.getActualUsed(), memory.getUsedPercent());

					// Print out CPUs usage
					for (int i = 0; i < cpus.length; i++)
						Logger.print(Tag.DEBUG, GamePanel.class, "CPU%d %f%%", i, cpus[i].getCombined() * 100);
				} catch (SigarException e) {
					Logger.print(Tag.ERROR, GamePanel.class, "Could not get RAM/CPUs info");
				}

				totalTime = 0L;
			}
		}
	}

	protected Thread getThread() {
		return thread;
	}

	protected void setThread(Thread thread) {
		this.thread = thread;
	}

	public GameState getState() {
		return state;
	}
}
