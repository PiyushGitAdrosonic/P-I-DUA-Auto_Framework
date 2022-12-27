package page;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.as.autot.business.BusinessException;
import com.as.autot.business.Structure;
import com.as.autot.core.BasePage;
import com.as.autot.core.common.TestDataInstance;
import com.as.autot.core.or.OrStruct;
import com.as.autot.tool.wrapper.api.IHTMLWebDriver;
import com.as.autot.utility.DataHandler;
import com.aventstack.extentreports.Status;

import data.Quote;
import data.Quote.Instalment;
import data.Quote.PolicyDates;
import data.Quote.Vessel;
import data.Quote.VesselBasicDetail;
import data.Quote.VesselPricingDetail;

/**
 * This Class is presenting Page-Object Model for P&I-DUA New Quote Page. It
 * includes all corresponding action functionality on the page. All the function
 * defined here are reusable to different test script Note : All data should be
 * parameterized from test script
 * 
 * @author Anuj Tripathi
 *
 */
public class NewQuotePage extends BasePage {

	@Structure(name = "txtNewQuoteHeader")
	private OrStruct txtNewQuoteHeader;

	@Structure(name = "quoteTemplate")
	private OrStruct quoteTemplate;

	@Structure(name = "quoteCreateDate")
	private OrStruct quoteCreateDate;

	@Structure(name = "closeButton")
	private OrStruct closeButton;

	@Structure(name = "closeConfirmButton")
	private OrStruct closeConfirmButton;

	@Structure(name = "saveButton")
	private OrStruct saveButton;

	@Structure(name = "bindButton")
	private OrStruct bindButton;

	@Structure(name = "acceptAndBindButton")
	private OrStruct acceptAndBindButton;

	@Structure(name = "bindQuoteButton")
	private OrStruct bindQuoteButton;

	@Structure(name = "cancelButton")
	private OrStruct cancelButton;

	@Structure(name = "referButton")
	private OrStruct referButton;

	@Structure(name = "calendar")
	private OrStruct calendar;

	@Structure(name = "calendarYearList")
	private OrStruct calendarYearList;

	@Structure(name = "policyDatesCheckBox")
	private OrStruct policyDatesCheckBox;

	@Structure(name = "memberDetailsCheckBox")
	private OrStruct memberDetailsCheckBox;

	@Structure(name = "addAdditionalVessel")
	private OrStruct addAdditionalVessel;

	@Structure(name = "vesselName")
	private OrStruct vesselName;

	@Structure(name = "vesselImo")
	private OrStruct vesselImo;

	@Structure(name = "vesselRefNo")
	private OrStruct vesselRefNo;

	@Structure(name = "vesselLoa")
	private OrStruct vesselLoa;

	@Structure(name = "vesselSubmarine")
	private OrStruct vesselSubmarine;

	@Structure(name = "vesselDistinctiveNo")
	private OrStruct vesselDistinctiveNo;

	@Structure(name = "vesselYearBuild")
	private OrStruct vesselYearBuild;

	@Structure(name = "vesselUsage")
	private OrStruct vesselUsage;

	@Structure(name = "vesselGt")
	private OrStruct vesselGt;

	@Structure(name = "vesselFlag")
	private OrStruct vesselFlag;

	@Structure(name = "vesselCrew")
	private OrStruct vesselCrew;

	@Structure(name = "vesselUsaRegistered")
	private OrStruct vesselUsaRegistered;

	@Structure(name = "navigationAreasExpand")
	private OrStruct navigationAreasExpand;

	@Structure(name = "navigationAreaWorldwide")
	private OrStruct navigationAreaWorldwide;

	@Structure(name = "navigationAreaWorldwideWithMonthInUsaWaters")
	private OrStruct navigationAreaWorldwideWithMonthInUsaWaters;

	@Structure(name = "navigationAreaAfrica")
	private OrStruct navigationAreaAfrica;

	@Structure(name = "navigationAreaAsia")
	private OrStruct navigationAreaAsia;

	@Structure(name = "navigationAreaAustralasia")
	private OrStruct navigationAreaAustralasia;

	@Structure(name = "navigationAreaCanada")
	private OrStruct navigationAreaCanada;

	@Structure(name = "navigationAreaCaribbean")
	private OrStruct navigationAreaCaribbean;

	@Structure(name = "navigationAreaEurope")
	private OrStruct navigationAreaEurope;

	@Structure(name = "navigationAreaMiddleEast")
	private OrStruct navigationAreaMiddleEast;

	@Structure(name = "navigationAreaSouthAndCentralAmerica")
	private OrStruct navigationAreaSouthAndCentralAmerica;

	@Structure(name = "navigationAreaUSANoTimeRestriction")
	private OrStruct navigationAreaUSANoTimeRestriction;

	@Structure(name = "navigationAreaUSAOneMonth")
	private OrStruct navigationAreaUSAOneMonth;

	@Structure(name = "waterSports")
	private OrStruct waterSports;

	@Structure(name = "racing")
	private OrStruct racing;

	@Structure(name = "currency")
	private OrStruct currency;

	@Structure(name = "addCoverButton")
	private OrStruct addCoverButton;

	@Structure(name = "instalmentPattern")
	private OrStruct instalmentPattern;

	@Structure(name = "totalGrossPremiumWidget")
	private OrStruct totalGrossPremiumWidget;

	@Structure(name = "expandSection")
	private OrStruct expandSection;

	private static final Logger LOGGER =  Logger.getLogger(NewQuotePage.class);
	
	public NewQuotePage(TestDataInstance testDataI, IHTMLWebDriver pHDriver) throws Exception {
		super(testDataI, pHDriver);
		super.initObjectRepository(this);
		pHDriver.waitForPageLoad();
		log(Status.INFO, "Page Object Model is generated for New Quote Page.");
	}

	public void fillNewQuoteDetails(Quote quote) throws Exception {
		log(Status.INFO, "addNewQuote process started ");
		try {
			waitForPageLoad();
			expandSections();
			fillPolicyDates(quote);
			fillMemberDetails(quote);
			fillVesselDetails(quote);
			fillInstalment(quote);

			hDriver.waitForPageLoad();

		} catch (BusinessException be) {
			log(Status.FAIL, be.getbMsg());
			log(Status.INFO, "Skipping further steps in addNewQuote process as previous step is failed");
		}
		log(Status.INFO, "addNewQuote process ended");
	}

	public void validateNewQuoteHeader() throws BusinessException {
		log(Status.INFO, "validateNewQuoteHeader process started ");
		String header = elem.getText(txtNewQuoteHeader);
		String template = elem.getText(quoteTemplate);
		String date = elem.getText(quoteCreateDate);
		date = date.substring(date.indexOf(":") + 2);

		if (header.equalsIgnoreCase("New Quote")) {
			log(Status.PASS, "Text [" + header + "] is displayed on header of New Quote form");
		} else {
			log(Status.FAIL, "Text [" + header + "] is NOT displayed on header of New Quote form");
		}

		if (template.equalsIgnoreCase("Yacht")) {
			log(Status.PASS, "Vessel Template [" + template + "] is selected by default");
		} else {
			log(Status.FAIL, "Vessel Template [" + template + "] is NOT selected by default");
		}

		if (date.equalsIgnoreCase(DataHandler.getCurrentDate())) {
			log(Status.PASS, "Current date [" + date + "] is displayed on header of New Quote form");
		} else {
			log(Status.FAIL, "Current date [" + date + "] is NOT displayed on header of New Quote form");
		}

		log(Status.INFO, "validateNewQuoteHeader process ended");
	}

	private void fillVesselDetails(Quote quote) throws Exception {
		for (int i = 0; i < quote.vessels().vesselList().size(); i++) {
			fillVessel(quote.vessels().vesselList().get(i), i);
		}
	}

	private void fillVessel(Vessel vessel, int i) throws Exception {
		VesselBasicDetail vesselInfo = vessel.vesselDetail();
		VesselPricingDetail pricinglInfo = vessel.vesselPricingDetail();
		if (i > 0) {
			elem.click(addAdditionalVessel);
		}
		elem.clearAndSetText(elem.getWebElements(vesselName).get(i), vesselInfo.name());
		elem.clearAndSetText(elem.getWebElements(vesselImo).get(i), vesselInfo.imo());
		//elem.clearAndSetText(elem.getWebElements(vesselRefNo).get(i), vesselInfo.refNo());
		elem.clearAndSetText(elem.getWebElements(vesselLoa).get(i), vesselInfo.loa());
		elem.setComboBoxValue(elem.getWebElements(vesselSubmarine).get(i), vesselInfo.submarine());
		elem.clearAndSetText(elem.getWebElements(vesselDistinctiveNo).get(i), vesselInfo.distinctiveNo());
		elem.clearAndSetText(elem.getWebElements(vesselYearBuild).get(i), vesselInfo.yearBuild());
		elem.setComboBoxValue(elem.getWebElements(vesselUsage).get(i), pricinglInfo.usage());
		elem.clearAndSetText(elem.getWebElements(vesselGt).get(i), pricinglInfo.gt());
		elem.setComboBoxValue(elem.getWebElements(vesselFlag).get(i), pricinglInfo.flag());
		elem.clearAndSetText(elem.getWebElements(vesselCrew).get(i), pricinglInfo.crew());
		elem.setComboBoxValue(elem.getWebElements(vesselUsaRegistered).get(i), pricinglInfo.usaRegistered());
		elem.setComboBoxValue(elem.getWebElements(waterSports).get(i), pricinglInfo.waterSports());
		elem.setComboBoxValue(elem.getWebElements(racing).get(i), pricinglInfo.racing());
		elem.setComboBoxValue(elem.getWebElements(currency).get(i), pricinglInfo.currency());
		log(Status.INFO, "Vessel details are filled for vessel " + (i + 1) + " : Vessel Name >>" + vesselInfo.name(),
				true);
		setNavigationAreas(pricinglInfo.navigationAreas(), i);

	}

	private void setNavigationAreas(List<String> navigationAreas, int i) throws Exception {
		elem.click(elem.getWebElements(navigationAreasExpand).get(i));
		hDriver.ThreadSleep(1);
		for (String navArea : navigationAreas) {
			switch (navArea) {
			case "Worldwide":
				elem.click(elem.getWebElements(navigationAreaWorldwide).get(i));
				break;

			case "Worldwide with up to one month in USA waters":
				elem.click(elem.getWebElements(navigationAreaUSAOneMonth).get(i));
				break;

			case "Africa":
				elem.click(elem.getWebElements(navigationAreaAfrica).get(i));
				break;

			case "Asia":
				elem.click(elem.getWebElements(navigationAreaAsia).get(i));
				break;

			case "Australasia":
				elem.click(elem.getWebElements(navigationAreaAustralasia).get(i));
				break;

			case "Canada":
				elem.click(elem.getWebElements(navigationAreaCanada).get(i));
				break;

			case "Caribbean":
				elem.click(elem.getWebElements(navigationAreaCaribbean).get(i));
				break;

			case "Europe":
				elem.click(elem.getWebElements(navigationAreaEurope).get(i));
				break;

			case "Middle East":
				elem.click(elem.getWebElements(navigationAreaMiddleEast).get(i));
				break;

			case "South and Central America (excluding US waters)":
				elem.click(elem.getWebElements(navigationAreaSouthAndCentralAmerica).get(i));
				break;

			case "USA (no time restriction)":
				elem.click(elem.getWebElements(navigationAreaUSANoTimeRestriction).get(i));
				break;

			case "USA for up to one month per year":
				elem.click(elem.getWebElements(navigationAreaUSAOneMonth).get(i));
				break;
			}
		}
	}

	private void fillInstalment(Quote quote) throws Exception {
		Instalment ip = quote.instalment();
		elem.setComboBoxValue(instalmentPattern, ip.toString());
	}

	private void fillMemberDetails(Quote quote) {
		elem.setCheckbox(memberDetailsCheckBox, true);
	}

	private void fillPolicyDates(Quote quote) throws Exception {
		log(Status.INFO, "fillPolicyDates process ended");
		
		PolicyDates policyDates = quote.policyDates();
		String strXpath1 = "//div[contains(text(),'";
		String strXpath2 = "')]";
		String strDay;
		int intMonth;
		String strMonth;
		String stryear;
		List<String> monthList = Arrays.asList(
				new String[] { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" });

		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				elem.click(elem.getWebElements(calendar).get(i));

				strDay = policyDates.startDate().split("/")[0];
				intMonth = Integer.parseInt(policyDates.startDate().split("/")[1]) - 1;
				stryear = policyDates.startDate().split("/")[2];
				strMonth = monthList.get(intMonth);

			} else {
				elem.click(elem.getWebElements(calendar).get(i));

				strDay = policyDates.endDate().split("/")[0];
				intMonth = Integer.parseInt(policyDates.endDate().split("/")[1]) - 1;
				stryear = policyDates.endDate().split("/")[2];
				strMonth = monthList.get(intMonth);
			}

			if (strDay.indexOf("0") == 0) {
				strDay = strDay.substring(strDay.indexOf("0") + 1);
			}

			elem.click(calendarYearList);

			WebElement ulYear = driver.findElement(By.xpath(strXpath1 + stryear + strXpath2));
			elem.click(ulYear);
			waitForPageLoad();
			WebElement ulMonth = driver.findElement(By.xpath(strXpath1 + strMonth + strXpath2));
			elem.click(ulMonth);
			waitForPageLoad();
			WebElement ulDay = driver.findElement(By.xpath("//tbody//div[contains(text(),'" + strDay + "')]"));
			elem.click(ulDay);

		}
		log(Status.INFO, "Policy Start Date set as: "+ policyDates.startDate());
		log(Status.INFO, "Policy Start Date set as: "+ policyDates.endDate());

		elem.setCheckbox(policyDatesCheckBox, true);
		
		log(Status.INFO, "fillPolicyDates process ended");
	}

	private void expandSections() throws Exception {

		List<WebElement> newQuotesectionExpand = driver.findElements(expandSection.getBy());
		for (int i = 0; i < newQuotesectionExpand.size(); i++) {
			waitForPageLoad();
			newQuotesectionExpand.get(i).click();
		}

	}

	// ------------------- Getter and Setter ---------------------------------------

	public void waitForPageLoad() throws Exception {
		try {
			hDriver.waitForPageLoad();
			elem.waitForElementToBeClickable(vesselName, vesselName.getwaitInSec());
		} catch (BusinessException be) {
			LOGGER.error(be.getbMsg());
			log(Status.FAIL, be.getbMsg());
		}
	}

	public OrStruct getQuotetemplate() {
		return quoteTemplate;
	}

	public void setQuotetemplate(OrStruct quoteTemplate) {
		this.quoteTemplate = quoteTemplate;
	}

	public OrStruct getQuoteCreateDate() {
		return quoteCreateDate;
	}

	public void setQuoteCreateDate(OrStruct quoteCreateDate) {
		this.quoteCreateDate = quoteCreateDate;
	}

	public OrStruct getCloseButton() {
		return closeButton;
	}

	public void setCloseButton(OrStruct closeButton) {
		this.closeButton = closeButton;
	}

	public OrStruct getCloseConfirmButton() {
		return closeConfirmButton;
	}

	public void setCloseConfirmButton(OrStruct closeConfirmButton) {
		this.closeConfirmButton = closeConfirmButton;
	}

	public OrStruct getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(OrStruct saveButton) {
		this.saveButton = saveButton;
	}

	public OrStruct getBindButton() {
		return bindButton;
	}

	public void setBindButton(OrStruct bindButton) {
		this.bindButton = bindButton;
	}

	public OrStruct getAcceptAndBindButton() {
		return acceptAndBindButton;
	}

	public void setAcceptAndBindButton(OrStruct acceptAndBindButton) {
		this.acceptAndBindButton = acceptAndBindButton;
	}

	public OrStruct getBindQuoteButton() {
		return bindQuoteButton;
	}

	public void setBindQuoteButton(OrStruct bindQuoteButton) {
		this.bindQuoteButton = bindQuoteButton;
	}

	public OrStruct getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(OrStruct cancelButton) {
		this.cancelButton = cancelButton;
	}

	public OrStruct getReferButton() {
		return referButton;
	}

	public void setReferButton(OrStruct referButton) {
		this.referButton = referButton;
	}

	public OrStruct getcalendarYearList() {
		return calendarYearList;
	}

	public void setcalendarYearList(OrStruct policyStartDate) {
		this.calendarYearList = policyStartDate;
	}

	public OrStruct getcalendar() {
		return calendar;
	}

	public void setcalendar(OrStruct policyEndDate) {
		this.calendar = policyEndDate;
	}

	public OrStruct getPolicyDatesCheckBox() {
		return policyDatesCheckBox;
	}

	public void setPolicyDatesCheckBox(OrStruct policyDatesCheckBox) {
		this.policyDatesCheckBox = policyDatesCheckBox;
	}

	public OrStruct getMemberDetailsCheckBox() {
		return memberDetailsCheckBox;
	}

	public void setMemberDetailsCheckBox(OrStruct memberDetailsCheckBox) {
		this.memberDetailsCheckBox = memberDetailsCheckBox;
	}

	public OrStruct getAddAdditionalVessel() {
		return addAdditionalVessel;
	}

	public void setAddAdditionalVessel(OrStruct addAdditionalVessel) {
		this.addAdditionalVessel = addAdditionalVessel;
	}

	public OrStruct getVesselName() {
		return vesselName;
	}

	public void setVesselName(OrStruct vesselName) {
		this.vesselName = vesselName;
	}

	public OrStruct getVesselImo() {
		return vesselImo;
	}

	public void setVesselImo(OrStruct vesselImo) {
		this.vesselImo = vesselImo;
	}

	public OrStruct getVesselRefNo() {
		return vesselRefNo;
	}

	public void setVesselRefNo(OrStruct vesselRefNo) {
		this.vesselRefNo = vesselRefNo;
	}

	public OrStruct getVesselLoa() {
		return vesselLoa;
	}

	public void setVesselLoa(OrStruct vesselLoa) {
		this.vesselLoa = vesselLoa;
	}

	public OrStruct getVesselSubmarine() {
		return vesselSubmarine;
	}

	public void setVesselSubmarine(OrStruct vesselSubmarine) {
		this.vesselSubmarine = vesselSubmarine;
	}

	public OrStruct getVesselDistinctiveNo() {
		return vesselDistinctiveNo;
	}

	public void setVesselDistinctiveNo(OrStruct vesselDistinctiveNo) {
		this.vesselDistinctiveNo = vesselDistinctiveNo;
	}

	public OrStruct getVesselYearBuild() {
		return vesselYearBuild;
	}

	public void setVesselYearBuild(OrStruct vesselYearBuild) {
		this.vesselYearBuild = vesselYearBuild;
	}

	public OrStruct getVesselUsage() {
		return vesselUsage;
	}

	public void setVesselUsage(OrStruct vesselUsage) {
		this.vesselUsage = vesselUsage;
	}

	public OrStruct getVesselGt() {
		return vesselGt;
	}

	public void setVesselGt(OrStruct vesselGt) {
		this.vesselGt = vesselGt;
	}

	public OrStruct getVesselFlag() {
		return vesselFlag;
	}

	public void setVesselFlag(OrStruct vesselFlag) {
		this.vesselFlag = vesselFlag;
	}

	public OrStruct getVesselCrew() {
		return vesselCrew;
	}

	public void setVesselCrew(OrStruct vesselCrew) {
		this.vesselCrew = vesselCrew;
	}

	public OrStruct getVesselUsaRegistered() {
		return vesselUsaRegistered;
	}

	public void setVesselUsaRegistered(OrStruct vesselUsaRegistered) {
		this.vesselUsaRegistered = vesselUsaRegistered;
	}

	public OrStruct getNavigationAreasExpand() {
		return navigationAreasExpand;
	}

	public void setNavigationAreasExpand(OrStruct navigationAreasExpand) {
		this.navigationAreasExpand = navigationAreasExpand;
	}

	public OrStruct getNavigationAreaWorldwide() {
		return navigationAreaWorldwide;
	}

	public void setNavigationAreaWorldwide(OrStruct navigationAreaWorldwide) {
		this.navigationAreaWorldwide = navigationAreaWorldwide;
	}

	public OrStruct getWaterSports() {
		return waterSports;
	}

	public void setWaterSports(OrStruct waterSports) {
		this.waterSports = waterSports;
	}

	public OrStruct getRacing() {
		return racing;
	}

	public void setRacing(OrStruct racing) {
		this.racing = racing;
	}

	public OrStruct getCurrency() {
		return currency;
	}

	public void setCurrency(OrStruct currency) {
		this.currency = currency;
	}

	public OrStruct getAddCoverButton() {
		return addCoverButton;
	}

	public void setAddCoverButton(OrStruct addCoverButton) {
		this.addCoverButton = addCoverButton;
	}

	public OrStruct getInstalmentPattern() {
		return instalmentPattern;
	}

	public OrStruct getTxtNewQuoteHeader() {
		return txtNewQuoteHeader;
	}

	public void setTxtNewQuoteHeader(OrStruct txtNewQuoteHeader) {
		this.txtNewQuoteHeader = txtNewQuoteHeader;
	}

	public OrStruct getNavigationAreaWorldwideWithMonthInUsaWaters() {
		return navigationAreaWorldwideWithMonthInUsaWaters;
	}

	public void setNavigationAreaWorldwideWithMonthInUsaWaters(OrStruct navigationAreaWorldwideWithMonthInUsaWaters) {
		this.navigationAreaWorldwideWithMonthInUsaWaters = navigationAreaWorldwideWithMonthInUsaWaters;
	}

	public OrStruct getNavigationAreaAfrica() {
		return navigationAreaAfrica;
	}

	public void setNavigationAreaAfrica(OrStruct navigationAreaAfrica) {
		this.navigationAreaAfrica = navigationAreaAfrica;
	}

	public OrStruct getNavigationAreaAsia() {
		return navigationAreaAsia;
	}

	public void setNavigationAreaAsia(OrStruct navigationAreaAsia) {
		this.navigationAreaAsia = navigationAreaAsia;
	}

	public OrStruct getNavigationAreaAustralasia() {
		return navigationAreaAustralasia;
	}

	public void setNavigationAreaAustralasia(OrStruct navigationAreaAustralasia) {
		this.navigationAreaAustralasia = navigationAreaAustralasia;
	}

	public OrStruct getNavigationAreaCanada() {
		return navigationAreaCanada;
	}

	public void setNavigationAreaCanada(OrStruct navigationAreaCanada) {
		this.navigationAreaCanada = navigationAreaCanada;
	}

	public OrStruct getNavigationAreaCaribbean() {
		return navigationAreaCaribbean;
	}

	public void setNavigationAreaCaribbean(OrStruct navigationAreaCaribbean) {
		this.navigationAreaCaribbean = navigationAreaCaribbean;
	}

	public OrStruct getNavigationAreaEurope() {
		return navigationAreaEurope;
	}

	public void setNavigationAreaEurope(OrStruct navigationAreaEurope) {
		this.navigationAreaEurope = navigationAreaEurope;
	}

	public OrStruct getNavigationAreaMiddleEast() {
		return navigationAreaMiddleEast;
	}

	public void setNavigationAreaMiddleEast(OrStruct navigationAreaMiddleEast) {
		this.navigationAreaMiddleEast = navigationAreaMiddleEast;
	}

	public OrStruct getNavigationAreaSouthAndCentralAmerica() {
		return navigationAreaSouthAndCentralAmerica;
	}

	public void setNavigationAreaSouthAndCentralAmerica(OrStruct navigationAreaSouthAndCentralAmerica) {
		this.navigationAreaSouthAndCentralAmerica = navigationAreaSouthAndCentralAmerica;
	}

	public OrStruct getNavigationAreaUSANoTimeRestriction() {
		return navigationAreaUSANoTimeRestriction;
	}

	public void setNavigationAreaUSANoTimeRestriction(OrStruct navigationAreaUSANoTimeRestriction) {
		this.navigationAreaUSANoTimeRestriction = navigationAreaUSANoTimeRestriction;
	}

	public OrStruct getNavigationAreaUSAOneMonth() {
		return navigationAreaUSAOneMonth;
	}

	public void setNavigationAreaUSAOneMonth(OrStruct navigationAreaUSAOneMonth) {
		this.navigationAreaUSAOneMonth = navigationAreaUSAOneMonth;
	}

	public void setInstalmentPattern(OrStruct instalmentPattern) {
		this.instalmentPattern = instalmentPattern;
	}

	public OrStruct getTotalGrossPremiumWidget() {
		return totalGrossPremiumWidget;
	}

	public void setTotalGrossPremiumWidget(OrStruct totalGrossPremiumWidget) {
		this.totalGrossPremiumWidget = totalGrossPremiumWidget;
	}

	public OrStruct getExpandSection() {
		return expandSection;
	}

	public void setExpandSection(OrStruct expandSection) {
		this.expandSection = expandSection;
	}

}
