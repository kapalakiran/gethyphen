package com.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	static public WebDriver driver;
	static String propertiesFile;
	Properties property = new  Properties();

	static ExtentReports extent = new ExtentReports();
	static ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/index.html");
	static ExtentTest test =null;
	static {
		WebDriverManager.chromedriver().setup();
	}

	/**
	 * @author kirankumar 
	 * @throws IOException 
	 * @description - To create driver object and to launch the url
	 */
	@BeforeMethod(alwaysRun = true)
	@Parameters({"Environment"})
	public void setUp(String propFile,Method method) throws IOException {
		propertiesFile = propFile;
		driver = new ChromeDriver();
		driver.get(getProperty(propFile,"URL"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		extent.attachReporter(spark);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Automation Reports");
		extent.attachReporter(spark);
		test = extent.createTest(method.getName());
	}

	@AfterMethod(alwaysRun=true)
	public void closeTheBrowser(){
		driver.quit();
		extent.flush();
	}
	
	public String getProperty(String key) throws IOException{
		return getProperty(propertiesFile,key);
	}
	
	public String getProperty(String propetyfilepath,String key) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(propetyfilepath);
			prop = new Properties();
			prop.load(fis);
		} catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}
		return prop.getProperty(key);
	}
}