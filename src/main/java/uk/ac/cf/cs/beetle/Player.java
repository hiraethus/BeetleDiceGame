package uk.ac.cf.cs.beetle;

public class Player {
	private String playerName;
	private Beetle playerBeetle;

	public Player(String name, Beetle playerBeetle) {
		this.playerName = name;
		this.playerBeetle = playerBeetle;
	}

	public Beetle getBeetle() {
		return playerBeetle;
	}

	public String getName() {
		return playerName;
	}
	
	public boolean hasWon(){
		boolean winStatus;
		if(playerBeetle.getBodyParts().size() == 12){
			winStatus = true;
		} else {
			winStatus = false;
		}
		return winStatus;
	}
}
