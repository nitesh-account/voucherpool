
package com.voucherpool.rest.controller;

import com.voucherpool.rest.domain.Recipient;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * RecipientControllerTest is used to test recipient related API's.
 *
 * @author Nitesh Kumar
 */


public class RecipientControllerTest extends AbstractControllerTest {

	@Test
	public void shouldAddCustomer() throws Exception {
		// given
		String recipientBody = "{\"name\":\"Nitesh\", \"email\":\"nitesh@gmail.com\"}";
		Recipient recipient = new Recipient("Admin","Nitesh", "nitesh@gmail.com");

		// when
		when(recipientService.save(recipient)).thenReturn(recipient);

		// then
		mockMvc.perform(post("/api/recipients")
				.content(recipientBody)
				.contentType(APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

	}
}
