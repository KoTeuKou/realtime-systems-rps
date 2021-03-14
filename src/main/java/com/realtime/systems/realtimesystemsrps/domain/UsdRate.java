package com.realtime.systems.realtimesystemsrps.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UsdRate {

    @JsonProperty("USD")
    private Double usdValue;
}
