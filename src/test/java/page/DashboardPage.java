package page;

import org.apache.log4j.Logger;

import com.as.autot.business.BusinessException;
import com.as.autot.business.Structure;
import com.as.autot.core.BasePage;
import com.as.autot.core.common.TestDataInstance;
import com.as.autot.core.extentreport.ExtentTestManager;
import com.as.autot.core.or.OrStruct;
import com.as.autot.tool.wrapper.api.IHTMLWebDriver;
import com.as.autot.tool.wrapper.api.IServiceClient;
import com.as.autot.tool.wrapper.api.ServiceClient;
import com.aventstack.extentreports.Status;

import io.restassured.response.Response;

/**
 * This Class is presenting Page-Object Model for P&I-DUA Dashboard Page
 * It includes all corresponding action functionality on the page.
 * All the function defined here are reusable to different test script
 * Note : All data should be parameterized from test script
 *  
 * @author Anuj Tripathi
 *
 */
public class DashboardPage extends BasePage {

	@Structure(name = "btnSideToggle")
	private OrStruct btnSideToggle;

	@Structure(name = "btnSideNavDashboard")
	private OrStruct btnSideNavDashboard;

	@Structure(name = "btnSideNavOpenQuotes")
	private OrStruct btnSideNavOpenQuotes;

	@Structure(name = "btnMembersLib")
	private OrStruct btnMembersLib;

	@Structure(name = "btnVesselsLib")
	private OrStruct btnVesselsLib;

	@Structure(name = "btnPoliciesLib")
	private OrStruct btnPoliciesLib;

	@Structure(name = "widgetCardholder")
	private OrStruct widgetCardholder;

	@Structure(name = "widgetRenewalsSummary")
	private OrStruct widgetRenewalsSummary;

	@Structure(name = "widgetRecentQuotes")
	private OrStruct widgetRecentQuotes;

	@Structure(name = "widgetCardholderStatements")
	private OrStruct widgetCardholderStatements;

	@Structure(name = "widgetContacts")
	private OrStruct widgetContacts;

	@Structure(name = "widgetLrlc")
	private OrStruct widgetLrlc;

	@Structure(name = "btnContinuePI")
	private OrStruct btnContinuePI;

	@Structure(name = "btnKnowledgeBase")
	private OrStruct btnKnowledgeBase;

	@Structure(name = "btnFAQ")
	private OrStruct btnFAQ;

	@Structure(name = "btnReportAProblem")
	private OrStruct btnReportAProblem;

	@Structure(name = "btnTnC")
	private OrStruct btnTnC;

	@Structure(name = "btnNeedHelp")
	private OrStruct btnNeedHelp;

	@Structure(name = "lblLoginUser")
	private OrStruct lblLoginUser;

	@Structure(name = "btnSignOut")
	private OrStruct btnSignOut;

	@Structure(name = "btnSignOutNow")
	private OrStruct btnSignOutNow;

	@Structure(name = "btnUsingPIOnline")
	private OrStruct btnUsingPIOnline;
	
	@Structure(name = "pniVersion")
	private OrStruct pniVersion;
	
	private static final Logger LOGGER =  Logger.getLogger(DashboardPage.class);
	
	public DashboardPage(TestDataInstance testDataI, IHTMLWebDriver pHDriver) {
		super(testDataI, pHDriver);
		super.initObjectRepository(this);
		log(Status.INFO, "Page Object Model is generated for dashboard");
		
		pHDriver.waitForPageLoad();
		try {
		boolean isPageLoaded = elem.isAvailableOnPage(lblLoginUser, lblLoginUser.getwaitInSec());
		if (isPageLoaded) {
			String logeinUser = elem.getText(lblLoginUser);
			log(Status.PASS, "User [" + logeinUser + "] login successfully",true);
		} else {
			log(Status.FAIL, "Unable to load Dashnoard page", true);
			
		}
		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps as previous step is failed");
		}

	}

	public void validateSideNavigationAvailability() throws Exception {
		log(Status.INFO, "validateSideNavigationAvilability process started");
		try {
			if (elem.isAvailableOnPage(btnSideToggle, btnSideToggle.getwaitInSec())) {
				log(Status.PASS, "[" + btnSideToggle.getName() + "] is available on the page");
			} else {
				log(Status.FAIL, "[" + btnSideToggle.getName() + "] is not available on the page");
			}
			if (elem.isAvailableOnPage(btnSideNavDashboard, btnSideNavDashboard.getwaitInSec())) {
				log(Status.PASS, "[" + btnSideNavDashboard.getName() + "] is available on the page");
			} else {
				log(Status.FAIL, "[" + btnSideNavDashboard.getName() + "] is not available on the page");
			}
			if (elem.isAvailableOnPage(btnSideNavOpenQuotes, btnSideNavOpenQuotes.getwaitInSec())) {
				log(Status.PASS, "[" + btnSideNavOpenQuotes.getName() + "] is available on the page");
			} else {
				log(Status.FAIL, "[" + btnSideNavOpenQuotes.getName() + "] is not available on the page");
			}
		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps in validateSideNavigationAvilability process as previous step is failed");
		}
		log(Status.INFO, "validateSideNavigationAvilability process ended");
	}

	public void validateLibraryAvailability() throws Exception {
		log(Status.INFO, "isLibraryAvilabile validation started");
		try {
			if(elem.isAvailableOnPage(btnMembersLib, btnMembersLib.getwaitInSec())) {
				log(Status.PASS,"["+btnMembersLib.getName()+"] is available on the page");
			}else {
				log(Status.FAIL,"["+btnMembersLib.getName()+"] is not available on the page");
			}
			if(elem.isAvailableOnPage(btnVesselsLib, btnVesselsLib.getwaitInSec())) {
				log(Status.PASS,"["+btnVesselsLib.getName()+"] is available on the page");
			}else {
				log(Status.FAIL,"["+btnVesselsLib.getName()+"] is not available on the page");
			}
			if(elem.isAvailableOnPage(btnPoliciesLib, btnPoliciesLib.getwaitInSec())) {
				log(Status.PASS,"["+btnPoliciesLib.getName()+"] is available on the page");
			}else {
				log(Status.FAIL,"["+btnPoliciesLib.getName()+"] is not available on the page");
			}
		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps in validateLibraryAvilability process as previous step is failed");
		}
		log(Status.INFO, "isLibraryAvilabile validation ended");
	}

	public void validateWigetAvailability() throws Exception {
		log(Status.INFO, "validateWigetAvilability validation started");
		try {
			if(elem.isAvailableOnPage(widgetCardholder, widgetCardholder.getwaitInSec())) {
				log(Status.PASS,"["+widgetCardholder.getName()+"] is available on the page");
			}else {
				log(Status.FAIL,"["+widgetCardholder.getName()+"] is not available on the page");
			}
			if(elem.isAvailableOnPage(widgetCardholderStatements, widgetCardholderStatements.getwaitInSec())) {
				log(Status.PASS,"["+widgetCardholderStatements.getName()+"] is available on the page");
			}else {
				log(Status.FAIL,"["+widgetCardholderStatements.getName()+"] is not available on the page");
			}
			if(elem.isAvailableOnPage(widgetContacts, widgetContacts.getwaitInSec())) {
				log(Status.PASS,"["+widgetContacts.getName()+"] is available on the page");
			}else {
				log(Status.FAIL,"["+widgetContacts.getName()+"] is not available on the page");
			}
			if(elem.isAvailableOnPage(widgetLrlc, widgetLrlc.getwaitInSec())) {
				log(Status.PASS,"["+widgetLrlc.getName()+"] is available on the page");
			}else {
				log(Status.FAIL,"["+widgetLrlc.getName()+"] is not available on the page");
			}
			if(elem.isAvailableOnPage(widgetRecentQuotes, widgetRecentQuotes.getwaitInSec())) {
				log(Status.PASS,"["+widgetRecentQuotes.getName()+"] is available on the page");
			}else {
				log(Status.FAIL,"["+widgetRecentQuotes.getName()+"] is not available on the page");
			}
			if(elem.isAvailableOnPage(widgetRenewalsSummary, widgetRenewalsSummary.getwaitInSec())) {
				log(Status.PASS,"["+widgetRenewalsSummary.getName()+"] is available on the page");
			}else {
				log(Status.FAIL,"["+widgetRenewalsSummary.getName()+"] is not available on the page");
			}
		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps in validateWigetAvilability process as previous step is failed");
		}
		log(Status.INFO, "validateWigetAvilability validation ended");
	}

	public void validateAdditionalNavAvailability() throws Exception{
		log(Status.INFO, "isAdditionalNavAvilabile validation started");
		try {
			if (elem.isAvailableOnPage(btnContinuePI, btnContinuePI.getwaitInSec())) {
				log(Status.PASS, "[" + btnContinuePI.getName() + "] is available on the page");
			} else {
				log(Status.FAIL, "[" + btnContinuePI.getName() + "] is not available on the page");
			}
			if (elem.isAvailableOnPage(btnKnowledgeBase, btnKnowledgeBase.getwaitInSec())) {
				log(Status.PASS, "[" + btnKnowledgeBase.getName() + "] is available on the page");
			} else {
				log(Status.FAIL, "[" + btnKnowledgeBase.getName() + "] is not available on the page");
			}
			if (elem.isAvailableOnPage(btnFAQ, btnFAQ.getwaitInSec())) {
				log(Status.PASS, "[" + btnFAQ.getName() + "] is available on the page");
			} else {
				log(Status.FAIL, "[" + btnFAQ.getName() + "] is not available on the page");
			}
			if (elem.isAvailableOnPage(btnReportAProblem, btnReportAProblem.getwaitInSec())) {
				log(Status.PASS, "[" + btnReportAProblem.getName() + "] is available on the page");
			} else {
				log(Status.FAIL, "[" + btnReportAProblem.getName() + "] is not available on the page");
			}
			if (elem.isAvailableOnPage(btnTnC, btnTnC.getwaitInSec())) {
				log(Status.PASS, "[" + btnTnC.getName() + "] is available on the page");
			} else {
				log(Status.FAIL, "[" + btnTnC.getName() + "] is not available on the page");
			}
			if (elem.isAvailableOnPage(btnNeedHelp, btnNeedHelp.getwaitInSec())) {
				log(Status.PASS, "[" + btnNeedHelp.getName() + "] is available on the page");
			} else {
				log(Status.FAIL, "[" + btnNeedHelp.getName() + "] is not available on the page");
			}
		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps in validateAdditionalNavAvilability process as previous step is failed");
		}
		log(Status.INFO, "isAdditionalNavAvilabile validation ended");
	}

	public void logout() throws Exception {
		log(Status.INFO, "logout process started");
		try {
			hDriver.waitForPageLoad();
			elem.waitForElementToBeClickable(lblLoginUser, lblLoginUser.getwaitInSec());
			elem.click(lblLoginUser);
			elem.click(btnSignOut);
			elem.click(btnSignOutNow);
			
			hDriver.waitForPageLoad();
			
		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps in logout process as previous step is failed");
		}
		super.log(Status.INFO, "logout process ended");
	}

	public void logoutCancel() throws Exception{
		log(Status.INFO, "logoutCancel process started");
		try {
			elem.waitForElementToBeClickable(lblLoginUser, lblLoginUser.getwaitInSec());
			elem.click(lblLoginUser);
			elem.click(btnSignOut);
			elem.click(btnUsingPIOnline);
		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps in logoutCancel process as previous step is failed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.log(Status.INFO, "logoutCancel process ended");
	}
	
	
	public void vesselList() throws Exception{
		log(Status.INFO, "vesselList process started");
		try {
			elem.waitForElementToBeClickable(btnVesselsLib, btnVesselsLib.getwaitInSec());
			elem.click(btnVesselsLib);
			IServiceClient serviceClient = new ServiceClient(driver);
			Response response = serviceClient.get​GetVesselListResponse(2, 1, true, "vesselname");
			LOGGER.debug("Vessels List Response :: " + response.asPrettyString());
			
		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps in vesselList process as previous step is failed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.log(Status.INFO, "vesselList process ended");
	}
	public synchronized void updatePniVersion() {
		LOGGER.debug("Inside updatePniVersion ");
		try {
			if (null == TestDataInstance.pniVersion) {
				elem.click(lblLoginUser);
				waitForPageLoad();
				TestDataInstance.pniVersion = elem.getText(pniVersion);
				TestDataInstance.pniVersion = TestDataInstance.pniVersion.split(":")[1];
				ExtentTestManager.extent.setSystemInfo("PNI Version", TestDataInstance.pniVersion.trim());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	
	
	public void waitForPageLoad() throws Exception {
		try {
			hDriver.waitForPageLoad();
			elem.waitForElementToBeClickable(lblLoginUser, lblLoginUser.getwaitInSec());
		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps as previous step is failed");
		}
	}
	
	public void clickOnPolicyLibraryButton() throws Exception {
		log(Status.INFO, "Click on to the Policy Library button.");
		try {
			hDriver.waitForPageLoad();
			elem.waitForElementToBeClickable(btnPoliciesLib, btnPoliciesLib.getwaitInSec());
			elem.highLightElement(btnPoliciesLib);
			elem.click(btnPoliciesLib);
			 
			hDriver.waitForPageLoad();
			
		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps in clicking policy library button process as previous step is failed.");
		}
		super.log(Status.INFO, "Clicking policy library button process ended");
	}
	
	//------------------- Getter and Setter ---------------------------------------

	public OrStruct getBtnSideToggle() {
		return btnSideToggle;
	}

	public void setBtnSideToggle(OrStruct btnSideToggle) {
		this.btnSideToggle = btnSideToggle;
	}

	public OrStruct getBtnSideNavDashboard() {
		return btnSideNavDashboard;
	}

	public void setBtnSideNavDashboard(OrStruct btnSideNavDashboard) {
		this.btnSideNavDashboard = btnSideNavDashboard;
	}

	public OrStruct getBtnSideNavOpenQuotes() {
		return btnSideNavOpenQuotes;
	}

	public void setBtnSideNavOpenQuotes(OrStruct btnSideNavOpenQuotes) {
		this.btnSideNavOpenQuotes = btnSideNavOpenQuotes;
	}

	public OrStruct getBtnMembersLib() {
		return btnMembersLib;
	}

	public void setBtnMembersLib(OrStruct btnMembersLib) {
		this.btnMembersLib = btnMembersLib;
	}

	public OrStruct getBtnVesselsLib() {
		return btnVesselsLib;
	}

	public void setBtnVesselsLib(OrStruct btnVesselsLib) {
		this.btnVesselsLib = btnVesselsLib;
	}

	public OrStruct getBtnPoliciesLib() {
		return btnPoliciesLib;
	}

	public void setBtnPoliciesLib(OrStruct btnPoliciesLib) {
		this.btnPoliciesLib = btnPoliciesLib;
	}

	public OrStruct getWidgetCardholder() {
		return widgetCardholder;
	}

	public void setWidgetCardholder(OrStruct widgetCardholder) {
		this.widgetCardholder = widgetCardholder;
	}

	public OrStruct getWidgetRenewalsSummary() {
		return widgetRenewalsSummary;
	}

	public void setWidgetRenewalsSummary(OrStruct widgetRenewalsSummary) {
		this.widgetRenewalsSummary = widgetRenewalsSummary;
	}

	public OrStruct getWidgetRecentQuotes() {
		return widgetRecentQuotes;
	}

	public void setWidgetRecentQuotes(OrStruct widgetRecentQuotes) {
		this.widgetRecentQuotes = widgetRecentQuotes;
	}

	public OrStruct getWidgetCardholderStatements() {
		return widgetCardholderStatements;
	}

	public void setWidgetCardholderStatements(OrStruct widgetCardholderStatements) {
		this.widgetCardholderStatements = widgetCardholderStatements;
	}

	public OrStruct getWidgetContacts() {
		return widgetContacts;
	}

	public void setWidgetContacts(OrStruct widgetContacts) {
		this.widgetContacts = widgetContacts;
	}

	public OrStruct getWidgetLrlc() {
		return widgetLrlc;
	}

	public void setWidgetLrlc(OrStruct widgetLrlc) {
		this.widgetLrlc = widgetLrlc;
	}

	public OrStruct getBtnContinuePI() {
		return btnContinuePI;
	}

	public void setBtnContinuePI(OrStruct btnContinuePI) {
		this.btnContinuePI = btnContinuePI;
	}

	public OrStruct getBtnKnowledgeBase() {
		return btnKnowledgeBase;
	}

	public void setBtnKnowledgeBase(OrStruct btnKnowledgeBase) {
		this.btnKnowledgeBase = btnKnowledgeBase;
	}

	public OrStruct getBtnFAQ() {
		return btnFAQ;
	}

	public void setBtnFAQ(OrStruct btnFAQ) {
		this.btnFAQ = btnFAQ;
	}

	public OrStruct getBtnReportAProblem() {
		return btnReportAProblem;
	}

	public void setBtnReportAProblem(OrStruct btnReportAProblem) {
		this.btnReportAProblem = btnReportAProblem;
	}

	public OrStruct getBtnTnC() {
		return btnTnC;
	}

	public void setBtnTnC(OrStruct btnTnC) {
		this.btnTnC = btnTnC;
	}

	public OrStruct getBtnNeedHelp() {
		return btnNeedHelp;
	}

	public void setBtnNeedHelp(OrStruct btnNeedHelp) {
		this.btnNeedHelp = btnNeedHelp;
	}

	public OrStruct getLblLoginUser() {
		return lblLoginUser;
	}

	public void setLblLoginUser(OrStruct lblLoginUser) {
		this.lblLoginUser = lblLoginUser;
	}

	public OrStruct getBtnSignOut() {
		return btnSignOut;
	}

	public void setBtnSignOut(OrStruct btnSignOut) {
		this.btnSignOut = btnSignOut;
	}

	public OrStruct getBtnSignOutNow() {
		return btnSignOutNow;
	}

	public void setBtnSignOutNow(OrStruct btnSignOutNow) {
		this.btnSignOutNow = btnSignOutNow;
	}

	public OrStruct getBtnUsingPIOnline() {
		return btnUsingPIOnline;
	}

	public void setBtnUsingPIOnline(OrStruct btnUsingPIOnline) {
		this.btnUsingPIOnline = btnUsingPIOnline;
	}

	public OrStruct getPniVersion() {
		return pniVersion;
	}

	public void setPniVersion(OrStruct pniVersion) {
		this.pniVersion = pniVersion;
	}

}
