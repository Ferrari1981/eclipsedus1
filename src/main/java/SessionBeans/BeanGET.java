package SessionBeans;

import businesslogic.*;
import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.jboss.ejb3.annotation.TransactionTimeout;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Session Bean implementation class BeanGET
 */
@Stateful(mappedName = "beanGET")
@LocalBean
@TransactionTimeout(value = 1, unit = TimeUnit.HOURS)
@Asynchronous
public class BeanGET  {
    /**
     * Default constructor.
     */
    @Inject
    private  GeneratorDataFromAndroid generatorDataFromAndroid;
    @Inject
    private  BEANCallsBack bEANCallsBack;
    @Inject
    private SubClassWriterErros subClassWriterErros;
    @Inject
    private TransationCompleteSession transationCompleteSession;
    @Inject
    private AsyncContextComplete asyncContextComplete;


    /*@Inject
    Producers producers;*/

    public void   МетодБинаGET(@NotNull ServletContext ЛОГ,
                              @NotNull HttpServletRequest request,
                              @NotNull  HttpServletResponse response,
                               @NotNull Session session) throws InterruptedException, ExecutionException {
        try {
            // TODO: 10.03.2023  данные от GET метода
            byte[]    БуферРезультатGETByte=null;

          //  producers.staringSendFileProducers(  response );

            String    JobForServer = Optional.ofNullable(request.getParameter("JobForServer")).map(String::new).orElse("");

            if (!JobForServer.isEmpty() && request !=null  &&   ЛОГ !=null  &&  response  !=null  && session !=null ) {

                if(JobForServer.trim().equalsIgnoreCase("Хотим Получить  JSON")){

               БуферРезультатGETByte=		generatorDataFromAndroid.ГлавныйМетод_МетодаGETByte(request,  ЛОГ,response,session);


              // TODO: 30.10.2023  НЕ сам JSON а сопуствубщие данные для радоты обмена
          }else {
              БуферРезультатGETByte=		generatorDataFromAndroid.ГлавныйМетод_МетодаGETService(request,  ЛОГ,session );

          }


                // TODO: 17.11.2023 commit transaction
                transationCompleteSession.commitingTransastion(ЛОГ,session);

                ///TODO ОТВЕТ КЛИЕНТУ ОТ СЕРВЕРА
                bEANCallsBack.МетодBackДанныеКлиентуByte(response, БуферРезультатGETByte, ЛОГ  );

                // TODO: 29.11.2023 закрывам Async
                 asyncContextComplete.endingContextComplete(request,ЛОГ);

                ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                        +"\n"+
                        " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                        + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()  +  "БуферРезультатGETByte " +БуферРезультатGETByte+
                        " JobForServer " +JobForServer);

            }

            ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                    +"\n"+
                    " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() );
        } catch (Exception e) {

            // TODO: 17.11.2023 ERROR transaction
            transationCompleteSession.erroringTransastion(ЛОГ,session);

            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }

}
// TODO: 01.12.2023  metods after and begore

    @PostConstruct
    private  void  bootBean(){
        System.out.println(Thread.currentThread().getStackTrace()[2].getClassName()
                +"\n"+
                " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() );
    }

    @PreDestroy
    private  void  exitBean(){
        System.out.println(Thread.currentThread().getStackTrace()[2].getClassName()
                +"\n"+
                " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() );
    }
}
