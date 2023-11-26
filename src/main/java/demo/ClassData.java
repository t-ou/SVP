package main.java.demo;

import java.util.HashMap;

public class ClassData {
	//变量
	public HashMap<Integer,SpectrumData> LineData;
	private int classNumber;
	//方法
	public ClassData(int classNumber) {
		LineData = new HashMap<Integer,SpectrumData>();
		this.classNumber = classNumber;
	}
	
	public int getClassNumber() {
		return classNumber;
	}
}
