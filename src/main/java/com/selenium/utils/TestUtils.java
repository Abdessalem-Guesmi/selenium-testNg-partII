package com.selenium.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.selenium.base.TestBasic;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class TestUtils extends TestBasic {

	public TestUtils() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void loadFile() throws IOException {
		properties = new Properties();
		// don't forget to change the path of the file according to the location on your
		// computer
		FileInputStream inputStream = new FileInputStream(
				"D:\\Training-WS\\selenium-testNg-partII\\src\\main\\java\\com\\application\\configuration\\config.properties");
		properties.load(inputStream);
	}

	public static void takeVideo(String name) throws ATUTestRecorderException {
		// Created object of ATUTestRecorder
		// Provide path to store videos and file name format.

		recorder = new ATUTestRecorder("D:\\Training-WS\\selenium-testNg-partII\\records", name, false);
		recorder.start();

	}

	public static void takePicture(String name) throws IOException {

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// don't forget to change the path of the file according to the location on your
		// computer

		FileUtils.copyFile(srcFile, new File("D:\\Training-WS\\selenium-testNg-partII\\screenShots\\" + name + ".png"));
	}

}
