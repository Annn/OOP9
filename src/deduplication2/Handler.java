package deduplication2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Handler implements InvocationHandler{

    private final DeduplicationInterface deduplication;

    public Handler (DeduplicationInterface deduplication){
        this.deduplication = deduplication;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(deduplication, args);
        return null;
    }

}