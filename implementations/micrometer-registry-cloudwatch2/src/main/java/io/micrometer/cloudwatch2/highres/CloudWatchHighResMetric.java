package io.micrometer.cloudwatch2.highres;

import io.micrometer.core.instrument.Meter;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class CloudWatchHighResMetric {
    private static final ArrayList<Double> buffer = new ArrayList<>();
    private final Meter.Id id;

    public CloudWatchHighResMetric(Meter.Id id) {
        this.id = id;
    }

    public void record(double datum) {
        synchronized (this) {
            buffer.add(datum);
        }
    }

    public Map<Double, Long> allValuesHistogram() {
        final Map<Double, Long> valueCountMap;
        synchronized (this) {
            valueCountMap = buffer.stream().collect(Collectors.groupingBy(Double::doubleValue, Collectors.counting()));
            buffer.clear();
        }

        return valueCountMap;
    }
}
