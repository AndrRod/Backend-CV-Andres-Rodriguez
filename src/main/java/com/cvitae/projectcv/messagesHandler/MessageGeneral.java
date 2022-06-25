package com.cvitae.projectcv.messagesHandler;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MessageGeneral {
    @JsonProperty("message")
    private String message;
    @JsonProperty("path")
    private String path;
}
