package uk.ac.cf.cs.beetle;

public class Player {
	private String playerName = null;
	private Beetle playerBeetle;

	public Player(Beetle playerBeetle) {
		this.playerBeetle = playerBeetle;
	}

	public Beetle getBeetle() {
		return playerBeetle;
	}

	public String getName() {
		return playerName;
	}

	public void setName(String name) {
        this.playerName = name;
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
