package Filters;

import businesslogic.SubClassWriterErros;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedTask;
import javax.enterprise.concurrent.ManagedTaskListener;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


@WebFilter(value={ "/sous.jboss.runtimejboss","/sous.jboss.download","/sous.jboss.scanner","/sous.jboss.tabel"}, filterName="FilterRuntime",asyncSupported = true)
public class FilterRuntime implements Filter {


    private ServletContext ЛОГ;
    @Inject
    private  SubClassWriterErros subClassWriterErros;
    @Inject
    private BusinessLogicFilterRuntime businessLogicFilterRuntime;







    public void init(FilterConfig fConfig) throws ServletException {
        ЛОГ = fConfig.getServletContext();
        ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        try {

            // TODO: 02.11.2023 ЗАпускаем Код Фиильра
            businessLogicFilterRuntime.startFilterRuntime(request,response,chain,ЛОГ);

            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    " Success    doFilter doFilter doFilter IDДевайсаКлиентаRuntime ");
            
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletRuntime.txt");

        }
    }





    public void destroy() {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        System.out.println(	"\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
    }


    // TODO: 28.11.2023 end filter
}
