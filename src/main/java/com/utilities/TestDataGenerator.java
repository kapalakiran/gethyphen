package com.utilities;

import java.util.Random;

import com.github.javafaker.Faker;

public class TestDataGenerator extends BaseFunctions{

	Faker objFaker = new Faker(); 

	public static int getRandomNumberBetweenRange(int low, int high) {
		Random r = new Random();
		int result = 0;
		int lowNum = low ;
		int highNum = high;

		if(low!=high) {
			result = r.nextInt(highNum-lowNum) + low;
			return result;
		}else 
			return low;
	}

	public String getRandomEmail() {
		return getFirstName()+"@mailinator.com";
	}

	public String getRandomMobileNo() {
		return objFaker.phoneNumber().cellPhone();
	}

	public String getFirstName() {
		return objFaker.name().firstName();
	}

	public String getLastName() {
		return objFaker.name().lastName();
	}
}

