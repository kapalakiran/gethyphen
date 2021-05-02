package com.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReUsableFunctions extends BaseFunctions{

	@FindBy(css="button[type=submit]")
	private WebElement submitBtn;
	
	public ReUsableFunctions(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * @author kirankumar
	 * @description to click on submit button
	 * @return
	 */
	public Boolean selectSubmitBtn() {
		return click(submitBtn,submitBtn.getText());
	}
}
