package net.blackhamm3rjack.mining_business.window;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.translations.LanguageSet;
import net.blackhamm3rjack.mining_business.utils.Configuration;
import net.blackhamm3rjack.mining_business.utils.Logger;
import net.blackhamm3rjack.mining_business.utils.Logger.Tag;
import net.blackhamm3rjack.mining_business.utils.Version;

/**
 * Manage the game window
 * 
 * @author Luca
 *
 */
@Versioning(major = 1, minor = 5, patch = 6)
public class GameFrame extends JFrame implements WindowListener {
	private static final long serialVersionUID = 5302285460533518710L;

	private GamePanel panel;
	private JPanel panelInfo;
	private JLabel labelInfo;
	private JLabel labelDescription;

	public GameFrame(int framesPerSecond, int buffersCount) {
		super();

		setTitle(LanguageSet.get("game_title"));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		addWindowListener(this);

		add(panel = new GamePanel(framesPerSecond, buffersCount, Configuration.display.shouldDoOptionalChecks(),
				Configuration.display.shouldDoBufferChecks(), false), BorderLayout.CENTER);

		if (Configuration.game.isShowInfo()) {
			panelInfo = new JPanel();
			panelInfo.setLayout(new BorderLayout());
			panelInfo.setBorder(BorderFactory.createEmptyBorder(3, 3, 4, 3));

			// Create the info & description labels
			labelInfo = new JLabel(
					new Version(1, 8, 1675, "Pre-alpha", "pa", "Copyright \u00a9 2016 Luca Cavallari").toString());
			labelInfo.setForeground(Color.red);
			labelDescription = new JLabel(LanguageSet.get("game_description"));
			labelDescription.setForeground(Color.gray);

			panelInfo.add(labelInfo, BorderLayout.WEST);
			panelInfo.add(labelDescription, BorderLayout.EAST);
			add(panelInfo, BorderLayout.SOUTH);
		}

		// Load the window icons
		try {
			ArrayList<BufferedImage> icons = new ArrayList<>(3);
			icons.add(ImageIO.read(new File("res/icons/16.png")));
			icons.add(ImageIO.read(new File("res/icons/32.png")));
			icons.add(ImageIO.read(new File("res/icons/64.png")));

			setIconImages(icons);
		} catch (IOException e) {
			Logger.print(Tag.ERROR, GameFrame.class, "Cannot set the window icons: " + e.getMessage().toLowerCase());
		}

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		// Start the game
		panel.getState().setRunning(true);
		panel.getState().setPaused(false);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		panel.getState().setRunning(false);
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
		panel.getState().setPaused(true);
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		panel.getState().setPaused(false);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		panel.getState().setPaused(false);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		panel.getState().setPaused(true);
	}
}
