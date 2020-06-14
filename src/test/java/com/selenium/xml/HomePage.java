package com.selenium.xml;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.selenium.utils.TestUtils;

import atu.testrecorder.exceptions.ATUTestRecorderException;

public class HomePage extends TestUtils {
	public HomePage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test(priority = 1, groups = { "ETE" })
	public static void getUrlTest(Method method) throws IOException, ATUTestRecorderException {

		System.out.println("it will execute second");
		String actual = driver.getCurrentUrl();
		String expected = "https://www.facebook.com";
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(expected, actual, "failed");
		System.out.println("it will execute in any case");

	}

	@Test(priority = 2, groups = { "Sanity", "ETE" })
	public static void checkButtonTest(Method method) throws IOException, ATUTestRecorderException {
		System.out.println("i will execute last");
		WebElement connect = driver.findElement(By.xpath("//input[@value='Connexion']"));
		boolean actual = connect.isDisplayed();

		Assert.assertTrue(actual, "logo is not display");

	}

	@Parameters({ "URL" })
	@BeforeMethod(groups = { "ETE" })
	public static void startMethodTest(String URL, Method method) throws IOException, ATUTestRecorderException {
		System.out.println("it will run befor each method of the class itself");
		extentTest = extentreports.startTest(method.getName());
		initilzation(URL);
		TestUtils.takeVideo(method.getName());
		TestUtils.takePicture(method.getName());
	}

	@AfterMethod(groups = { "ETE" })
	public static void endMethodTest(Method method, ITestResult iTestResult)
			throws ATUTestRecorderException, IOException {
		System.out.println("i'm after method: it will run after each method of the class itself");
		String status = Integer.toBinaryString(iTestResult.getStatus());
		// TestUtils.takeVideo(method.getName());
		// TestUtils.takePicture(method.getName());
		if (iTestResult.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "test pass with success");
			extentTest.log(LogStatus.PASS, status);
			extentTest.log(LogStatus.PASS, "<a href='D:\\Training-WS\\selenium-testNg-part4\\records\\"
					+ method.getName() + ".mov" + "'><span class='lable info'>Download Video</span></a>");
			extentTest.log(LogStatus.PASS, "<a href='D:\\Training-WS\\selenium-testNg-part4\\screenShots\\"
					+ method.getName() + ".png" + "'><span class='lable info'>Download Picture</span></a>");
		} else if (iTestResult.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "test failed verify your code!!!");
			extentTest.log(LogStatus.PASS, status);
			extentTest.log(LogStatus.PASS, "<a href='D:\\Training-WS\\selenium-testNg-part4\\records\\"
					+ method.getName() + ".mov" + "'><span class='lable info'>Download Video</span></a>");
			extentTest.log(LogStatus.PASS, "<a href='D:\\Training-WS\\selenium-testNg-part4\\screenShots\\"
					+ method.getName() + ".png" + "'><span class='lable info'>Download Picture</span></a>");
		} else {
			extentTest.log(LogStatus.SKIP, "try again");
			extentTest.log(LogStatus.PASS, status);
			extentTest.log(LogStatus.PASS, "<a href='D:\\Training-WS\\selenium-testNg-part4\\records\\"
					+ method.getName() + ".mov" + "'><span class='lable info'>Download Video</span></a>");
			extentTest.log(LogStatus.PASS, "<a href='D:\\Training-WS\\selenium-testNg-part4\\screenShots\\"
					+ method.getName() + ".png" + "'><span class='lable info'>Download Picture</span></a>");
		}
		driver.quit();
	}

}
