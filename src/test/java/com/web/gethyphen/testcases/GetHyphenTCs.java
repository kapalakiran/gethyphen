package com.web.gethyphen.testcases;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gethyphen.app.pages.GethyphenHomePage;
import com.gethyphen.app.pages.GethyphenHomePage.PostType;
import com.gethyphen.app.pages.GethyphenHomePage.QuestionType;
import com.gethyphen.app.pages.SignInPage;
import com.utilities.BaseFunctions;

public class GetHyphenTCs extends BaseFunctions{

	@BeforeMethod
	public void login() throws IOException, InterruptedException{
		new SignInPage(driver).login(getProperty("Email"), getProperty("Password"));
	}

	@Test
	public void createNewPostWithAnonymousPostTypeAndOpenQuestion() {
		logInfo("Create a new post with anonymous Post Type & Open Question");
		if(new GethyphenHomePage(driver).createNewPostAndVerifyIt(PostType.Anonymous, QuestionType.Open))
			logPassed("Able to create a new post with anonymous Post Type & Open Question ");
		else
			logFailed("Unable to create a new post with anonymous Post Type & Open Question ");
	}
	
	@Test
	public void createNewPostWithNamedPostTypeAndOpenQuestion() {
		logInfo("Create a new post with named Post Type & Open Question");
		if(new GethyphenHomePage(driver).createNewPostAndVerifyIt(PostType.Named, QuestionType.Open))
			logPassed("Able to create a new post with named Post Type & Open Question ");
		else
			logFailed("Unable to create a new post with named Post Type & Open Question ");
	}
	
	@Test
	public void createNewPostWithOfficialPostTypeAndOpenQuestion() {
		logInfo("Create a new post with Official Post Type & Open Question");
		if(new GethyphenHomePage(driver).createNewPostAndVerifyIt(PostType.Official, QuestionType.Open))
			logPassed("Able to create a new post with Official Post Type & Open Question ");
		else
			logFailed("Unable to create a new post with Official Post Type & Open Question ");
	}
	
	@Test
	public void createNewPostWithOfficialPostTypeAndMultipleChoicenQuestion() {
		logInfo("Create a new post with Official Post Type & Multiple Choice Question");
		if(new GethyphenHomePage(driver).createNewPostAndVerifyIt(PostType.Official, QuestionType.MultipleChoice))
			logPassed("Able to create a new post with Official Post Type & Multiple Choice Question ");
		else
			logFailed("Unable to create a new post with Official Post Type & Multiple Choice Question ");
	}
	
	@Test
	public void createNewPostWithNamedPostTypeAndMultipleChoicenQuestion() {
		logInfo("Create a new post with Named Post Type & Multiple Choice Question");
		if(new GethyphenHomePage(driver).createNewPostAndVerifyIt(PostType.Named, QuestionType.MultipleChoice))
			logPassed("Able to create a new post with Named Post Type & Multiple Choice Question ");
		else
			logFailed("Unable to create a new post with Named Post Type & Multiple Choice Question ");
	}
	
	@Test
	public void createNewPostWithAnonymousPostTypeAndMultipleChoicenQuestion() {
		logInfo("Create a new post with Anonymous Post Type & Multiple Choice Question");
		if(new GethyphenHomePage(driver).createNewPostAndVerifyIt(PostType.Anonymous, QuestionType.MultipleChoice))
			logPassed("Able to create a new post with Anonymous Post Type & Multiple Choice Question ");
		else
			logFailed("Unable to create a new post with Anonymous Post Type & Multiple Choice Question ");
	}
}
