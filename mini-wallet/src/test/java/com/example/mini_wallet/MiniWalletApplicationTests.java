package com.example.mini_wallet;
import com.example.mini_wallet.dto.CreateUserRequest;
import com.example.mini_wallet.dto.TransferRequest;
import com.example.mini_wallet.model.UserWallet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class MiniWalletApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void shouldTransferMoneyAtomically() {

		// Create Alice
		CreateUserRequest aliceReq = new CreateUserRequest();
		aliceReq.name = "Alice";
		aliceReq.balance = new BigDecimal("100");

		ResponseEntity<UserWallet> aliceResponse =
				restTemplate.postForEntity("/api/users", aliceReq, UserWallet.class);

		assertThat(aliceResponse.getBody()).isNotNull();

		// Create Bob
		CreateUserRequest bobReq = new CreateUserRequest();
		bobReq.name = "Bob";
		bobReq.balance = new BigDecimal("50");

		ResponseEntity<UserWallet> bobResponse =
				restTemplate.postForEntity("/api/users", bobReq, UserWallet.class);

		assertThat(bobResponse.getBody()).isNotNull();

		// Transfer 50 from Alice to Bob
		TransferRequest transfer = new TransferRequest();
		transfer.fromUserId = aliceResponse.getBody().getId();
		transfer.toUserId = bobResponse.getBody().getId();
		transfer.amount = new BigDecimal("50");

		restTemplate.postForEntity("/api/transfer", transfer, String.class);

		// Verify balances
		String aliceBalance =
				restTemplate.getForObject(
						"/api/users/" + transfer.fromUserId + "/balance",
						String.class
				);

		String bobBalance =
				restTemplate.getForObject(
						"/api/users/" + transfer.toUserId + "/balance",
						String.class
				);

		assertThat(aliceBalance).isEqualTo("50.00");
		assertThat(bobBalance).isEqualTo("100.00");
	}
}






