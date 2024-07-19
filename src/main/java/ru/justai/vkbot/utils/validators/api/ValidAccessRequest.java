package ru.justai.vkbot.utils.validators.api;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;
import ru.justai.vkbot.config.VkCredentials;
import ru.justai.vkbot.dto.callbacks.CallbackRequestDTO;

@Component
@RequiredArgsConstructor
public class ValidAccessRequest {

    private final VkCredentials vkCredentials;

    public ResponseEntity<String> isValidRequest(@NonNull CallbackRequestDTO callbackRequestDTO) {

        if (!Objects.equals(callbackRequestDTO.getVersionApi(), vkCredentials.getVersion())) {
            return new ResponseEntity<>(ErrorValidAccessTypes.ANOTHER_API_VERSION.getErrorMessage(),
                    HttpStatus.FORBIDDEN);
        }


        if (!Objects.equals(callbackRequestDTO.getGroupId(), vkCredentials.getGroupId())) {
            return new ResponseEntity<>(ErrorValidAccessTypes.WRONG_GROUP.getErrorMessage(), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
