package ru.clevertec.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName="of")
public class Response {
    private final Integer value;
}
