package ru.justai.vkbot.utils.validators.messages;

public class MessageSendValidator {

    private static final int MAX_LENGTH_MESSAGE = 4096;

    public static boolean isValidMessage(String message) {
        return message.length() <= MAX_LENGTH_MESSAGE;
    }

}
