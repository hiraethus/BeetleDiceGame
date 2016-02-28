package uk.ac.cf.cs.beetle;

public abstract class Player {
	private String playerName;
	private BeetleJComponent playerBeetle;

	public Player() {
		this.playerName = "";
		this.playerBeetle = new BeetleJComponent();
	}

	public Player(String name) {
		this.playerName = name;
		this.playerBeetle = new BeetleJComponent();
	}

	public BeetleJComponent getBeetle() {
		return playerBeetle;
	}

	public String getName() {
		return playerName;
	}
	
	public void setName(String playerName) {
		this.playerName = playerName;
	}
	
	
	public boolean hasWon(){
		boolean winStatus;
		if(playerBeetle.getNumberOfBodyParts()==12){
			winStatus = true;
		} else {
			winStatus = false;
		}
		return winStatus;
	}
}
