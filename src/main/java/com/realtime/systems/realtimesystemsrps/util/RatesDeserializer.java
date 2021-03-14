package com.realtime.systems.realtimesystemsrps.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.realtime.systems.realtimesystemsrps.domain.UsdRate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RatesDeserializer
        extends JsonDeserializer<List<UsdRate>> {

    @Override
    public List<UsdRate> deserialize(JsonParser p, DeserializationContext context) throws IOException {
        List<UsdRate> rates = new ArrayList<>();
        String code = "";
        while (!Objects.equals(code, "start_at")) {
            code = p.nextFieldName();
            if (code != null && code.equals("USD")) {
                JsonToken token = p.nextValue();
                if (token.isNumeric()) {
                    UsdRate rate = new UsdRate();
                    rate.setUsdValue(p.getDoubleValue());
                    rates.add(rate);
                }
            }
        }
        return rates;
    }
}