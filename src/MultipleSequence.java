import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MultipleSequence {
	
	private static int[][] profile;
	//posiciones sobre el profile por letra
	private static int GAP = 0;
	private static int A = 1;
	private static int C = 2;
	private static int G = 3;
	private static int T = 4;
	
	private static int limit = 1;
	private static int misMatchPenalty = 3; 
	private static int gapPenalty = 2; 
	
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
	  
	private static void printVecino(List<String> newSequence) {
		for(String sequence: newSequence) {
			System.out.println(sequence);
		}
	}
	
	// Driver code 
	public static void main(String[] args) 
	{ 
		//test de generacion de vecinos  y local search
//		List<String> sequencias = new ArrayList<String>();
//		sequencias.add("A__T");
//		sequencias.add("AG_T");
//		sequencias.add("T_GT");
//		localSearch(sequencias);
//		List<Vecino> vecinoss = getVecinosPosibles(sequencias);
		
		
		//test de score por columna
		/**List<String> columnas = new ArrayList<String>();
		columnas.add("A");
		columnas.add("C");
		columnas.add("A");
		columnas.add("_");
		getScoreColumn(columnas);
		**/
		
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
	    //GRASP
	    grasp(orderedSequences, genes);
//	    QualifiedSequence selectedSequence = getRandom(orderedSequences);
//	    //TODO: setear profile y armarlo
//  		//seteo el primer profile entre las dos secuencias		
//  		setFirstProfile(genes, selectedSequence);
//  		System.out.println("el profile");
//  		printMatrix(profile);
	}

	private static void grasp(List<QualifiedSequence> orderedSequences, List<String> genes) {
		int currentScore = 0; // o infinito +/-
		for(int i = 0; i < limit; i++) {
			QualifiedSequence selectedSequence = getRandom(orderedSequences);
		    //TODO: setear profile y armarlo
	  		//seteo el primer profile entre las dos secuencias		
	  		setFirstProfile(genes, selectedSequence);
	  		System.out.println("el profile");
	  		printMatrix(profile);
	  		//TODO: generar todas las alineaciones con los demas secuencias vs el profile
	  		System.out.println("Llamada inicial a local search");
	  		List<String> result = localSearch(new ArrayList<String>());
	  		int score = score(result);
	  		if(score < currentScore) {
	  			currentScore = score;
	  			// guardar mejor alineamiento general. todas las instancias
	  		}
		}
		// mostrar todas las instancias
		
	}
	
	// obtiene el score de una secuencia
	private static int score(List<String> result) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static List<String> localSearch(List<String> sequences) {
		List<Vecino> vecinos = getVecinosPosibles(sequences);
		for(Vecino vecino : vecinos){
			List<String> newSequence = scoreVecino(sequences, vecino);
			if(newSequence != null) {//si es mejor
				System.out.println("Encontro un mejor vecino");
				printVecino(newSequence);
				return localSearch(vecino.getVecino());
			}else {
				System.out.println("No encontro un mejor vecino");
			}
		}
		// si recorri todos y no mejoraron retorno sequences
		return sequences;
	}

	// calcula el mejor score entre el original y el vecino
	private static List<String> scoreVecino(List<String> originalSequences, Vecino vecino) {
		int idxFirstColumn = vecino.getFirstColumn();
		int idxSecondColumn = vecino.getSecondColumn();
		
		List<String> originalFirstColumn = getColumn(idxFirstColumn, originalSequences); 
		List<String> originalSecondColumn = getColumn(idxSecondColumn, originalSequences);
		List<String> firstColumn = getColumn(idxFirstColumn, vecino.getVecino());
		List<String> secondColumn = getColumn(idxSecondColumn, vecino.getVecino());
		
		int originalScore = getScoreColumn(originalFirstColumn) + getScoreColumn(originalSecondColumn);
		int newScore = getScoreColumn(firstColumn) + getScoreColumn(secondColumn);
		
		if(newScore < originalScore) {
			return vecino.getVecino();
		}
		
		return null;
	}

	public static int getScoreColumn(List<String> column) {
		List<String[]> permutaciones = Permutacion.permWrap(column);
		int score = 0;
		for(String[] tupla : permutaciones) {
			System.out.println(tupla[0] + "-" + tupla[1]);
			//en esta version solo me enfoque en que sean distintos los caracteres añadiendo el mismatchPenalty
			if(!column.get(Integer.valueOf(tupla[0])).equals(column.get(Integer.valueOf(tupla[1])))) {
				score += misMatchPenalty;
			}
		}
		
		return score;
	}

	private static List<String> getColumn(int idxFirstColumn, List<String> sequences) {
		List<String> column = new ArrayList<String>();
		for(String sequence : sequences) {
			String character = String.valueOf(sequence.charAt(idxFirstColumn));
			column.add(character);
		}
		return column;
	}

	private static List<Vecino> getVecinosPosibles(List<String> sequences) {
		List<String> originalSequences = sequences;
		List<Vecino> vecinos = new ArrayList<Vecino>();
		for(int i = 0; i < sequences.size(); i++) {
			String originalSeq = sequences.get(i);
			for(int idx = 0; idx < sequences.get(i).length(); idx++) {
				if(originalSeq.charAt(idx) == '_') {
					List<SequenceVecina> posibles = new ArrayList<SequenceVecina>();
					if(idx > 0) {
						StringBuilder sb = new StringBuilder(originalSeq);
						char toReplace = originalSeq.charAt(idx-1);
						if(toReplace != '_') {
							sb.setCharAt(idx-1, '_');
							sb.setCharAt(idx, toReplace);
							String seq = sb.toString();
							posibles.add(new SequenceVecina(seq, idx-1, idx));
						}
					}
					if(idx < originalSeq.length() - 1){
						String newSeq = sequences.get(i);
						StringBuilder sb = new StringBuilder(newSeq);
						char toReplace = newSeq.charAt(idx+1);
						if(toReplace != '_') {
							sb.setCharAt(idx+1, '_');
							sb.setCharAt(idx, toReplace);
							String seq = sb.toString();
							posibles.add(new SequenceVecina(seq, idx+1, idx));
						}
					}
					for(SequenceVecina posible : posibles) {
						vecinos.add(rearmar(posible, i, originalSequences));
					}
				}
			}
		}
		return vecinos;
	}

	private static Vecino rearmar(SequenceVecina posible, int i, List<String> sequences) {
		List<String> vecino = new ArrayList<String>();
		
		for(int idx = 0; idx < sequences.size();  idx++) {
			if(i == idx) {
				vecino.add(posible.getVecino());
			}else {
				vecino.add(sequences.get(idx));
			}
		}
		// me guardo las columnas que tengo que comparar despues
		return new Vecino(vecino, posible.getFirstColumn(), posible.getSecondColumn()); 
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
		Alignment alignment = TwoSequence.getMinimumPenalty(gene1, gene2,  
		        misMatchPenalty, gapPenalty);
		QualifiedSequence qualifiedSequence = new QualifiedSequence(alignment.getPenalty(), tuple, alignment.getAlignment());
		return qualifiedSequence;
	}

}
