package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

	@Test(groups= {"regression", "master"})
	public void verify_account_registration() 
	
	{
		logger.info("*****starting TC_001_AccountRegistrationTest  *****" );
		
		logger.debug("application logs...");
		
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount link");
		
		hp.clickRegister();
		logger.info("Clicked on registartion link");
		
		
		logger.info("Entering customer details..");
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString() + "@gmail.com"); // randomly generated the email
		regpage.setTelephone(randomeNumber());

		String password = randomAlphaNumeric();

		regpage.setPassword(password);
		regpage.setConfirmPassword(password);

		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		logger.info("Clicked on continue");

		String confmsg = regpage.getConfirmationMsg();
		logger.info("Validating expected message...");
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			logger.info("Test passed..");
			Assert.assertTrue(true);
		}
		
		else
		{
			logger.info("Test failed..");
			Assert.fail();
		}
		}
		catch(Exception e)
		{
			logger.error("test failed..");
			logger.debug("debug logs...");
			Assert.fail();
		}
		
		logger.debug("application ends...");
		logger.info("*****finished TC_001_AccountRegistrationTest  *****" );
	}

}
