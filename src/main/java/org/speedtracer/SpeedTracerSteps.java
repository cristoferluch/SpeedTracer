package org.speedtracer;

public class SpeedTracerSteps {

    private final int step;
    private final String stepName;
    private final long stepDuration;
    private final long elapsedTime;
    private final long usedMemory;
    private final SpeedTracerTime timeUnit;

    SpeedTracerSteps(int step, String stepName, long elapsedTime, long stepDuration, SpeedTracerTime timeUnit, long usedMemory) {
        this.step = step;
        this.stepName = stepName;
        this.elapsedTime = elapsedTime;
        this.stepDuration = stepDuration;
        this.timeUnit = timeUnit;
        this.usedMemory = usedMemory;
    }

    protected int getStep() {
        return this.step;
    }

    protected String getStepName() {
        return this.stepName;
    }

    protected String getDuration()  {
        return (this.timeUnit == SpeedTracerTime.DEFAULT ? convertDefault(this.stepDuration) : convertTime(this.stepDuration));
    }

    protected String getElapsedTime() {
        return (this.timeUnit == SpeedTracerTime.DEFAULT ? convertDefault(this.elapsedTime) : convertTime(this.elapsedTime));
    }

    protected String getUsedMemory() {
        return String.format("%.2f MB", (double) this.usedMemory);
    }

    protected String convertTime(long duration)  {

        switch (this.timeUnit) {
            case NANOSECONDS:
                return String.format("%.2f ns", (double) duration);
            case MILLISECONDS:
                return String.format("%.2f ms", (double) duration / 1_000_000.0);
            case SECONDS:
                return String.format("%.2f s", (double) duration / 1_000_000_000.0);
            default:
                return String.format("%.2f", (double) duration);
        }
    }

    protected String convertDefault(long duration) {

        if (duration > 1_000_000_000) {
            return String.format("%.2f s", (double) duration / 1_000_000_000.0);
        }

        if (duration > 1_000_000) {
            return String.format("%.2f ms", (double) duration / 1_000_000.0);
        }

        return String.format("%.2f ns", (double) duration);
    }

}
