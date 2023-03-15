package ru.clevertec.client;

import lombok.SneakyThrows;
import ru.clevertec.client.api.IClient;
import ru.clevertec.server.api.IServer;
import ru.clevertec.dto.Request;
import ru.clevertec.dto.Response;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DefClientImpl implements IClient<Request, Response> {
    private final List<Request> requests;
    private List<Response> responses;

    public DefClientImpl(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    @SneakyThrows
    public List<Response> sendRequests(IServer<Request, Response> server) throws Exception {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        return requests.stream()
                    .map(req -> threadPool.submit(() -> server.handleRequest(req)))
                    .map(this::getFuture)
                    .toList();
    }

    @SneakyThrows
    private Response getFuture(Future<Response> responseFuture){
        return responseFuture.get();
    }
}
