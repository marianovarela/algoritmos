import java.util.Arrays;

public class Profile {
	
	private static int GAP = 0;
	private static int A = 1;
	private static int C = 2;
	private static int G = 3;
	private static int T = 4;
	
	//test de copia
	public static void main(String[] args) {
		int[] a1 = {0,1,2,3};
		int[] a2 = {0,1,0,3};
		int[][] aa = {a1, a2};
		printMatrix(aa);
		
		int[][] aa2 = new int[5][5];
		System.out.println(" ");
		printMatrix(aa2);
		System.out.println(" ");
		aa2 = arrayCopy(aa, aa2);
		printMatrix(aa2);
	}
	
	public static int[][] arrayCopy(int[][] aSource, int[][] aDestination) {
	    for (int i = 0; i < aSource.length; i++) {
	        System.arraycopy(aSource[i], 0, aDestination[i], 0, aSource[i].length);
	    }
	    return aDestination;
	}
	
	//idx es el id de la secuencia que estoy evaluando
	public static ProfileSequence getMinimumPenalty(int idx, String x, int pxy, int pgap, int[][] profile) {
		printMatrix(profile);
		int i, j; // intialising variables 
	      
	    int m = x.length(); // length of gene1 
	    int n = profile[0].length; // length of profile
	    System.out.println("cantidad de columnas:" + n);
	      
	    // table for storing optimal 
	    // substructure answers 
	    int dp[][] = new int[n + m + 1][n + m + 1]; 
	      
	    for (int[] x1 : dp) 
	    Arrays.fill(x1, 0); 
	  
	    // intialising the table  
	    for (i = 0; i <= (n + m); i++) 
	    { 
	        dp[i][0] = i * pgap; 
	        dp[0][i] = i * pgap; 
	    }  
	    
		printMatrix(dp);
		
	    // calcuting the minimum penalty 
		int[][] newProfile = crearCopia(profile, x.length());
	    for (i = 1; i <= m; i++) 
	    { 
	        for (j = 1; j <= n; j++) 
	        { 
	        		System.out.println(x);
	        		System.out.println(i);
        			dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1] + getCosto(x, pxy, pgap, dp, newProfile, i, j),  
                            dp[i - 1][j] + getCosto(x, pxy, pgap, dp, newProfile, i, j)),  
                            dp[i][j - 1] + getCosto(x, pxy, pgap, dp, newProfile, i, j));
	        } 
	    } 
	  
	    // Reconstructing the solution 
	    int l = n + m; // maximum possible length 
	      
	    i = m; j = n; 
	      
	    int xpos = l; 
	    int ypos = l; 
	  
	    // Final answers for  
	    // the respective strings 
	    int xans[] = new int[l + 1];  
	    int yans[] = new int[l + 1]; 
	    
	    printMatrix(dp);
	    printMatrix(newProfile);
	    return new ProfileSequence(idx, x,  dp[m][n], newProfile);
	}

	private static int getCosto(String sequence, int pxy, int pgap, int[][] dp, int[][] profile, int i, int j) {
		//printMatrix(dp);
//		int[][] newProfile = crearCopia(profile);
		// si coinciden suma por 0 y sino agrego mm
		if(i < sequence.length()) {
			char character = sequence.charAt(i);
			int scoreGAP = character == '_' ? 0 : pxy * getOccurrences(character, profile, i, GAP);
			int scoreA = character == 'A' ? 0 : pxy * getOccurrences(character, profile, i, A);
			int scoreC = character == 'C' ? 0 : pxy * getOccurrences(character, profile, i, C);
			int scoreG = character == 'G' ? 0 : pxy * getOccurrences(character, profile, i, G);
			int scoreT = character == 'T' ? 0 : pxy * getOccurrences(character, profile, i, T);
			return scoreGAP + scoreA + scoreC + scoreG + scoreT;
		}else {
			//costo de meter un gap porque no hay otra posibilidad
			return pgap;
		}
	}

	private static int[][] crearCopia(int[][] src, int newLength) {
		int length = src.length;
		int width = newLength > src[0].length ? newLength : src[0].length;
		int[][] target = new int[length][width];
		target = arrayCopy(src, target);
		return target;
	}

	private static int getOccurrences(char character, int[][] profile, int i, int idxLetter) {
		int ocurrences = 0;
		if(i >= profile[A].length) {
			printMatrix(profile);
    	}
		switch(character) 
		{ 
		    case 'A': 
		    	System.out.println(profile[A].length);
		    	ocurrences = profile[A][i];
		    	profile[A][i]++;
		    case 'C': 
		    	System.out.println(profile[C].length);
		    	ocurrences = profile[C][i];
		    	profile[C][i]++;
		    case 'G': 
		    	System.out.println(profile[G].length);
		    	ocurrences = profile[G][i];
		    	profile[G][i]++;
		    case 'T': 
		    	System.out.println(profile[T].length);
		    	ocurrences = profile[T][i];
		    	profile[T][i]++;
		    default:// "-" 
		    	System.out.println(profile[GAP].length);
		    	ocurrences = profile[GAP][i];
		    	profile[GAP][i]++;
		}
		return ocurrences;
	}

	private static void printMatrix(int[][] dp) {
		for(int[] x1 : dp) {
	    	String row = "";
	    	for(int idx : x1) {
	    		row += (idx + "|") ;
	    	}
	    	System.out.println(row);
		}
	} 
}
