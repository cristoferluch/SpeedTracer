package org.speedtracer;

public class SpeedTracerBuilder {

    protected final String traceName;
    protected SpeedTracerTime timeUnit = SpeedTracerTime.DEFAULT;

    public SpeedTracerBuilder(String traceName) {
        this.traceName = traceName;
    }

    public SpeedTracerBuilder timeUnit(SpeedTracerTime timeUnit) {
        this.timeUnit = timeUnit;
        return this;
    }

    public SpeedTracer build() {
        return new SpeedTracer(this);
    }

}
