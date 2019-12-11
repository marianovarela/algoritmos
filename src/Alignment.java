
public class Alignment {
	private String alignmentOne;
	
	private String alignmentTwo;
	
	private int penalty;
	
	public Alignment() {}
	
	public Alignment(String alignmentOne, String alignmentTwo, int penalty) {
		this.alignmentOne = alignmentOne;
		this.alignmentTwo = alignmentTwo;
		this.penalty = penalty;
	}

	public String getAlignmentOne() {
		return alignmentOne;
	}

	public void setAlignmentOne(String alignmentOne) {
		this.alignmentOne = alignmentOne;
	}

	public String getAlignmentTwo() {
		return alignmentTwo;
	}

	public void setAlignmentTwo(String alignmentTwo) {
		this.alignmentTwo = alignmentTwo;
	}

	public int getPenalty() {
		return penalty;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}
	
}
