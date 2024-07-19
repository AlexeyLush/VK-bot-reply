package ru.justai.vkbot.utils.uri.implementations;

import java.net.URI;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import ru.justai.vkbot.config.VkCredentials;
import ru.justai.vkbot.dto.messages.MessageResponseDTO;
import ru.justai.vkbot.utils.uri.UriCreator;

@Component
@RequiredArgsConstructor
public class MessageSendUriCreator implements UriCreator<MessageResponseDTO> {

    private static String vkUri = "https://api.vk.com/method/messages.send?";
    private final VkCredentials vkCredentials;

    @Override
    public URI getUriOnAction(MessageResponseDTO messageResponseDTO) {

        return UriComponentsBuilder.fromUriString(vkUri)
                .queryParam("access_token", vkCredentials.getKey())
                .queryParam("user_id", messageResponseDTO.getUserId())
                .queryParam("random_id", messageResponseDTO.getRandomId())
                .queryParam("message", messageResponseDTO.getMessage())
                .queryParam("v", vkCredentials.getVersion())
                .build()
                .toUri();

    }

}
