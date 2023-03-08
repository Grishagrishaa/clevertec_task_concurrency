package ru.clevertec.server.api;

public interface IServer<I, O> {// I -> INPUT -> REQUEST, O -> OUTPUT -> RESPONSE
    O handleRequest(I request);
}
