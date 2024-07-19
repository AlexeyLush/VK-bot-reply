package ru.justai.vkbot.utils.uri;

import java.net.URI;

public interface UriCreator<T> {

    URI getUriOnAction(T object);
    
} 