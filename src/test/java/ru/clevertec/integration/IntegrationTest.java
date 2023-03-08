package ru.clevertec.integration;

import org.junit.jupiter.api.Test;
import ru.clevertec.client.DefClientImpl;
import ru.clevertec.client.api.IClient;
import ru.clevertec.dto.Request;
import ru.clevertec.dto.Response;
import ru.clevertec.server.DefServerImpl;
import ru.clevertec.server.api.IServer;
import ru.clevertec.util.ResponseUtil;
import ru.clevertec.utils.RequestUtils;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class IntegrationTest {
    private final IClient<Request, Response> client;
    private final IServer<Request, Response> server;
    private final List<Request> requests;


    public IntegrationTest() {
        requests = RequestUtils.supplyDefRequestsList(2);
        client = new DefClientImpl(requests);
        server =  new DefServerImpl(2000);
    }

    @Test
    void sendRequestsShouldReturnRightResponses() throws Exception {
        List<Response> actualResponses = client.sendRequests(server);

        List<Response> expectedResponsesList = requests.stream()
                .map(ResponseUtil::buildResponseForRequest)
                .toList();

        assertThat(expectedResponsesList).isEqualTo(actualResponses);
    }
}
