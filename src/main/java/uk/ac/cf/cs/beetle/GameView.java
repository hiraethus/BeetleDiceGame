package uk.ac.cf.cs.beetle;

import uk.ac.cf.cs.beetle.exception.InvalidBodyPartSequence;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GameView implements ActionListener {
	private final String gameTitle = "Java Beetle-Dice Game";
	private JFrame frame;
	private int frameWidth = 512;
	private int frameHeight = 384;
	JPanel mainPanel;
	
	private int numberOfPlayers;
	Player humanPlayer[];//Array of players
	private Vector<JPanel> playerPanels; //Array of playerPanels

	public GameView(String[] playerName) {
		this.numberOfPlayers = playerName.length;
		frame = new JFrame(gameTitle);
		frame.setSize(new Dimension(frameWidth, frameHeight));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		Container contentPane = frame.getContentPane();
		mainPanel = new JPanel(new BorderLayout());
		contentPane.add(mainPanel);

		// =========Menu Bar============================
		JMenuBar menuBar = new JMenuBar();
		mainPanel.add(menuBar, BorderLayout.NORTH);

		// =========File Menu===========================

		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_ALT);
		menuBar.add(fileMenu);

		JMenuItem newGameMenuItem = new JMenuItem("New Game");
		newGameMenuItem.setMnemonic(KeyEvent.VK_N);
		newGameMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				GameLauncher gL = new GameLauncher();
				frame.dispose(); //kill frame and return memory
			}
		});
		fileMenu.add(newGameMenuItem);

		JMenuItem quitGameMenuItem = new JMenuItem("Quit Game");
		quitGameMenuItem.setMnemonic(KeyEvent.VK_Q);
		quitGameMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose(); //kill frame and return memory
			}
		});
		fileMenu.add(quitGameMenuItem);

		// ========Edit Menu===========================
		JMenu editMenu = new JMenu("Edit");
		menuBar.add(editMenu);

		JMenuItem undoLastMoveMenuItem = new JMenuItem("Undo last Move");
		undoLastMoveMenuItem.setMnemonic(KeyEvent.VK_U);
		editMenu.add(undoLastMoveMenuItem);

		// ========Help Menu===========================
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);

		JMenuItem aboutMenuItem = new JMenuItem("About " + gameTitle);
		aboutMenuItem.setMnemonic(KeyEvent.VK_A);
		helpMenu.add(aboutMenuItem);

		// =======End Menu Bar=========================
		// =======Player grid=========================
		JPanel playerGrid = new JPanel();
		playerGrid.setLayout(new GridLayout(1, numberOfPlayers));
		mainPanel.add(playerGrid, BorderLayout.CENTER);

		// ======Panels for each Player================

		humanPlayer = new HumanPlayer[numberOfPlayers];
		playerPanels = new Vector<JPanel>(numberOfPlayers);
		for (int i=0; i < numberOfPlayers; i++) {
			humanPlayer[i] = new HumanPlayer();
			humanPlayer[i].setName(playerName[i]);
			JPanel playerPanel = makePlayerPanel(humanPlayer[i]);
			playerPanels.add(playerPanel);
		}
		// =======Add player panels from Vector to grid =======
		for(JPanel currentPlayerPanel: playerPanels){
			playerGrid.add(currentPlayerPanel);
		}
		
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Makes a panel for the player to use with user specific ActionListeners so
	 * that the buttons and Beetle displayed are for the specific player
	 * 
	 * @return
	 */
	public JPanel makePlayerPanel(final Player p) {
		final BeetleJComponent beetle = p.getBeetle();
		beetle.setBorder(new EtchedBorder());
		beetle.repaint();
		final Die playerDice = new Die();

		JPanel playerPanel = new JPanel();
		playerPanel.setBorder(new TitledBorder(p.getName()));
		playerPanel.setLayout(new BorderLayout());

		playerPanel.add(beetle, BorderLayout.CENTER);
		JPanel playerControlPanel = new JPanel();
		playerControlPanel.setLayout(new GridLayout(1, 2));
		playerControlPanel.setMinimumSize(new Dimension(200,100));
		playerPanel.add(playerControlPanel, BorderLayout.SOUTH);
		final JButton rollDiceButton = new JButton("Roll Dice");
		rollDiceButton.setPreferredSize(new Dimension(100, 100));
		rollDiceButton.setMinimumSize(new Dimension(100,100));
		rollDiceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int randomNumber = playerDice.generateRandomNumber();
				BodyPart nextBodyPart = new BodyPart(randomNumber);
                try {
                    beetle.addBodyPart(nextBodyPart);
                } catch (InvalidBodyPartSequence ex) {
                    launchProblemDialog(p, ex);
                }
				beetle.repaint();
				playerDice.repaint();
				System.out.println("Number of BodyParts: "+beetle.getNumberOfBodyParts());
				if(p.hasWon()){
					hideAllPlayerPanels();
					launchWinnerDialog(p);
					
				}

			}
		});

		playerControlPanel.add(rollDiceButton);
		playerControlPanel.add(playerDice);


		return playerPanel;
	}
	
	public void hideAllPlayerPanels(){
		for(JPanel currentPlayerPanel: playerPanels){
			currentPlayerPanel.setVisible(false);
		}
	}
	
	public void launchWinnerDialog(Player player){
		JOptionPane.showMessageDialog(frame,
				player.getName()+" has won the game!",
				"BeetleDice2D - Winner!",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void launchProblemDialog(Player player, InvalidBodyPartSequence e){
		JOptionPane.showMessageDialog(frame,
				player.getName()+": "+ e.getMessage(),
				"BeetleDice2D - Error!",
				JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
