package opentelemetry;

import java.util.ArrayList;
import java.util.List;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.LongUpDownCounter;

public class Pipeline {
    public void run(){
       
       // Use Jenkins extensionList to retrieve singleton (this will look)
       // a bit different I believe it is simply ExtensionList.get(Class) in Jenkins
       OpenTelemetryApp app = App.get().getExtensionList().get(OpenTelemetryApp.class);
       LongUpDownCounter counter = app.getCounter();
       
       // Increment counter or whatever metric we are interested in and add labels
       // this will get flushed out of the buffer in 60 seconds.
       counter.add(1, Attributes.of(AttributeKey.stringKey("test"), "test"));
    }
}
