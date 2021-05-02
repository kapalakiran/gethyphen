package com.web.gethyphen.testcases;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gethyphen.pages.GethyphenHomePage;
import com.gethyphen.pages.SignInPage;
import com.gethyphen.pages.GethyphenHomePage.POSTYPE;
import com.gethyphen.pages.GethyphenHomePage.QUESTIONTYPE;
import com.utilities.BaseFunctions;

public class CreateNewPostTCs extends BaseFunctions{

	@BeforeMethod
	public void login() throws IOException, InterruptedException{
		new SignInPage(driver).login(getProperty("Email"), getProperty("Password"));
	}

	@Test
	public void createNewPostWithAnonymousPostTypeAndOpenQuestion() {
		logInfo("Create a new post with anonymous Post Type & Open Question");
		if(new GethyphenHomePage(driver).createNewPostAndVerifyIt(POSTYPE.Anonymous, QUESTIONTYPE.Open))
			logPassed("Able to create a new post with anonymous Post Type & Open Question ");
		else
			logFailed("Unable to create a new post with anonymous Post Type & Open Question ");
	}
	
	@Test
	public void createNewPostWithNamedPostTypeAndOpenQuestion() {
		logInfo("Create a new post with named Post Type & Open Question");
		if(new GethyphenHomePage(driver).createNewPostAndVerifyIt(POSTYPE.Named, QUESTIONTYPE.Open))
			logPassed("Able to create a new post with named Post Type & Open Question ");
		else
			logFailed("Unable to create a new post with named Post Type & Open Question ");
	}
	
	@Test
	public void createNewPostWithOfficialPostTypeAndOpenQuestion() {
		logInfo("Create a new post with Official Post Type & Open Question");
		if(new GethyphenHomePage(driver).createNewPostAndVerifyIt(POSTYPE.Official, QUESTIONTYPE.Open))
			logPassed("Able to create a new post with Official Post Type & Open Question ");
		else
			logFailed("Unable to create a new post with Official Post Type & Open Question ");
	}
	
	@Test
	public void createNewPostWithOfficialPostTypeAndMultipleChoicenQuestion() {
		logInfo("Create a new post with Official Post Type & Multiple Choice Question");
		if(new GethyphenHomePage(driver).createNewPostAndVerifyIt(POSTYPE.Official, QUESTIONTYPE.MultipleChoice))
			logPassed("Able to create a new post with Official Post Type & Multiple Choice Question ");
		else
			logFailed("Unable to create a new post with Official Post Type & Multiple Choice Question ");
	}
	
	@Test
	public void createNewPostWithNamedPostTypeAndMultipleChoicenQuestion() {
		logInfo("Create a new post with Named Post Type & Multiple Choice Question");
		if(new GethyphenHomePage(driver).createNewPostAndVerifyIt(POSTYPE.Named, QUESTIONTYPE.MultipleChoice))
			logPassed("Able to create a new post with Named Post Type & Multiple Choice Question ");
		else
			logFailed("Unable to create a new post with Named Post Type & Multiple Choice Question ");
	}
	
	@Test
	public void createNewPostWithAnonymousPostTypeAndMultipleChoicenQuestion() {
		logInfo("Create a new post with Anonymous Post Type & Multiple Choice Question");
		if(new GethyphenHomePage(driver).createNewPostAndVerifyIt(POSTYPE.Anonymous, QUESTIONTYPE.MultipleChoice))
			logPassed("Able to create a new post with Anonymous Post Type & Multiple Choice Question ");
		else
			logFailed("Unable to create a new post with Anonymous Post Type & Multiple Choice Question ");
	}
}
