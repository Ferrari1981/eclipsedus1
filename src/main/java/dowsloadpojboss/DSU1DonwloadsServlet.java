package dowsloadpojboss;


import businesslogic.*;
import dsu1glassfishatomic.workinterfaces.ProducedCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( value="/sous.jboss.download",asyncSupported = true)
public class DSU1DonwloadsServlet extends HttpServlet {
    @Inject
    private  SubClassWriterErros subClassWriterErros;


    @EJB
    private BeanCallsBackDownloadPO beanCallsBackDownloadPO;

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





    DSU1DonwloadsServlet(){
        System.out.println(" class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     // super.doGet(req, resp);
           ЛОГ = getServletContext();
            req.startAsync();
        try {
            // TODO: 29.11.2023 listrer
            listerAsyncContext.runnigAsyncContext(req.getAsyncContext(),ЛОГ);
            // TODO: 22.05.2023 lister asynccontext
            if (sessionSousJboss.isOpen()) {
                // TODO: 01.11.2023 Получаем Сессию
                final   Session  session=     transationCompleteSession.startingSession(ЛОГ,sessionSousJboss);

                transationCompleteSession.startingTransastion(ЛОГ,session);
                // TODO: 01.11.2023 Аунтификайия Имя И Пароль
                Boolean    СтатусаАунтификацииПользователя   =
                        ayntificationDontPasswordAndLogin.successAyntificationUserForServlets((HttpServletRequest) req.getAsyncContext().getRequest(),
                                (HttpServletResponse)  req.getAsyncContext().getResponse(),session,ЛОГ);


                if (СтатусаАунтификацииПользователя==true) {

                    // TODO: 24.07.2023 запуск обновение ПО
                    beanCallsBackDownloadPO.
                            МетодЗапускаОбновлениеПО(ЛОГ, (HttpServletRequest) req.getAsyncContext().getRequest(),
                                    (HttpServletResponse)  req.getAsyncContext().getResponse(), session);
                    //  beanCallsBackDownloadPO.     МетодЗапускаОбновлениеПО(ЛОГ, req,  resp);


                }


            }






            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ((HttpServletRequest) req).getPathInfo() " + ((HttpServletRequest) req).getPathInfo());
        } catch (Exception e) {
            // TODO: 02.11.2023 запись ошибку
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ, "ErrorsLogs/ErrorJbossServletUpdatePO.txt");

        }

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
                          ЛОГ,"ErrorsLogs/ErrorJbossServletUpdatePO.txt");

    }

    }
}

































