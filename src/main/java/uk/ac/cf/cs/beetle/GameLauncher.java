package uk.ac.cf.cs.beetle;

import com.sun.imageio.plugins.common.ImageUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class GameLauncher implements ItemListener {
	private final String frameTitle = "BeetleDice 2D - Game Launcher";
	private final int frameWidth = 260;
	private final int frameLength = 560;
	private final int textFieldWidth = 15;
	private JFrame frame;
	private GridLayout frameSeperator;
	private ImageIcon logo;
	private Border border;

	private String playerName[];
	private GameView game;
	//===Player Details Panel and Variables ==================s
	private JPanel playerDetailsPanel;
	private GridLayout playerDetailsLayout;

	private JPanel firstPlayerPanel;
	private JCheckBox firstPlayerCheckBox;
	private JTextField firstPlayerName;

	private JPanel secondPlayerPanel;
	private JCheckBox secondPlayerCheckBox;
	private JTextField secondPlayerName;

	private JPanel thirdPlayerPanel;
	private JCheckBox thirdPlayerCheckBox;
	private JTextField thirdPlayerName;

	private JPanel fourthPlayerPanel;
	private JCheckBox fourthPlayerCheckBox;
	private JTextField fourthPlayerName;

	private final ApplicationContext ctx;

	//===Play Game Button and Panel =========================
	private JPanel launchGamePanel;
	private JButton launchButton;

	//===Constructors =======================================
	public GameLauncher(){
		//Initialise frame and properties of
		//its border and grid.

		this.ctx = new ClassPathXmlApplicationContext("spring-context.xml");

		frame = new JFrame(frameTitle);
		frame.setSize(frameWidth, frameLength);
		frameSeperator = new GridLayout(2,1);
		frame.setLayout(frameSeperator);
		border = new EtchedBorder();
		
		
		//=== Launch Panel and Button initialization =========
		launchGamePanel = new JPanel();
		//launchGamePanel.setBorder(border);

        logo = new ImageIcon(ImageUtil.class.getResource("/logo/logo.png"));
		JLabel gameLogoLabel = new JLabel(logo);
		launchButton = new JButton("Play Game!");
		launchButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				launchGame();
			}
		});
		launchGamePanel.add(gameLogoLabel);
		launchGamePanel.add(launchButton);
		frame.add(launchGamePanel);

		//===Player Details initialization etc ==============
		playerDetailsPanel = new JPanel();
		playerDetailsLayout = new GridLayout(4,1);
		playerDetailsPanel.setLayout(playerDetailsLayout);
		playerDetailsPanel.setBorder(border);
		frame.add(playerDetailsPanel);

		//=== firstPlayer ===================================
		firstPlayerPanel = new JPanel();
		//firstPlayerPanel.setBorder(border);
		firstPlayerCheckBox = new JCheckBox();
		//firstPlayerCheckBox.setSelected(true);
		firstPlayerCheckBox.addItemListener(this);

		firstPlayerName = new JTextField("Enter name", textFieldWidth);
		firstPlayerName.setEnabled(false);
		firstPlayerPanel.add(firstPlayerCheckBox);
		firstPlayerPanel.add(firstPlayerName);

		//=== secondPlayer ===================================
		secondPlayerPanel = new JPanel();
		//secondPlayerPanel.setBorder(border);
		secondPlayerCheckBox = new JCheckBox();
		//secondPlayerCheckBox.setSelected(true);
		secondPlayerCheckBox.addItemListener(this);

		secondPlayerName = new JTextField("Enter name", textFieldWidth);
		secondPlayerName.setEnabled(false);
		secondPlayerPanel.add(secondPlayerCheckBox);
		secondPlayerPanel.add(secondPlayerName);

		//=== thirdPlayer ====================================
		thirdPlayerPanel = new JPanel();
		//thirdPlayerPanel.setBorder(border);
		thirdPlayerCheckBox = new JCheckBox();
		//thirdPlayerCheckBox.setSelected(true);
		thirdPlayerCheckBox.addItemListener(this);

		thirdPlayerName = new JTextField("Enter name", textFieldWidth);
		thirdPlayerName.setEnabled(false);
		thirdPlayerPanel.add(thirdPlayerCheckBox);
		thirdPlayerPanel.add(thirdPlayerName);

		//=== fourthPlayer ===================================
		fourthPlayerPanel = new JPanel();
		//fourthPlayerPanel.setBorder(border);
		fourthPlayerCheckBox = new JCheckBox();
		//fourthPlayerCheckBox.setSelected(true);
		fourthPlayerCheckBox.addItemListener(this);

		fourthPlayerName = new JTextField("Enter name", textFieldWidth);
		fourthPlayerName.setEnabled(false);
		fourthPlayerPanel.add(fourthPlayerCheckBox);
		fourthPlayerPanel.add(fourthPlayerName);

		//=== Add all player panels to ==========================
		playerDetailsPanel.add(firstPlayerPanel);
		playerDetailsPanel.add(secondPlayerPanel);
		playerDetailsPanel.add(thirdPlayerPanel);
		playerDetailsPanel.add(fourthPlayerPanel);
		playerDetailsPanel.validate();
		//=== Add launchGamePanel to frame ====================
		
		frame.pack();
		frame.setVisible(true);
	}

	/** Listens to the check boxes. */
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getItemSelectable();
		if (source == firstPlayerCheckBox) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				//Activate player name text field
				firstPlayerName.setEnabled(true);
			} else {
				//disable player name text field
				firstPlayerName.setEnabled(false);
			}
		} else if (source == secondPlayerCheckBox) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				//Activate player name text field
				secondPlayerName.setEnabled(true);
			} else {
				secondPlayerName.setEnabled(false);
			}
		} else if (source == thirdPlayerCheckBox) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				//Activate player name text field
				thirdPlayerName.setEnabled(true);
			} else {
				//disable player name text field
				thirdPlayerName.setEnabled(false);
			}
		} else if (source == fourthPlayerCheckBox) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				//Activate player name text field
				fourthPlayerName.setEnabled(true);
			} else {
				//disable player name text field
				fourthPlayerName.setEnabled(false);
			}
		}

		System.out.println(this.getNumberOfPlayers()+" selected");
	}

	/**
	 * Calculates the number of players by counting each of
	 * the JCheckBoxes that have been ticked. This method will
	 * then be used to decide which constructor to use to launch
	 * the GameView
	 * @return numOfPlayers Number of players
	 */
	public int getNumberOfPlayers(){
		int numOfPlayers = 0;
		if (firstPlayerCheckBox.isSelected()){ numOfPlayers++; }
		if (secondPlayerCheckBox.isSelected()){ numOfPlayers++; }
		if (thirdPlayerCheckBox.isSelected()){ numOfPlayers++; }
		if (fourthPlayerCheckBox.isSelected()){ numOfPlayers++; }
		return numOfPlayers;
	}

	public void launchGame(){
		int numberOfPlayers = this.getNumberOfPlayers();
		playerName = this.getPlayerNames();
		if(numberOfPlayers <=0){
			//custom title, warning icon
			JOptionPane.showMessageDialog(frame,
					"A Game must have at least one player!",
					"BeetleDice2D - Warning",
					JOptionPane.ERROR_MESSAGE);
			firstPlayerCheckBox.setSelected(true);
		} else {
			game = (GameView) ctx.getBean("gameView", GameView.class);
			game.setPlayers(playerName);
			frame.dispose(); //kill frame and return memory
		}
	}

	public String[] getPlayerNames(){
		String []currentPlayerName = new String[this.getNumberOfPlayers()];
		int arrayPosition = 0;
		if (firstPlayerCheckBox.isSelected()) { 
			currentPlayerName[arrayPosition] = firstPlayerName.getText();
			arrayPosition++;
		}
		if (secondPlayerCheckBox.isSelected()) {
			currentPlayerName[arrayPosition] = secondPlayerName.getText();
			arrayPosition++;
		}
		if (thirdPlayerCheckBox.isSelected()) {
			currentPlayerName[arrayPosition] = thirdPlayerName.getText();
			arrayPosition++;
		}
		if (fourthPlayerCheckBox.isSelected()) { 
			currentPlayerName[arrayPosition] = fourthPlayerName.getText();
			arrayPosition++; //in case further player initiators added in future
		}
		return currentPlayerName;

	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameLauncher gL = new GameLauncher();
		System.out.println("Number of checkboxes selected: "+ gL.getNumberOfPlayers());
	}

}
