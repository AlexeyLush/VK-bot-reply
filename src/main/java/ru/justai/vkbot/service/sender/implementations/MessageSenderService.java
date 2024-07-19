package ru.justai.vkbot.service.sender.implementations;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;
import ru.justai.vkbot.dto.messages.MessageResponseDTO;
import ru.justai.vkbot.service.sender.SenderService;
import ru.justai.vkbot.utils.uri.implementations.MessageSendUriCreator;

@Service
@RequiredArgsConstructor
public class MessageSenderService implements SenderService<MessageResponseDTO> {


    private final MessageSendUriCreator uriCreator;


    static class ResponseFromServer {
        public int response;    
    }

    @Override
    public ResponseEntity<String> send(@NonNull MessageResponseDTO messageResponseDTO) {

        URI messageSendUri = uriCreator.getUriOnAction(messageResponseDTO);


        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ResponseFromServer> entity = restTemplate.postForEntity(messageSendUri, null, ResponseFromServer.class);
        if (entity.getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
