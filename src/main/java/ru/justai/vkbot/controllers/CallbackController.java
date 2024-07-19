package ru.justai.vkbot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.justai.vkbot.controllers.enums.VkTypesOfRequest;
import ru.justai.vkbot.dto.callbacks.CallbackRequestDTO;
import ru.justai.vkbot.service.callbacks.implementations.ConfirmationService;
import ru.justai.vkbot.service.callbacks.implementations.MessageNewCallbackService;
import ru.justai.vkbot.utils.validators.api.ValidAccessRequest;

@RestController
@RequestMapping("/callback")
@RequiredArgsConstructor
public class CallbackController {

    private final ValidAccessRequest validAccessRequest;
    private final ConfirmationService confirmationService;
    private final MessageNewCallbackService messageNewCallbackService;

    @PostMapping("")
    public ResponseEntity<String> processCallback(@RequestBody CallbackRequestDTO callbackRequestDTO) {

        ResponseEntity<String> response = validAccessRequest.isValidRequest(callbackRequestDTO);

        if (response.getStatusCode() != HttpStatus.OK) {
            return response;
        }

        VkTypesOfRequest typeOfRequest = VkTypesOfRequest.getTypeOfRequest(callbackRequestDTO.getType());

        if (typeOfRequest == VkTypesOfRequest.CONFIRMATION) {
            return confirmationService.processRequest(callbackRequestDTO);
        }

        if (typeOfRequest == VkTypesOfRequest.MESSAGE_NEW) {
            return messageNewCallbackService.processRequest(callbackRequestDTO);
        }
                
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        

    }

}
