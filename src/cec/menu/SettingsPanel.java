package cec.menu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cec.menu.GameOptions;

/**
 * Defines the settings UI. Submenus are nested classes.
 * 
 * @author Unsal Ozturk
 * @version 01.05.2017
 */
public class SettingsPanel extends AbstractCECPanel {

	private JButton soundSettings;
	private JButton videoSettings;
	private JButton difficultySettings;

	private SoundPanel soundPanel;
	private VideoPanel videoPanel;
	private DifficultyPanel difficultyPanel;

	private GameOptions options;

	public SettingsPanel(JFrame frame, AbstractCECPanel superPanel, int x, int y) {
		super(frame, superPanel);

		options = new GameOptions();

		soundPanel = new SoundPanel(frame, this);
		videoPanel = new VideoPanel(frame, this);
		difficultyPanel = new DifficultyPanel(frame, this);

		soundSettings = new JButton("Sound Settings");
		videoSettings = new JButton("Video Settings");
		difficultySettings = new JButton("Difficulty Settings");

		MenuListener soundSettingsListener = new MenuListener(this, soundPanel);
		MenuListener videoSettingsListener = new MenuListener(this, videoPanel);
		MenuListener difficultySettingsListener = new MenuListener(this, difficultyPanel);

		soundSettings.addActionListener(soundSettingsListener);
		videoSettings.addActionListener(videoSettingsListener);
		difficultySettings.addActionListener(difficultySettingsListener);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(x / 15)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(difficultySettings, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(videoSettings, Alignment.LEADING,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(soundSettings, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, x / 7,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(getBackButton(), Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(x / 15)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(y / 3)
				.addComponent(soundSettings, GroupLayout.PREFERRED_SIZE, y / 25, GroupLayout.PREFERRED_SIZE)
				.addGap(y / 15)
				.addComponent(videoSettings, GroupLayout.PREFERRED_SIZE, y / 25, GroupLayout.PREFERRED_SIZE)
				.addGap(y / 15)
				.addComponent(difficultySettings, GroupLayout.PREFERRED_SIZE, y / 25, GroupLayout.PREFERRED_SIZE)
				.addGap(y / 15)
				.addComponent(getBackButton(), GroupLayout.PREFERRED_SIZE, y / 25, GroupLayout.PREFERRED_SIZE)
				.addGap(y / 4)));
		setLayout(groupLayout);

		add(soundSettings);
		add(videoSettings);
		add(difficultySettings);
		addBackButton();
		setAnimation(new MenuAnimation(300, 4, "OM", this));
	}

	public GameOptions getOptions() {
		return options;
	}

	private class SoundPanel extends AbstractCECPanel {
		private JCheckBox muteBox;
		private JSlider soundSlider;
		private JLabel soundLabel;
		private int lastVolume;

		public SoundPanel(JFrame frame, AbstractCECPanel superPanel) {
			super(frame, superPanel);
			lastVolume = options.getSoundLevel();
			muteBox = new JCheckBox("Mute");
			if (options.isMute() == GameOptions.MUTED) {
				muteBox.setSelected(true);
				soundSlider.setValue(0);
			}
			soundSlider = new JSlider();
			soundLabel = new JLabel("Sound Level: ");

			ActionListener muteBoxListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (muteBox.isSelected()) {
						options.setSoundLevel(0);
						soundSlider.setValue(0);
					} else {
						options.setSoundLevel(lastVolume);
						soundSlider.setValue(lastVolume);
					}
				}
			};

			ChangeListener sliderListener = new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					options.setSoundLevel(soundSlider.getValue());
					if (options.getSoundLevel() == 0) {
						muteBox.setSelected(true);
					} else {
						muteBox.setSelected(false);
						lastVolume = soundSlider.getValue();
					}
				}
			};

			soundSlider.setValue(options.getSoundLevel());

			soundSlider.addChangeListener(sliderListener);
			muteBox.addActionListener(muteBoxListener);

			add(muteBox);
			add(soundLabel);
			add(soundSlider);
			addBackButton();
			setAnimation(new MenuAnimation(300, 4, "OM", this));
		}
	}

	private class VideoPanel extends AbstractCECPanel {
		private JLabel info;
		private JComboBox<String> dropDownMenu;
		private JRadioButton fullscreen;
		private JRadioButton windowed;
		private JRadioButton borderlessFullscreen;
		private ButtonGroup group;

		private final String[] resolutions = { "640x480", "800x600", "1024x640", "1600x900", "1920x1080", "1200x650" };

		public VideoPanel(JFrame frame, AbstractCECPanel superPanel) {
			super(frame, superPanel);

			info = new JLabel("Resolution: ");
			dropDownMenu = new JComboBox<String>(resolutions);
			for (int i = 0; i < resolutions.length; i++) {
				if (options.getResolution().toString().equals(resolutions[i])) {
					dropDownMenu.setSelectedIndex(i);
				}
			}
			fullscreen = new JRadioButton("Fullscreen");
			windowed = new JRadioButton("Windowed");
			borderlessFullscreen = new JRadioButton("Borderless Fullscreen");
			group = new ButtonGroup();
			group.add(fullscreen);
			group.add(windowed);
			group.add(borderlessFullscreen);
			if (options.getScreenState() == GameOptions.FULLSCREEN) {
				fullscreen.setSelected(true);
			} else if (options.getScreenState() == GameOptions.WINDOWED) {
				windowed.setSelected(true);
			} else if (options.getScreenState() == GameOptions.BORDERLESS_FULLSCREEN) {
				borderlessFullscreen.setSelected(true);
			}

			ActionListener resolutionListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox<String> tmp = (JComboBox<String>) (e.getSource());
					for (int i = 0; i < resolutions.length; i++) {
						if (tmp.getSelectedItem().equals(resolutions[i])) {
							options.setResolution(resolutions[i]);
						}
					}
				}
			};

			ActionListener radioListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (fullscreen.isSelected()) {
						options.setScreenState(GameOptions.FULLSCREEN);
					} else if (windowed.isSelected()) {
						options.setScreenState(GameOptions.WINDOWED);
					} else if (borderlessFullscreen.isSelected()) {
						options.setScreenState(GameOptions.BORDERLESS_FULLSCREEN);
					}
				}
			};

			dropDownMenu.addActionListener(resolutionListener);
			fullscreen.addActionListener(radioListener);
			windowed.addActionListener(radioListener);
			borderlessFullscreen.addActionListener(radioListener);

			add(info);
			add(dropDownMenu);
			add(fullscreen);
			add(windowed);
			add(borderlessFullscreen);
			addBackButton();
			setAnimation(new MenuAnimation(300, 4, "OM", this));
		}
	}

	private class DifficultyPanel extends AbstractCECPanel {
		private JRadioButton easy;
		private JRadioButton medium;
		private JRadioButton hard;
		private ButtonGroup group;
		private JLabel info;

		public DifficultyPanel(JFrame frame, AbstractCECPanel superPanel) {
			super(frame, superPanel);
			info = new JLabel("Difficulty: ");
			easy = new JRadioButton("Easy");
			medium = new JRadioButton("Medium");
			hard = new JRadioButton("Hard");
			group = new ButtonGroup();
			group.add(easy);
			group.add(medium);
			group.add(hard);
			if (options.getDifficulty() == GameOptions.EASY) {
				easy.setSelected(true);
			} else if (options.getDifficulty() == GameOptions.MEDIUM) {
				medium.setSelected(true);
			} else if (options.getDifficulty() == GameOptions.HARD) {
				hard.setSelected(true);
			}

			ActionListener radioListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (easy.isSelected()) {
						options.setDifficulty(GameOptions.EASY);
					} else if (medium.isSelected()) {
						options.setDifficulty(GameOptions.MEDIUM);
					} else if (hard.isSelected()) {
						options.setDifficulty(GameOptions.HARD);
					}
				}
			};

			easy.addActionListener(radioListener);
			medium.addActionListener(radioListener);
			hard.addActionListener(radioListener);

			add(easy);
			add(medium);
			add(hard);
			addBackButton();
			setAnimation(new MenuAnimation(300, 4, "OM", this));
		}
	}
}