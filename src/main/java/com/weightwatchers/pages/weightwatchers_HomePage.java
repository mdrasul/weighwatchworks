package com.weightwatchers.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.weightwatchers.util.WebUI;


public class weightwatchers_HomePage extends WebUI
{
	
	
	public static String homepageTitle = "Weight Loss Program, Recipes & Help | Weight Watchers";
	
	@FindBy(xpath="//a[@href='/logout']")
	public WebElement logoutLinkIcon;


	public weightwatchers_HomePage(WebDriver driver) 
	{
		super(driver);
	}
	
	public boolean goTo(String Url)
	{
		try
		{
			System.out.println(" - **Landing On weightwatchers Home Page With : " + Url + " ** ");
			NavigateToPage(Url);
			if(verifyTitle(homepageTitle))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (WebDriverException e) 
		{
			System.out.println("Error/Exception - WW.com Landing Page:  Failed !!");
			System.out.println(e.getMessage());
			return false;
		}
	}
	public String getTitle()
	{
		return getPageTitle();
	}
	public boolean verifyTitle(String exptitle)
	{
		String ActualTitle = getPageTitle();
		if(ActualTitle.equals(exptitle))
		{
			return true;
		}
		return false;
	}
	
	public Object clickLinkAndNavigate(String linkName)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			if(driver.findElements(By.id(linkName)).size() > 0)
			{
				driver.findElement(By.id(linkName)).click();
			}
			else if(driver.findElements(By.linkText(linkName)).size()>0)
			{
				driver.findElement(By.linkText(linkName)).click();
			}
			else if(driver.findElements(By.className(linkName.replace(" ", "-"))).size()>0)
			{
				driver.findElement(By.className(linkName)).click();
			}
			else 
			{
				// Nothing Happened 
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				return null;
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//===== Returning Appropriate Navigated Page 
			if(linkName.equals("Find a Meeting"))
			{
				weightwatchers_MeetingPage MeetingPage = new weightwatchers_MeetingPage(driver);
				//PageFactory.initElements(driver, MeetingPage);
				return MeetingPage;
			}
			
		}
		catch(Exception e)
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			e.printStackTrace();
		}
		return null;
		
	}
	
}
