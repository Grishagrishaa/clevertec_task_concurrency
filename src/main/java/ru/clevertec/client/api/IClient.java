package ru.clevertec.client.api;

import ru.clevertec.server.api.IServer;

import java.util.List;

public interface IClient<I, O> {// I -> INPUT -> REQUEST, O -> OUTPUT -> RESPONSE
    List<O> sendRequests(IServer<I, O> server) throws Exception;
}
