package ru.justai.vkbot.service.callbacks;

import org.springframework.http.ResponseEntity;

import ru.justai.vkbot.dto.callbacks.CallbackRequestDTO;


public interface CallbackService {
    ResponseEntity<String> processRequest(CallbackRequestDTO callbackRequestDTO); 
} 
