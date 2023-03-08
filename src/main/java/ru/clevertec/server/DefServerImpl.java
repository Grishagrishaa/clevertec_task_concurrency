package ru.clevertec.server;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import ru.clevertec.server.api.IServer;
import ru.clevertec.dto.Request;
import ru.clevertec.dto.Response;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@AllArgsConstructor
public class DefServerImpl implements IServer<Request, Response> {
    private int delay;
    private final Lock locker = new ReentrantLock();

    @Override
    @SneakyThrows
    public Response handleRequest(Request request) {
        locker.lock();
        int responseValue;

        try {
            responseValue = request.getValue() * 999 - request.getValue();
            Thread.sleep(ThreadLocalRandom.current().nextInt(delay));
        } finally{
            locker.unlock();
        }

        return Response.of(responseValue);
    }
}
