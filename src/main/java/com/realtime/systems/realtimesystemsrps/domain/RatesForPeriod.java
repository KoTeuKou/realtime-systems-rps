package com.realtime.systems.realtimesystemsrps.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.realtime.systems.realtimesystemsrps.util.RatesDeserializer;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonSerialize
@JsonDeserialize
public class RatesForPeriod {

    @JsonProperty("rates")
    @JsonDeserialize(using = RatesDeserializer.class)
    private List<UsdRate> rates;

    @JsonProperty("date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
