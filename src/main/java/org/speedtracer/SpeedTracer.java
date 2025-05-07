package org.speedtracer;

import java.util.ArrayList;

public class SpeedTracer {

    private static final long MEGABYTE = 1024L * 1024L;

    private String traceName = "";
    private long startTime = 0;
    private long lastStepDuration = 0;
    private long stepDuration = 0;
    private long elapsedTime = 0;
    private int counter = 1;
    private SpeedTracerTime timeUnit = SpeedTracerTime.MILLISECONDS;
    private final ArrayList<SpeedTracerSteps> steps = new ArrayList<>();

    public SpeedTracer(SpeedTracerBuilder builder) {
        this.traceName = builder.traceName;
        this.timeUnit = builder.timeUnit;
    }

    public void start() throws Exception {
        this.startTime = getTime();
    }

    public void step(String stepName) throws Exception {

        long time = getTime();
        long usedMemory = getMemory();

        this.elapsedTime = time - this.startTime;
        this.stepDuration = time - (this.lastStepDuration == 0 ? this.startTime : this.lastStepDuration);
        this.lastStepDuration = time;

        steps.add(new SpeedTracerSteps(this.counter, stepName, this.elapsedTime, this.stepDuration, this.timeUnit, usedMemory));

        this.counter++;
    }

    private long getTime() {
        return System.nanoTime();
    }

    private long getMemory() {

        Runtime runtime = Runtime.getRuntime();
        long memory = runtime.totalMemory() - runtime.freeMemory();

        return memory / MEGABYTE;
    }

    public void clear() {
        this.steps.clear();
        this.traceName = "";
        this.startTime = 0;
        this.lastStepDuration = 0;
        this.stepDuration = 0;
        this.elapsedTime = 0;
        this.counter = 1;
    }

    public String end() throws Exception {

        final StringBuilder str = new StringBuilder();

        int columStepSize = 5;
        int columNameSize = 10;
        int columStepDurationSize = 13;
        int columElapsedSize = 13;
        int columMemorySize = 13;

        for (SpeedTracerSteps speedTracerSteps : steps) {
            columStepSize = Math.max(columStepSize, String.valueOf(speedTracerSteps.getStep()).length());
            columStepDurationSize = Math.max(columStepDurationSize, String.valueOf(speedTracerSteps.getDuration()).length());
            columElapsedSize = Math.max(columElapsedSize, String.valueOf(speedTracerSteps.getElapsedTime()).length());
            columNameSize = Math.max(columNameSize, speedTracerSteps.getStepName().length());
        }

        int columnsSize = (columStepSize + columNameSize + columStepDurationSize + columElapsedSize + columMemorySize) + 16;
        int leftSpace = ((columnsSize - 2) - this.traceName.length()) / 2;
        int rightSpace = (columnsSize - 2) - this.traceName.length() - leftSpace;

        String divider = "-".repeat(columnsSize)+"\n";
        String tableFormat = "| %-" + columStepSize + "s | %-" + columNameSize + "s | %" + columStepDurationSize + "s | %" + columElapsedSize + "s | %" + columMemorySize + "s |%n";

        str.append(divider)
                .append(String.format("|%" + (leftSpace + this.traceName.length()) + "s%" + rightSpace + "s|%n", this.traceName.toUpperCase(), ""))
                .append(divider)
                .append(String.format(tableFormat, "STEP", "NAME", "DURATION", "ELAPSED", "MEMORY"))
                .append(divider);


        steps.forEach(step ->
                str.append(String.format(tableFormat, step.getStep(), step.getStepName(), step.getDuration(), step.getElapsedTime(), step.getUsedMemory()))
        );


        str.append(divider);

        return str.toString();
    }
}
