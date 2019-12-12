import java.util.Arrays;

public class Profile {
	
	private static int GAP = 0;
	private static int A = 1;
	private static int C = 2;
	private static int G = 3;
	private static int T = 4;
	
	
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
		int[][] newProfile = crearCopia(profile);
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
	    //aca hay que armar el alineamiento
	    /*while ( !(i == 0 || j == 0)) 
	    { 
	        if (x.charAt(i - 1) == y.charAt(j - 1)) 
	        { 
	            xans[xpos--] = (int)x.charAt(i - 1); 
	            yans[ypos--] = (int)y.charAt(j - 1); 
	            i--; j--; 
	        } 
	        else if (dp[i - 1][j - 1] + pxy == dp[i][j]) 
	        { 
	            xans[xpos--] = (int)x.charAt(i - 1); 
	            yans[ypos--] = (int)y.charAt(j - 1); 
	            i--; j--; 
	        } 
	        else if (dp[i - 1][j] + pgap == dp[i][j]) 
	        { 
	            xans[xpos--] = (int)x.charAt(i - 1); 
	            yans[ypos--] = (int)'_'; 
	            i--; 
	        } 
	        else if (dp[i][j - 1] + pgap == dp[i][j]) 
	        { 
	            xans[xpos--] = (int)'_'; 
	            yans[ypos--] = (int)y.charAt(j - 1); 
	            j--; 
	        } 
	    } 
	    while (xpos > 0) 
	    { 
	        if (i > 0) xans[xpos--] = (int)x.charAt(--i); 
	        else xans[xpos--] = (int)'_'; 
	    } 
	    while (ypos > 0) 
	    { 
	        if (j > 0) yans[ypos--] = (int)y.charAt(--j); 
	        else yans[ypos--] = (int)'_'; 
	    } 
	  
	    // Since we have assumed the  
	    // answer to be n+m long,  
	    // we need to remove the extra  
	    // gaps in the starting id  
	    // represents the index from  
	    // which the arrays xans, 
	    // yans are useful 
	    int id = 1; 
	    for (i = l; i >= 1; i--) 
	    { 
	        if ((char)yans[i] == '_' &&  
	            (char)xans[i] == '_') 
	        { 
	            id = i + 1; 
	            break; 
	        } 
	    } 
	  
	    // Printing the final answer 
	    System.out.print("Minimum Penalty in " +  
	                     "aligning the genes = "); 
	    System.out.print(dp[m][n] + "\n"); 
	    System.out.println("The aligned genes are :"); 
	    String alignmentOne = "";
	    for (i = id; i <= l; i++) 
	    { 
	    	alignmentOne += (char)xans[i];
	        System.out.print((char)xans[i]); 
	    } 
	    System.out.print("\n"); 
	    String alignmentTwo = "";
	    for (i = id; i <= l; i++) 
	    { 
	    	alignmentTwo += (char)yans[i];
	    	System.out.print((char)yans[i]); 
	    } 
	    */
//	    return dp[m][n]; 
	    printMatrix(dp);
	    System.out.println("el score final es " + dp[m][n]);
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

	private static int[][] crearCopia(int[][] src) {
	    int length = src.length;
	    int[][] target = new int[length][src[0].length];
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(src[i], 0, target[i], 0, src[i].length);
	    }
	    return target;
	}

	private static int getOccurrences(char character, int[][] profile, int i, int idxLetter) {
		int ocurrences = 0;
		switch(character) 
		{ 
		    case 'A': 
		    	ocurrences = profile[A][i];
		    	profile[A][i]++;
		    case 'C': 
		    	ocurrences = profile[C][i];
		    	profile[C][i]++;
		    case 'G': 
		    	ocurrences = profile[G][i];
		    	profile[G][i]++;
		    case 'T': 
		    	ocurrences = profile[T][i];
		    	profile[T][i]++;
		    default:// "-" 
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
