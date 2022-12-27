package com.as.autot.tool.wrapper.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.as.autot.core.common.TestDataInstance;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * This class will interact with application API layer and provide response to different resource service call 
 * @author Anuj Tripathi
 *
 */
public class ServiceClient implements IServiceClient {

	protected Logger LOGGER = Logger.getLogger(this.getClass());
	
	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String TOKEN_PREFIX_BEARER = "Bearer ";

	public static final String GET_VESSEL_RESOURCE_PATH = "/api/vessel/vessels";
	public static final String GET_MEMBER_RESOURCE_PATH = "/api/member/members";
	public static final String GET_POLICY_RESOURCE_PATH = "/api/policy/policies";
	public static final String GET_QUOTE_RESOURCE_PATH = "/api/enquiry/get-quotes-enquiry";
	public static final String GET_CONFIGURATION_RESOURCE_PATH = "/api/enquiry/configuration";
	public static final String GET_RENEWAL_SUMMARY_RESOURCE_PATH = "/api/policy/renewalsummary";
	public static final String GET_RENEWAL_POLICY_RESOURCE_PATH = "/api/policy/renewalpolicies";
	public static final String GET_LOSS_RATIO_RESOURCE_PATH = "/api/broker/broker/lossratio";
	
	public enum QueryParameter {
		pagesize, page, sortascending, sortcolumn, timespanindays, filter, filtertype
	}
	
	public enum Scope {
	    VESSEL_READ("vessel.read"),
	    MEMBER_READ("member.read"),
	    POLICY_READ("policy.read"),
	    ENQUIRY_READ("enquiry.read"),
	    CONFIG_READ("config.read"),
	    DOCUMENT_READ("document.read");

	    private String text;

	    Scope(String text) {
	        this.text = text;
	    }

	    public String getText() {
	        return this.text;
	    }

	    public static Scope fromString(String text) {
	        for (Scope s : Scope.values()) {
	            if (s.text.equalsIgnoreCase(text)) {
	                return s;
	            }
	        }
	        return null;
	    }
	}
	
	private WebDriver driver;

	Logger logger = Logger.getLogger(this.getClass());

	public ServiceClient(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * This method will provide service response for vessel enquires
	 */
	@Override
	public Response get​GetVesselListResponse(Map<String, String> queryParams) throws Exception {
		String[] scopeNames = { Scope.VESSEL_READ.getText() };
		Map<String, String> headerParams = getHeaderList(scopeNames);
		return getGetServiceResponse(GET_VESSEL_RESOURCE_PATH, queryParams, headerParams);
	}

	/**
	 * This method will provide service response for vessel enquires
	 */
	@Override
	public Response get​GetVesselListResponse(Integer pageSize, Integer page, Boolean isSortAscending,
			String sortByColumn) throws Exception {
		Map<String, String> queryParams = new LinkedHashMap<String, String>();
		queryParams.put(QueryParameter.pagesize.name(), pageSize.toString());
		queryParams.put(QueryParameter.page.name(), page.toString());
		queryParams.put(QueryParameter.sortascending.name(), isSortAscending.toString());
		queryParams.put(QueryParameter.sortcolumn.name(), "vesselname");
		return get​GetVesselListResponse(queryParams);
	}
	
	/**
	 * This method will provide service response for vessel enquires
	 */
	@Override
	public Response get​GetVesselListResponse() throws Exception {
		Map<String, String> queryParams = new LinkedHashMap<String, String>();
		queryParams.put(QueryParameter.pagesize.name(), "30");
		queryParams.put(QueryParameter.page.name(), "1");
		queryParams.put(QueryParameter.sortascending.name(), "true");
		queryParams.put(QueryParameter.sortcolumn.name(), "vesselname");
		return get​GetVesselListResponse(queryParams);
	}

	/**
	 * This method will provide service response for member enquires
	 */
	@Override
	public Response get​GetMemberListResponse(Map<String, String> queryParams) throws Exception {
		String[] scopeNames = { Scope.MEMBER_READ.getText() };
		Map<String, String> headerParams = getHeaderList(scopeNames);
		return getGetServiceResponse(GET_MEMBER_RESOURCE_PATH, queryParams, headerParams);
	}

	/**
	 * This method will provide service response for member enquires
	 */
	@Override
	public Response get​GetMemberListResponse(Integer pageSize, Integer page, Boolean isSortAscending,
			String sortByColumn) throws Exception {
		Map<String, String> queryParams = new LinkedHashMap<String, String>();
		queryParams.put(QueryParameter.pagesize.name(), pageSize.toString());
		queryParams.put(QueryParameter.page.name(), page.toString());
		queryParams.put(QueryParameter.sortascending.name(), isSortAscending.toString());
		queryParams.put(QueryParameter.sortcolumn.name(), sortByColumn);
		return get​GetMemberListResponse(queryParams);
	}

	/**
	 * This method will provide service response for member enquires
	 */
	@Override
	public Response get​GetMemberListResponse() throws Exception {
		Map<String, String> queryParams = new LinkedHashMap<String, String>();
		queryParams.put(QueryParameter.pagesize.name(), "30");
		queryParams.put(QueryParameter.page.name(), "1");
		queryParams.put(QueryParameter.sortascending.name(), "true");
		queryParams.put(QueryParameter.sortcolumn.name(), "membername");
		return get​GetMemberListResponse(queryParams);
	}

	/**
	 * This method will provide service response for policy enquires
	 */
	@Override
	public Response get​GetPolicyListResponse(Map<String, String> queryParams) throws Exception {
		String[] scopeNames = {Scope.POLICY_READ.getText()};
		Map<String, String> headerParams = getHeaderList(scopeNames);
		return getGetServiceResponse(GET_POLICY_RESOURCE_PATH, queryParams, headerParams);
	}

	/**
	 * This method will provide service response for policy enquires
	 */
	@Override
	public Response get​GetPolicyListResponse(Integer pageSize, Integer page, Boolean isSortAscending,
			String sortByColumn) throws Exception {
		Map<String, String> queryParams = new LinkedHashMap<String, String>();
		queryParams.put(QueryParameter.pagesize.name(), pageSize.toString());
		queryParams.put(QueryParameter.page.name(), page.toString());
		queryParams.put(QueryParameter.sortascending.name(), isSortAscending.toString());
		queryParams.put(QueryParameter.sortcolumn.name(), sortByColumn);
		return get​GetPolicyListResponse(queryParams);
	}

	/**
	 * This method will provide service response for policy enquires
	 */
	@Override
	public Response get​GetPolicyListResponse() throws Exception {
		Map<String, String> queryParams = new LinkedHashMap<String, String>();
		queryParams.put(QueryParameter.pagesize.name(), "30");
		queryParams.put(QueryParameter.page.name(), "1");
		queryParams.put(QueryParameter.sortascending.name(), "true");
		queryParams.put(QueryParameter.sortcolumn.name(), "membername");
		return get​GetPolicyListResponse(queryParams);
	}

	/**
	 * This method will provide service response for quote enquires
	 */
	@Override
	public Response get​GetQuoteListResponse() throws Exception{
		Map<String, String> queryParams = new LinkedHashMap<String, String>();
		queryParams.put(QueryParameter.timespanindays.name(), "1");
		queryParams.put(QueryParameter.sortascending.name(), "true");
		queryParams.put(QueryParameter.sortcolumn.name(), "LastModified");
		return get​GetQuoteListResponse(queryParams);
	}
	
	/**
	 * This method will provide service response for quote enquires
	 */
	@Override
	public Response get​GetQuoteListResponse(Map<String, String> queryParams) throws Exception{
		String[] scopeNames = {Scope.ENQUIRY_READ.getText()};
		Map<String, String> headerParams = getHeaderList(scopeNames);
		return getGetServiceResponse(GET_QUOTE_RESOURCE_PATH, queryParams, headerParams);
	}
	
	/**
	 * This method will provide service response for quote enquires
	 */
	@Override
	public Response get​GetQuoteListResponse(Integer timeSpanDays, Boolean  isSortAscending, String sortByColumn) throws Exception{
		Map<String, String> queryParams = new LinkedHashMap<String, String>();
		queryParams.put(QueryParameter.timespanindays.name(), timeSpanDays.toString());
		queryParams.put(QueryParameter.sortascending.name(), isSortAscending.toString());
		queryParams.put(QueryParameter.sortcolumn.name(), sortByColumn);
		return get​GetQuoteListResponse(queryParams);
	}

	/**
	 * This method will provide service response for broker configuration
	 */
	@Override
	public Response get​GetConfigurationResponse() throws Exception{
		Map<String, String> queryParams = new LinkedHashMap<String, String>();
		return get​GetConfigurationResponse(queryParams);
	}
	
	/**
	 * This method will provide service response for broker configuration
	 */
	@Override
	public Response get​GetConfigurationResponse(Map<String, String> queryParams) throws Exception{
		String[] scopeNames = { Scope.CONFIG_READ.getText() };
		Map<String, String> headerParams = getHeaderList(scopeNames);
		return getGetServiceResponse(GET_CONFIGURATION_RESOURCE_PATH, queryParams, headerParams);
	}

	/**
	 * This method will provide service response for renewal summary enquire
	 */
	@Override
	public Response get​GetRenewalSummaryResponse() throws Exception{
		Map<String, String> queryParams = new LinkedHashMap<String, String>();
		queryParams.put(QueryParameter.filter.name(), "1");
		return get​GetRenewalSummaryResponse(queryParams);
	}

	/**
	 * This method will provide service response for renewal summary enquire
	 */
	@Override
	public Response get​GetRenewalSummaryResponse(Map<String, String> queryParams) throws Exception{
		String[] scopeNames = { Scope.POLICY_READ.getText() };
		Map<String, String> headerParams = getHeaderList(scopeNames);
		return getGetServiceResponse(GET_RENEWAL_SUMMARY_RESOURCE_PATH, queryParams, headerParams);
	}
	
	/**
	 * This method will provide service response for renewal summary enquire
	 */
	@Override
	public Response get​GetRenewalSummaryResponse(Integer filter) throws Exception{
		Map<String, String> queryParams = new LinkedHashMap<String, String>();
		queryParams.put(QueryParameter.filter.name(), filter.toString());
		return get​GetRenewalSummaryResponse(queryParams);
	}
	
	/**
	 * This method will provide service response for renewal policy enquires
	 */
	@Override
	public Response get​GetRenewalPolicyResponse() throws Exception{
		Map<String, String> queryParams = new LinkedHashMap<String, String>();
		queryParams.put(QueryParameter.pagesize.name(), "30");
		queryParams.put(QueryParameter.page.name(), "1");
		queryParams.put(QueryParameter.sortascending.name(), "true");
		queryParams.put(QueryParameter.sortcolumn.name(), "membername");
		queryParams.put(QueryParameter.filtertype.name(), "1");
		return get​GetRenewalPolicyResponse(queryParams);
	}
	
	/**
	 * This method will provide service response for renewal policy enquires
	 */
	@Override
	public Response get​GetRenewalPolicyResponse(Map<String, String> queryParams) throws Exception{
		String[] scopeNames = { Scope.POLICY_READ.getText() };
		Map<String, String> headerParams = getHeaderList(scopeNames);
		return getGetServiceResponse(GET_RENEWAL_POLICY_RESOURCE_PATH, queryParams, headerParams);
	}
	
	/**
	 * This method will provide service response for renewal policy enquires
	 */
	@Override
	public Response get​GetRenewalPolicyResponse(Integer pageSize, Integer page, Boolean  isSortAscending, String sortByColumn, Integer filterType) throws Exception{
		Map<String, String> queryParams = new LinkedHashMap<String, String>();
		queryParams.put(QueryParameter.pagesize.name(), pageSize.toString());
		queryParams.put(QueryParameter.page.name(), page.toString());
		queryParams.put(QueryParameter.sortascending.name(), isSortAscending.toString());
		queryParams.put(QueryParameter.sortcolumn.name(), sortByColumn);
		queryParams.put(QueryParameter.filtertype.name(), filterType.toString());
		return get​GetRenewalPolicyResponse(queryParams);
	}
	
	/**
	 * This method will provide service response for loss ratio enquires
	 */
	@Override
	public Response get​GetLossRatioResponse() throws Exception{
		Map<String, String> queryParams = new LinkedHashMap<String, String>();
		return get​GetLossRatioResponse(queryParams);
	}
	
	/**
	 * This method will provide service response for loss ratio enquires
	 */
	@Override
	public Response get​GetLossRatioResponse(Map<String, String> queryParams) throws Exception{
		String[] scopeNames = { Scope.DOCUMENT_READ.getText() };
		Map<String, String> headerParams = getHeaderList(scopeNames);
		return getGetServiceResponse(GET_LOSS_RATIO_RESOURCE_PATH, queryParams, headerParams);
	}

	/**
	 * This method will provide GET service response for request resource
	 */
	@Override
	public Response getGetServiceResponse(String urlPath, Map<String, String> queryParams,
			Map<String, String> headerParams) throws Exception {
		URI uri = updateUrlWithQParams(TestDataInstance.getDefaultEnvValue(TestDataInstance.EnvironmentKey.API_BASE_URL.name()) + urlPath, queryParams);
		/*
		 * RestAssured.config = RestAssured.config()
		 * .httpClient(HttpClientConfig.httpClientConfig()
		 * .setParam(ClientPNames.CONN_MANAGER_TIMEOUT,60000)
		 * .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000)
		 * .setParam(CoreConnectionPNames.SO_TIMEOUT, 60000));
		 */
		Response response = RestAssured.given().headers(headerParams).get(uri);
		logger.debug("Response :: " + response.asPrettyString());
		return response;
	}

	/**
	 * This method will provide resource url updated with query parameters
	 */
	private static URI updateUrlWithQParams(String serviceUrl, Map<String, String> queryParams)
			throws URISyntaxException {
		if (null != queryParams && queryParams.size() != 0) {
			serviceUrl = serviceUrl + "?";
			for (Map.Entry<String, String> entry : queryParams.entrySet()) {
				serviceUrl = serviceUrl + entry.getKey() + "=" + entry.getValue() + "&";
			}
			serviceUrl = serviceUrl.substring(0, serviceUrl.length() - 1);
		}
		return new URI(serviceUrl);
	}
	
	/**
	 * This method will generate authorization token on the basis of of scope 
	 * and return header parameters required for service
	 */
	private HashMap<String, String> getHeaderList(String[] scopeNames) {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", CONTENT_TYPE_JSON);
		headers.put("api-version", TestDataInstance.getDefaultEnvValue(TestDataInstance.EnvironmentKey.API_VERSION.name()));
		headers.put(TestDataInstance.getDefaultEnvValue(TestDataInstance.EnvironmentKey.API_KEY.name()), 
				TestDataInstance.getDefaultEnvValue(TestDataInstance.EnvironmentKey.API_KEY_VALUE.name()));
		headers.put("Authorization", getAuthToken(scopeNames));
		return headers;
	}

	/**
	 * This method will generate authorization token on the basis of of scope
	 */
	@Override
	public String getAuthToken(String[] scopeNames) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		Integer temp = ((Number) jsExecutor.executeScript("return window.sessionStorage.length")).intValue();
		LOGGER.debug("Number of keys in session storage - " + temp);
		String tokenvalue = null;
		for (int t = 0; t < temp; t++) {
			String key = (String) jsExecutor.executeScript(String.format("return window.sessionStorage.key('%s');", t));
			LOGGER.debug(t + "=" + key);
			boolean matching = true;
			for (String scopeName : scopeNames) {
				if (!key.contains(scopeName)) {
					matching = false;
					break;
				}
			}
			if (matching == true) {
				tokenvalue = (String) jsExecutor
						.executeScript(String.format("return window.sessionStorage.getItem('%s')", key));
				break;
			}
		}
		String[] strValues = tokenvalue.split(",");
		HashMap<String, String> Valuesmap = new HashMap<String, String>();
		for (String Values : strValues) {
			String[] kv = Values.split(":");
			String k = kv[0].replace("\"", "");
			String v = kv[1].replace("\"", "");
			Valuesmap.put(k, v);
		}
		String strSessionToken = Valuesmap.get("secret");
		return TOKEN_PREFIX_BEARER + strSessionToken;
	}
}
