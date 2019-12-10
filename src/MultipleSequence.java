import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MultipleSequence {
	
	private static int[][] profile;
	private static int GAP = 0;
	private static int A = 1;
	private static int C = 2;
	private static int G = 3;
	private static int T = 4;
	
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
	    //TODO: setear profile y armarlo
  		//seteo el primer profile entre las dos secuencias		
  		setFirstProfile(genes, selectedSequence);
  		System.out.println("el profile");
  		printMatrix(profile);
	}

	//Setea el profile entre las dos secuencias
	private static void setFirstProfile(List<String> genes, QualifiedSequence qualifiedSequence) {
		String sequence1 = genes.get(Integer.valueOf(qualifiedSequence.getSequences()[0]));
		String sequence2 = genes.get(Integer.valueOf(qualifiedSequence.getSequences()[1]));
		applyToProfileFirstSequence(sequence1);
		printMatrix(profile);
		applyToProfile(sequence2);
		printMatrix(profile);
	}

	private static void applyToProfileFirstSequence(String sequence) {
		profile = new int[5][sequence.length()];
		for(int i = 0; i < sequence.length(); i++) {
			sumToProfile(sequence, i); 
		}
	}

	private static void sumToProfile(String sequence, int i) {
		System.out.println(sequence);
		switch(sequence.charAt(i)) 
		{ 
		    case 'A': 
		        profile[A][i] += 1; 
		        break; 
		    case 'C': 
		    	profile[C][i] += 1; 
		        break; 
		    case 'G': 
		    	profile[G][i] += 1;  
		        break; 
		    case 'T': 
		    	profile[T][i] += 1; 
		        break; 
		    default:// "-" 
		    	profile[GAP][i] += 1;  
		}
	}

	private static void applyToProfile(String sequence) {
		if(sequence.length() > profile[0].length) {//verifico si necesito mas espacio
			for(int i = 0; i < 5; i++) {
				profile[i] = Arrays.copyOf(profile[i], sequence.length());
			}
			printMatrix(profile);
		}
		for(int i = 0; i < sequence.length(); i++) {
			sumToProfile(sequence, i); 
		}
	}

	// elige aleatoriamente uno de los mejores n profiles
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
		Alignment alignment = TwoSequence.getMinimumPenalty(gene1, gene2,  
		        misMatchPenalty, gapPenalty);
		QualifiedSequence qualifiedSequence = new QualifiedSequence(alignment.getPenalty(), tuple, alignment.getAlignment());
		return qualifiedSequence;
	}

}
