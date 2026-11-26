package api.utilities;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportManager implements ITestListener{


    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
//    public ExtentTest test;
    private ThreadLocal<ExtentTest> test =  new ThreadLocal<>();


    String reportName;
   
    public void onStart(ITestContext context) {

        String timeStamp = new SimpleDateFormat( "yyyy.MM.dd.HH.mm.ss").format(new Date());

        reportName = "TestReport-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter( ".\\reports\\" + reportName);

        sparkReporter.config().setDocumentTitle( "REST Assured Automation Report");

        sparkReporter.config().setReportName( "User API Automation Testing");

        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();

        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "User API");
        extent.setSystemInfo("Module", "User Management");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Framework", "REST Assured");
        extent.setSystemInfo("Tester", "Akash Mittal");

        extent.setSystemInfo("Operating System", System.getProperty("os.name"));

        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

    public void onTestStart(ITestResult result) {

    	ExtentTest extentTest = extent.createTest( result.getMethod().getMethodName());

        test.set(extentTest);

        test.get().assignCategory(
                result.getTestClass().getName());

        test.get().log(Status.INFO,
                "Test execution started");

        if (result.getParameters().length > 0) {

            test.get().info("Data-driven test execution");
        }
    }

    public void onTestSuccess(ITestResult result) {

        test.get().log(Status.PASS,
                "Test case PASSED");

        test.remove();
    }

    
    public void onTestFailure(ITestResult result) {

        test.get().log(Status.FAIL,
                "Test case FAILED");

        test.get().fail(result.getThrowable());

        test.remove();
    }

    public void onTestSkipped(ITestResult result) {

        if (test.get() == null) {

            test.set(extent.createTest(
                    result.getMethod().getMethodName()));
        }

        test.get().log(Status.SKIP,
                "Test case SKIPPED");

        if (result.getThrowable() != null) {

            test.get().skip(result.getThrowable());
        }

        test.remove();
    }

    
    public void onFinish(ITestContext context) {

        if (extent != null) {

            extent.flush();
        }
    }
}

