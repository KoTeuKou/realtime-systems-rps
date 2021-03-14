package com.realtime.systems.realtimesystemsrps.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

@Data
@JsonSerialize
@JsonDeserialize
public class RatesForDate {
    @JsonProperty("rates")
    private UsdRate usdRate;

    @JsonProperty("date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
