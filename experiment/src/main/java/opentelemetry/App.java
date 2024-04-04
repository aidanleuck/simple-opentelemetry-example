package opentelemetry;

import io.opentelemetry.api.OpenTelemetry;

/**
 * Hello world!
 *
 */
public class App 
{
    private static App app;
    private final ExtensionList extensionList;
    private static final Object lock = new Object();

    private App(){
        OpenTelemetryApp openTelemetry = new OpenTelemetryApp();
        extensionList = new ExtensionList();
        extensionList.registerSingleton(openTelemetry);
    }

    public ExtensionList getExtensionList(){
       return extensionList;
    }

    public static App get(){
        synchronized(lock){
            if(app == null){
                app = new App();
            }
        }

        return app;
    }

    public static void main( String[] args )
    {
        Pipeline p = new Pipeline();
        p.run();
    }
}
