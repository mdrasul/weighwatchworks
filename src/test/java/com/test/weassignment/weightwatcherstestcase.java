package com.test.weassignment;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.weightwatchers.pages.weightwatchers_HomePage;
import com.weightwatchers.pages.weightwatchers_MeetingPage;

public class weightwatcherstestcase extends BaseTest 
{
	
	public String appurl = "https://www.weightwatchers.com/us/";
	//public String appurl = "https://www.msg.com";

	

	@BeforeTest
	public void Initilize()
	{
		 
		  init("Chrome");
		  
	}
	

	/*
 	* Test to validate Home page Load & Page Title is Matching 
 	* Note : Page Title is Hard Coded Inside the HOme PAge Class to make sure we are in Correct Site   
 	*/
  @Test(priority = 0)
  public void weightwatchersPageLoadAndTitleValidation() 
  {
	  boolean testresult = false;
	  weightwatchers_HomePage home = new weightwatchers_HomePage(driver);
	  testresult = home.goTo(appurl);
	  Assert.assertTrue(testresult, "\nFailed to Land on " + appurl);
  }
  
  
  @Test(priority = 1)
  public void weightwatchersNavigateToMeetingPAge() 
  {
	  boolean testresult = false;
	  weightwatchers_HomePage home = new weightwatchers_HomePage(driver);
	  
	  Object page=home.clickLinkAndNavigate("Find a Meeting");
	  
	  if(page instanceof weightwatchers_MeetingPage)
	  {
			//System.out.println("Properly Landed On Meeting Page");
		  	String expTitle = "Get Schedules & Times Near You";
	  		testresult = ((weightwatchers_MeetingPage) page).verifyTitle(expTitle);
	  		Assert.assertTrue(testresult, "\nFailed to Land on weightwatchers_MeetingPage or \n Check Page  Exp Title:" + expTitle +
	  									 " \n Is MAtching with Actual TItle :  " +((weightwatchers_MeetingPage) page).getTitle() );
	  }
	  else
	  {
		  System.out.println("Failed TO Landed On Meeting PAge");
	  }
  }
  
  
  
  @Test(priority = 2)
  public void SearchMeetingAndPrintFirstResult() 
  {
	  boolean testresult = false;
	  weightwatchers_MeetingPage meetingpage = new weightwatchers_MeetingPage(driver);
	  PageFactory.initElements(driver, meetingpage);
	  meetingpage.searchameetingwith("10011");
	  testresult= meetingpage.PrintFirstResultLocationAndDistance();
	  Assert.assertTrue(testresult, "Failed to Search And Print First Result Title & Distance ");
  }
  
  
  @Test(priority = 3)
  public void ClickSearchResultAndValidte() 
  {
	  boolean testresult = false;
	  weightwatchers_MeetingPage meetingpage = new weightwatchers_MeetingPage(driver);
	  PageFactory.initElements(driver, meetingpage);
	  testresult= meetingpage.ClickSearchResultAndValidte();
	  Assert.assertTrue(testresult, "Failed to match First Result with Location Result ....");
  }
  
  @Test(priority = 4)
  public void PrinttodaysHourOfOpration() 
  {
	  boolean testresult = false;
	  weightwatchers_MeetingPage meetingpage = new weightwatchers_MeetingPage(driver);
	  PageFactory.initElements(driver, meetingpage);
	  testresult= meetingpage.PrinttodaysHourOfOpration();
	  Assert.assertTrue(testresult, "Failed to match First Result with Location Result ....");
  }
  
  @Test(priority = 5)
  public void ReadDictionary() 
  {
	  System.out.println("\n\n################ Below Is Not  UI test ####################");
	  System.out.println("1. This Test reads a text file like Dictionry Parse and Print It ");
	  System.out.println("1. Please See class zReaddictionary inside src/test/java folder ");
	  
	  try
		{
			String filetoRead = System.getProperty("user.dir")+"//dictionary.txt";
			File file = null;
			 
			//==>> Access The FIle 
			if(zReaddictionary.doesFileExist(filetoRead))
			{
				  file = new File(filetoRead);
			}
			else
			{
				System.out.println("Error - File is not Exit on Given Path : " + filetoRead);
				return; // Getting Out from program File is not Exist no point to move frowrd
			}
		 
			//==>> Read & Print the File 
			HashMap<String, String> dictionarykeyvalue = new HashMap<String, String>();
			dictionarykeyvalue = zReaddictionary.ReadAndParseTheFile(file);
			if(!dictionarykeyvalue.isEmpty())
			{
				zReaddictionary.printFileFromHasmap(dictionarykeyvalue);
			}
			else
			{
				System.out.println("Error : dictionary File Was Empty please Check the FIle : " + filetoRead);
			}
		 
		}
		catch(Exception e)
		{
			System.out.println("Something is Wrong Please Check The Technical Error Below ");
			e.printStackTrace();
		}

  }
  
  
  @Test(priority = 6)
  public void nthsmallnumber() 
  {
	  System.out.println("\n2. This test generate 500 random number & print the nth smallest number ");
	  
	  try
		{
		  
		    Double array[]  = new Double[500];
		    //System.out.println("enter 15 number ");

		    for (int i = 0 ; i < array.length; i++ ) {
		      array[i] =  Math.random();
		      System.out.println(array[i]);
		    }

		    System.out.println("===================");

		    System.out.println(zNthSmallest.findNthSmallest(array, 9));

		}
		catch(Exception e)
		{
			System.out.println("Something is Wrong Please Check The Technical Error Below ");
			e.printStackTrace();
		}

  }
  
  
}
