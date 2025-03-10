package com.amstech.tripplanner.booking.response;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


public class RestResponse implements Map<String, Object>{
	
	
	public static final String KEY_STATUS = "status";

	public static final String KEY_STATUS_CODE = "statusCode";

	public static final String KEY_STATUS_MESSAGE = "status";

	public static final String KEY_PARAMETERS = "parameters";

	public static final String KEY_MESSAGE = "message";

	public static final String KEY_DATA = "data";

	public static final String KEY_TOTAL_RECORDS = "totalRecords";

	public static final String KEY_TOTAL_UN_READ = "unReadCount";

	public static final String KEY_TOTAL_PAGES = "totalPages";

	public static final String KEY_PAGE_SIZE = "pageSize";

	public static final String KEY_PAGE_NUMBER = "pageNumber";

	public static final String KEY_ROW_COUNT = "rowCount";

	public static final String KEY_CURRENT_TIMESTAMP = "currentTimestamp";

	public static final String STATUS_ERROR = "error";

	public static final String STATUS_SUCCESS = "success";

	private Map<String, Object> response = new LinkedHashMap<>();

	private RestResponse() {
	}

	public static RestResponse build() {
		return new RestResponse().withCurrentTimestamp(new Date().getTime());
	}

	public RestResponse add(String name, Object value) {
		this.response.put(name, value);
		return this;
	}

	public RestResponse withPageNumber(long value) {
		return add(KEY_PAGE_NUMBER, value);
	}

	public RestResponse withTotalRecords(long value) {
		return add(KEY_TOTAL_RECORDS, value);
	}

	public RestResponse withPageSize(long value) {
		return add(KEY_PAGE_SIZE, value);
	}

	public RestResponse withCurrentTimestamp(long value) {
		return add(KEY_CURRENT_TIMESTAMP, value);
	}

	public RestResponse withMessage(String message) {
		return add(KEY_MESSAGE, message);
	}

	public RestResponse withStatusCode(Object statusCode) {
		return add(KEY_STATUS_CODE, statusCode);
	}

	public RestResponse withError(String error) {
		return add(KEY_STATUS, STATUS_ERROR).add(KEY_MESSAGE, error);
	}

	public RestResponse withError(String errorMessage, Object statusCode, Object statusMessage, String[] parameters) {
		return add(KEY_STATUS, STATUS_ERROR).add(KEY_MESSAGE, errorMessage).add(KEY_STATUS_CODE, statusCode).add(KEY_STATUS_MESSAGE, statusMessage).add(KEY_PARAMETERS, parameters);
	}

	public RestResponse withSuccess(String message) {
		return add(KEY_STATUS, STATUS_SUCCESS).add(KEY_MESSAGE, message);
	}

	public RestResponse withSuccess() {
		return add(KEY_STATUS, STATUS_SUCCESS);
	}

	public RestResponse withSuccess(String message, Object data) {
		return withSuccess(message, KEY_DATA, data);
	}

	public RestResponse withSuccess(String message, Object data, Object statusCode, Object statusMessage) {
		return withSuccess(message, KEY_DATA, data).add(KEY_STATUS_CODE, statusCode).add(KEY_STATUS_MESSAGE, statusMessage);
	}

	public RestResponse withSuccess(String message, String name, Object value) {
		return withSuccess(message).add(name, value);
	}

	public RestResponse withData(Object data) {
		return add(KEY_DATA, data);
	}

	public RestResponse withData(Long totalPages, Long totalSize, Integer pageNumber, String key, Object value) {
		if (!response.containsKey(KEY_DATA)) {
			add(KEY_TOTAL_RECORDS, totalSize).add(KEY_TOTAL_PAGES, totalPages).add(KEY_PAGE_NUMBER, pageNumber)
					// .add(KEY_DATA, new LinkedHashMap<String, Object>());
					.add(KEY_DATA, value);
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	public RestResponse withData(String key, Object value) {
		if (!response.containsKey(KEY_DATA)) {
			add(KEY_DATA, new LinkedHashMap<String, Object>());
		}
		((Map<String, Object>) response.get(KEY_DATA)).put(key, value);
		return this;
	}

	public RestResponse withUnReadCount(long count) {
		return add(KEY_TOTAL_UN_READ, count);
	}

	public static RestResponse success() {
		return new RestResponse().withSuccess();
	}

	public static RestResponse success(String message) {
		return new RestResponse().withSuccess(message);
	}

	public static RestResponse success(String message, Object data) {
		return RestResponse.success(message, KEY_DATA, data);
	}

	public static RestResponse success2(String message, Object data) {
		throw new IllegalStateException();
	}

	public static RestResponse success2() {
		throw new IllegalStateException();
	}

	public static RestResponse success(String message, String name, Object data) {
		return new RestResponse().withSuccess(message).add(name, data);
	}

	public static RestResponse error(String errorMessage) {
		return new RestResponse().withError(errorMessage);
	}

	public static RestResponse error(Exception e) {
		return new RestResponse().withError(e.getMessage());
	}

	public static RestResponse missingParam(String param) {
		return new RestResponse().withError("Param " + param + " is missing");
	}

		@Override
	public int size() {
		return response.size();
	}

	@Override
	public boolean isEmpty() {
		return response.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return response.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return response.containsValue(value);
	}

	@Override
	public Object get(Object key) {
		return response.get(key);
	}

	@Override
	public Object put(String key, Object value) {
		return response.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return response.remove(key);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		response.putAll(m);
	}

	@Override
	public void clear() {
		response.clear();

	}

	@Override
	public Set<String> keySet() {
		return response.keySet();
	}

	@Override
	public Collection<Object> values() {

		return response.values();
	}

	@Override
	public Set<Entry<String, Object>> entrySet() {
		return response.entrySet();
	}


}
