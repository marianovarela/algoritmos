// Java program to implement  
// sequence alignment problem. 
import java.io.*; 
import java.util.*; 
import java.lang.*; 

// esta clase la saque de un ejemplo y la fui retocando a demanda según lo necesario
class TwoSequence 
{ 
// function to find out  
// the minimum penalty 
static Alignment getMinimumPenalty(String x, String y,  
                              int pxy, int pgap) 
{ 
    int i, j; // intialising variables 
      
    int m = x.length(); // length of gene1 
    int n = y.length(); // length of gene2 
      
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
	
    // calcuting the  
    // minimum penalty 
    for (i = 1; i <= m; i++) 
    { 
        for (j = 1; j <= n; j++) 
        { 
            if (x.charAt(i - 1) == y.charAt(j - 1)) 
            { 
                dp[i][j] = dp[i - 1][j - 1]; 
            } 
            else
            { 
                dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1] + pxy ,  
                                             dp[i - 1][j] + pgap) ,  
                                             dp[i][j - 1] + pgap ); 
            } 
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
      
    while ( !(i == 0 || j == 0)) 
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
//    return dp[m][n]; 
    return new Alignment(alignmentOne, alignmentTwo, dp[m][n]);
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
  
// Driver code 
public static void main(String[] args) 
{ 
    // input strings 
    String gene1 = "AGGGCT"; 
    String gene2 = "AGGCA"; 
      
    // intialsing penalties 
    // of different types 
    int misMatchPenalty = 3; 
    int gapPenalty = 2; 
  
    // calling the function to 
    // calculate the result 
    Alignment min = getMinimumPenalty(gene1, gene2,  
        misMatchPenalty, gapPenalty);
    
    System.out.println();
    System.out.println(min.getPenalty());
} 
} 