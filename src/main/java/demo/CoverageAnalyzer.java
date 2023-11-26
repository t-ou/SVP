package main.java.demo;

import java.io.File;
import java.io.IOException;
import org.jacoco.core.analysis.Analyzer;
import org.jacoco.core.data.ExecutionDataStore;

public class CoverageAnalyzer {
	SubjectData subjectData;
	String classesDirectory;
	String sourceDirectory;
	
	public CoverageAnalyzer(SubjectData subjectData, String classesDirectory, String sourceDirectory) {
		this.subjectData = subjectData;
		this.classesDirectory = classesDirectory;
		this.sourceDirectory = sourceDirectory; //used to output report;
	}
	
	public void analyze(ExecutionDataStore executionDataStore) {
		Analyzer analyzer = new Analyzer(executionDataStore, new CoverageVisitor(subjectData));
	    try {
			analyzer.analyzeAll(new File(classesDirectory)); //这行会直接分析所有class文件
			System.out.println("analysis finish");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //System.out.println("end");
	}
}
