
public class ProfileSequence {
	private int sequence;
	
	private String alignment;
	
	private int score;
	
	private int[][] profile;
	
	public int[][] getProfile() {
		return profile;
	}

	public void setProfile(int[][] profile) {
		this.profile = profile;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ProfileSequence() {
		
	}
	
	public ProfileSequence(int sequence, String alignment, int score, int[][] profile) {
		this.sequence = sequence;
		this.score = score;
		this.alignment = alignment;
		this.profile = profile;
	}
}
