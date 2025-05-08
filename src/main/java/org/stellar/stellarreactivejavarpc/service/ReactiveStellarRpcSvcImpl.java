package org.stellar.stellarreactivejavarpc.service;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.stellar.sdk.SorobanServer;
import org.stellar.sdk.responses.sorobanrpc.GetEventsResponse;
import org.stellar.sdk.responses.sorobanrpc.GetLatestLedgerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.Function;

import static org.stellar.stellarreactivejavarpc.service.ReactiveStellarRpcSvc.buildEventsRequest;

@Service
class ReactiveStellarRpcSvcImpl implements ReactiveStellarRpcSvc {

	private static final Logger log = LoggerFactory.getLogger(ReactiveStellarRpcSvcImpl.class);
	private final SorobanServer sorobanServer;

	ReactiveStellarRpcSvcImpl(SorobanServer sorobanServer) {
		this.sorobanServer = sorobanServer;
	}

	public void subscribeToMono(Function<Long, Publisher<? extends GetLatestLedgerResponse>> publisherFunction) {
		Flux.interval(Duration.ofSeconds(5))
				.flatMap(publisherFunction)
				.buffer(10)
				.log()
				.subscribe();
	}

	@Override
	public Mono<GetLatestLedgerResponse> getLatestLedger() {
		return Mono.fromCallable(sorobanServer::getLatestLedger)
				.ofType(GetLatestLedgerResponse.class)
				.name(GET_LATEST_LEDGER)
				.log()
				.cache(BLOCK_TIME)
				.retry(1);
	}

	@Override
	public Mono<GetEventsResponse> getEvents() {
		return Mono.fromCallable(
				() -> sorobanServer.getEvents(buildEventsRequest()));
	}

	private Publisher<? extends GetLatestLedgerResponse> subscribeToLatestLedger(Long tick) {
		return getLatestLedger();
	}
}
