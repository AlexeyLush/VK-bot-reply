package ru.justai.vkbot.utils.validators.messages;

import lombok.Getter;

@Getter
public enum ErrorValidMessageTypes {

    MAX_LENGTH_MESSAGE("Превышен лимит символов в сообщении");

    private final String errorMessage;

    ErrorValidMessageTypes(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
