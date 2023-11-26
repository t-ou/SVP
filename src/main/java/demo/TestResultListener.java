package main.java.demo;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;
import org.jacoco.core.data.ExecutionDataStore;
import org.jacoco.core.data.SessionInfoStore;
import org.jacoco.core.runtime.LoggerRuntime;
import org.jacoco.core.runtime.RuntimeData;

public class TestResultListener implements TestExecutionListener {
	private SubjectData subjectData;
	private String classesDirectory;
	private String sourceDirectory;
	private RuntimeData runtimeData;
	private LoggerRuntime runtime;
	
	@Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        System.out.println("Test Plan Execution Started.");
        //测试整体开始
        try {
			initialize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        System.out.println("Test Plan Execution Finished.");
        //测试整体结束
        runtime.shutdown();
        subjectData.SBFLCalculate(22);
        for(int i = 0; i < subjectData.isPass.size(); i++) {
        	System.out.print(i + ": " + subjectData.isPass.get(i) + " ");
        }
    }
	
	@Override
	public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
	    if (testExecutionResult.getStatus() == TestExecutionResult.Status.SUCCESSFUL) {
	        // 测试通过
	    	System.out.println(testIdentifier.getDisplayName() + " test pass");
	    	subjectData.isPass.add(true);
	    } else if (testExecutionResult.getStatus() == TestExecutionResult.Status.FAILED) {
	        // 测试失败
	    	System.out.println(testIdentifier.getDisplayName() + " test fail");
	    	subjectData.isPass.add(false);
	    } else {
	    	System.out.println(testIdentifier.getDisplayName() + " test error");
	    }
	    // 从 JaCoCo 代理获取执行数据
        // 将原始数据加载到 ExecutionDataStore
        ExecutionDataStore executionDataStore = new ExecutionDataStore();
        SessionInfoStore sessionInfoStore = new SessionInfoStore();
        // 从 RuntimeData 中提取执行数据，
        //Collects the current execution data and writes it to the given IExecutionDataVisitor object.
        //也许这步就可以直接重置执行数据
        runtimeData.collect(executionDataStore, sessionInfoStore, false);
	    //启动exec分析器，Merge数据
	    //CoverageAnalyzer构造器想来应该再包含一个地址，用于读文件
	    CoverageAnalyzer coverageAnalyzer = new CoverageAnalyzer(
	    		subjectData, classesDirectory, sourceDirectory);
	    coverageAnalyzer.analyze(executionDataStore);
	    subjectData.addtestCaseNumber();
	}
	
	//单个测试开始
	@Override
	public void executionStarted(TestIdentifier testIdentifier) {
		System.out.println(testIdentifier.getDisplayName() + " test start");
		//重置执行数据
		if (testIdentifier.isTest()) {
			runtimeData.reset();
		}
	}
	
	//尝试改为无参构造函数
	 public TestResultListener(){
		 System.out.println("TestResultListener is loaded");
	 }
	 
	 public void initialize() throws Exception {
	     // 将原本构造函数中的逻辑移至初始化方法
		 System.out.println("TestResultListener is initialized");
	     classesDirectory = "bin/quicksort/Solution.class";
	     sourceDirectory = "testobject/quicksort/quicksortresult";
	     subjectData = new SubjectData();
	     runtimeData = new RuntimeData();
	     runtime = new LoggerRuntime();
	     runtime.startup(runtimeData);
	 }
	 

}
