package com.realtime.systems.realtimesystemsrps.util;

import java.util.List;

public class Util {

    public static double getVariance(List<Double> list) {
        double averageInList = list.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        double variance = 0;
        for (Double elem : list) {
            variance += (elem - averageInList) * (elem - averageInList);
        }
        variance /= list.size();

        return Math.sqrt(variance);
    }

}
