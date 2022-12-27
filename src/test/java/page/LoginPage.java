package page;

import org.apache.log4j.Logger;

import com.as.autot.business.BusinessException;
import com.as.autot.business.Structure;
import com.as.autot.core.BasePage;
import com.as.autot.core.common.TestDataInstance;
import com.as.autot.core.or.OrStruct;
import com.as.autot.tool.wrapper.api.IHTMLWebDriver;
import com.aventstack.extentreports.Status;

/**
 * This Class is presenting Page-Object Model for P&I-DUA Login Page
 * It includes all corresponding action functionality on the page.
 * All the function defined here are reusable to different test script
 * Note : All data should be parameterized from test script
 *  
 * @author Anuj Tripathi
 *
 */
public class LoginPage extends BasePage {

	@Structure(name = "txtEmailID")
	private OrStruct txtEmailID;

	@Structure(name = "txtPassword")
	private OrStruct txtPassword;

	@Structure(name = "btnSignin")
	private OrStruct btnSignin;

	private static final Logger LOGGER =  Logger.getLogger(LoginPage.class);
	
	public LoginPage(TestDataInstance testDataI, IHTMLWebDriver pHDriver) throws Exception {
		super(testDataI, pHDriver);
		super.initObjectRepository(this);
		pHDriver.waitForPageLoad();
		log(Status.INFO, "Page Object Model is generated for Login Page.");
	}

	public void login(String userName, String password) throws Exception {
		

		log(Status.INFO, "Login process started");
		try {
			waitForPageLoad();
			elem.setText(txtEmailID, userName);
			elem.setText(txtPassword, password);
			log(Status.INFO, "Entered user credential to login",true);
			elem.click(btnSignin);
			hDriver.waitForPageLoad();
		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps in Login process as previous step is failed");
		}
		super.log(Status.INFO, "Login process is completed with user [" + userName + "]");
	}
	
	
	public void verifyLoginPageTitle() throws Exception {
		log(Status.INFO, "verifyLoginPageTitle process started");
		try {
			waitForPageLoad();
			String pageTitle = hDriver.getDriver().getTitle();
			if (null != pageTitle && pageTitle.contains("Shipowners | P & I Online")) {
				hDriver.log("logout successful", Status.PASS, true);
			} else {
				hDriver.log("Unable to open login page after logout", Status.FAIL, true);
			}
			
		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps in verifyLoginPageTitle process as previous step is failed");
		}
		super.log(Status.INFO, "verifyLoginPageTitle process ended");
	}
	
	public void waitForPageLoad() throws Exception {
		try {
			hDriver.waitForPageLoad();
			elem.waitForElementToBeClickable(txtEmailID, txtEmailID.getwaitInSec());
		} catch (BusinessException be) {
			LOGGER.error(be.getbMsg());
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps as previous step is failed");
		}
	}
	
	
	//------------------- Getter and Setter ---------------------------------------

	public OrStruct getTxtEmailID() {
		return txtEmailID;
	}

	public void setTxtEmailID(OrStruct txtEmailID) {
		this.txtEmailID = txtEmailID;
	}

	public OrStruct getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(OrStruct txtPassword) {
		this.txtPassword = txtPassword;
	}

	public OrStruct getBtnSignin() {
		return btnSignin;
	}

	public void setBtnSignin(OrStruct btnSignin) {
		this.btnSignin = btnSignin;
	}

}
