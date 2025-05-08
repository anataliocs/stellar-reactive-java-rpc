package org.stellar.stellarreactivejavarpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.stellar.stellarreactivejavarpc.service.ReactiveStellarRpcSvc;

@SpringBootApplication
public class StellarReactiveJavaRpcApplication {

	public static void main(String[] args) {
		SpringApplication.run(StellarReactiveJavaRpcApplication.class, args);
	}
}
