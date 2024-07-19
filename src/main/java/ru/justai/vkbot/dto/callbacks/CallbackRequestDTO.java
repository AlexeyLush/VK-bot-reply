package ru.justai.vkbot.dto.callbacks;

import java.util.Map;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallbackRequestDTO {

    private String type;

    @JsonProperty(value = "event_id")
    private String eventId;

    @JsonProperty(value = "v")
    private String versionApi;

    private Map<String, Object> object;
    
    @JsonProperty(value = "group_id")
    private Long groupId;
}
