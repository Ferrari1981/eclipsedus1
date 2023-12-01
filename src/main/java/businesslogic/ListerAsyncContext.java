package businesslogic;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Named("listerAsyncContext")
public class ListerAsyncContext {
    @Inject
    private   SubClassWriterErros subClassWriterErros;

    // TODO: 29.11.2023
    public  void runnigAsyncContext   (@NotNull AsyncContext asyncContext,@NotNull ServletContext ЛОГ){

try{
    asyncContext.setTimeout(1500000);
    asyncContext.addListener(new AsyncListener() {
        @Override
        public void onComplete(AsyncEvent asyncEvent) throws IOException {
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    +" Listener  onComplete onComplete onComplete onComplete onComplete onComplete  "+Thread.currentThread().getName() );
        }

        @Override
        public void onTimeout(AsyncEvent asyncEvent) throws IOException {
            // TODO: 01.12.2023 clear
            if (asyncEvent.getSuppliedRequest().isAsyncStarted()) {
                asyncEvent.getAsyncContext().complete();
            }
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    +"  Listener onTimeout onTimeout onTimeout  onTimeout onTimeout onTimeout "+Thread.currentThread().getName()+
                    " getTimeout " +asyncEvent.getAsyncContext().getTimeout());
        }

        @Override
        public void onError(AsyncEvent asyncEvent) throws IOException {
// TODO: 29.11.2023
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    +" Listener  onError onError onError onError onError onError onError onError onError  "+Thread.currentThread().getName());

            Exception exception=new Exception(asyncEvent.getThrowable());
            // TODO: 02.11.2023 запись ошибку
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(exception,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
            // TODO: 01.12.2023  clear  
            if (asyncEvent.getSuppliedRequest().isAsyncStarted()) {
                // TODO: 29.11.2023
                asyncEvent.getAsyncContext().complete();
            }

        }

        @Override
        public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    +"  Listener onStartAsync onStartAsync onStartAsync  onStartAsync onStartAsync onStartAsync onStartAsync onStartAsync onStartAsync"
                    +Thread.currentThread().getName() );
        }
    },asyncContext.getRequest(), asyncContext.getResponse());


      ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
              " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
              " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
              +"  Listener END  "+Thread.currentThread().getName() );
  } catch (Exception e) {
        // TODO: 02.11.2023 запись ошибку
        subClassWriterErros.
                МетодаЗаписиОшибкиВЛог(e,
                        Thread.currentThread().
                                getStackTrace(),
                        ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
    }
    }
}
