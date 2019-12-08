public class Tuple<X, Y> { 
  public final X x; 
  public final Y y; 
  public Tuple(X x, Y y) { 
    this.x = x; 
    this.y = y; 
  }
  
  public boolean exist(Tuple t) {
	return (this.x == t.x && this.y == t.y) || (this.x == t.y && this.y == t.x);   
  }
}