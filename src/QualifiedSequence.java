
public class QualifiedSequence {

	private String[] sequences;
	private Alignment alignment;
	
	public Alignment getAlignment() {
		return alignment;
	}
	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}
	public String[] getSequences() {
		return sequences;
	}
	public void setSequences(String[] sequences) {
		this.sequences = sequences;
	}
	
	public QualifiedSequence() {
		
	}
	
//	public QualifiedSequence(int value, String[] sequences, String alignment) {
//		this.sequences = sequences;
//		this.value = value;
//		this.alignment = alignment;
//	}
	
	public QualifiedSequence(Alignment alignment, String[] sequences) {
		this.sequences = sequences;
		this.alignment = alignment;
	}
}
