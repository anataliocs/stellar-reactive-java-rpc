package org.stellar.stellarreactivejavarpc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stellar.sdk.responses.sorobanrpc.GetLatestLedgerResponse;
import org.stellar.stellarreactivejavarpc.service.ReactiveStellarRpcSvc;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
class EventStream {

	private final ReactiveStellarRpcSvc reactiveStellarRpcSvc;

	EventStream(ReactiveStellarRpcSvc reactiveStellarRpcSvc) {
		this.reactiveStellarRpcSvc = reactiveStellarRpcSvc;
	}

	@GetMapping("/latestLedger")
	public Mono<GetLatestLedgerResponse> subscribeToLatestLedger() {
		return reactiveStellarRpcSvc.getLatestLedger();
	}
}
