package main.java.demo;

public class SpectrumData {
	//变量
	int passCovered;  //通过的测试用例中覆盖了的次数
	int passMissed;  //通过的测试用例中没覆盖的次数
	int failCovered;  //失败的测试用例中覆盖了的次数
	int failMissed;  //失败的测试用例中没覆盖的次数
	//int classNumber;  //所属的class文件的编号（为了便于索引）
	//方法
	public SpectrumData() {
		this.passCovered = 0;
		this.passMissed = 0;
		this.failCovered = 0;
		this.failMissed = 0;
		//this.classNumber = classNumber;
	}
	//以下按照需要可以考虑改成++
	public void addPassCovered(int a) {
		passCovered += a;
	}
	public void addPassMissed(int a) {
		passMissed += a;
	}
	public void addFailCovered(int a) {
		failCovered += a;
	}
	public void addFailMissed(int a) {
		failMissed += a;
	}
}
