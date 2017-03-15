package traceclustering;

import weka.core.converters.CSVLoader;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

import weka.core.converters.ArffLoader;
import weka.core.converters.CSVSaver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Classeur {

	
	private static String CSVfile = "traces.csv";
	private static String ArffFile = "traces_1000.arff";

	public static Instances LoadARFF(String ArffFile) throws Exception{
		ArffLoader loader = new ArffLoader();
		loader.setSource(new File(ArffFile));
		Instances data = loader.getDataSet();
		
		return data;
		
	}
	
	public static void saveCSV(String CSVFile, Instances data) throws Exception{
		CSVSaver saver = new CSVSaver();
		saver.setInstances(data);
		saver.setFile(new File(CSVFile));
		
		saver.writeBatch();
	}
	
	public static Instances LoadCSV(String CSVfile) throws Exception{
		CSVLoader loader = new CSVLoader();
		
		loader.setSource(new File(CSVfile));
		
		String[] options = new String[1];
		options[0] = "-O";
		
		loader.setOptions(options);
		
		//Le fichier est chargé
		Instances data = loader.getDataSet();
		
		return data;
	}
	
	public static void SaveARFF(String ArffFile, Instances data) throws IOException{
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(ArffFile));
		writer.write(data.toString());
		writer.flush();
		writer.close();
	}
	

	public static void main(String[] args) throws Exception{
		
		Instances data = LoadCSV(CSVfile);
		//System.out.println(data.toString());
		SaveARFF(ArffFile, data);
		
		/*Instances data = LoadARFF(ArffFile);
		System.out.println(data.toString());*/
		
		/*Instances data = LoadARFF("essai.arff"); 
		saveCSV("sortie.csv", data);*/
	}	
	
}


