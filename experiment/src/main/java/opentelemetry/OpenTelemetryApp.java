package opentelemetry;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.metrics.LongUpDownCounter;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.sdk.autoconfigure.AutoConfiguredOpenTelemetrySdk;

public class OpenTelemetryApp {
    private OpenTelemetry openTelemetrySDK;
    private Meter meter;
    private LongUpDownCounter counter;
    private final String INSTRUMENTATION_NAME = "test-recorder";

    // Initialize SDK and counters we are interested in. Unfortunately
    // creating a single instance of the OpenTelemetry SDK means we will
    // only have one instrumentation name. This is required to make everything work
    // unfortunately.
    public OpenTelemetryApp(){
        openTelemetrySDK = AutoConfiguredOpenTelemetrySdk.initialize()
        .getOpenTelemetrySdk();

        meter = openTelemetrySDK.meterBuilder(INSTRUMENTATION_NAME).build();

        counter = meter.upDownCounterBuilder("test-case-counter")
        .setDescription("test case metric")
        .setUnit("decimal")
        .build();
    }

    public LongUpDownCounter getCounter(){
        return counter;
    }
}
