
public class Alignment {
	private String alignment;
	
	private int penalty;
	
	public Alignment() {}
	
	public Alignment(String alignment, int penalty) {
		this.alignment = alignment;
		this.penalty = penalty;
	}

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public int getPenalty() {
		return penalty;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}
	
}
