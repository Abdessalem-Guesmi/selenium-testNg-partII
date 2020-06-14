package com.selenium.base;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import atu.testrecorder.ATUTestRecorder;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBasic {
	public static WebDriver driver;
	public static Properties properties;
	public static ATUTestRecorder recorder;
	public static ExtentTest extentTest;
	public static ExtentReports extentreports;

	public static void initilzation(String URL) {
		System.out.println("******" + URL);
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

}
