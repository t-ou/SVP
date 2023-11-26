package main.java.demo;
import java.util.ArrayList;
import java.util.HashMap;
public class SubjectData {
	//
	private int classNumber; 
	private int testCaseNumber;
	HashMap<String,Integer> className;
	HashMap<Integer,ClassData> classLocator;
	public ArrayList<Boolean> isPass;
	//
	public SubjectData() {
		classNumber = 0; 
		className = new HashMap<String,Integer>();
		classLocator = new HashMap<Integer,ClassData>();
		isPass = new ArrayList<Boolean>();
		testCaseNumber = 0;
	}
	//
	public int getclassNumber() {
		return classNumber;
	}
	public int gettestCaseNumber() {
		return testCaseNumber;
	}
	public void addtestCaseNumber() {
		testCaseNumber++;
	}
	public ClassData addNewClassData(String name) {  //根据类名，创建类文件数据并自动编号
		className.put(name, classNumber);
		ClassData classData = new ClassData(classNumber);
		classLocator.put(classNumber, classData);
		classNumber++;
		return classData;
	}
	public void SBFLCalculate(int line) {
        int a = this.classLocator.get(0).LineData.get(line).failCovered;
        int b = this.classLocator.get(0).LineData.get(line).passCovered;
        int c = this.classLocator.get(0).LineData.get(line).failMissed;
        int d = this.classLocator.get(0).LineData.get(line).passMissed;
        System.out.println("Line: " +  line + " ,fail Covered: "
        + a + " ,pass Covered: " + b + " ,failMissed: " + c + " ,passMissed: " + d +
        " Ochiai rate = " + (a/Math.sqrt((a + b) * (a + c))) );
	}
}
