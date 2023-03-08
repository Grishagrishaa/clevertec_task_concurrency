package ru.clevertec.utils;

import ru.clevertec.dto.Request;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public final class RequestUtils {
    public static List<Request> supplyDefRequestsList(int count){
        return Stream.generate(() -> Request.of(ThreadLocalRandom.current().nextInt(999)))
                     .limit(count)
                     .toList();
    }
}
