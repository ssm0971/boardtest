package com.example.board.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ItemDTO {
    @JsonProperty("가구평균감량")
    private String a;
    @JsonProperty("가구평균감량률")
    private String b;
    @JsonProperty("가구평균배출량")
    private String c;

}
