package Filters;

import businesslogic.AyntificationDontPasswordAndLogin;
import businesslogic.SubClassWriterErros;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.Optional;



@Named ("controllerFilterRuntime")
public class BusinessLogicFilterRuntime {

    @Inject
    private   SubClassWriterErros subClassWriterErros;
    @Inject
    private     AyntificationDontPasswordAndLogin ayntificationDontPasswordAndLogin;


  protected     void startFilterRuntime(@NotNull  ServletRequest request,
                                        @NotNull ServletResponse response,
                                        @NotNull FilterChain chain,
                                        @NotNull ServletContext ЛОГ){
        try{

            HttpServletRequest asyrequest = (HttpServletRequest) request;
            HttpServletResponse asyresponse = (HttpServletResponse) response;
            asyrequest.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            asyresponse.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            asyresponse.setBufferSize(2048);/////buffer 163840
            // TODO: 10.03.2023  ТОЛЬКО ID DEVICE
            Object IDДевайсаКлиентаRuntime=
                    Optional.ofNullable(( asyrequest).getHeaders("id_device_androis").nextElement()).orElse("");
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    "  ЛогинОтAndroid    doFilter doFilter doFilter IDДевайсаКлиентаRuntime " +IDДевайсаКлиентаRuntime);
            if (IDДевайсаКлиентаRuntime.toString().length()>5) {
                // TODO: 11.03.2023 ГЛАВНАЯ СТРОЧКА ПЕРЕНАРАВЛЕНИЕ НА СЕВРЕЛТЫ НА ГЛАВНЫЙ КОД
                chain.doFilter(asyrequest, asyresponse);
                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                        " Success    doFilter doFilter doFilter IDДевайсаКлиента " + IDДевайсаКлиентаRuntime);
            }else{
                // TODO: 11.03.2023  нет не имени не пароля
                ayntificationDontPasswordAndLogin.ayntificationDontPasswordAndLogin(asyrequest,asyresponse,ЛОГ);

                // TODO: 11.03.2023  нет не имени не пароля
          /*      RequestDispatcher requestDispatcher = asyrequest.getRequestDispatcher("/indexfiltetuntime.jsp");
                requestDispatcher.forward(asyrequest, asyresponse);
                asyresponse.sendRedirect("/indexfiltetuntime.jsp");*/

            }
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    " Success    doFilter doFilter doFilter IDДевайсаКлиентаRuntime " +IDДевайсаКлиентаRuntime+
                    " IDДевайсаКлиентаRuntime " +IDДевайсаКлиентаRuntime);



        ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
    } catch (Exception e) {
        subClassWriterErros.
                МетодаЗаписиОшибкиВЛог(e,
                        Thread.currentThread().
                                getStackTrace(),
                        ЛОГ,"ErrorsLogs/ErrorJbossServletRuntime.txt");

    }
    }
}
