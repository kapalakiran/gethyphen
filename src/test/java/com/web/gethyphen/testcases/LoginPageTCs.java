package com.web.gethyphen.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.gethyphen.pages.GethyphenHomePage;
import com.gethyphen.pages.SignInPage;
import com.utilities.BaseFunctions;
import com.utilities.TestDataGenerator;

public class LoginPageTCs extends BaseFunctions{

   @Test
   public void verifyLoginWithValidCredentials() throws InterruptedException, IOException {
	   new SignInPage(driver).login(getProperty("Email"), getProperty("Password"));
	   if(new GethyphenHomePage(driver).verifyCreateNewPostBtnPresence())
		   logPassed("Able to verify login with valid credentials");
	   else
		   logPassed("Unable to verify login with valid credentials");
   }
   
   @Test
   public void verifyLoginWithValidUserNameAndInValidPassword() throws InterruptedException, IOException {
	   SignInPage objSignInPage= new SignInPage(driver);
			   objSignInPage.login(getProperty("Email"),TestDataGenerator.getRandomNumberBetweenRange(1, 122222)+"");
	   if(objSignInPage.verifyErrorFieldText().equals("Invalid verification code"))
		   logPassed("Able to verify error message with invalid credentials");
	   else
		   logPassed("Unable to verify error message with invalid credentials");
   }
   
   @Test
   public void verifyLoginWithInValidEmail() throws InterruptedException, IOException {
	   if(new SignInPage(driver).verifyWithInvalidEmailID(TestDataGenerator.getRandomNumberBetweenRange(1, 122222)+""))
		   logPassed("System doesn't let the user to navigate to the next screen when we use invalid email id");
	   else
		   logPassed("System let the user to navigate to the next screen when we use invalid email id");
   }
}
