package com.as.autot.tool.wrapper.api;

import java.util.Map;

import io.restassured.response.Response;

public interface IServiceClient {

	public Response get​GetVesselListResponse() throws Exception;

	public Response get​GetVesselListResponse(Map<String, String> queryParams) throws Exception;

	public Response get​GetVesselListResponse(Integer pageSize, Integer page, Boolean isSortAscending,
			String sortByColumn) throws Exception;

	public Response get​GetMemberListResponse() throws Exception;

	public Response get​GetMemberListResponse(Map<String, String> queryParams) throws Exception;

	public Response get​GetMemberListResponse(Integer pageSize, Integer page, Boolean isSortAscending,
			String sortByColumn) throws Exception;

	public Response get​GetPolicyListResponse() throws Exception;

	public Response get​GetPolicyListResponse(Map<String, String> queryParams) throws Exception;

	public Response get​GetPolicyListResponse(Integer pageSize, Integer page, Boolean isSortAscending,
			String sortByColumn) throws Exception;

	public Response get​GetQuoteListResponse() throws Exception;

	public Response get​GetQuoteListResponse(Map<String, String> queryParams) throws Exception;

	public Response get​GetQuoteListResponse(Integer timeSpanDays, Boolean isSortAscending, String sortByColumn)
			throws Exception;

	public Response get​GetConfigurationResponse() throws Exception;

	public Response get​GetConfigurationResponse(Map<String, String> queryParams) throws Exception;

	public Response get​GetRenewalSummaryResponse() throws Exception;

	public Response get​GetRenewalSummaryResponse(Map<String, String> queryParams) throws Exception;

	public Response get​GetRenewalSummaryResponse(Integer filter) throws Exception;

	public Response get​GetRenewalPolicyResponse() throws Exception;

	public Response get​GetRenewalPolicyResponse(Map<String, String> queryParams) throws Exception;

	public Response get​GetRenewalPolicyResponse(Integer pageSize, Integer page, Boolean isSortAscending,
			String sortByColumn, Integer filterType) throws Exception;

	public Response get​GetLossRatioResponse() throws Exception;

	public Response get​GetLossRatioResponse(Map<String, String> queryParams) throws Exception;

	public Response getGetServiceResponse(String urlPath, Map<String, String> queryParams,
			Map<String, String> headerParams) throws Exception;

	public String getAuthToken(String[] scopeNames);

}
