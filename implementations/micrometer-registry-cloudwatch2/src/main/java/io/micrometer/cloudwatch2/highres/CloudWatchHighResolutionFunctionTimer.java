package io.micrometer.cloudwatch2.highres;

import io.micrometer.core.instrument.FunctionTimer;

import java.util.concurrent.TimeUnit;

public class CloudWatchHighResolutionFunctionTimer implements FunctionTimer {
    @Override
    public double count() {
        return 0;
    }

    @Override
    public double totalTime(TimeUnit unit) {
        return 0;
    }

    @Override
    public TimeUnit baseTimeUnit() {
        return null;
    }

    @Override
    public Id getId() {
        return null;
    }
}
