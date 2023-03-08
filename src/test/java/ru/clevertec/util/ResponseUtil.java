package ru.clevertec.util;

import lombok.Data;
import ru.clevertec.dto.Request;
import ru.clevertec.dto.Response;

@Data
public class ResponseUtil {

    public static Response buildResponseForRequest(Request request){
        return Response.of(request.getValue() * 999 - request.getValue());
    }
}
