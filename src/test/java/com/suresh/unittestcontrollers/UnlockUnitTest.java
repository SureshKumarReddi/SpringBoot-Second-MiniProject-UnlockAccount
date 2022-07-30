package com.suresh.unittestcontrollers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suresh.bindings.UnlockAccountRequest;
import com.suresh.constants.AppConstants;
import com.suresh.service.UnlockAccountService;

@WebMvcTest
public class UnlockUnitTest {

	@MockBean
	private UnlockAccountService service;

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void unlockAccountTest() throws Exception {
		
		UnlockAccountRequest request = new UnlockAccountRequest();
		request.setConfirmPwd("confirmpassword");
		request.setEmail("suresh.y@hcl.ccom");
		request.setNewPwd("confirmpassword");
		request.setTempPwd("temporarypassword");
		
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonrequest = mapper.writeValueAsString(request);
		
		when(service.unlockAccount(request)).thenReturn(AppConstants.ACCOUNT_UNLOCKED);		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/unlock")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(jsonrequest);
		
		
		MvcResult result = mvc.perform(builder).andReturn();
		
		String acctualresult = result.getResponse().getContentAsString();
		System.out.println(acctualresult);
	  
		assertEquals(AppConstants.ACCOUNT_UNLOCKED, acctualresult);
		
	}
}
