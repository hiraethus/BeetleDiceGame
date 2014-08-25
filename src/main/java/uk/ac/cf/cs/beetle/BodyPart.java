package uk.ac.cf.cs.beetle;
public class BodyPart {
	private String type; // head, body, leg, antenna, eye or tail (use enum to
							// limit these values)
	private String colour;
	private String powerFactor;

	public BodyPart(String type) {
		this.type = type;
		colour = null;
		powerFactor = null;
	}

	public BodyPart(int diceNum) {
		switch (diceNum) {
		case 1:
			this.setType("body");
			break;
		case 2:
			this.setType("head");
			break;
		case 3:
			this.setType("eye");
			break;
		case 4:
			this.setType("leg");
			break;
		case 5:
			this.setType("eye");
			break;
		case 6:
			this.setType("antenna");
			break;
		default:
			break;
		}
	}

	public String getType() {
		// e.g. head, leg, feeler etc.
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
