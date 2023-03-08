package ru.clevertec;

import lombok.SneakyThrows;
import ru.clevertec.client.DefClientImpl;
import ru.clevertec.client.api.IClient;
import ru.clevertec.server.DefServerImpl;
import ru.clevertec.server.api.IServer;
import ru.clevertec.dto.Request;
import ru.clevertec.dto.Response;
import ru.clevertec.utils.RequestUtils;

import java.util.List;

public class Runner {
    @SneakyThrows
    public void run(){
        IServer<Request, Response> server = new DefServerImpl(2000);
        IClient<Request, Response> client = new DefClientImpl(RequestUtils.supplyDefRequestsList(10));

        List<Response> responses = client.sendRequests(server);
        System.out.println(responses);
    }
}
