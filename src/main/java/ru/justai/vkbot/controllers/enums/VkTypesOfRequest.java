package ru.justai.vkbot.controllers.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.HashMap;

@Getter
@AllArgsConstructor
public enum VkTypesOfRequest {

    CONFIRMATION("confirmation"),
    MESSAGE_NEW("message_new");


    private String type;
    
    public static VkTypesOfRequest getTypeOfRequest(String typeString) {
        for (VkTypesOfRequest vkTypesOfRequest : VkTypesOfRequest.values()) {
            if (vkTypesOfRequest.getType().equals(typeString)) {
                return vkTypesOfRequest;
            }
        }
        return null;
    }

}
