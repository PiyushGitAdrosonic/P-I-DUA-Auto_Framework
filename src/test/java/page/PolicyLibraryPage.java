package page;


import java.util.List;
import org.openqa.selenium.WebElement;
import com.as.autot.business.BusinessException;
import com.as.autot.business.Structure;
import com.as.autot.core.BasePage;
import com.as.autot.core.common.TestDataInstance;
import com.as.autot.core.or.OrStruct;
import com.as.autot.tool.wrapper.api.IHTMLWebDriver;
import com.aventstack.extentreports.Status;

/**
 * This Class is presenting Page-Object Model for P&I-DUA Dashboard Page It
 * includes all corresponding action functionality on the page. All the function
 * defined here are reusable to different test script Note : All data should be
 * parameterized from test script
 * 
 * @author Nainika Sinha
 *
 */
public class PolicyLibraryPage extends BasePage {
	
	
	@Structure(name = "PolicyPageheader")
	private OrStruct PolicyPageheader;

	@Structure(name = "PoliciesBtn")
	private OrStruct PoliciesBtn;
	
	@Structure(name = "LibraryContentBTNS")
	private OrStruct LibraryContentBTNS;
	
	@Structure(name = "SearchPolicies")
	private OrStruct SearchPolicies;
	
	@Structure(name = "ClosePolicyWindow")
	private OrStruct ClosePolicyWindow;
	
	@Structure(name = "PolicyColumn")
	private OrStruct PolicyColumn;
	
	@Structure(name = "MemberColumn")
	private OrStruct MemberColumn;
	
	@Structure(name = "PolicyStartColumn")
	private OrStruct PolicyStartColumn;
	
	@Structure(name = "PolicyEndColumn")
	private OrStruct PolicyEndColumn;
	
	@Structure(name = "PolicyStatusColumn")
	private OrStruct PolicyStatusColumn;
	
	@Structure(name = "NoOfRiskColumn")
	private OrStruct NoOfRiskColumn;
	
	@Structure(name = "TotalRows")
	private OrStruct TotalRows;
	
	@Structure(name = "PolicyRefNo")
	private OrStruct PolicyRefNo;
	
	@Structure(name = "PolicyMemberName")
	private OrStruct PolicyMemberName;
	
	@Structure(name = "PolicyMemberRefNo")
	private OrStruct PolicyMemberRefNo;
	
	@Structure(name = "PolicyStartDate")
	private OrStruct PolicyStartDate;
	
	@Structure(name = "PolicyEndDate")
	private OrStruct PolicyEndDate;
	
	@Structure(name = "PolicyStatus")
	private OrStruct PolicyStatus;
	
	@Structure(name = "NoOfRiskAttached")
	private OrStruct NoOfRiskAttached;
	
	
	
	
	
	
	public PolicyLibraryPage(TestDataInstance testDataI, IHTMLWebDriver pHDriver) {
		
		super(testDataI, pHDriver);
		super.initObjectRepository(this);
		pHDriver.waitForPageLoad();
		log(Status.INFO, "Page Object Model is generated for Policy Library Page.");
		
		pHDriver.waitForPageLoad();
		try {
			boolean isPageLoaded = elem.isAvailableOnPage(PolicyPageheader, PolicyPageheader.getwaitInSec());
			if (isPageLoaded) {
				String PolicyPageHeader = elem.getText(PolicyPageheader);
				log(Status.PASS, "Policy header page :-  [" + PolicyPageHeader + "] is visible successfully", true);
			} else {
				log(Status.FAIL, "Unable to load Policy Library page", true);

			}
		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps as previous step is failed");
		}
		
		
	}
	
	
	public void validatePolicyTableColumns(IHTMLWebDriver pHDriver) throws BusinessException {
		log(Status.INFO, "Policy Table Columns verification started");
		
		try {
			if (elem.isAvailableOnPage(PolicyColumn,PolicyColumn.getwaitInSec())) {
				String PolicyColumnText = elem.getText(PolicyColumn);
				log(Status.PASS, "[" + PolicyColumn.getName() + "] :- ["+ PolicyColumnText  +"] is available on the page ");
			} else {
				log(Status.FAIL, "[" + PolicyColumn.getName() + "] is not available on the page");
			}
			if (elem.isAvailableOnPage(MemberColumn, MemberColumn.getwaitInSec())) {
				String MemberColumnText = elem.getText(MemberColumn);
				log(Status.PASS, "[" + MemberColumn.getName() + "]:- ["+ MemberColumnText  +"] is available on the page");
			} else {
				log(Status.FAIL, "[" + MemberColumn.getName() + "] is not available on the page");
			}
			if (elem.isAvailableOnPage(PolicyStartColumn, PolicyStartColumn.getwaitInSec())) {
				String PolicyStartColumnText = elem.getText(PolicyStartColumn);
				log(Status.PASS, "[" + PolicyStartColumn.getName() + "]:-["+ PolicyStartColumnText  +"] is available on the page");
			} else {
				log(Status.FAIL, "[" + PolicyStartColumn.getName() + "] is not available on the page");
			}
			if (elem.isAvailableOnPage(PolicyEndColumn, PolicyEndColumn.getwaitInSec())) {
				String PolicyEndColumnText = elem.getText(PolicyEndColumn);
				log(Status.PASS, "[" + PolicyEndColumn.getName() + "]:-["+ PolicyEndColumnText  +"] is available on the page");
			} else {
				log(Status.FAIL, "[" + PolicyEndColumn.getName() + "] is not available on the page");
			}
			if (elem.isAvailableOnPage(PolicyStatusColumn, PolicyStatusColumn.getwaitInSec())) {
				String PolicyStatusColumnText = elem.getText(PolicyStatusColumn);
				log(Status.PASS, "[" + PolicyStatusColumn.getName() + "]:-["+ PolicyStatusColumnText  +"] is available on the page");
			} else {
				log(Status.FAIL, "[" + PolicyStatusColumn.getName() + "] is not available on the page");
			}
			if (elem.isAvailableOnPage(NoOfRiskColumn, NoOfRiskColumn.getwaitInSec())) {
				String PolicyNoOfRiskColumnText = elem.getText(NoOfRiskColumn);
				log(Status.PASS, "[" + NoOfRiskColumn.getName() + "]:-["+ PolicyNoOfRiskColumnText  +"] is available on the page");
			} else {
				log(Status.FAIL, "[" + NoOfRiskColumn.getName() + "] is not available on the page");
			}
			
			
		} catch (Exception be) {
			log(Status.FAIL, be.getMessage());
			log(Status.INFO, "Skipping further steps in validatePolicyTableHeaders process as previous step is failed");
		}
		log(Status.INFO, "Policy Table Columns verification ended");
		

	}
	
	
	public void VerifyPolicyDetails() {
		
		//PolicyRefNo.setLocValue("//pandi-policy-data-table//pandi-data-table//following::tbody//tr[<REPL>]/child::td[3]//span");
		log(Status.INFO,"Total Policy Rows details started");
		try {
			hDriver.waitForPageLoad();

		} catch (Exception e) {
		}
		
		List<WebElement> TotalListRows = hDriver.findElements(TotalRows.getBy());
		if(null!= TotalListRows && TotalListRows.size() >= 0  ) {
		
			log(Status.INFO,"TotalListRows count is :-" + TotalListRows.size());
			System.out.println("  TotalListRows count is :-" + TotalListRows.size());
			
			for(int i=1; i<=TotalListRows.size(); i+=2) {
				
				OrStruct policyRefName = PolicyRefNo.replaceDynamicValue(i+"");
				System.out.println("This is my new xpath"+ PolicyRefNo);
				System.out.println("Printing the PolicyRefNo :- " + elem.getWebElements(PolicyRefNo).get(0).getText());
				
				PolicyMemberName.replaceDynamicValue(i+"");
				System.out.println("Printing the PolicyMemberName :- " + elem.getWebElements(PolicyMemberName).get(0).getText());
				
				PolicyMemberRefNo.replaceDynamicValue(i+"");
				System.out.println("Printing the PolicyMemberRefNo :- " + elem.getWebElements(PolicyMemberRefNo).get(0).getText());
				
				PolicyStartDate.replaceDynamicValue(i+"");
				System.out.println("Printing the PolicyStartDate :- " + elem.getWebElements(PolicyStartDate).get(0).getText());
				
				PolicyEndDate.replaceDynamicValue(i+"");
				System.out.println("Printing the PolicyEndDate :- " + elem.getWebElements(PolicyEndDate).get(0).getText());
				
				PolicyStatus.replaceDynamicValue(i+"");
				System.out.println("Printing the PolicyStatus :- " + elem.getWebElements(PolicyStatus).get(0).getText());
				
				NoOfRiskAttached.replaceDynamicValue(i+"");
				System.out.println("Printing the NoOfRiskAttached :- " + elem.getWebElements(NoOfRiskAttached).get(0).getText());
				
				
				
			}
			
			
		}
		else {
			log(Status.WARNING, "No Records found for testing");
		}
		
		
		
	}
	
	
	public void PolicyStatusDetails() {
		log(Status.INFO,"Policy Status details started");
		
		try {
			hDriver.waitForPageLoad();

		} catch (Exception e) {
		}
		
		List<WebElement> TotalListRows = hDriver.findElements(TotalRows.getBy());
		if(null!= TotalListRows && TotalListRows.size() >= 0  ) {
		
			//log(Status.INFO,"TotalListRows count is :-" + TotalListRows.size());
			System.out.println("  TotalListRows count is :-" + TotalListRows.size());
		
			String policyRefNo = elem.getWebElements(PolicyRefNo).get(0).getText();
			String policyStatus = elem.getWebElements(PolicyStatus).get(0).getText();
			
			System.out.println("This Policy:" + policyRefNo + " has associated with" +policyStatus + "Status");
			

		
		}	
	}

	public void ClosePolicyWindow() {
		log(Status.INFO, "Closing process started");
		
		try {
			elem.highLightElement(ClosePolicyWindow);
			elem.click(ClosePolicyWindow);
		} catch (Exception be) {
			log(Status.FAIL, be.getMessage());
			log(Status.INFO, "Skipping further steps in logout process as previous step is failed");
		}
		
		super.log(Status.INFO, "Closing process ended");
		
	}
	 
		  
}
