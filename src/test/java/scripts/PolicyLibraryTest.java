package scripts;

import org.testng.annotations.Test;

import com.as.autot.business.AppUtil;
import com.as.autot.business.BusinessException;
import com.as.autot.core.BaseTest;
import com.as.autot.core.common.TestDataInstance;
import com.as.autot.core.common.TestDataInstance.Browser;
import com.as.autot.core.common.TestDataInstance.User;
import com.as.autot.tool.wrapper.api.IHTMLWebDriver;

import page.DashboardPage;
import page.LoginPage;
import page.PolicyLibraryPage;

/**
 * @author Nainika Sinha
 * @implNote The Class used to Validate Policy Summary, Policy Financials and Instalment Pattern in Policy Library.
 * @Date 21-12-2022
 * 
 */
public class PolicyLibraryTest extends BaseTest {

	@Test(description = "To verify Policy Summary, Policy Financials and Instalment Pattern in Policy Library", groups = {
			"Regression" }, enabled = false)
	public void verifyPolicyDetailsInPolicyLibrary() throws Exception {
		TestDataInstance td = new TestDataInstance(User.DUA_USER_1);
		IHTMLWebDriver hDriver = AppUtil.preparePrequisites(Browser.EDGE);

		LoginPage loginPage = new LoginPage(td, hDriver);
		loginPage.login(td.getLoginUser(), td.getLoginPassword());
		//loginPage.verifyLoginPageTitle();
		
		DashboardPage dashboardpage=new DashboardPage(td, hDriver);
		dashboardpage.clickOnPolicyLibraryButton();
		PolicyLibraryPage PolicyLibraryPage = new PolicyLibraryPage(td, hDriver);
		PolicyLibraryPage.ClosePolicyWindow();
		
		hDriver.testClosure();
		hDriver.getSoftAssert().assertAll();
	}
	
	@Test(description = "To verify Policy Broker details in Policy Library", groups = {
	"Regression" }, enabled = true)
	
	public void verifyBrokerPolicyLibraryDetails() throws Exception {
		
		TestDataInstance td = new TestDataInstance(User.DUA_USER_1);
		IHTMLWebDriver hDriver = AppUtil.preparePrequisites(Browser.EDGE);
		
		LoginPage loginPage = new LoginPage(td, hDriver);
		loginPage.login(td.getLoginUser(), td.getLoginPassword());
		loginPage.verifyLoginPageTitle();
		
		DashboardPage dashboardpage=new DashboardPage(td, hDriver);
		dashboardpage.clickOnPolicyLibraryButton();
		
		PolicyLibraryPage PolicyLibraryPage = new PolicyLibraryPage(td, hDriver);
		PolicyLibraryPage.validatePolicyTableColumns(hDriver);
		PolicyLibraryPage.VerifyPolicyDetails();
		
		hDriver.testClosure();
		hDriver.getSoftAssert().assertAll();
		
	}

}
