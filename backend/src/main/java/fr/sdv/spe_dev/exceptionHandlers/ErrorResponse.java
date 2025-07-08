package fr.sdv.spe_dev.exceptionHandlers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse<T> {
    private T message;
    private String code;
}

