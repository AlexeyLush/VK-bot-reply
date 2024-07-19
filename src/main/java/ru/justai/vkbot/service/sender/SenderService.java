package ru.justai.vkbot.service.sender;

import org.springframework.http.ResponseEntity;

public interface SenderService<T> {
    ResponseEntity<String> send(T object);
}
