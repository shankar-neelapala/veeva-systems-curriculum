package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import utils.DataProviders;

public class DataDrivenTesting extends BaseTest{
	
	private LoginPage loginPage;
	private DashboardPage dashboardPage;

	@Test(dataProvider = "login-data", dataProviderClass = DataProviders.class)
	public void dataDrivenTesting(String username ,String password, String result) {
		loginPage = new LoginPage(driver);
		dashboardPage = new DashboardPage(driver);
		
		loginPage.enterUsernameAndPassword(username, password);
		loginPage.clickLogin();
		boolean status = dashboardPage.isLoginSuccessful();
		if(result.equals("pass") && status ) {
			dashboardPage.clickLogout();
			Assert.assertTrue(status);
		}
		if(result.equals("fail") && !status) {
			Assert.assertFalse(status);
		}
	}
}
