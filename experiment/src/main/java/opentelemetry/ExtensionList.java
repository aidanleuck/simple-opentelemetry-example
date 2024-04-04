package opentelemetry;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.stream.IntStream;

// Simple mock ExtensionList class
public class ExtensionList {
    private final List<Object> extensionList;

    protected ExtensionList(){
        extensionList = new ArrayList<>();
    }

    protected <T> T get(Class<T> clazz){
    Object item = extensionList.stream().
        filter(clazz2 -> clazz2.getClass().getName().equals(clazz.getName()))
        .findFirst()
        .orElseThrow();

        return (T) item;
    }

    // Register singleton
    public synchronized void registerSingleton(Object o){
        try{
            get(o.getClass());
        }
        catch(NoSuchElementException e){
            extensionList.add(o);
            return;
        }
        
        throw new NoSuchElementException("Yeah only one allowed buddy");
        
    }

    // Remove item
    public synchronized boolean remove(Class clazz){
       OptionalInt indexOpt = IntStream.range(0, extensionList.size())
        .filter(i -> clazz.getSimpleName().equals(extensionList.get(i)))
        .findFirst();

        if(indexOpt.isEmpty()){
            return false;
        }

        return extensionList.remove(indexOpt.getAsInt()) != null;
    }
}
