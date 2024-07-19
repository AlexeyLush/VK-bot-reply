package ru.justai.vkbot.utils.validators.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorValidAccessTypes {

    ANOTHER_API_VERSION("Бот не поддерживает данную версию API"),
    WRONG_GROUP("ID группы не совпадает");

    private final String errorMessage;

}
