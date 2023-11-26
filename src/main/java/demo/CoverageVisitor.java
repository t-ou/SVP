package main.java.demo;

import org.jacoco.core.analysis.IClassCoverage;
import org.jacoco.core.analysis.ICoverageVisitor;

public class CoverageVisitor implements ICoverageVisitor {
	SubjectData subjectData;
	ClassData classData;

    @Override
    public void visitCoverage(IClassCoverage coverage) {
        // 这里处理每个类的覆盖率信息
        // 打印类名和行覆盖率信息
    	//建立类名和类编号的映射和类与行的映射
        //如果类现存，就调取，没有就后面加
        if(subjectData.className.containsKey(coverage.getName())) {
        	classData = subjectData.classLocator.get(subjectData.className.get(coverage.getName()));
        	//System.out.println("Class name: " + coverage.getName() + " Class number: " + classData.getClassNumber());
        }else {
        	classData =  subjectData.addNewClassData(coverage.getName());
        	//System.out.println("Class name: " + coverage.getName() + " Class number: " + classData.getClassNumber());
        }
        
        for (int i = coverage.getFirstLine(); i <= coverage.getLastLine(); i++) {
            // 获取每一行的覆盖信息
            //System.out.println("Line " + i + ": " + coverage.getLine(i).getStatus());
            //按照通过情况汇报数据
            updateLineData(i, coverage.getLine(i).getStatus());
        }
        System.out.println("visitCoverage finish");
    }
    //
    public CoverageVisitor(SubjectData subjectData) {
    	this.subjectData = subjectData;
    	System.out.println("CoverageVisitor Created, now testCaseNumber: " + subjectData.gettestCaseNumber());
    	
    }
    
    private void updateLineData(int lineNumber, int count) {
    	if(count == 0) {
    		if(classData.LineData.containsKey(lineNumber)) {
        		//行数据存在
        		if(subjectData.isPass.get(subjectData.gettestCaseNumber())) {
        			classData.LineData.get(lineNumber).addPassMissed(count);
            		//System.out.println("update success: exist, pass, Line: " + lineNumber);
        		}else {
        			classData.LineData.get(lineNumber).addFailMissed(count);
        			//System.out.println("update success: exist, fail, Line: " + lineNumber);
        		}
        		
        	}else {
        		//行数据不存在
        		classData.LineData.put(lineNumber, new SpectrumData());
        		if(subjectData.isPass.get(subjectData.gettestCaseNumber())) {
        			classData.LineData.get(lineNumber).addPassMissed(count);
            		//System.out.println("update success: exist, pass, Line: " + lineNumber);
        		}else {
        			classData.LineData.get(lineNumber).addFailMissed(count);
        			//System.out.println("update success: exist, fail, Line: " + lineNumber);
        		}
        	}
    	}else {
    		if(classData.LineData.containsKey(lineNumber)) {
        		//行数据存在
        		if(subjectData.isPass.get(subjectData.gettestCaseNumber())) {
        			classData.LineData.get(lineNumber).addPassCovered(count);
            		//System.out.println("update success: exist, pass, Line: " + lineNumber);
        		}else {
        			classData.LineData.get(lineNumber).addFailCovered(count);
        			//System.out.println("update success: exist, fail, Line: " + lineNumber);
        		}
        		
        	}else {
        		//行数据不存在
        		classData.LineData.put(lineNumber, new SpectrumData());
        		if(subjectData.isPass.get(subjectData.gettestCaseNumber())) {
        			classData.LineData.get(lineNumber).addPassCovered(count);
            		//System.out.println("update success: exist, pass, Line: " + lineNumber);
        		}else {
        			classData.LineData.get(lineNumber).addFailCovered(count);
        			//System.out.println("update success: exist, fail, Line: " + lineNumber);
        		}
        	}
    	}
    }
}

