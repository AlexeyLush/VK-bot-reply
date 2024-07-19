package ru.justai.vkbot.dto.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageNewRequestDTO {

    private int id;

    @JsonProperty(value = "from_id")
    private int fromId;

    private String text;

}
