package ru.clevertec.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.client.api.IClient;
import ru.clevertec.dto.Request;
import ru.clevertec.dto.Response;
import ru.clevertec.server.api.IServer;
import ru.clevertec.util.ResponseUtil;
import ru.clevertec.utils.RequestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefClientImplTest {
    private final IClient<Request, Response> client;
    private final List<Request> requests;

    public DefClientImplTest() {
        requests = RequestUtils.supplyDefRequestsList(2);
        client = new DefClientImpl(requests);
    }

    @Mock
    private IServer<Request, Response> server;

    @Test
    void sendRequestsShouldReturnListOfResponses() throws Exception {
        requests.forEach(req -> doReturn(ResponseUtil.buildResponseForRequest(req))
                                        .when(server).handleRequest(req));

        List<Response> responses = client.sendRequests(server);

        assertAll(
                () -> assertThat(responses).contains(ResponseUtil.buildResponseForRequest(requests.get(0)),
                                                     ResponseUtil.buildResponseForRequest(requests.get(1))),
                () -> assertThat(responses.size()).isEqualTo(requests.size())
        );
        verify(server, times(requests.size())).handleRequest(any(Request.class));
    }
}