package dsu1glassfishatomic;


import SessionBeans.BeanGET;
import SessionBeans.BeanPOST;
import businesslogic.AyntificationDontPasswordAndLogin;
import businesslogic.ListerAsyncContext;
import businesslogic.SubClassWriterErros;
import businesslogic.TransationCompleteSession;
import dsu1glassfishatomic.workinterfaces.ProducedCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet( value="/sous.jboss.tabel",asyncSupported = true)
public class DSU1JsonServlet extends HttpServlet {

    @EJB
    private BeanGET СессионыйБинGET;
    @EJB
    private BeanPOST СессионыйБинPOST;

    @Inject
    private   SubClassWriterErros subClassWriterErros;



    private  ServletContext   ЛОГ;

    @Inject
    @ProducedCard
    private SessionFactory sessionSousJboss;

    @Inject
    private AyntificationDontPasswordAndLogin ayntificationDontPasswordAndLogin;

    @Inject
    private TransationCompleteSession transationCompleteSession;

    @Inject
    private  ListerAsyncContext listerAsyncContext;



            DSU1JsonServlet(){
        // TODO: 10.10.2023 ihit
        //sessionSousJboss.openSession();
        System.out.println(" class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());


    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     // super.doGet(req, resp);
       ЛОГ = getServletContext();
        // TODO: 17.10.2023 init async context
        req.startAsync();
        try{
            // TODO: 29.11.2023 listrer
            listerAsyncContext.runnigAsyncContext(req.getAsyncContext(),ЛОГ);

            if (sessionSousJboss.isOpen()) {
                // TODO: 01.11.2023 Получаем Сессию
                final   Session  session=     transationCompleteSession.startingSession(ЛОГ,sessionSousJboss);

                transationCompleteSession.startingTransastion(ЛОГ,session);
                // TODO: 01.11.2023 Аунтификайия Имя И Пароль
                Boolean    СтатусаАунтификацииПользователя   =
                        ayntificationDontPasswordAndLogin
                                .successAyntificationUserForServlets( (HttpServletRequest) req.getAsyncContext().getRequest(),
                                        (HttpServletResponse)  req.getAsyncContext().getResponse(),session,ЛОГ);


                if (СтатусаАунтификацииПользователя==true) {
                    //TODO ЗАПУСКАЕМ КОДЕ МЕТОДА GET()
                    СессионыйБинGET.МетодБинаGET(ЛОГ,(HttpServletRequest) req.getAsyncContext().getRequest(),
                            (HttpServletResponse)  req.getAsyncContext().getResponse(),session);
                }

                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " ((HttpServletRequest) req).getPathInfo() " +((HttpServletRequest) req).getPathInfo()+
                        " POOL CURRENT  "+Thread.currentThread().getName()+ " req.isAsyncStarted() " +req.isAsyncStarted()
                        +"  POOL  THREAD "+Thread.currentThread().getName() + " СтатусаАунтификацииПользователя " +СтатусаАунтификацииПользователя);

            }


            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ((HttpServletRequest) req).getPathInfo() " +((HttpServletRequest) req).getPathInfo()+
                    " POOL CURRENT  "+Thread.currentThread().getName()+ " req.isAsyncStarted() " +req.isAsyncStarted()
                    +"  POOL  THREAD "+Thread.currentThread().getName() );
        } catch (Exception e) {
            // TODO: 02.11.2023 запись ошибку
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }





    }//TODO END doGet

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        ЛОГ = getServletContext();
        //TODO ПОТОК ДЛЯ МЕТОДА POST
          req.startAsync();
        try {
            // TODO: 29.11.2023 listrer
            listerAsyncContext.runnigAsyncContext(req.getAsyncContext(),ЛОГ);

            if (sessionSousJboss.isOpen()) {
                // TODO: 01.11.2023 Получаем Сессию
                final Session session = transationCompleteSession.startingSession(ЛОГ, sessionSousJboss);

                transationCompleteSession.startingTransastion(ЛОГ, session);
                // TODO: 01.11.2023 Аунтификайия Имя И Пароль
                Boolean СтатусаАунтификацииПользователя =
                        ayntificationDontPasswordAndLogin.successAyntificationUserForServlets( (HttpServletRequest) req.getAsyncContext().getRequest(),
                                (HttpServletResponse)  req.getAsyncContext().getResponse(), session, ЛОГ);

                if (СтатусаАунтификацииПользователя == true) {
                    //TODO ЗАПУСКАЕМ КОДЕ МЕТОДА POST()
                    СессионыйБинPOST.МетодБинаPOST(ЛОГ,  (HttpServletRequest) req.getAsyncContext().getRequest(),
                            (HttpServletResponse)  req.getAsyncContext().getResponse(), session);

                }
            }





            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + ((HttpServletRequest) req).getPathInfo() +
                    " POOL CURRENT  " + Thread.currentThread().getName() + " req.isAsyncStarted() " + req.isAsyncStarted());
        } catch (Exception e) {
            // TODO: 02.11.2023 запись ошибку
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ, "ErrorsLogs/ErrorJbossServletDSU1.txt");

        }

    }//todo end doPost

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
                          ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");


      }

    }

}

































