package com.voucherpool.rest.controller;

import com.voucherpool.rest.service.RecipientService;
import com.voucherpool.rest.service.SpecialOfferService;
import com.voucherpool.rest.service.VoucherCodeService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public abstract class AbstractControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@MockBean
	protected RecipientService recipientService;

	@MockBean
	protected SpecialOfferService specialOfferService;

	@MockBean
	protected VoucherCodeService voucherCodeService;

	@Before
	public void setUp() {
		Mockito.reset(recipientService, specialOfferService, voucherCodeService);
	}

}
