package com.weightwatchers.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebUI 
{
	
	
		public String currentTestSuite;  // Name Of the Excel File which Is Running 
	
	//==>> Driver That Perform All Actions on UI - This will Be Loaded In Constructor with driver coming From Test 
		public WebDriver driver;


	//==>> Reporting Variable
		public static String PassFailFailDescription;
		

		
	public WebUI(WebDriver driver)
	{
		//super(driver,test);
		this.driver=driver;
	}
		
	public void NavigateToPage(String Url)
	{

		try
		{
			driver.get(Url);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public String getPageTitle()
	{
		return driver.getTitle();
	}
	
	
	public boolean ValidateElementExist(WebElement el)
	{
		//************ All SELENIUM Function Header ***************************************************
			String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
		           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************

	    	boolean Rtype = false;
	    	
		WebElement TargetObject=el;
		boolean s=false;
		
		try
		{
			if(!waitUntilClickable(TargetObject)) return false; // wait a Little bit for Object to Load
			//TargetObject = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
			s = TargetObject.isDisplayed();
		}
		catch(Exception e)
		{
			s=false;
		}

		if(s==false)
		{
			WebUI.PassFailFailDescription = thisMethodName + " -> Element Not Found " + "<br>";
			Rtype= false;
		}
		else
		{
			WebUI.PassFailFailDescription = thisMethodName + " -> Element Found " + "<br>";
			highlightElement(TargetObject,"pass");
			Rtype= true;
		}
	    return Rtype;		
	}

	public boolean EnterText(WebElement el, String Input)
	{
		
		//************ All SELENIUM Function Header ***************************************************
			String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
		           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************
		
	    		boolean Rtype = false;
	    	//****** Report: This Step That is Executing ************************************   
	    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" + ObjectName + "->" + Input);
	    	//********************************************************************************
	    	
	    	//==>> We need to check if the Input is ( Email 1 / User 1 / RandomName / Random Number) ETC
				//Input = fInterpretInput(Input);
				//Input = fInterpretExcelData(Input);
				
	    	//if (fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
			//{
	    		try
	    		{
	    			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
	    			WebElement TargetObject = el;
	    			TargetObject.clear();
	    			hardWaitFor(1000);
	    			TargetObject.sendKeys(Input);;
	    			Rtype= true;
	    			PassFailFailDescription = thisMethodName + "->" + el + "->"+ Input + "-> Passed" + "<br>";
	    		}
	    		catch(Exception e){
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "->" + ObjectName + "->"+ Input + "-> Failed");
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage());
	    			Rtype= false;
	
	    			PassFailFailDescription = thisMethodName + "->" + el + "->"+ Input + "-> Failed" + "<br>";
	    			PassFailFailDescription = e.getMessage() + "<br>";
	    		}
			//}
			//else
			//{
				//Rtype= false; // Failed Report in Pageready Object Ready Function
			//}
			return Rtype;
	   }
	
	public boolean ClickButton(WebElement el)
    {
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************
		
		
		
		boolean Rtype = false;
    	
    	//****** Report: This Step That is Executing ************************************   
    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" + ObjectName );
    	//********************************************************************************
    	
    	//if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		//{
    		try{
    			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    			if(!waitUntilClickable(el)) return false; // wait a Little bit for Object to Load
    			WebElement TargetObject = el;
    			TargetObject.click();
    			hardWait();
    			Rtype= true;
    			PassFailFailDescription = thisMethodName + "->" + el + "-> Passed" + "<br>";
    			//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "->" + ObjectName + "-> Passed");
    		}
    		catch(Exception e){
    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "->" + ObjectName + "-> Failed");
    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage());
	    		Rtype= false;
    			PassFailFailDescription = thisMethodName + "->" + el + "-> Failed" + "<br>";
    			PassFailFailDescription = e.getMessage() + "<br>";
    		}
		//}
		//else
		//{
			//Rtype= false; //If Failed (PAge is not Ready or Object is Not Ready) Pass/Report is inside the fEnsurePageIsReady, fEnsureObjectIsReady Function.
		//}
		return Rtype;
    }
	
	public boolean ClickLink(WebElement el)
    {
		
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************

	    	boolean Rtype = false;
	    	
	    	//****** Report: This Step That is Executing ************************************   
	    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" + ObjectName );
	    	//********************************************************************************
	    	
	    	//if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
			//{
	    		try{
	    			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
	    			if(!waitUntilClickable(el)) return false; // wait a Little bit for Object to Load
	    			WebElement TargetObject = el;
	    			TargetObject.click();
	    			hardWait();
	    			Rtype= true;
	    			PassFailFailDescription = thisMethodName + "->" + el + "-> Passed" + "<br>";
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "->" + ObjectName + "-> Passed");
	    		}
	    		catch(Exception e){
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "->" + ObjectName + "-> Failed");
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage());
		    		Rtype= false;
	    			PassFailFailDescription = thisMethodName + "->" + el + "-> Failed" + "<br>";
	    			PassFailFailDescription = e.getMessage() + "<br>";
	    		}
			//}
			//else
			//{
				//Rtype= false; //If Failed (PAge is not Ready or Object is Not Ready) Pass/Report is inside the fEnsurePageIsReady, fEnsureObjectIsReady Function.
			//}
			return Rtype;
    }
	
	public boolean SelectAnItemFrom_ListBox(WebElement listElement, String listNameOrNumbertoSelect)
	{
		//************ All SELENIUM Function Header ***************************************************
			String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
		           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************
	  
		    	boolean Rtype = false;
		    boolean matChFoundinTable=false;
		    String errorFlag = "";
				
	    	//==== Re-set Driver Time -  because it has a Driver.FindElemens Function which takes time 
	    		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

	    		try
	    		{
	    			WebElement liste_element = listElement;
	    	        List<WebElement> list_collection = liste_element.findElements(By.xpath("li"));
	    	        printconsole.printdebuglog("Total No of Item Found in ListBox = "+list_collection.size());
	
	       listLoop:for(int i=0;i<list_collection.size();i++)
	    	        {
					printconsole.printdebuglog("ListBox Item ("+i+") :"+ list_collection.get(i).getText());
					if(list_collection.get(i).getText().trim().equals(listNameOrNumbertoSelect.trim()) || list_collection.get(i).getText().trim().contains(listNameOrNumbertoSelect.trim()))
					{
						list_collection.get(i).click();
						hardWait();
						PassFailFailDescription = thisMethodName + "->" + "" + "-> Passed" + "<br>";
						Rtype= true;
						matChFoundinTable=true;
						break listLoop;
					}
					else
	                {
						matChFoundinTable= false;
						errorFlag = listNameOrNumbertoSelect + "  Not Found In List ";
	                }
	    	        } 		
	    		}
	    		catch(Exception e)
	    		{
	    			PassFailFailDescription = thisMethodName + "->" + "" +" Item->" + listNameOrNumbertoSelect + "->Clicked Failed" + "<br>";
	    			PassFailFailDescription = e.getMessage() + "<br>";
	    			System.out.println("Failed : " + PassFailFailDescription);
	    			Rtype= false;
	    		}
		    	
		    	if(!matChFoundinTable)
		    	{
		        	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From List  " + ObjectName +"->" + Input + "-> Clicked Failed" );
					PassFailFailDescription = thisMethodName + "->" + "" +" Item->" + listNameOrNumbertoSelect + "->Clicked Failed" + "<br>";
		    	}
	    	
	    	//==== Re-set Driver Time -  because it has a Driver.FindElemens Function which takes time 
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			return Rtype;
	}

	public boolean Click_A_WebElement_FromListBox(WebElement listElement, String ElementType, String listNameOrNumbertoSelect)
	{
	
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************

		
	    	boolean Rtype = false;
	    boolean matChFoundinTable=false;
	    String errorFlag = "";
	
	    //****** Report: This Step That is Executing ************************************   
	    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "-> From Table  " + ObjectName +"->" + Input );
	    	//********************************************************************************
	    	
			//Input = fInterpretExcelData(Input);
	
	    	
	    	//==== Re-set Driver Time -  because it has a Driver.FindElemens Function which takes time 
	    		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	    	
	    	//if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		//{
	    		try
	    		{
	    			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
	    			//WebElement liste_element = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
	    			WebElement liste_element = listElement;
	    	        List<WebElement> list_collection = liste_element.findElements(By.xpath("li"));
	    	        //System.out.println("NUMBER OF Item Found in List  = "+list_collection.size());
	
	       listLoop:for(int i=0;i<list_collection.size();i++)
	    	        {
					//System.out.println(list_collection.get(i).getText());
					if(list_collection.get(i).getText().trim().equals(listNameOrNumbertoSelect.trim()) || list_collection.get(i).getText().trim().contains(listNameOrNumbertoSelect.trim()))
					{
						//==>> There Could Be Different type of webelement inside a ListBox Like ( EditBox/ Button/ Link /span ETC)
						//==>> We Have to check which Item need to click and then Click it 
						if(list_collection.get(i).findElements(By.xpath(ElementType)).size() > 0) //Month FiletList in Suite Client Section
						{
							list_collection.get(i).findElement(By.xpath(ElementType)).click();
						}
						hardWait();
						//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "-> From Listbox  " + ObjectName +"->" + Input + "->Clicked Passed" );
						PassFailFailDescription = thisMethodName + "->" + "" + "-> Passed" + "<br>";
						Rtype= true;
						matChFoundinTable=true;
						break listLoop;
					}
					else
	                {
						matChFoundinTable= false;
						errorFlag = listNameOrNumbertoSelect + "  Not Found In List ";
	                }
	    	        } 		
	    		}
	    		catch(Exception e)
	    		{
	            	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From List  " + ObjectName +"->" + Input + "->Clicked Failed" );
	            	//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage() );
	    			PassFailFailDescription = thisMethodName + "->" + "" +" Item->" + listNameOrNumbertoSelect + "->Clicked Failed" + "<br>";
	    			PassFailFailDescription = e.getMessage() + "<br>";
	    			Rtype= false;
	    		}
			//}
			//else
			//{
				//==If Page OR Object Is Not Ready - We Don't Need to Report Here Reporting Already Performed [fEnsurePageRady & fEnsureObject Ready Function]
				//Rtype= false;
			//}
	    	
	    	if(!matChFoundinTable)
	    	{
	        	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From List  " + ObjectName +"->" + Input + "-> Clicked Failed" );
				PassFailFailDescription = thisMethodName + "->" + "" +" Item->" + listNameOrNumbertoSelect + "->Clicked Failed" + "<br>";
	    	}
	    	
	    	//==== Re-set Driver Time -  because it has a Driver.FindElemens Function which takes time 
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    	
			return Rtype;
	    	
	    
	}

	public boolean SelectDropDown(String ObjectXpath, String Input)
    {
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
	//************ ************************** *****************************************************

	    	boolean Rtype = false;
	    	
    		try
    		{
    			Select select = new Select(driver.findElement(By.xpath(ObjectXpath)));
    			select.selectByVisibleText(Input);
    			Rtype= true;
    		}
    		catch(Exception e)
    		{
    			Rtype= false;
    			PassFailFailDescription = e.getMessage() + "<br>";
    		}   		
    		return Rtype;
    }
	
	public String GetDropDownFirstSelectedOption(String ObjectXpath)
    {
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
	//************ ************************** *****************************************************

		
	    	
	    	String FirstSelectedOption = null;
    		try
    		{
    			Select select = new Select(driver.findElement(By.xpath(ObjectXpath)));
    			FirstSelectedOption = select.getFirstSelectedOption().getText();
    			return FirstSelectedOption;
    		}
    		catch(Exception e)
    		{
    			PassFailFailDescription = e.getMessage() + "<br>";
    			System.out.println("Error in Selenium Method " + thisMethodName +" : "+ PassFailFailDescription);
    			return "";
    		}   		
    }

	public List<String> GetAllDropDownOptionAsList(String ObjectXpath)
    {
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
	//************ ************************** *****************************************************

	    	
		List<String> OptionList = new ArrayList<String>();
	    	
    		try
    		{
    			Select select = new Select(driver.findElement(By.xpath(ObjectXpath)));
    			List <WebElement> allOptions= select.getOptions();
    			//System.out.println("\nTotal NUmber Of Option Found Inside the Dropdown Box : " + allOptions.size() );
    			for(int i=0;i<allOptions.size();i++)
    			{
        			//System.out.println("Category Box Oprion " + i + " is : " + allOptions.get(i).getText() );
    				OptionList.add(allOptions.get(i).getText());
    			}
    			return OptionList;
    		}
    		catch(Exception e)
    		{
    			PassFailFailDescription = e.getMessage() + "<br>";
    			System.out.println("Error in Selenium Method " + thisMethodName +" : "+ PassFailFailDescription);
    			return null;
    		}   		
    }
	
	
	public boolean scrollToElement(WebElement el)
	{
		
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************

		boolean rtype = false;

		WebElement TargetObject=el;
		try
		{
			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
			//TargetObject = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", TargetObject);
			hardWaitFor(1000);
			rtype=true;
		}
		catch(Exception e) 
		{
			PassFailFailDescription = thisMethodName + " Scrolling To Element " + TargetObject.toString() + "  -Failed" + "<br>";
			PassFailFailDescription = e.getMessage() + "<br>";
			System.out.println(PassFailFailDescription);
			rtype=false;
			//e.printStackTrace();
		}
		return rtype;
	}
	
	
	public String GetAttributeText(WebElement el, String AttributeName)
	{
		
		
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************

	    		String Rtype = "";
	    	//****** Report: This Step That is Executing ************************************   
	    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" + ObjectName + "->" + Input);
	    	//********************************************************************************
	    	
	    	//==>> We need to check if the Input is ( Email 1 / User 1 / RandomName / Random Number) ETC
				//Input = fInterpretInput(Input);
				//Input = fInterpretExcelData(Input);
				
	    	//if (fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
			//{
	    		try
	    		{
	    			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
	    			WebElement TargetObject = el;
	    			//TargetObject.clear();
	    			hardWaitFor(1000);
	    			//TargetObject.sendKeys(Input);
	    			Rtype= TargetObject.getAttribute(AttributeName);
	    			PassFailFailDescription = thisMethodName + " From -> " + el + " -> Passed" + "<br>";
	    		}
	    		catch(Exception e)
	    		{
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "->" + ObjectName + "->"+ Input + "-> Failed");
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage());
	    			Rtype= "";
	    			PassFailFailDescription = thisMethodName + " From -> " + el + " -> Failed" + "<br>";
	    			PassFailFailDescription = e.getMessage() + "<br>";
	    		}
			//}
			//else
			//{
				//Rtype= false; // Failed Report in Pageready Object Ready Function
			//}
			return Rtype;
			
	   }
	
	
	
	

	public boolean waitForpageLoad(String Step, String Page, String ObjectName, String Input)
	{
		boolean rtype = false;
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();

			try 
			{
				if(Input.contains("."))
				{
					Input = Input.replaceAll("\\.0*$", "");
				}
				
				Thread.sleep(Long.parseLong(Input));
				PassFailFailDescription = thisMethodName + " Waited for " + Input + "  -Second" + "<br>";
				rtype = true;
			} 
			catch (InterruptedException e) 
			{
				PassFailFailDescription = thisMethodName + " Waited for " + Input + "  -Second Failed" + "<br>";
    			PassFailFailDescription = e.getMessage() + "<br>";
    			rtype=false;
				//e.printStackTrace();
			}
		
		return rtype;
	}
	
	public void MouseOvertoelement(WebElement ele)
	{
		try
		{
			Actions action = new Actions(driver);
			action.moveToElement(ele).build().perform();
		}
		catch(Exception e)
		{
    		WebUI.PassFailFailDescription =  " -> Failed to Mouse Over On element  " + ele.getText() + "<br>";
    		WebUI.PassFailFailDescription = e.getMessage() + "<br>";
			//Rtype= false;
		}
	}
	
	public static Integer verifyIfLinkIsWorking(String urlToVerify) throws Exception
	{
			URL url = new URL(urlToVerify);
			HttpURLConnection myConnection = (HttpURLConnection) url.openConnection();
			try	 
			{	 
				myConnection.connect(); 
			    Integer myResponse = myConnection.getResponseCode();      	 
			    myConnection.disconnect();	 
			    return myResponse;	 
			}	 
			catch(Exception exp)	 
			{	 
				System.out.println("URL: "+url+" Can't be rsolved");
				return 404;
			}					 
	}	
	
	public boolean NavigateBack(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
	{
    	boolean rtype = false;
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		try
		{
			driver.navigate().back();
			hardWait();
			rtype=true;
		}
		catch(Exception e) 
		{
			PassFailFailDescription = thisMethodName + " Navigating Back From Page  " + Page + "  -Failed" + "<br>";
			PassFailFailDescription = e.getMessage() + "<br>";
			rtype=false;
			//e.printStackTrace();
		}
		return rtype;
	}
	
	public boolean DebugCode()
	    {
	    	//Nothing 
	    	return true;
	    }
	
	public boolean ExitDebug()
    {
    	//Nothing 
    	return false;
    }
	
	
    
//###################  Helper Functions  ##################################################################################################
  
	
	//==== Highlight element 
	public void highlightElement(WebElement element, String flag) 
	{
           JavascriptExecutor js = (JavascriptExecutor) driver;
           
           if(flag.equalsIgnoreCase("pass"))
           {
               js.executeScript("arguments[0].style.border='2px groove green'", element);
           }
           else 
           {
               js.executeScript("arguments[0].style.border='2px solid red'", element);

           }

           try 
           {
			Thread.sleep(3000);
           } 
           catch (InterruptedException e) 
           {
			e.printStackTrace();
           }
                   
           // Take a Screen Shot and Reset the Element 
           //takeScreenShot();
           //js.executeScript("arguments[0].style.border=''", element);
    }
	public void highlightElementBackground(WebElement element, String flag)
	{
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        if(flag.equalsIgnoreCase("pass"))
        {
            //js.executeScript("arguments[0].style.border='1px groove green'", element);
	        js.executeScript("arguments[0].style.backgroundColor = '"+"yellow"+"'",  element);
        }
        else 
        {
            //js.executeScript("arguments[0].style.border='4px solid red'", element);
	        js.executeScript("arguments[0].style.backgroundColor = '"+"red"+"'",  element);
        }

        try 
        {
			Thread.sleep(3000);
        } 
        catch (InterruptedException e) 
        {
			e.printStackTrace();
        }
                
        //===> Take a Screen Shot and Reset the Element 
        	//takeScreenShot();
        //js.executeScript("arguments[0].style.border=''", element);
        //js.executeScript("arguments[0].style.backgroundColor = '"+""+"'",  element);
	}
	public void highlightElementBorder(WebElement element, String flag) 
	{
           JavascriptExecutor js = (JavascriptExecutor) driver;
           
           if(flag.equalsIgnoreCase("pass"))
           {
               js.executeScript("arguments[0].style.border='2px groove green'", element);
           }
           else 
           {
               js.executeScript("arguments[0].style.border='2px solid red'", element);

           }

           try 
           {
			Thread.sleep(1000);
           } 
           catch (InterruptedException e) 
           {
			e.printStackTrace();
           }
                   
           // Take a Screen Shot and Reset the Element 
           //takeScreenShot();
           //js.executeScript("arguments[0].style.border=''", element);
    }

	//=== Driver Wait & Special Screen Shot Function
	public void waitForPageToLoad() throws InterruptedException 
	{
		//wait(2);
		this.hardWaitFor(500);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		String state = (String)js.executeScript("return document.readyState");
		while(!state.equals("complete"))
		{
			//wait(2);
			this.hardWaitFor(500);
			state = (String)js.executeScript("return document.readyState");
		}
	}	
	public void hardWait()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void hardWaitFor(int Second)
	{
		try {
			Thread.sleep(Second);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public boolean waitUntilClickable(String ObjectName)
	{
		//************
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//*********
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			//WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ObjectName)));
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ObjectName)));
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjectName)));
			return true;
		}
		catch(Exception e)
		{
			
    		PassFailFailDescription = thisMethodName + "-> For  :  " + ObjectName + " ->Failed" + "<br>";
    		PassFailFailDescription = e.getMessage() + "<br>";
		}

		return false;
	}
	
	public boolean waitUntilClickable(WebElement ObjectName)
	{
		//************
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//*********
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,1);
			//WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ObjectName)));
			 wait.until(ExpectedConditions.elementToBeClickable(ObjectName));
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjectName)));
			return true;
		}
		catch(Exception e)
		{
			
    		PassFailFailDescription = thisMethodName + "-> For  :  " + ObjectName + " ->Failed" + "<br>";
    		PassFailFailDescription = e.getMessage() + "<br>";
		}

		return false;
	}
	public void SwitchFrameWait()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
		
}
