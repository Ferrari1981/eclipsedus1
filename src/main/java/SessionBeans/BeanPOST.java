package SessionBeans;


import businesslogic.*;
import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.jboss.ejb3.annotation.TransactionTimeout;

import javax.ejb.*;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Session Bean implementation class BeanPOST
 */
@Stateful(mappedName = "beanPOST")
@LocalBean
@TransactionTimeout(value = 1, unit = TimeUnit.HOURS)
@Asynchronous
public class BeanPOST {
    @Inject
    private SubClassSessionBeanPOST subClassSessionBeanPOST;
    @Inject
    private  BEANCallsBack bEANCallsBack;
    @Inject
    private    SubClassWriterErros subClassWriterErros;

    @Inject
    private  TransationCompleteSession transationCompleteSession;

    @Inject
    private AsyncContextComplete asyncContextComplete;


    /**
     * Default constructor.
     */
    public BeanPOST() {
        // TODO Auto-generated constructor stub
        System.out.print("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
    }




    public void МетодБинаPOST(@NotNull ServletContext ЛОГ,
                                      @NotNull HttpServletRequest request,
                                      @NotNull  HttpServletResponse response,
                              @NotNull Session session) throws InterruptedException, ExecutionException {;
        try {
            ///Todo  получаем данные от клиента
          byte[]  БуферРезультатPOST=		subClassSessionBeanPOST.МетодЗапускаPOST(request, response, ЛОГ,session);


            // TODO: 17.11.2023 commit transaction
            transationCompleteSession.commitingTransastion(ЛОГ,session);
            ///Todo получаем данные от Клиента на Сервер
                bEANCallsBack.МетодBackДанныеКлиентуByte(response, БуферРезультатPOST, ЛОГ  );

// TODO: 29.11.2023 закрывам Async
            asyncContextComplete.endingContextComplete(request,ЛОГ);

            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
        } catch (Exception e) {
            // TODO: 02.11.2023 ROLLBACK
            // TODO: 17.11.2023 ERROR transaction
            transationCompleteSession.erroringTransastion(ЛОГ,session);
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
    }


}

