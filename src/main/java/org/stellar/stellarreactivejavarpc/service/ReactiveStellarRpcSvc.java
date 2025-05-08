package org.stellar.stellarreactivejavarpc.service;

import org.springframework.stereotype.Service;
import org.stellar.sdk.requests.sorobanrpc.GetEventsRequest;
import org.stellar.sdk.responses.sorobanrpc.GetEventsResponse;
import org.stellar.sdk.responses.sorobanrpc.GetLatestLedgerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface ReactiveStellarRpcSvc {
	String GET_LATEST_LEDGER = "getLatestLedger";
	Duration BLOCK_TIME = Duration.of(5, ChronoUnit.SECONDS);

	static GetEventsRequest buildEventsRequest() {
		return GetEventsRequest.builder()
				.filter(buildEventFilter())
				.build();
	}

	static GetEventsRequest.EventFilter buildEventFilter() {
		return GetEventsRequest.EventFilter.builder()
				.contractIds(List.of(""))
				.build();
	}

	Mono<GetLatestLedgerResponse> getLatestLedger();

	Mono<GetEventsResponse> getEvents();
}
