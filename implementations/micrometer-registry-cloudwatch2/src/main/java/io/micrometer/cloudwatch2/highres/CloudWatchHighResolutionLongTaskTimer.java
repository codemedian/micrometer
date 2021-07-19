package io.micrometer.cloudwatch2.highres;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.distribution.HistogramSnapshot;
import io.micrometer.core.instrument.internal.DefaultLongTaskTimer;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class CloudWatchHighResolutionLongTaskTimer extends DefaultLongTaskTimer {

    public CloudWatchHighResolutionLongTaskTimer(Id id, Clock clock, TimeUnit baseTimeUnit) {
        super(id, clock, baseTimeUnit, null, false);
    }

    @Override
    public void forEachActive(Consumer<Sample> sample) {
        super.forEachActive(sample);
    }

    @Override
    public HistogramSnapshot takeSnapshot() {
        throw new IllegalStateException("takeSnapshot not supported for HighResolution registry");
    }
}
