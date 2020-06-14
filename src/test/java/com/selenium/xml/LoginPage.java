package com.selenium.xml;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.utils.TestUtils;

import atu.testrecorder.exceptions.ATUTestRecorderException;

public class LoginPage extends TestUtils {
	public LoginPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test(groups = { "ETE" }, dataProvider = "fromdata")
	public static void loginTest(String emails, String passw, Method method)
			throws IOException, ATUTestRecorderException {

		System.out.println("username: " + emails + " password:" + passw);
		WebElement username = driver.findElement(By.id("email"));
		WebElement password = driver.findElement(By.id("pass"));
		WebElement connect = driver.findElement(By.xpath("//input[@value='Connexion']"));
		username.sendKeys(emails);
		password.sendKeys(passw);
		connect.click();
		TestUtils.takeVideo(method.getName());
		TestUtils.takePicture(method.getName());

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		String actual = driver.getCurrentUrl();
		String expected = "https://www.facebook.com/";

		assertEquals(actual, expected, "not equals!!");

	}

	@DataProvider(name = "fromdata")
	public Object[][] Mydata() {
		Object[][] myData = new Object[1][2];
		/*
		 * I want to transmit two data of different value.
		 * 
		 * 1- correct username and password
		 * 
		 */

		myData[0][0] = "sselenium674@gmail.com";
		myData[0][1] = "Selenium01";

		/*
		 * 2- false username/password
		 */
		/*
		 * System.out.println(myData.toString()); myData[1][0] = "sselenium@gmail.com";
		 * myData[1][1] = "Selenium";
		 */
		return myData;
	}

	@Parameters({ "URL" })
	@BeforeMethod(groups = { "ETE" })
	public static void startMethodTest(Method method, String url) throws IOException, ATUTestRecorderException {
		System.out.println("i'm before method: it will run befor each method of the class itself");
		extentTest = extentreports.startTest(method.getName());
		initilzation(url);

		// System.out.println("it will read the url form xml file!!: " + URL);
	}

	@AfterMethod // (groups = { "ETE" })
	public static void endMethodTest(Method method, ITestResult iTestResult)
			throws ATUTestRecorderException, IOException {
		System.out.println("i'm after method: it will run after each method of the class itself");
		String status = Integer.toBinaryString(iTestResult.getStatus());
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
		} else {
			extentTest.log(LogStatus.SKIP, "try again");
			extentTest.log(LogStatus.PASS, status);
		}
		driver.quit();
		recorder.stop();
	}

	@BeforeClass
	public static void BeforClass() {
		System.out.println("i'm before class: it will execute one time bofore all Methods test");
	}

	@AfterClass
	public static void AfterClass() {
		System.out.println("i'm after class: it will execute one time After all Methods test");
	}

	@BeforeTest
	public static void BeforTest() {
		System.out.println("i'm bore test: it will execute befor any test");

	}

	@AfterTest
	public static void AfterTest() {

	}

	@BeforeSuite(alwaysRun = true, enabled = true)
	public static void BeforSuite() throws ATUTestRecorderException {
		System.out.println("i'm before suite: it will execute bofore all tests");
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		Date date = new Date();
		extentreports = new ExtentReports("D:\\Training-WS\\selenium-testNg-part4\\reports\\testReport.html", true);

		// you can add into report any infos
		extentreports.addSystemInfo("OS", "Windows7");
		extentreports.addSystemInfo("Langage", "JAVA version 8");
		extentreports.addSystemInfo("Tester", "Abdessalem");
		extentreports.addSystemInfo("Entreprise", "Freelancer");
		extentreports.addSystemInfo("Entreprise", "Freelancer");
		extentreports.addSystemInfo("Date", dateFormat.format(date));
		System.out.println(extentreports.getProjectName());
	}

	@AfterSuite(alwaysRun = true, enabled = true)
	public static void AfterSuite() {
		System.out.println("i'm after suite: it will execute after all tests");
		extentreports.flush();
	}

}