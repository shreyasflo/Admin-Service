package com.idexcel.adminservicetests.controllerTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.idexcel.adminservice.AdminServiceApplication;
import com.idexcel.adminservice.controller.LenderController;
import com.idexcel.adminservice.dto.LenderDTO;
import com.idexcel.adminservice.entity.Address;
import com.idexcel.adminservice.entity.Lender;
import com.idexcel.adminservice.service.LenderServiceImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=AdminServiceApplication.class)
@WebMvcTest(controllers=LenderController.class)
public class LenderControllerTest {

	
	@MockBean
	private LenderServiceImpl lenderService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getLendersTest() throws Exception {
	
		when(lenderService.getAllLenders()).thenReturn(Arrays.asList(new Lender("shreyas",
						new Address("717 Alabama Dr","Herndon","VA","20170","US"),
						"active",
						"shreyas",
						"2019-09-03",
						"ramnath",
						"2019-09-03"),
						new Lender("Rakesh",
						new Address("716 Florida Dr","Herndon","VA","20170","US"),
						"suspended",
						"Rakesh",
						"2019-09-03",
						"Sharma",
						"2019-09-03")));
	
		RequestBuilder request = MockMvcRequestBuilders.get("/api/lenders").accept(MediaType.APPLICATION_JSON);
		String jsonContent = "[" + 
				"{" + 
				"\"name\": \"shreyas\"," + 
				"\"address\": {" + 
				"\"street\": \"717 Alabama Dr\"," + 
				"\"city\": \"Herndon\"," + 
				"\"state\": \"VA\"," + 
				"\"zipCode\": \"20170\"," + 
				"\"country\": \"US\"" + 
				"}," + 
				"\"status\": \"active\"," + 
				"\"createdBy\": \"shreyas\"," + 
				"\"createdDate\": \"2019-09-03\"," + 
				"\"updatedBy\": \"ramnath\"," + 
				"\"updatedDate\": \"2019-09-03\"" + 
				"}," + 
				"{" + 
				"\"name\": \"Rakesh\"," + 
				"\"address\": {" + 
				"\"street\": \"716 Florida Dr\"," + 
				"\"city\": \"Herndon\"," + 
				"\"state\": \"VA\"," + 
				"\"zipCode\": \"20170\"," + 
				"\"country\": \"US\"" + 
				"}," + 
				"\"status\": \"suspended\"," + 
				"\"createdBy\": \"Rakesh\"," + 
				"\"createdDate\": \"2019-09-03\"," + 
				"\"updatedBy\": \"Sharma\"," + 
				"\"updatedDate\": \"2019-09-03\"" + 
				"}" + 
				"]" + 
				"";
		
				mockMvc
				.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json(jsonContent))
				.andReturn();
	}
	
	@Test
	public void postLendersTest() throws Exception {
		LenderDTO lenderdto = new LenderDTO("shreyas",
						new Address("PlayboyAve",
								"San Francisco",
								"CA",
								"90001",
								"US"));			
		when(lenderService.createLender(lenderdto)).thenReturn("hf2082309hf");
		RequestBuilder request = MockMvcRequestBuilders.get("/api/lenders").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void getLendersByIdTest() throws Exception {
		Lender lender = new Lender("shreyas",
				new Address("717 Alabama Dr","Herndon","VA","20170","US"),
				"active",
				"shreyas",
				"2019-09-03",
				"ramnath",
				"2019-09-03");
		lender.setId("123231weg");
		when(lenderService.getLenderById("123231weg")).thenReturn(lender);
		RequestBuilder request  = MockMvcRequestBuilders.get("api/lenders/123231weg");
		
		String json = "{" + 
				"\"id\": \"123231weg\"," + 
				"\"name\": \"shreyas\"," + 
				"\"address\": {" + 
				"\"street\": \"717 Alabama Dr\"," + 
				"\"city\": \"Herndon\"," + 
				"\"state\": \"VA\"," + 
				"\"zipCode\": \"20170\"," + 
				"\"country\": \"US\"" + 
				"}," + 
				"\"status\": \"active\"," + 
				"\"createdBy\": \"shreyas\"," + 
				"\"updatedBy\": \"2019-09-03\"," + 
				"\"createdDate\": \"ramnath\"," + 
				"\"updatedDate\": \"2019-09-03\"" + 
				"}";
		
		JSONObject jsonContent = new JSONObject(json);
				mockMvc
				.perform(request)
				.andExpect(status().isNotFound())
				.andReturn();	
	}
	
	@Test
	public void DeleteLenderTest() throws Exception {
		String id = "123231weg";
		lenderService.deleteLenderById(id);
		
		RequestBuilder request = MockMvcRequestBuilders.delete("/api/lenders/123231weg");
		
		mockMvc
		.perform(request).
		andExpect(status().isOk())
		.andReturn();
	}
	
	@Test 
	public void patchLenderTest() throws Exception {
		String id = "12345678";
		Lender lender = new Lender("Heather",
				new Address("3151 S Babcock St","Melbourne","FL","32901","US"),
				"active",
				"heather",
				"2019-09-03",
				"crawford",
				"2019-09-03");
		lender.setId(id);
		lenderService.updateLender(lender,id);
		RequestBuilder request = MockMvcRequestBuilders.patch("api/lenders/12345678/status");
		mockMvc.perform(request).andExpect(status().isNotFound()).andReturn();
	}

}

