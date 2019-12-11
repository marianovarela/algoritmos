import java.util.List;

public class SequenceVecina {

	private String vecino;
	private int firstColumn;
	private int secondColumn;
	public String getVecino() {
		return vecino;
	}
	public void setVecino(String vecino) {
		this.vecino = vecino;
	}
	public int getFirstColumn() {
		return firstColumn;
	}
	public void setFirstColumn(int firstColumn) {
		this.firstColumn = firstColumn;
	}
	public int getSecondColumn() {
		return secondColumn;
	}
	public void setSecondColumn(int secondColumn) {
		this.secondColumn = secondColumn;
	}
	
	public SequenceVecina() {
	}
	
	public SequenceVecina(String vecino, int firstColumn, int secondColumn) {
		this.vecino = vecino;
		this.firstColumn = firstColumn;
		this.secondColumn = secondColumn;
	}
}
