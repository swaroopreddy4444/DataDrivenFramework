package ExtentListeners;
import java.util.Arrays;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentListener1 implements ITestListener {
	
	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
	private static ExtentReports extent = ExtentManager1.createInstance(".\\reports\\"+fileName);
	public static ExtentTest test;

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName() + " @TestCase: " + result.getMethod().getMethodName());
	}
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:-" + methodName + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		test.pass(m);
	}
	public void onTestFailure(ITestResult result) {
		String except_msg = Arrays.toString(result.getThrowable().getStackTrace());
		test.fail(except_msg);
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:-" + methodName + " FAILED" + "</b>";
		
		String screenshot ="..\\screenshots\\shot1.png";
		test.fail("<b><font color=red>"+"Screenshot of Failure"+"</font></b><br>",	
				MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());	
//		test.addScreenCaptureFromPath(screenshot);
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		test.log(Status.FAIL, m);	
	}
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:-" + methodName + " SKIPPED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
		test.skip(m);
	}
	public void onFinish(ITestContext context) {
		if(extent!=null)
			extent.flush();
	}
}
