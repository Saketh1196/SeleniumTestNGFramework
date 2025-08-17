package com.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentReport = new ExtentReports();
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter SparkReporter = new ExtentSparkReporter(extentReportFile);
		
		SparkReporter.config().setTheme(Theme.DARK);
		SparkReporter.config().setReportName("Test Automation Results Report");
		SparkReporter.config().setDocumentTitle("Automation Report");
		SparkReporter.config().setTimeStampFormat("dd.MM.yyyy hh:mm:ss");
		
		extentReport.attachReporter(SparkReporter);
		
		Properties configprop = new Properties();
		File configPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\automation\\config\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(configPropFile);
			configprop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application URL", configprop.getProperty("url"));
		extentReport.setSystemInfo("Browser name", configprop.getProperty("browser"));
		extentReport.setSystemInfo("Email", configprop.getProperty("ValidEmail"));
		extentReport.setSystemInfo("Password", configprop.getProperty("ValidPassword"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReport;
	}
}
