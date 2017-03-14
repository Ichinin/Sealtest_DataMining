package traceclustering;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import ca.uqac.lif.ecp.Trace;
import ca.uqac.lif.ecp.atomic.AtomicEvent;
import ca.uqac.lif.ecp.atomic.AtomicTrace;
import ca.uqac.lif.ecp.ltl.AtomicParserBuilder;
import ca.uqac.lif.ecp.ltl.Operator;
import ca.uqac.lif.ecp.ltl.OperatorBuilder.BuildException;

public class RandomTraceGenerator {

	private static PrintWriter sauvegarde; 
	/*
	 * Generation aleatoire d'une trace. 
	 * Un evenement atomique est un evenement caracterise par une lettre de l'alphabet. Possibilite de generer des traces de tailles variable
	 */
	public static void GenerateTrace(Trace<AtomicEvent> trace, int length){
		Random r = new Random();
		
		for (int j = 0; j < length; j++) {
			char atom = (char)(r.nextInt(26) + 'a');
			trace.add(new AtomicEvent(Character.toString(atom)));
		}

		
	}
	
	
	/*
	 * On effectue une evaluation aleatoire de chaque trace. 
	 * Note : la finalite est devaluer chaque trace selon une formule via TragingFunction()
	 */
	
	public static List<Object> evaluateTrace(Trace<AtomicEvent> trace){
		
		List<Object> evaluatedTrace = new ArrayList<Object>();
		evaluatedTrace.add(trace);
		
		double rand = Math.random();
		
		if(rand<0.5){
			evaluatedTrace.add(0);
		}else{
			evaluatedTrace.add(1);
		}
		
		return evaluatedTrace;
	}
	
	public static void saveTrace(List<Object> evaluatedTrace, PrintWriter txt){
		
		Trace<AtomicEvent> trace = (Trace<AtomicEvent>) evaluatedTrace.get(0);
		
		String str = "";
		//Ajout de la trace à la chaine de caractère
		
		for (AtomicEvent atomicEvent : trace) {
			str+= atomicEvent.toString();
		}
		
		str += ",";
		
		str += evaluatedTrace.get(1).toString();
		str += ".";
		//String str = evaluatedTrace.get(0).toString() + ", " + evaluatedTrace.get(1).toString(); 
		
		txt.println(str);
		
		
	}
	public void TriagingFunction(Operator<AtomicEvent> operator,String LTLFormula ){
		
		AtomicParserBuilder parser =  new AtomicParserBuilder(LTLFormula);
		try {
			operator = parser.build();
		} catch (BuildException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args){
		
		try {
			sauvegarde = new PrintWriter(new FileWriter("traces.csv", true));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Trace<AtomicEvent> trace1, trace2, trace3;
		 trace1= new AtomicTrace();
		 trace2= new AtomicTrace();
		 trace3= new AtomicTrace();
		
		GenerateTrace(trace1, 15);
		GenerateTrace(trace2, 15);
		GenerateTrace(trace3, 15);
		
		//trace1.toString();
		
		
		List<Object> etrace1 = evaluateTrace(trace1);
		List<Object> etrace2 = evaluateTrace(trace2);
		List<Object> etrace3 = evaluateTrace(trace3);
		
		saveTrace(etrace1, sauvegarde);
		saveTrace(etrace2, sauvegarde);
		saveTrace(etrace3, sauvegarde);
		
		sauvegarde.close();
		
	}
}
