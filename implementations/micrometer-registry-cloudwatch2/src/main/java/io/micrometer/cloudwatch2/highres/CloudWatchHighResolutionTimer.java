package io.micrometer.cloudwatch2.highres;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.distribution.HistogramSnapshot;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class CloudWatchHighResolutionTimer extends CloudWatchHighResMetric implements Timer  {
    private final Id id;
    private final Clock clock;

    public CloudWatchHighResolutionTimer(Id id, Clock clock) {
        super(id);
        this.id = id;
        this.clock = clock;
    }

    @Override
    public Id getId() {
        return id;
    }

    @Override
    public void record(long amount, TimeUnit unit) {
        super.record(unit.toMillis(amount));
    }

    @Override
    public <T> T record(Supplier<T> f) {
        final long s = clock.monotonicTime();
        try {
            return f.get();
        } finally {
            final long e = clock.monotonicTime();
            record(e - s, TimeUnit.NANOSECONDS);
        }
    }

    @Override
    public <T> T recordCallable(Callable<T> f) throws Exception {
        final long s = clock.monotonicTime();
        try {
            return f.call();
        } finally {
            final long e = clock.monotonicTime();
            record(e - s, TimeUnit.NANOSECONDS);
        }
    }

    @Override
    public void record(Runnable f) {
        final long s = clock.monotonicTime();
        try {
            f.run();
        } finally {
            final long e = clock.monotonicTime();
            record(e - s, TimeUnit.NANOSECONDS);
        }
    }

    @Override
    public long count() {
        throw new IllegalStateException("Illegal operation on CW high resolution timer");
    }

    @Override
    public double totalTime(TimeUnit unit) {
        throw new IllegalStateException("Illegal operation on CW high resolution timer");
    }

    @Override
    public double max(TimeUnit unit) {
        throw new IllegalStateException("Illegal operation on CW high resolution timer");
    }

    @Override
    public TimeUnit baseTimeUnit() {
        return TimeUnit.MILLISECONDS;
    }

    @Override
    public HistogramSnapshot takeSnapshot() {
        throw new IllegalStateException("Illegal operation on CW high resolution timer");
    }
}
