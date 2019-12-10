
public class QualifiedSequence {

	private int value;
	private String[] sequences;
	private String alignment;
	
	public String getAlignment() {
		return alignment;
	}
	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String[] getSequences() {
		return sequences;
	}
	public void setSequences(String[] sequences) {
		this.sequences = sequences;
	}
	
	public QualifiedSequence() {
		
	}
	
	public QualifiedSequence(int value, String[] sequences, String alignment) {
		this.sequences = sequences;
		this.value = value;
		this.alignment = alignment;
	}
}
