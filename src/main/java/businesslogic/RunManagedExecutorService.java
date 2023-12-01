package businesslogic;

import org.hibernate.Session;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedTask;
import javax.enterprise.concurrent.ManagedTaskListener;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

// TODO: 28.11.2023  starting classs runnuber GET
public class RunManagedExecutorService implements Runnable, ManagedTask, ManagedTaskListener {
    ServletContext ЛОГ;

    public RunManagedExecutorService(ServletContext ЛОГ) {
        this.ЛОГ = ЛОГ;
    }


    public void run() {
        ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                +"  POOL  THREAD "+Thread.currentThread().getName() );
    }


    @Override
    public ManagedTaskListener getManagedTaskListener() {
        ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                +"  POOL  THREAD "+Thread.currentThread().getName() );
        return this;
    }

    @Override
    public Map<String, String> getExecutionProperties() {
        ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                +"  POOL  THREAD "+Thread.currentThread().getName() );
        return new HashMap<>();
    }

    @Override
    public void taskSubmitted(Future<?> future, ManagedExecutorService managedExecutorService, Object o) {
        ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                +"  POOL  THREAD "+Thread.currentThread().getName() );
    }

    @Override
    public void taskAborted(Future<?> future, ManagedExecutorService managedExecutorService, Object o, Throwable throwable) {
        ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                +"  POOL  THREAD "+Thread.currentThread().getName() );
    }

    @Override
    public void taskDone(Future<?> future, ManagedExecutorService managedExecutorService, Object o, Throwable throwable) {
        ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                +"  POOL  THREAD "+Thread.currentThread().getName() );
    }

    @Override
    public void taskStarting(Future<?> future, ManagedExecutorService managedExecutorService, Object o) {
        ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                +"  POOL  THREAD "+Thread.currentThread().getName() );
    }
}
