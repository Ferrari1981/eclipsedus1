package businesslogic;

import com.sun.istack.NotNull;
import io.reactivex.rxjava3.core.Completable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Named ("asyncContextComplete")
public class AsyncContextComplete {
    @Inject
    private   SubClassWriterErros subClassWriterErros;

   public void endingContextComplete(@NotNull HttpServletRequest request,@NotNull ServletContext ЛОГ){
try{

    if (request.isAsyncStarted()) {
        request.getAsyncContext().complete();
    }
    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                + " request.isAsyncStarted() " + request.isAsyncStarted()
            + " CompletableAsync Async Sesson  !!!! CompletableAsync Async Sesson  !!!!  CompletableAsync  Async Sesson  !!!!  " +new Date().toLocaleString()+
            "\n"+ "    Runtime.getRuntime().availableProcessors() " +   Runtime.getRuntime().availableProcessors());
    } catch (Exception e) {
// TODO: 17.11.2023 ERROR joiner
        subClassWriterErros.
                МетодаЗаписиОшибкиВЛог(e,
                        Thread.currentThread().
                                getStackTrace(),
                        ЛОГ, "ErrorsLogs/ErrorJbossServletUpdatePO.txt");

    }

    }


}
