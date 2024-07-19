package ru.justai.vkbot.service.callbacks.implementations;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.justai.vkbot.dto.callbacks.CallbackRequestDTO;
import ru.justai.vkbot.dto.messages.MessageNewRequestDTO;
import ru.justai.vkbot.dto.messages.MessageResponseDTO;
import ru.justai.vkbot.service.callbacks.CallbackService;
import ru.justai.vkbot.service.sender.implementations.MessageSenderService;
import ru.justai.vkbot.utils.validators.messages.ErrorValidMessageTypes;
import ru.justai.vkbot.utils.validators.messages.MessageSendValidator;

@Service
@RequiredArgsConstructor
public class MessageNewCallbackService implements CallbackService {

    private final MessageSenderService messageSenderService;
    private static final String SUCCESSFUL_MESSAGE = "ok";

    @Override
    public ResponseEntity<String> processRequest(CallbackRequestDTO callbackRequestDTO) {

        MessageNewRequestDTO messageNewRequestDTO = getMessageFromCallbackRequest(callbackRequestDTO);

        if (messageNewRequestDTO == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

        MessageResponseDTO messageResponseDTO = createMessageForUser(messageNewRequestDTO);

        if (!MessageSendValidator.isValidMessage(messageResponseDTO.getMessage())) {
            return new ResponseEntity<>(ErrorValidMessageTypes.MAX_LENGTH_MESSAGE.getErrorMessage(),
                    HttpStatus.BAD_REQUEST);
        }

        if (messageSenderService.send(messageResponseDTO).getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>(SUCCESSFUL_MESSAGE, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private MessageNewRequestDTO getMessageFromCallbackRequest(CallbackRequestDTO callbackRequestDTO) {

        Map<String, Object> message = (Map<String, Object>) callbackRequestDTO.getObject().get("message");

        MessageNewRequestDTO messageNewRequestDTO = new MessageNewRequestDTO();

        try {

            messageNewRequestDTO.setId(Integer.parseInt(message.get("id").toString()));
            messageNewRequestDTO.setFromId(Integer.parseInt(message.get("from_id").toString()));
            messageNewRequestDTO.setText(message.get("text").toString());

            return messageNewRequestDTO;

        } catch (NumberFormatException exception) {
            return null;
        }

    }

    private MessageResponseDTO createMessageForUser(MessageNewRequestDTO messageNewRequestDTO) {

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();

        messageResponseDTO.setUserId(messageNewRequestDTO.getFromId());
        messageResponseDTO.setMessage(String.format("Вы сказали: %s", messageNewRequestDTO.getText()));
        messageResponseDTO.setRandomId(messageNewRequestDTO.getId());

        return messageResponseDTO;
    }

}
