package com.realtime.systems.realtimesystemsrps.util;

import java.util.List;

public class Util {

    public static double getVariance(List<Double> list) {
        double averageInList = list.parallelStream().mapToDouble(Double::doubleValue).average().orElse(0);

        return Math.sqrt(list
                .parallelStream()
                .mapToDouble(elem -> (elem - averageInList) * (elem - averageInList))
                .sum() / list.size());
    }

}
