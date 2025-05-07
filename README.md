# SpeedTracer

## Overview

SpeedTracer is a lightweight Java utility designed to help developers measure and analyze the performance of their code execution. It provides detailed timing information for each step of your process along with memory usage metrics.

### Features

- Measure execution time of individual steps

- Track total elapsed time

- Monitor memory usage (in MB)

- Flexible time units (nanoseconds, milliseconds, seconds)

- Automatic time unit selection (DEFAULT mode)

- Clean tabular output format

## Usage

### Basic Usage

```
SpeedTracer tracer = new SpeedTracerBuilder("benchmark")
        .timeUnit(SpeedTracerTime.DEFAULT)
        .build();

try {
    tracer.start();
    
    // Your code block 1
    tracer.step("step 1");
    
    // Your code block 2
    tracer.step("step 2");
    
    // Your code block 3
    tracer.step("step 3");
    
    // Print results
    System.out.println(tracer.end());

} catch (Exception e) {
    e.printStackTrace();
}
```

### Output Example

```
----------------------------------------------------------------------
|                             BENCHMARK                              |
----------------------------------------------------------------------
| STEP  | NAME       |      DURATION |       ELAPSED |        MEMORY |
----------------------------------------------------------------------
| 1     | step 1     |    1700,00 ns |    1700,00 ns |       4,00 MB |
| 2     | step 2     |     500,66 ms |     500,66 ms |       4,00 MB |
| 3     | step 3     |        1,01 s |        1,52 s |      38,00 MB |
----------------------------------------------------------------------
```

## API Reference

### SpeedTracerBuilder

- `SpeedTracerBuilder(String traceName)` - Creates a new builder with the given trace name

- `timeUnit(SpeedTracerTime timeUnit)` - Sets the time unit for output (default: DEFAULT auto-scales)

- `build()` - Constructs the SpeedTracer instance

### SpeedTracer

- `start()` - Begins the timing process

- `step(String stepName)` - Records a step with the given name

- `end()` - Stops tracing and returns formatted results

- `clear()` - Resets the tracer to its initial state


### Time Units

Available through SpeedTracerTime enum:

- `NANOSECONDS` - Display times in nanoseconds

- `MILLISECONDS` - Display times in milliseconds

- `SECONDS` - Display times in seconds

- `DEFAULT` - Automatically selects the most appropriate unit  (default)


## Contribution

Contributions are welcome! Please fork the repository and submit pull requests for any improvements or bug fixes.
