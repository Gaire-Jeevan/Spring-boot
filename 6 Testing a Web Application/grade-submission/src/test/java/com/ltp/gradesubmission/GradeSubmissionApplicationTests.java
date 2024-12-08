package com.ltp.gradesubmission;

import com.ltp.gradesubmission.controller.GradeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertNotNull;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GradeSubmissionApplicationTests {

	@Autowired
	private GradeController controller;

//	@Autowired
//	private GradeController gradeController;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertNotNull(controller);
	}

	@Test
	public void testShowGradeForm() throws Exception{
//		the id is not much important as it will return the empty object or will return an object that's pre populated
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/?id=123");
//		the below code is used to execute the request defined by requestBuilder
		mockMvc.perform(requestBuilder)
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("form"))
				.andExpect(model().attributeExists("grade"));
	}

	@Test
	public void testSuccessfulSubmission() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit")
				.param("name", "Harry")
				.param("subject", "Potions")
				.param("score", "C"); // here the name should match the name of POJO class

		mockMvc.perform(request)
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/grades"));
	}

	@Test
	public void testUnsuccessfulSubmission() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit")
				.param("name", " ")
				.param("subject", " ")
				.param("score", " ");

		mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("form"));
	}

	@Test
	public void testFetGrades() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/grades");

		mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("grades"))
				.andExpect(model().attributeExists("grades"));
	}

}
