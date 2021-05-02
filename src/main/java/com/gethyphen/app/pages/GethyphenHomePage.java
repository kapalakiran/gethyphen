package com.gethyphen.app.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.BaseFunctions;
import com.utilities.ReUsableFunctions;
import com.utilities.TestDataGenerator;

public class GethyphenHomePage extends BaseFunctions {

	@FindBy(id="create-button")
	private WebElement createNewPostBtn;

	@FindBy(xpath="//button[text()='OPEN']")
	private WebElement openButton;

	@FindBy(id="textAreaField")
	private WebElement textAreaField;

	@FindBy(css="span[class^=questionText] span")
	private WebElement postTextList;

	@FindBy(css="span[class^=hyphen] ")
	private WebElement groupTextList;

	@FindBy(css="div[aria-label='Example Modal'] span:nth-child(1)")
	private List<WebElement> createNewPostRadioBtns;

	@FindBy(css="div.modal-body button[class^=hyphenButton]")
	private List<WebElement> openAndMultipleChoiceBtns;

	@FindBy(css="div[class$='is-clearable is-searchable']")
	private WebElement privateGroupsBtn;

	@FindBy(id="inputGroupField")
	private List<WebElement> optionTb;

	@FindBy(css="span.Linkify span")
	private WebElement questionTxt;

	@FindBy(css="div.voteOptionWrapper span")
	private List<WebElement> answerOtionBtns;

	@FindBy(css="textarea[name=comment]")
	private WebElement commentTb;

	public static enum PostType{
		Anonymous,Official,Named;
	}

	public static enum QuestionType{
		Open,MultipleChoice;
	}

	/**
	 * @author kirankumar 
	 * @description - select create new post type
	 * @param create New Post Type & questions type
	 * @return
	 */
	public Boolean selectCreateNewPostTypeAndQuestionType(PostType createNewPostType,QuestionType selectQuestionType) {
		Boolean createNewPostStatus = true,questionTypeStatus = false;
		try {
			if(PostType.Anonymous == createNewPostType) {
				if(!(getAtttributeValue(createNewPostRadioBtns.get(0), "class").contains("checked")))
					createNewPostStatus = click(createNewPostRadioBtns.get(0),createNewPostType.toString());
			}else if(PostType.Official == createNewPostType) {
				if(!(getAtttributeValue(createNewPostRadioBtns.get(1), "class").contains("checked")))
					createNewPostStatus = click(createNewPostRadioBtns.get(1),createNewPostType.toString());
			}else if(PostType.Named == createNewPostType) {
				if(!(getAtttributeValue(createNewPostRadioBtns.get(2), "class").contains("checked")))
					createNewPostStatus = click(createNewPostRadioBtns.get(2),createNewPostType.toString());
			}
			if(QuestionType.Open==selectQuestionType)
				questionTypeStatus = click(openAndMultipleChoiceBtns.get(0), "Open");
			else
				questionTypeStatus = click(openAndMultipleChoiceBtns.get(1), "Multiple Choice");
			if(questionTypeStatus && createNewPostStatus)
				return true;
		}catch (Exception e) {
			logFailed(e.toString());
		}
		return false;
	}
	/**
	 * @author kirankumar 
	 * @description - to select private
	 * @return
	 */
	public String selectPostGroup() {
		click(privateGroupsBtn, "Private select Button");
		String selectedDrpDwn = selectRandomValueFromReactDropdown();
		new ReUsableFunctions(driver).selectSubmitBtn();
		return selectedDrpDwn;
	}

	public HashMap<String, String> writeNewOpenPostAndSubmit() {
		HashMap<String, String>openQuesDetails = new HashMap<String, String>();
		openQuesDetails.put("Question",TestDataGenerator.getRandomNumberBetweenRange(10000, 100000000)+"");
		enterText(textAreaField,openQuesDetails.get("Question"), "Text Area");
		new ReUsableFunctions(driver).selectSubmitBtn();
		return openQuesDetails;
	}

	public HashMap<String, String> writeNewMultipleChoicePostAndSubmit() {
		HashMap<String, String> multipleChoiceQuesDetails = new HashMap<String, String>();
		multipleChoiceQuesDetails.put("Question",TestDataGenerator.getRandomNumberBetweenRange(10000, 100000000)+"");
		enterText(textAreaField,multipleChoiceQuesDetails.get("Question"), "Text Area");
		for(int i=0;i<optionTb.size();i++) {
			multipleChoiceQuesDetails.put("Option"+i,TestDataGenerator.getRandomNumberBetweenRange(10000, 900000000)+"");
			enterText(optionTb.get(i),multipleChoiceQuesDetails.get("Option"+i), "Option"+i);
		}
		new ReUsableFunctions(driver).selectSubmitBtn();
		return multipleChoiceQuesDetails;
	}
	public GethyphenHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	/**
	 * @author kirankumar 
	 * @description to verify multiple choice options
	 * @param postDetails
	 * @return
	 */
	public Boolean verifyMultipleChoiceButtons(	HashMap<String, String> postDetails) {
		List<Boolean> status = new ArrayList<Boolean>();
		waitUntilElementFound(answerOtionBtns.get(0));
		for(int i=0;i<postDetails.size()-1;i++) {
			String option = answerOtionBtns.get(i).getText();
			if(postDetails.containsValue(option)) {
				logPassed("Option "+option+" are verified");
				status.add(true);
			}else {
				logFailed("Option "+option+" are different than expected");
				status.add(false);
			}
		}
		return status.stream().allMatch(val -> val == true);
	}
	/**
	 * @author kirankumar
	 * @param create New PostType
	 * @param selectQuestionType
	 * @return
	 */
	public Boolean createNewPostAndVerifyIt(PostType createNewPostType,QuestionType selectQuestionType) {
		try {
			click(createNewPostBtn, "Create New Post");
			selectCreateNewPostTypeAndQuestionType(createNewPostType,selectQuestionType);
			selectPostGroup();
			HashMap<String, String> postDetails = new HashMap<String, String>();
			if(QuestionType.Open==selectQuestionType)
				postDetails = writeNewOpenPostAndSubmit();
			else if(QuestionType.MultipleChoice==selectQuestionType)
				postDetails = writeNewMultipleChoicePostAndSubmit();
			customWait(2000);
			if(questionTxt.getText().equalsIgnoreCase(postDetails.get("Question")))
				logPassed("Question text is verified");
			else
				logFailed("Question text is different");
			if(QuestionType.Open==selectQuestionType)
				return isElementPresent(commentTb);
			else
				return verifyMultipleChoiceButtons(postDetails);
		}catch (Exception e) {
			logFailed(e.getMessage());
		}
		return false;
	}
	/**
	 * @author kirankumar
	 * @description - to verify create new post button is present or not 
	 * @return
	 */
	public Boolean verifyCreateNewPostBtnPresence() {
		return isElementPresent(createNewPostBtn);
	}

}
