package scripts;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.as.autot.business.AppUtil;
import com.as.autot.core.BaseTest;
import com.as.autot.core.common.TestDataInstance;
import com.as.autot.core.common.TestDataInstance.Browser;
import com.as.autot.core.common.TestDataInstance.User;
import com.as.autot.tool.wrapper.api.IHTMLWebDriver;
import com.as.autot.tool.wrapper.api.IServiceClient;
import com.as.autot.tool.wrapper.api.ServiceClient;

import data.ProjectTestDataInstance;
import data.Quote;
import io.restassured.response.Response;
import page.DashboardPage;
import page.LoginPage;
import page.NewQuotePage;
import page.OpenQuotesPage;

/**
 * This Class is presenting test scripts at @test method level
 * @author Anuj Tripathi
 */

public class SmokeTest extends BaseTest {

	protected static final Logger LOGGER =  Logger.getLogger(SmokeTest.class);

	@Test(description = "To verify DUA coverholder dashboard elements after successful login", priority = 1, groups = {
			"Smoke" }, enabled = true)
	public void verifyDashboardElements() throws Exception {
		TestDataInstance td = new TestDataInstance(User.DUA_USER_1);
		IHTMLWebDriver hDriver = AppUtil.preparePrequisites(Browser.EDGE);
		
		  LoginPage loginPage = new LoginPage(td, hDriver);
		  loginPage.login(td.getLoginUser(), td.getLoginPassword());
		  
		  DashboardPage dashboardPage = new DashboardPage(td, hDriver);
		  dashboardPage.validateSideNavigationAvailability();
		  dashboardPage.validateLibraryAvailability();
		  dashboardPage.validateWigetAvailability();
		  dashboardPage.validateAdditionalNavAvailability(); 
		  
		  dashboardPage.logout();
		  
		  loginPage.verifyLoginPageTitle();
		 
		  hDriver.testClosure();
		hDriver.getSoftAssert().assertAll();
	}
	
	@Test(description = "To verify Open Quote's delete-confirmation process after successful login", priority = 3, groups = {
			"Regression" }, enabled = false)
	public void verifyQuoteDeletConfirmation() throws Exception {
		IHTMLWebDriver hDriver = AppUtil.preparePrequisites();
		TestDataInstance td = new TestDataInstance();

		LoginPage loginPage = new LoginPage(td, hDriver);
		loginPage.login(td.getLoginUser(), td.getLoginPassword());

		DashboardPage dashboardPage = new DashboardPage(td, hDriver);
		dashboardPage.getElem().waitForElementToBeClickable(dashboardPage.getBtnSideNavOpenQuotes(), 
				dashboardPage.getBtnSideNavOpenQuotes().getwaitInSec());
		dashboardPage.getElem().click(dashboardPage.getBtnSideNavOpenQuotes());
		
		OpenQuotesPage openQuotesPage = new OpenQuotesPage(td, hDriver);
		openQuotesPage.waitForPageLoad();
		openQuotesPage.validateDeleteQuoteCancel();
		openQuotesPage.validateDeleteQuoteConfirm();
		
		dashboardPage.logout();
		hDriver.testClosure();
		hDriver.getSoftAssert().assertAll();
	}
	
	@Test(description = "To create new quote and validate after successful login", priority = 2, groups = {
			"Regression" }, enabled = true)
	public void createAndValidateNewQuote() throws Exception {

		TestDataInstance td = new TestDataInstance();
		IHTMLWebDriver hDriver = AppUtil.preparePrequisites();
		
		Quote quote = new Quote();
		//quote = (Quote) td.getTestData("quote.json", quote);
		quote = (Quote) ProjectTestDataInstance.getTestDataAfterMassage("quote.json", quote);
		
		LoginPage loginPage = new LoginPage(td, hDriver);
		loginPage.login(td.getLoginUser(), td.getLoginPassword());

		DashboardPage dashboardPage = new DashboardPage(td, hDriver);
		dashboardPage.getElem().waitForElementToBeClickable(dashboardPage.getBtnSideNavOpenQuotes(), 
				dashboardPage.getBtnSideNavOpenQuotes().getwaitInSec());
		dashboardPage.getElem().click(dashboardPage.getBtnSideNavOpenQuotes());
		
		OpenQuotesPage openQuotesPage = new OpenQuotesPage(td, hDriver);
		openQuotesPage.waitForPageLoad();
		openQuotesPage.getElem().clickOnceClickable(openQuotesPage.getAddNewQuote());
		
		NewQuotePage newQuotePage = new NewQuotePage(td, hDriver);
		newQuotePage.waitForPageLoad();
		newQuotePage.validateNewQuoteHeader();
		newQuotePage.fillNewQuoteDetails(quote);
		
		newQuotePage.getElem().clickOnceClickable(newQuotePage.getSaveButton());
	
		openQuotesPage.waitForPageLoad();
		openQuotesPage.verifySavedQuote(quote);
		
		dashboardPage.logout();

		hDriver.testClosure();
		hDriver.getSoftAssert().assertAll();
	}

	@Test(description = "To update PNI version in execution report after successful login", priority = 1, groups = {
			"Smoke" }, enabled = true)
	public void getPniVersion() throws Exception {
		TestDataInstance td = new TestDataInstance();
		IHTMLWebDriver hDriver = AppUtil.preparePrequisites(Browser.EDGE);

		LoginPage loginPage = new LoginPage(td, hDriver);
		loginPage.login(td.getLoginUser(), td.getLoginPassword());

		DashboardPage dashboardPage = new DashboardPage(td, hDriver);
		dashboardPage.updatePniVersion();
		
		hDriver.testClosure();
		hDriver.getSoftAssert().assertAll();
	}
	
	@Test(description = "To verify API response after successful login", priority = 1, groups = {
	"Smoke" }, enabled = false)
	public void verifyApiResponse() throws Exception {
		TestDataInstance td = new TestDataInstance(User.DUA_USER_2);
		IHTMLWebDriver hDriver = AppUtil.preparePrequisites(Browser.EDGE);

		LoginPage loginPage = new LoginPage(td, hDriver);
		loginPage.login(td.getLoginUser(), td.getLoginPassword());

		DashboardPage dashboardPage = new DashboardPage(td, hDriver);
	
		dashboardPage.getElem().waitForElementToBeClickable(dashboardPage.getBtnSideNavOpenQuotes(), 
				dashboardPage.getBtnSideNavOpenQuotes().getwaitInSec());
		
		IServiceClient serviceClient = new ServiceClient(hDriver.getDriver());
		Response response = serviceClient.get​GetConfigurationResponse();
		LOGGER.debug("get​GetConfigurationResponse Response :: " + response.asPrettyString());
		
		response = serviceClient.get​GetRenewalSummaryResponse();
		LOGGER.debug("get​GetRenewalSummaryResponse Response :: " + response.asPrettyString());
		
		response = serviceClient.get​GetRenewalPolicyResponse();
		LOGGER.debug("get​GetRenewalPolicyResponse Response :: " + response.asPrettyString());
		
		response = serviceClient.get​GetLossRatioResponse();
		LOGGER.debug("get​GetLossRatioResponse Response :: " + response.asPrettyString());
		
		hDriver.testClosure();
		hDriver.getSoftAssert().assertAll();
	}
}
