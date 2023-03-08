package ru.clevertec.server;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.clevertec.dto.Request;
import ru.clevertec.dto.Response;
import ru.clevertec.server.api.IServer;
import ru.clevertec.util.ResponseUtil;
import org.junit.jupiter.params.ParameterizedTest;

import static org.assertj.core.api.Assertions.*;
import java.util.stream.Stream;


class DefServerImplTest {
    private IServer<Request, Response> server =  new DefServerImpl(2000);

    @ParameterizedTest
    @MethodSource("ru.clevertec.server.DefServerImplTest#provideRequests")
    void handleRequestShouldReturnResponse(Request request){
        Response response = server.handleRequest(request);

        assertThat(response).isEqualTo(ResponseUtil.buildResponseForRequest(request));
    }


    static Stream<Arguments> provideRequests(){
        return Stream.of(
                Arguments.of(
                        Request.of(1),
                        Request.of(2),
                        Request.of(3),
                        Request.of(4),
                        Request.of(5))
        );
    }

}