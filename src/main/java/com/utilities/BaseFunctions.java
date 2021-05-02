package com.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseFunctions extends TestBase{

	/**
	 * @author kirankumar 
	 * @description - to wait until element found
	 * @param element
	 */
	public void waitUntilElementFound(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * @author - kirankumar 
	 * @param - WebElement element, text to enter & Label
	 * @description - To enter text on webelement
	 */

	public void enterText(WebElement element, String textToEnter,String Label) {
		try {
			if(element.getAttribute("value").length()>0|| element.getText().length()>0){
				element.clear();
			}
			element.sendKeys(textToEnter);
		}catch (Exception e) {
			logFailed(e.getMessage().toString());
		}
	}
	/**
	 * @author kirankumar 
	 * @description - get current url & compare it
	 * @param url
	 * @return
	 * @throws InterruptedException 
	 */
	public Boolean getCurrentUrlAndCompareIt(String url) {
		if(driver.getCurrentUrl().equals(url)) {
			logPassed("URL is same as expected");
			return true;
		}else
			logFailed("URL is different than expected");
		return false;
	}
	/**
	 * @author - kirankumar 
	 * @param element
	 * @param textToEnter
	 * @param Label
	 * @description - To send text on webelement & press enter
	 */
	public void sendTextAndEnter(WebElement element, String textToEnter,String Label) {
		try {
			element.sendKeys(textToEnter+Keys.ENTER);
		}catch (Exception e) {
			logFailed(e.getMessage().toString());
		}
	}

	/**
	 * @author - kirankumar 
	 * @param - WebElement element, Label
	 * @description - To click on webelement
	 */
	public Boolean click(WebElement element, String Label) {
		try {
			waitUntilElementFound(element);
			element.click();
			logPassed("Able to click on "+Label);
			return true;
		}catch (Exception e) {
			logFailed(e.getMessage().toString());
			return false;
		}
	}

	public String switchToLastTabWithOutURL() throws InterruptedException {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		int LastTab = tabs.size();
		driver.switchTo().window(tabs.get(LastTab - 1));
		Thread.sleep(2000);
		return driver.getCurrentUrl();
	}

	public void clickUsingJavaScript(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public Boolean clickUsingActions(WebElement element,String Label) {
		try {
			Actions actionBuilder = new Actions(driver);
			actionBuilder.moveToElement(element).click(element).build().perform();
			return true;
		}catch (Exception e) {
			logFailed(e.toString());
		}
		return false;
	}

	public void logPassed(String passedLog) {
		try {
			test.pass(passedLog);
		}catch (Exception e) {
			logFailed(e.getMessage());
		}
	}

	public void logInfo(String info) {
		test.info(info);
	}

	public void logFailed(String failedLog) {
		test.fail(failedLog);
	}

	public Boolean verifySearchTextInListOfWebElements(List<WebElement> lstOfWebElements,String expectedText) {
		List<Boolean> status = new ArrayList<Boolean>();
		for(WebElement webElement : lstOfWebElements) {
			if(webElement.getText().contains(expectedText)) 
				status.add(true);
		}
		if(status.size()==0)
			status.add(false);
		return status.stream().allMatch(val -> val == true);
	}

	public Boolean selectValueFromListOfWebElements(List<WebElement> lstOfWebElements,String expectedText) {
		Boolean Status = false;
		for(WebElement webElement : lstOfWebElements) {
			if(webElement.getText().equalsIgnoreCase(expectedText)) {
				Status= click(webElement, "Expected Text");
				break;
			}
		}
		return Status;
	}
	/**
	 * @author kirankumar 
	 * @description to scroll down
	 */
	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
	}
	/**
	 * @author kirankumar 
	 * @description Check whether webelement is present or not
	 * @param element
	 * @return
	 */
	public static boolean isElementPresent(WebElement element) {
		boolean flag = false;
		try {
			if (element.isDisplayed() || element.isEnabled())
				flag = true;
		} catch (NoSuchElementException e) {
			flag = false;
		} catch (StaleElementReferenceException e) {
			flag = false;
		}
		return flag;
	}

	public void customWait(long var) throws InterruptedException {
		Thread.sleep(var);
	}
	/**
	 * @author kirankumar
	 * @param element
	 * @description - to hover on particular webelment
	 */
	public void mouseHover(WebElement element) {
		Actions actions = new Actions(driver);
		Action mouseOverHome = actions.moveToElement(element).build();
		mouseOverHome.perform(); 
	}

	/**
	 * @author kirankumar 
	 * @description - Select webelement using text 
	 * @param value
	 * @return
	 */
	public Boolean selectUsingText(String value) {
		WebElement we = driver.findElement(By.xpath("//*[contains(text(),'" + value + "')]"));
		return click(we, value);
	}
	/**
	 * @author kirankumar
	 * @description - to select values from react
	 * @return
	 */
	public String selectRandomValueFromReactDropdown() {
		String dropdownValue = "";
		try {
			customWait(2000);
			List<WebElement> reactSelect_Options = driver
					.findElements(By.xpath("//div[contains(@id,'react-select')][contains(@id,'option')]"));
			waitUntilElementFound(reactSelect_Options.get(0));
			if (reactSelect_Options.size() > 1) {
				int randomvalue = TestDataGenerator.getRandomNumberBetweenRange(1, reactSelect_Options.size() - 1);
				scrollToParticularElement(reactSelect_Options.get(randomvalue));
				dropdownValue = reactSelect_Options.get(randomvalue).getText();
				click(reactSelect_Options.get(randomvalue), "Random drop down");
			} else {
				click(reactSelect_Options.get(0), "First drop down");
			}
		} catch (Exception e) {
			logFailed(e.getMessage());
		}
		return dropdownValue;
	}
	/**
	 * @author kirankumar
	 * @description - wait until element is clickable
	 * @param element
	 * @return
	 */
	public Boolean waitUntilElementIsClickable(WebElement element) {

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		Boolean success = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver,30);

			wait.until(ExpectedConditions.elementToBeClickable(element));
			success = true;
		} catch (Exception e) {

		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		return success;
	}
	
	/**
	 * @author kirankumar
	 * @description - to scroll to particular webelement
	 * @param element
	 * @return
	 */
	public void scrollToParticularElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
			waitUntilElementIsClickable(element);
		} catch (Exception E) {
			logFailed(E.getMessage());
		}
	}
	/**
	 * @author kirankumar 
	 * @description to get attribute values
	 * @param element
	 * @param attribute
	 * @return
	 */
	public String getAtttributeValue(WebElement element,String attribute) {
		return element.getAttribute(attribute);
	}
}