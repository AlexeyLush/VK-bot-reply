package ru.justai.vkbot.service.callbacks.implementations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;
import ru.justai.vkbot.config.VkCredentials;
import ru.justai.vkbot.dto.callbacks.CallbackRequestDTO;
import ru.justai.vkbot.service.callbacks.CallbackService;

@Service
@RequiredArgsConstructor
public class ConfirmationService implements CallbackService {

    private final VkCredentials vkCredentials;

    @Override
    public ResponseEntity<String> processRequest(@NonNull CallbackRequestDTO callbackRequestDTO) {
        return new ResponseEntity<>(vkCredentials.getConfirmationCode(), HttpStatus.OK);
    }

}
