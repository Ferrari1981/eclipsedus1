package businesslogic;

import org.hibernate.Session;
import runtimejboss.BeanGetLoginAndPasswords;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Named ("ayntificationDontPasswordAndLogin")
public class AyntificationDontPasswordAndLogin {
    @Inject
    private BEANCallsBack bEANCallsBack;


    @Inject
    private  SubClassWriterErros subClassWriterErros;
    @Inject
    private  BeanGetLoginAndPasswords beanGetLoginAndPasswords;
    @Inject
    private   TransationCompleteSession transationCompleteSession;

    @Inject
    private AsyncContextComplete asyncContextComplete;

    // TODO: 01.11.2023  метод ответ что не прошел Аунтификацию 
    public void ayntificationDontPasswordAndLogin( @NotNull HttpServletRequest asyrequest, @NotNull HttpServletResponse asyresponse,
                                                   @NotNull ServletContext ЛОГ)
            throws IOException, ServletException {
        try {
            String  СерверРаботаетБезПараметров= "Server Running...... Don't Login and Password" ;
            // TODO: 10.03.2023 Ответ От Сервера
            bEANCallsBack.МетодBackДанныеКлиентуByte(asyresponse, СерверРаботаетБезПараметров.getBytes(StandardCharsets.UTF_8), ЛОГ  );


            // TODO: 29.11.2023 закрывам Async
            asyncContextComplete.endingContextComplete(asyrequest,ЛОГ);


            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    "  Server Running...... Don't Login and Password\" " );

        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletRuntime.txt");

        }
    }


    // TODO: 01.11.2023 проверяем логин и пароль Аунтификация  
    public Boolean successAyntificationUserForServlets(@NotNull  HttpServletRequest asyrequest,
                                                       @NotNull HttpServletResponse asyresponse,
                                                       @NotNull Session session,
                                                       @NotNull ServletContext   ЛОГ)
            throws IOException, ServletException {
        Boolean     СтатусаАунтификацииПользователя=false;
        try {

            // TODO: 01.11.2023  проверка Аунтификации ЛОГИН и Пароль
              СтатусаАунтификацииПользователя = beanGetLoginAndPasswords.МетодGetsLoginAndPassword(ЛОГ, asyrequest, asyrequest.getSession(),session);

                if (СтатусаАунтификацииПользователя == true) { // pass the request along the filter
                    // TODO: 01.11.2023 success logon and password
                    СтатусаАунтификацииПользователя=true;
                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                            " Success    doFilter doFilter doFilter СтатусаАунтификацииПользователя " + СтатусаАунтификацииПользователя);
                } else {
                    // TODO: 11.03.2023 ИМя и Пароль не Правильный
                    ayntificationDontPasswordAndLogin( asyrequest,asyresponse,ЛОГ);
                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                            " Error  doFilter doFilter doFilter СтатусаАунтификацииПользователя " + СтатусаАунтификацииПользователя);
                }
                
                ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    "    СтатусаАунтификацииПользователя "+СтатусаАунтификацииПользователя );

        } catch (Exception e) {
            // TODO: 02.11.2023 ROLLBACK
            // TODO: 17.11.2023 ERROR transaction
            transationCompleteSession.erroringTransastion(ЛОГ,session);

            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletRuntime.txt");

        }
        return  СтатусаАунтификацииПользователя;
    }





}
