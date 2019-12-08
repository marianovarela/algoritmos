import java.util.LinkedList;
import java.util.List;

public class Permutacion {

    public static void main(String[] args) {
        String[] elementos = "0,1,2".split(",");
        int n = 2;                  //Tipos para escoger
        int r = elementos.length;   //Elementos elegidos
        List<String[]> tuples = new LinkedList<String[]>();
        boolean matriz[][] = new boolean[r][r];
        tuples = perm(elementos, "", n, r, tuples, matriz);
        System.out.println(tuples.size());
    }

    public static List<String[]> permWrap(List<String> genes){
    	String elemsStrings = "";
    	int i = 0;

    	while (i < genes.size() ) {  
    		elemsStrings = i + 1 < genes.size() ? elemsStrings + i + "," :  elemsStrings + i;
    		i++;
    	}
    	System.out.println(elemsStrings);
    	String[] elementos = elemsStrings.split(",");
    	int n = 2;                  //Tipos para escoger
        int r = elementos.length;   //Elementos elegidos
        List<String[]> tuples = new LinkedList<String[]>();
        boolean matriz[][] = new boolean[r][r];
        tuples = perm(elementos, "", n, r, tuples, matriz);
        System.out.println(tuples);
        System.out.println(tuples.size());
    	return tuples;
    }
    
    private static List<String[]> perm(String[] elem, String act, int n, int r, List<String[]> tuples, boolean[][] matriz) {
        if (n == 0) {
            String[] splitted = act.split(",");
            
            if(!exist(Integer.valueOf(splitted[0]),Integer.valueOf(splitted[1]), matriz)) {
            	matriz[Integer.valueOf(splitted[0])][Integer.valueOf(splitted[1])] = true;
            	tuples.add(splitted);
            }
        } else {
            for (int i = 0; i < r; i++) {
                if (!act.contains(elem[i])) { // Controla que no haya repeticiones
                    perm(elem, act + elem[i] + ",", n - 1, r, tuples, matriz);
                }
            }
        }
		return tuples;
    }

	private static boolean exist(int first, int second, boolean[][] matriz) {
		return matriz[first][second] || matriz[second][first];
	}
}