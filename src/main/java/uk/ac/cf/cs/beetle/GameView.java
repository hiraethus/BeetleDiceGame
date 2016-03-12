package uk.ac.cf.cs.beetle;

import uk.ac.cf.cs.beetle.exception.InvalidBodyPartSequence;
import uk.ac.cf.cs.beetle.exception.InvalidDieValue;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GameView {
    private final BodyPartFactory bodyPartFactory;

	private final String gameTitle = "Java Beetle-Dice Game";
	private final PlayerFactory playerFactory;
	private JFrame frame;
	private int frameWidth = 512;
	private int frameHeight = 384;
	JPanel mainPanel;
	
	private int numberOfPlayers;
	Player players[];//Array of players
	private Vector<JPanel> playerPanels; //Array of playerPanels

	public GameView(BodyPartFactory bodyFactory, PlayerFactory playerFactory) {
		bodyPartFactory = bodyFactory;
		this.playerFactory = playerFactory;
	}

	public void setPlayers(String[] playerName) {
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
				frame.setVisible(false);
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

		players = new Player[numberOfPlayers];
		playerPanels = new Vector<JPanel>(numberOfPlayers);
		for (int i=0; i < numberOfPlayers; i++) {
			players[i] = playerFactory.createPlayer();
			players[i].setName(playerName[i]);
			JPanel playerPanel = makePlayerPanel(players[i]);
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
	public JPanel  makePlayerPanel(final Player p) {
		final Beetle beetle = p.getBeetle();
		final Java2DBeetleRenderer beetleRenderer = new Java2DBeetleRenderer(beetle);

		beetleRenderer.setBorder(new EtchedBorder());
		final Die playerDice = new Die();
		playerDice.setPreferredSize(new Dimension(100, 100));
		playerDice.setMinimumSize(new Dimension(100,100));

		JPanel playerPanel = new JPanel();
		playerPanel.setBorder(new TitledBorder(p.getName()));
		playerPanel.setLayout(new BorderLayout());

		playerPanel.add(beetleRenderer, BorderLayout.CENTER);
		JPanel playerControlPanel = new JPanel();

		playerControlPanel.setMinimumSize(new Dimension(200,100));
		playerPanel.add(playerControlPanel, BorderLayout.SOUTH);
		final JButton rollDiceButton = new JButton("Roll Dice");
		rollDiceButton.setPreferredSize(new Dimension(100, 100));
		rollDiceButton.setMinimumSize(new Dimension(100,100));
		rollDiceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int randomNumber = playerDice.generateRandomNumber();
				playerDice.repaint();
                try {
				    IBodyPart nextBodyPart = bodyPartFactory.createBodyPart(randomNumber);
                    beetle.addBodyPart(nextBodyPart);
                } catch (InvalidBodyPartSequence ex) {
                    launchProblemDialog(p, ex);
                } catch (InvalidDieValue invalidDieValue) {
                    invalidDieValue.printStackTrace();
                }
				System.out.println("Number of BodyParts: "+beetle.getBodyParts().size());
				if(p.hasWon()){
					hideAllPlayerPanels();
					launchWinnerDialog(p);
					
				}

			}
		});

		playerControlPanel.setLayout(new GridBagLayout());
		GridBagConstraints controlGbc = new GridBagConstraints();
		controlGbc.gridx = 0;
		controlGbc.gridy = 0;
		controlGbc.fill = GridBagConstraints.BOTH;
		controlGbc.gridwidth = 1;

		playerControlPanel.add(rollDiceButton, controlGbc);

		controlGbc.gridx = 1;
		playerControlPanel.add(playerDice, controlGbc);

		controlGbc.gridx = 0;
		controlGbc.gridy = 1;
		controlGbc.gridwidth = 2;
		playerControlPanel.add(new BeetleProgressBar(beetle, 12), controlGbc);


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
}
