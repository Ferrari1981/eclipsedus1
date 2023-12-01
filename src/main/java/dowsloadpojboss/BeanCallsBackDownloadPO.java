package dowsloadpojboss;





import businesslogic.AsyncContextComplete;
import businesslogic.SubClassWriterErros;
import businesslogic.CallBaksSend.SuccessSendAndroidBinatyStream;
import businesslogic.TransationCompleteSession;
import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.jboss.ejb3.annotation.TransactionTimeout;

import javax.ejb.*;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPOutputStream;

/**
 * Session Bean implementation class BeanCallsBackDownloadPO
 */
@Singleton(mappedName = "beanCallsBackDownloadPO")
@LocalBean
@TransactionTimeout(value = 1, unit = TimeUnit.HOURS)
@Lock(LockType.READ)
public class BeanCallsBackDownloadPO {

   @Inject
   private   SubClassWriterErros subClassWriterErros;
    @EJB
    private BeanCallsBackDownloadPO beanCallsBackDownloadPO;
    @Inject
    private  TransationCompleteSession transationCompleteSession;

    @Inject
    private AsyncContextComplete asyncContextComplete;

    @Inject
    private  SuccessSendAndroidBinatyStream staringSendFileAndJsonAndroidOrBinatyStream;

    public BeanCallsBackDownloadPO() {
        // TODO Auto-generated constructor stub
        try {
            System.out.print("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.print("\n"+" Error.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+e.getMessage().toString());
        }
    }




    @Asynchronous
    public void МетодЗапускаОбновлениеПО(@NotNull ServletContext ЛОГ,
                                         @NotNull HttpServletRequest request,
                                         @NotNull HttpServletResponse response,
                                         @NotNull Session session) throws InterruptedException, ExecutionException {
        try {
            Object ЗаданиеДляСервераЗагрузкиНовогоПо = request.getHeaders("task_downlonupdatepo").nextElement();
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "  ЛогинОтAndroid    ЗаданиеДляСервераЗагрузкиНовогоПо " + ЗаданиеДляСервераЗагрузкиНовогоПо
                    + " req.isAsyncStarted() " + request.isAsyncStarted()+"  POOL  THREAD "+Thread.currentThread().getName());
            switch (ЗаданиеДляСервераЗагрузкиНовогоПо.toString()) {
                case "FileJsonUpdatePO":
                    // TODO: 13.03.2023  запуск Кода пополучениею File JSON Для Обнолвенеи ПО
                    response.setContentType("application/json");
                    beanCallsBackDownloadPO.МетодЗапускаДляФайлаJSON(ЛОГ, request, response,session);
                    break;
                case "FileAPKUpdatePO":
                    // TODO: 13.03.2023  запуск Кода пополучениею File .APK Для Обнолвенеи ПО
                    response.setContentType("application/octet-stream");
                    beanCallsBackDownloadPO.МетодЗапускаДляФайлаAPK(ЛОГ, request, response,session);
                    break;
            }


            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ((HttpServletRequest) req).getPathInfo() " + ((HttpServletRequest) request).getPathInfo());
        } catch (Exception e) {
            // TODO: 02.11.2023 ROLLBACK
            transationCompleteSession.erroringTransastion(ЛОГ,session);
// TODO: 17.11.2023 ERROR joiner
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ, "ErrorsLogs/ErrorJbossServletUpdatePO.txt");

        }
    }



    public void МетодЗапускаДляФайлаJSON(@NotNull ServletContext ЛОГ,
                                                 @NotNull HttpServletRequest request,
                                                 @NotNull HttpServletResponse response,
                                             @NotNull Session session) throws InterruptedException, ExecutionException {
        try {
            // TODO: 10.03.2023  данные от JSON ANALIZE
          File ПолучаемJSONФайл= 	 МетодДляJSONФайла(ЛОГ,request,response);


            // TODO: 17.11.2023 commit transaction
            transationCompleteSession.commitingTransastion(ЛОГ,session);

            // TODO: 01.12.2023 Send File Cliknt

            МетодBackДанныеКлиентуНовоеПО(response ,ПолучаемJSONФайл,ЛОГ   );
            // TODO: 29.11.2023

            // TODO: 29.11.2023 закрывам Async
            asyncContextComplete.endingContextComplete(request,ЛОГ);

            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  ПолучаемJSONФайл " +ПолучаемJSONФайл);
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletUpdatePO.txt");
        }
    }

    public void  МетодЗапускаДляФайлаAPK(@NotNull ServletContext ЛОГ,
                                         @NotNull HttpServletRequest request,
                                         @NotNull HttpServletResponse response,
                                         @NotNull Session session) throws InterruptedException, ExecutionException {
        try {
            // TODO: 10.03.2023  данные от .APK Download
           File ПолучаемAPKФайл= 	 МетодДляAPKФайла(ЛОГ,request,response);


            // TODO: 17.11.2023 commit transaction
            transationCompleteSession.commitingTransastion(ЛОГ,session);

            // TODO: 29.11.2023 ответ клиенту
            МетодBackДанныеКлиентуНовоеПО(response ,ПолучаемAPKФайл,ЛОГ );


            // TODO: 29.11.2023 закрывам Async
            asyncContextComplete.endingContextComplete(request,ЛОГ);


            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  ПолучаемAPKФайл " +ПолучаемAPKФайл);
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletUpdatePO.txt");
        }
    }


    public File МетодДляJSONФайла(@NotNull ServletContext ЛОГ, @NotNull HttpServletRequest request,  @NotNull HttpServletResponse response){
        File fileJson = null;
        try{
            //String filepath ="C:\\Users\\moraru_pi\\AndroidStudioProjectsSERVER\\sous.jboss.idea\\src\\main\\webapp\\update_android_dsu1\\output-metadata.json";
            String filepath ="update_android_dsu1/output-metadata.json";
           // String filepath ="output-metadata.json";
            // TODO: 13.03.2023 ГЛАВНЫЙ КОД РАБОТА С ФАЙЛАМИ
            Path path = Paths.get(filepath);
            fileJson = Paths.get(filepath).toFile();
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  fileJson " +fileJson);
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletUpdatePO.txt");
        }
        return fileJson;
    }

    private File МетодДляAPKФайла(@NotNull ServletContext ЛОГ,
                                          @NotNull HttpServletRequest request,@NotNull HttpServletResponse response){
        File fileApk = null;
        try {
           // String filepath ="C:\\Users\\moraru_pi\\AndroidStudioProjectsSERVER\\sous.jboss.idea\\src\\main\\webapp\\update_android_dsu1\\app-release.apk";
           // String filepath ="src\\main\\webapp\\update_android_dsu1\\app-release.apk";
            String filepath ="update_android_dsu1/app-release.apk";
            // TODO: 13.03.2023 ГЛАВНЫЙ КОД РАБОТА С ФАЙЛАМИ
            Path path = Paths.get(filepath);
            fileApk = Paths.get(filepath).toFile();
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  fileApk " +fileApk);
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletUpdatePO.txt");
        }
        return fileApk;
    }




































    // TODO МетодКласса отправки данных андройду
    public void МетодBackДанныеКлиентуНовоеПО(@NotNull HttpServletResponse response,
                                              @NotNull File ОтправкаФайлаJsonAPK,
                                              @NotNull ServletContext ЛОГ) throws IOException, ServletException {


        try{
            // TODO: 01.12.2023 Запускам класс Отправки Данных НА пользователя File
            staringSendFileAndJsonAndroidOrBinatyStream.successSendAndroidFileStream( response, ОтправкаФайлаJsonAPK, ЛОГ);

            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        } catch (Exception e) {
        subClassWriterErros.
                МетодаЗаписиОшибкиВЛог(e,
                        Thread.currentThread().
                                getStackTrace(),
                        ЛОГ,"ErrorsLogs/ErrorJbossServletUpdatePO.txt");
    }
}


    }




