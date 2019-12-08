
public class QualifiedSequence {

	private int value;
	private String[] sequences;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String[] getSequences() {
		return sequences;
	}
	public void setSequence(String[] sequences) {
		this.sequences = sequences;
	}
	
	public QualifiedSequence() {
		
	}
	
	public QualifiedSequence(int value, String[] sequences) {
		this.sequences = sequences;
		this.value = value;
	}
}
