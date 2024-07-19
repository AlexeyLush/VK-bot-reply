package ru.justai.vkbot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@PropertySource(value = "classpath:vk.properties")
@ConfigurationProperties(prefix = "vkbot.api")
public class VkCredentials {

    private String key;
    private String version;
    private String confirmationCode;
    private Long groupId;

}
