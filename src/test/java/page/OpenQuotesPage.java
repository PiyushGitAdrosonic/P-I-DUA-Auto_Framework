package page;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.as.autot.business.BusinessException;
import com.as.autot.business.Structure;
import com.as.autot.core.BasePage;
import com.as.autot.core.common.TestDataInstance;
import com.as.autot.core.or.OrStruct;
import com.as.autot.tool.wrapper.api.IHTMLWebDriver;
import com.as.autot.tool.wrapper.api.IServiceClient;
import com.as.autot.tool.wrapper.api.ServiceClient;
import com.aventstack.extentreports.Status;

import data.Quote;
import io.restassured.response.Response;

/**
 * This Class is presenting Page-Object Model for P&I-DUA Open Quote Page.
 * It includes all corresponding action functionality on the page.
 * All the function defined here are reusable to different test script
 * Note : All data should be parameterized from test script
 *  
 * @author Anuj Tripathi
 *
 */
public class OpenQuotesPage extends BasePage {

	@Structure(name = "openQuotesCard")
	private OrStruct openQuotesCard;

	@Structure(name = "deleteIconFirstQuote")
	private OrStruct deleteIconFirstQuote;

	@Structure(name = "deleteWarningMsg")
	private OrStruct deleteWarningMsg;

	@Structure(name = "deleteCancelBtn")
	private OrStruct deleteCancelBtn;

	@Structure(name = "deleteConfirmBtn")
	private OrStruct deleteConfirmBtn;

	@Structure(name = "quoteWithDataIndex")
	private OrStruct quoteWithDataIndex;
	
	
	@Structure(name = "addNewQuote")
	private OrStruct addNewQuote;
	

	@Structure(name = "txtSearch")
	private OrStruct txtSearch;

	private static final Logger LOGGER =  Logger.getLogger(OpenQuotesPage.class);
	
	public OpenQuotesPage(TestDataInstance testDataI, IHTMLWebDriver pHDriver) throws Exception {
		super(testDataI, pHDriver);
		super.initObjectRepository(this);
		pHDriver.waitForPageLoad();
		log(Status.INFO, "Page Object Model is generated for Open Quotes Page.");
	}

	public void validateDeleteQuoteConfirm() throws Exception {

		log(Status.INFO, "validateDeleteQuoteConfirm process started");
		try {
			waitForPageLoad();
			try {
				elem.waitForElementToBeClickable(deleteIconFirstQuote);
			}catch(Exception e) {}
			List<WebElement> openQuotes = hDriver.findElements(openQuotesCard.getBy());
			if (null != openQuotes && openQuotes.size() >= 1) {

				WebElement firstQuote = openQuotes.get(0);
				String quoteId = firstQuote.getAttribute("data-index");

				waitForPageLoad();
				WebElement deleteIcon = hDriver.findElement(deleteIconFirstQuote.getBy());

				new Actions(driver).moveToElement(deleteIcon);
				log(Status.INFO, "Select  quote[" + quoteId + "] for deleting");

				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].scrollIntoView(true);", deleteIcon);
				executor.executeScript("arguments[0].click();", deleteIcon);
				log(Status.INFO, "Quote Delete icon clicked");

				verifyDeleteWarningMessage();
				deleteConfirmQuote(quoteId);

			} else {
				log(Status.WARNING, "No available quotes for testing");
			}

		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps in validateDeleteQuoteConfirm process as previous step is failed");
		}
		super.log(Status.INFO, "validateDeleteQuoteConfirm process is completed");
	}
	
	public void validateDeleteQuoteCancel() throws Exception {

		log(Status.INFO, "validateDeleteQuoteCancel process started");
		try {
			waitForPageLoad();
			try {
				elem.waitForElementToBeClickable(deleteIconFirstQuote);
			}catch(Exception e) {}
			
			List<WebElement> openQuotes = hDriver.findElements(openQuotesCard.getBy());
			if (null != openQuotes && openQuotes.size() >= 1) {

				WebElement firstQuote = openQuotes.get(0);
				String quoteId = firstQuote.getAttribute("data-index");

				waitForPageLoad();
				WebElement deleteIcon = hDriver.findElement(deleteIconFirstQuote.getBy());

				new Actions(driver).moveToElement(deleteIcon);
				log(Status.INFO, "Select  quote[" + quoteId + "] for deleting");

				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].scrollIntoView(true);", deleteIcon);
				executor.executeScript("arguments[0].click();", deleteIcon);
				log(Status.INFO, "Quote Delete icon clicked");

				verifyDeleteWarningMessage();
				deleteCancelQuote(quoteId);

			} else {
				log(Status.WARNING, "No available quotes for testing");
			}

		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps in validateDeleteQuoteCancel process as previous step is failed");
		}
		super.log(Status.INFO, "validateDeleteQuoteCancel process is completed");
	}

	private void verifyDeleteWarningMessage() throws Exception {

		try {
			String warningText = elem.getText(deleteWarningMsg);
			if (null != warningText && warningText.equals("Do you want to Delete the quote?")) {
				log(Status.PASS, "Warning message : \"" + warningText
						+ "\" is equal to expected value[Do you want to Delete the quote?]");
			} else {
				log(Status.FAIL, "Warning message : [" + warningText
						+ "] is not equal to expected value[Do you want to Delete the quote?]");
			}
		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps as previous step is failed");
		}

	}

	private void deleteCancelQuote(String quoteId) throws Exception {
		try {
			String cancelBtnText = elem.getText(deleteCancelBtn);
			if (null != cancelBtnText && cancelBtnText.equals("Cancel")) {
				log(Status.PASS, "Cancel button is available");
			} else {
				log(Status.FAIL, "Delete button is not available as its visible text is [" + cancelBtnText + "]");
			}

			elem.click(deleteCancelBtn);
			waitForPageLoad();

			List<WebElement> searchQuotes = hDriver
					.findElements(quoteWithDataIndex.replaceDynamicValue(quoteId).getBy());
			if (null == searchQuotes || searchQuotes.size() == 0) {
				log(Status.FAIL, "Delete cancelled Quote is not available in open quotes");
			} else {
				log(Status.PASS, "Delete cancelled Quote is available in open quotes");
			}

		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps in deleteCancelQuote process as previous step is failed");
		}
	}

	private void deleteConfirmQuote(String quoteId) throws Exception {
		try {
			String deleteBtnText = elem.getText(deleteConfirmBtn);
			if (null != deleteBtnText && deleteBtnText.equals("Delete")) {
				log(Status.PASS, "Delete button is available");
			} else {
				log(Status.FAIL, "Delete button is not available as its visible text is [" + deleteBtnText + "]");
			}

			elem.click(deleteConfirmBtn);
			waitForPageLoad();

			List<WebElement> searchQuotes = hDriver
					.findElements(quoteWithDataIndex.replaceDynamicValue(quoteId).getBy());
			if (null == searchQuotes || searchQuotes.size() == 0) {
				log(Status.PASS, "Deleted Quote is not available in open quotes");

			} else {
				log(Status.FAIL, "Deleted Quote is available in open quotes");
			}

		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps in deleteConfirmQuote process as previous step is failed");
		}
	}
	

	public void verifySavedQuote(Quote quote) throws Exception {

		log(Status.INFO, "verifySavedQuote process started");
		try {
			waitForPageLoad();

			elem.setTextAndEnter(txtSearch, quote.vessels().vesselList().get(0).vesselDetail().name()); 
			waitForPageLoad();
			
			List<WebElement> openQuotes = hDriver.findElements(openQuotesCard.getBy());
			if (null != openQuotes && openQuotes.size() >= 1) {
				log(Status.PASS, "Saved Quote is available",true);
			}else {
				log(Status.FAIL, "Saved Quote is not available", true);
			}

				

		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps in verifySavedQuote process as previous step is failed");
		}
		super.log(Status.INFO, "verifySavedQuote process is completed");
	}
	
	public void quoteList() {
		log(Status.INFO, "vesselList process started ");
		try {
			
			IServiceClient serviceClient = new ServiceClient(driver);
			Response response = serviceClient.get​GetQuoteListResponse(9, false, "LastModified");
			LOGGER.debug("Vessels Quote List Response  :: " + response.asPrettyString());
			
		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps in vesselList process as previous step is failed");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		super.log(Status.INFO, "vesselList process ended");
	}

	public void waitForPageLoad() throws Exception {
		try {
			hDriver.waitForPageLoad();
			elem.waitForElementToBeClickable(addNewQuote);
		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps as previous step is failed");
		}
	}
	
	
	//------------------- Getter and Setter ---------------------------------------

	public OrStruct getOpenQuotesCard() {
		return openQuotesCard;
	}

	public void setOpenQuotesCard(OrStruct openQuotesCard) {
		this.openQuotesCard = openQuotesCard;
	}

	public OrStruct getDeleteIconFirstQuote() {
		return deleteIconFirstQuote;
	}

	public void setDeleteIconFirstQuote(OrStruct deleteIconFirstQuote) {
		this.deleteIconFirstQuote = deleteIconFirstQuote;
	}

	public OrStruct getDeleteWarningMsg() {
		return deleteWarningMsg;
	}

	public void setDeleteWarningMsg(OrStruct deleteWarningMsg) {
		this.deleteWarningMsg = deleteWarningMsg;
	}

	public OrStruct getDeleteCancelBtn() {
		return deleteCancelBtn;
	}

	public void setDeleteCancelBtn(OrStruct deleteCancelBtn) {
		this.deleteCancelBtn = deleteCancelBtn;
	}

	public OrStruct getDeleteConfirmBtn() {
		return deleteConfirmBtn;
	}

	public void setDeleteConfirmBtn(OrStruct deleteConfirmBtn) {
		this.deleteConfirmBtn = deleteConfirmBtn;
	}

	public OrStruct getQuoteWithDataIndex() {
		return quoteWithDataIndex;
	}

	public void setQuoteWithDataIndex(OrStruct quoteWithDataIndex) {
		this.quoteWithDataIndex = quoteWithDataIndex;
	}

	public OrStruct getAddNewQuote() {
		return addNewQuote;
	}

	public void setAddNewQuote(OrStruct addNewQuote) {
		this.addNewQuote = addNewQuote;
	}

	public OrStruct getTxtSearch() {
		return txtSearch;
	}

	public void setTxtSearch(OrStruct txtSearch) {
		this.txtSearch = txtSearch;
	}



	

	
	
	

}
