package com.test.weassignment;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTest 
{
	
	public static final String CHROME_DRIVER_EXE=System.getProperty("user.dir")+"//chromedriver";
	public static final String Gecko_DRIVER_EXE=System.getProperty("user.dir")+"//geckodriver";

	
	public WebDriver driver;
	DesiredCapabilities myBrowserCapability = new DesiredCapabilities();
	static String myScreenResolutionCapability = "screenresolution";

	public void init(String bType)
	{
		if(bType.equals("Mozilla"))
		{
			System.setProperty("webdriver.gecko.driver", Gecko_DRIVER_EXE);
			driver= new FirefoxDriver();
		}
		else if(bType.equals("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_EXE);
			myBrowserCapability = DesiredCapabilities.chrome();	
			//myBrowserCapability.setCapability(myScreenResolutionCapability,"1600x1200"); 
			driver= new ChromeDriver(myBrowserCapability);
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

}
