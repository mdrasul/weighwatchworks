package com.weightwatchers.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.weightwatchers.util.WebUI;


public class weightwatchers_MeetingPage extends WebUI
{
	
	
	public String meetingTitle = "Get Schedules & Times Near You";
	
	@FindBy(id="meetingSearch")
	public WebElement searchBox;
	
	@FindBy(xpath="//button[@class='btn btn-default form-blue-pill__btn']")
	public WebElement searchbutton;
	
	@FindBy(xpath="//div[@class='location__name']")
	public WebElement resultLocationstitle;
	
	@FindBy(xpath="//div[@class='location__distance']")
	public WebElement resultLocationsdistance;

	



	public weightwatchers_MeetingPage(WebDriver driver) 
	{
		super(driver);
	}
	
	public boolean goTo(String Url)
	{
		try
		{
			System.out.println(" - **Landing On weightwatchers Meeting Page With : " + Url + " ** ");
			NavigateToPage(Url);
			if(verifyTitle(meetingTitle))
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
		if(ActualTitle.equals(exptitle) || ActualTitle.contains(exptitle) )
		{
			return true;
		}
		return false;
	}
	
	public void searchameetingwith(String searchValue)
	{
		highlightElement(searchBox,"pass");
		EnterText(searchBox,searchValue);
		
		highlightElement(searchbutton,"pass");
		ClickButton(searchbutton);
	}
	
	public boolean PrintFirstResultLocationAndDistance()
	{
		try
		{
			List<WebElement> alllocation = driver.findElements(By.xpath("//div[@class='location__name']"));
			List<WebElement> allldistance = driver.findElements(By.xpath("//div[@class='location__distance']"));
			System.out.println("NUMBER OF Result Location Found "+alllocation.size());
			
			highlightElement(alllocation.get(0),"pass");
			highlightElement(allldistance.get(0),"pass");
			
			System.out.println("Printing The First REsult Location & Distance : " + alllocation.get(0).getText() + "  " +allldistance.get(0).getText() );
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public void PrintAllResultLocationAndDistance()
	{
		//to do 
	}
	
	
	@SuppressWarnings("unused")
	public boolean ClickSearchResultAndValidte()
	{
		try
		{
			
			// Get the First Location TExt 
			List<WebElement> alllocationAddress = driver.findElements(By.xpath("//div[@class='location__address']"));
			List<WebElement> alllocationCityStateZip = driver.findElements(By.xpath("//div[@class='location__city-state-zip']"));
			
			String expFirstLocationAddress = alllocationAddress.get(0).getText();
			String expFirstLocationCityStateZip= alllocationCityStateZip.get(0).getText();

			//Click The First Result Now 
			List<WebElement> alllocation = driver.findElements(By.xpath("//div[@class='location__name']"));
			alllocation.get(0).click();
			
			//Wait till the Pop-up Open 
			waitUntilClickable(driver.findElement(By.xpath("//div[@class='location__address']")));

			//Validate the Result with the new Popup result 
			String actualFirstLocationAddress = driver.findElement(By.xpath("//div[@class='location__address']")).getText();
			String actualFirstLocationCityStateZip= driver.findElement(By.xpath("//div[@class='location__city-state-zip']")).getText();

			if(expFirstLocationAddress.equals(actualFirstLocationAddress) && expFirstLocationCityStateZip.equals(actualFirstLocationCityStateZip))
			{
				highlightElement(driver.findElement(By.xpath("//div[@class='location__address']")),"pass");
				highlightElement(driver.findElement(By.xpath("//div[@class='location__city-state-zip']")),"pass");
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	@SuppressWarnings("unused")
	public boolean PrinttodaysHourOfOpration()
	{
		boolean rtype=false;
		try
		{
			
			List<WebElement> houroperationlist = driver.findElements(By.xpath("//ul[@class='hours-list list-unstyled in collapse']//li"));

			//Get What is Today 
			Calendar calendar = Calendar.getInstance();
			Date date = calendar.getTime();
			String today = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());

			for(int j=0;j<houroperationlist.size();j++)
			{
				//System.out.println(houroperationlist.get(j).getText());

				if(houroperationlist.get(j).getText().toLowerCase().contains(today.toLowerCase()))
				{
					System.out.println("Printing Hour Of Operaion For Today " + today  );
					System.out.println(houroperationlist.get(j).getText().replace(today, ""));
					rtype = true;
					break;
				}
			}
			return rtype;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	
}
