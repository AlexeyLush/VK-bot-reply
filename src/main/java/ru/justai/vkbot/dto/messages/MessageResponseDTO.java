package ru.justai.vkbot.dto.messages;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageResponseDTO implements Serializable{
    @JsonProperty(value = "user_id")
    private int userId;

    private String message;

    @JsonProperty(value = "random_id", defaultValue = "0")
    private int randomId;

}
