import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class MultipleSequence {
	
	private static int nFirst = 3;
	
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
	    String gene3 = "AGGCAT"; 
	    String gene4 = "AGAGCA"; 
//	    String gene5 = "AGTGCAT"; 
//	    String gene6 = "AGGCA"; 
//	    String gene7 = "AGCAT"; 
//	    String gene8 = "AGGTCA"; 
//	    String gene9 = "TAGGCAT"; 
	    List<String> genes = new ArrayList<String>();
	    genes.add(gene1);
	    genes.add(gene2);
	    genes.add(gene3);
	    genes.add(gene4);
//	    genes.add(gene5);
//	    genes.add(gene6);
//	    genes.add(gene7);
//	    genes.add(gene8);
//	    genes.add(gene9);
	    
	    
	    
	    // calling the function to 
	    // calculate the result 
	    List<String[]> tuples = Permutacion.permWrap(genes);
	    System.out.println("tamaño de tuplas eses" + tuples.size());
	    List<QualifiedSequence> orderedSequences = new ArrayList<QualifiedSequence>(); 
	    for(String[] tuple : tuples) {
	    	System.out.println(tuple[0] + "-" + tuple[1]);
	    	orderedSequences.add(getQualifiedSequence(tuple, genes));
	    }
	    Collections.sort(orderedSequences, new Comparator<QualifiedSequence>(){
            public int compare(QualifiedSequence s1,QualifiedSequence s2){
				return s1.getValue() - s2.getValue();
                // Write your logic here.
          }});   
	    
	    System.out.println("asdasdasd");
	    System.out.println("tamaño es" + orderedSequences.size());
	    for(QualifiedSequence q : orderedSequences) {
	    	System.out.println(q.getValue());
	    }
	    QualifiedSequence selectedSequence = getRandom(orderedSequences);
	}

	private static QualifiedSequence getRandom(List<QualifiedSequence> orderedSequences) {
		List<QualifiedSequence> sublist = orderedSequences.subList(0, nFirst);
		double randomDouble = Math.random();
		randomDouble = randomDouble * nFirst;
		int randomInt = (int) randomDouble;
		return sublist.get(randomInt);
	}

	private static QualifiedSequence getQualifiedSequence(String[] tuple, List<String> genes) {
		String gene1 = genes.get(Integer.valueOf(tuple[0]));
		String gene2 = genes.get(Integer.valueOf(tuple[1]));
		// intialsing penalties 
	    // of different types 
	    int misMatchPenalty = 3; 
	    int gapPenalty = 2; 
		int value = TwoSequence.getMinimumPenalty(gene1, gene2,  
		        misMatchPenalty, gapPenalty); 
		return new QualifiedSequence(value, tuple);
	}

}
