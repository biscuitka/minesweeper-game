package com.biscuitka.minesweeper.error;

import com.biscuitka.minesweeper.error.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(MethodArgumentNotValidException exp) {
        log.error("Ошибка валидации", exp);
        return ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .reason("Произошла непредвиденная ошибка")
                .build();
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBadRequestException(final BadRequestException exp) {
        log.error("Запрос составлен некорректно", exp);
        return ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .reason("Произошла непредвиденная ошибка")
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleThrowable(final Throwable exp) {
        log.error("Ошибка сервера", exp);
        return ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .reason("Произошла непредвиденная ошибка")
                .build();
    }

}
