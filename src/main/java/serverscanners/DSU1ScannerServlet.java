package serverscanners;




import businesslogic.*;
import dsu1glassfishatomic.workinterfaces.ProducedCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.inject.Inject;
import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( value="/sous.jboss.scanner",asyncSupported = true)
public class DSU1ScannerServlet extends HttpServlet {
    private      ServletContext    ЛОГ;
    /*SessionFactory sessionSousJboss;*/
    @Inject
    SubClassWriterErros subClassWriterErros;

    @Inject
    @ProducedCard
    private SessionFactory sessionSousJboss;

    @Inject
    private   AyntificationDontPasswordAndLogin ayntificationDontPasswordAndLogin;



    @Inject
    private TransationCompleteSession transationCompleteSession;

    @Inject
    private ListerAsyncContext listerAsyncContext;





    DSU1ScannerServlet(){
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

            if (sessionSousJboss.isOpen()) {
                // TODO: 01.11.2023 Получаем Сессию
                final   Session  session=     transationCompleteSession.startingSession(ЛОГ,sessionSousJboss);

                transationCompleteSession.startingTransastion(ЛОГ,session);
                // TODO: 01.11.2023 Аунтификайия Имя И Пароль
                Boolean    СтатусаАунтификацииПользователя   =
                        ayntificationDontPasswordAndLogin.successAyntificationUserForServlets((HttpServletRequest) req.getAsyncContext().getRequest(),
                                (HttpServletResponse)  req.getAsyncContext().getResponse(),session,ЛОГ);


                if (СтатусаАунтификацииПользователя==true) {

                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " ((HttpServletRequest) req).getPathInfo() " + ((HttpServletRequest) req).getPathInfo());
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        ЛОГ = getServletContext();
        // TODO: 17.10.2023 init Async Context
           req.startAsync();
        //TODO ПОТОК ДЛЯ МЕТОДА POST
        try {
            // TODO: 29.11.2023 listrer
            listerAsyncContext.runnigAsyncContext(req.getAsyncContext(),ЛОГ);

            if (sessionSousJboss.isOpen()) {
                // TODO: 01.11.2023 Получаем Сессию
                final   Session  session=     transationCompleteSession.startingSession(ЛОГ,sessionSousJboss);

                transationCompleteSession.startingTransastion(ЛОГ,session);


                // TODO: 01.11.2023 Аунтификайия Имя И Пароль
                Boolean    СтатусаАунтификацииПользователя   =
                        ayntificationDontPasswordAndLogin.successAyntificationUserForServlets( (HttpServletRequest) req.getAsyncContext().getRequest(),
                                (HttpServletResponse)  req.getAsyncContext().getResponse()
                                ,session,ЛОГ);

                if (СтатусаАунтификацииПользователя==true) {
                    //TODO ЗАПУСКАЕМ КОДЕ МЕТОДА POST()


                }
            }



            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + ((HttpServletRequest) req).getPathInfo() +
                    " POOL CURRENT  " + Thread.currentThread().getName()+ " req.isAsyncStarted() " +req.isAsyncStarted());
        } catch (Exception e) {
            // TODO: 02.11.2023 запись ошибку
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ, "ErrorsLogs/ErrorJbossServletScanner.txt");

        }
    }

    public void destroy() {
        try{
          /*  if (sessionSousJboss!=null && sessionSousJboss.isOpen()) {
                sessionSousJboss.close();
            }*/
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletScanner.txt");

        }

    }
}

































