package runtimes;



import businesslogic.*;
import dsu1glassfishatomic.workinterfaces.ProducedCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( value="/sous.jboss.runtimejboss",asyncSupported = true)
public class DSU1ServletRuntimeJboss extends HttpServlet {
    @EJB
    private SessionBeanGETRuntimeJboss sessionBeanGETRuntimeJboss;
    @Inject
    private SubClassWriterErros subClassWriterErros;



    private  ServletContext   ЛОГ;


    @Inject
    @ProducedCard
    private SessionFactory sessionSousJboss;

    @Inject
    private  AyntificationDontPasswordAndLogin ayntificationDontPasswordAndLogin;


    @Inject
    private TransationCompleteSession transationCompleteSession;

    @Inject
    private ListerAsyncContext listerAsyncContext;



    DSU1ServletRuntimeJboss(){
        System.out.println(" class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

    }





    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     // super.doGet(req, resp);
           ЛОГ = getServletContext();
                // TODO: 29.11.2023 async
              req.startAsync();
                try {
                    // TODO: 29.11.2023 listrer
                    listerAsyncContext.runnigAsyncContext(req.getAsyncContext(),ЛОГ);

                    if (sessionSousJboss.isOpen()) {
                        // TODO: 01.11.2023 Получаем Сессию
                        final   Session  session=     transationCompleteSession.startingSession(ЛОГ,sessionSousJboss);

                        transationCompleteSession.startingTransastion(ЛОГ,session);

                        // TODO: 01.11.2023 Аунтификайия Имя И Пароль
                        Boolean    СтатусаАунтификацииПользователя   =
                                ayntificationDontPasswordAndLogin.successAyntificationUserForServlets(  (HttpServletRequest) req.getAsyncContext().getRequest(),
                                        (HttpServletResponse)  req.getAsyncContext().getResponse(),session,ЛОГ);


                        if (СтатусаАунтификацииПользователя==true) {
                            //TODO ЗАПУСКАЕМ КОДЕ МЕТОДА GET()
                            sessionBeanGETRuntimeJboss.МетодГлавныйRuntimeJboss(ЛОГ, (HttpServletRequest) req.getAsyncContext().getRequest(),
                                    (HttpServletResponse)  req.getAsyncContext().getResponse(), session);
                        }

                    }



                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " ((HttpServletRequest) req).getPathInfo() " + ((HttpServletRequest) req).getPathInfo()
                            + "  POOL  THREAD " + Thread.currentThread().getName());
                    // TODO: 23.04.2023 clears Async
                } catch (Exception e) {
                    // TODO: 02.11.2023 запись ошибку
                    subClassWriterErros.
                            МетодаЗаписиОшибкиВЛог(e,
                                    Thread.currentThread().
                                            getStackTrace(),
                                    ЛОГ, "ErrorsLogs/ErrorJbossServletRuntime.txt");
                }



                // TODO: 23.04.2023 exit
    }


    public void destroy() {
      try{
          System.out.println(" class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                  " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                  " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    } catch (Exception e) {
          subClassWriterErros.
                  МетодаЗаписиОшибкиВЛог(e,
                          Thread.currentThread().
                                  getStackTrace(),
                          ЛОГ,"ErrorsLogs/ErrorJbossServletRuntime.txt");

    }

    }
}

































