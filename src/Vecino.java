import java.util.List;

public class Vecino {

	private List<String> vecino;
	private int firstColumn;
	private int secondColumn;
	public List<String> getVecino() {
		return vecino;
	}
	public void setVecino(List<String> vecino) {
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
	
	public Vecino() {}
	
	public Vecino(List<String> vecino, int firstColumn, int secondColumn) {
		this.vecino = vecino;
		this.firstColumn = firstColumn;
		this.secondColumn = secondColumn;
	}
	
}
