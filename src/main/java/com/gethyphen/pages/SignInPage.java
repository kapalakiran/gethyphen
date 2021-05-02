package com.gethyphen.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.BaseFunctions;
import com.utilities.ReUsableFunctions;

public class SignInPage extends BaseFunctions{

	@FindBy(css="input#inputGroupField")
	private WebElement emailAndPwdTb;

	@FindBy(css="div.errorField")
	private WebElement errorFieldText;

	@FindBy(xpath="//div[@class='rubix-panel-body']/preceding::h3")
	private WebElement signUp_LogBackInText;

	public SignInPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/**
	 * @author kirankumar
	 * @description to login into the application
	 * @param username
	 * @param pwd
	 * @return
	 * @throws InterruptedException 
	 */
	public Boolean login(String username,String pwd) throws InterruptedException {
		waitUntilElementFound(emailAndPwdTb);
		ReUsableFunctions objReUsableFunctions = new ReUsableFunctions(driver);
		enterText(emailAndPwdTb, username, "Email");
		objReUsableFunctions.selectSubmitBtn();
		customWait(3000);
		waitUntilElementFound(emailAndPwdTb);
		enterText(emailAndPwdTb, pwd, "Password");
		return objReUsableFunctions.selectSubmitBtn();	
	}

	/**
	 * @author kirankumar
	 * @description - to validate error messageInvalid verification code
	 * @return
	 * @throws InterruptedException 
	 */
	public String verifyErrorFieldText() throws InterruptedException {
		customWait(2000);
		String error = "";
		if(isElementPresent(errorFieldText))
			error = errorFieldText.getText();
		return error;
	}
	/**
	 * @author kirankumar 
	 * @description - able to verify with invalid email
	 * @param userName
	 * @return
	 */
	public Boolean verifyWithInvalidEmailID(String userName) {
		Boolean Status = false;
		enterText(emailAndPwdTb, userName, "Email");
		 new ReUsableFunctions(driver).selectSubmitBtn();
		if(signUp_LogBackInText.getText().equals("Sign up / Log back in")) {
			logPassed("Unable to navigate to next screen as we tried with login");
			Status = true;
		}else
			logFailed("Able to navigate to next screen as we tried with login");
		return Status;	
	}
}
