package net.notetalking.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.notetalking.util.ConstantUtils;
import net.notetalking.util.Paging;


/**
 * Common function Format request, response
 *
 */
@Service
public class BaseController{
	private static final Logger log = LoggerFactory.getLogger(BaseController.class);
	protected String langCode = null;
	protected HttpStatus status;
//
//	@Value("${spring.profiles.active}")
//	protected String env;

	
	public BaseController() {
	}

	protected ResponseEntity response() {
		return response(null);
	}
	protected ResponseEntity response(Object data) {
		return response(HttpStatus.OK, data);
	}
	protected ResponseEntity response(HttpStatus status, Object data) {
		log.debug("ResponseEntity: " + data);
		HttpHeaders headers = new HttpHeaders();
//		try {
//			headers.set(HttpHeaders.CONTENT_LENGTH,	String.valueOf(new ObjectMapper().writeValueAsString(data).length()));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return new ResponseEntity(data, headers, status);
	}
	/**
	 * Return REST with List object
	 * @param responseList 
	 * @param result
	 */
	public ResponseEntity responseList(List<?> responseList, Paging paging) {
		log.debug("responseList " + responseList.size());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ConstantUtils.DATA_LIST, responseList);
		map.put(Paging.class.getSimpleName().toLowerCase(), paging);
		return response(map);
	}
	/**
	 * Return REST with List object
	 * @param responseList 
	 * @param result
	 */
	public ResponseEntity responseList(List<?> responseList) {
		log.debug("responseList " + responseList.size());
		Paging paging = new Paging(1,responseList.size()); 
		return responseList(responseList, paging);
	}
	
}
