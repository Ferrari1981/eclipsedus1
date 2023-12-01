package businesslogic;

import businesslogic.genertoringjson.GeneratorJsonSequenceWriter;
import businesslogic.genertoringjson.GeneratorJsonWriteValue;
import com.sun.istack.NotNull;
import dsu1glassfishatomic.workinterfaces.ProducedGeneratorJson2;
import dsu1glassfishatomic.workinterfaces.ProducedGeneratorJson3;
import jakarta.persistence.LockModeType;
import model.*;
import org.hibernate.*;
import org.hibernate.criterion.Order;

import javax.ejb.Lock;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Named("generatorDataFromAndroid")
public class GeneratorDataFromAndroid {// extends WITH

    private ServletContext ЛОГ;
    //private	Connection conn; ////// общий коннект для всего севлтера
    private Statement stmt;
    @SuppressWarnings("unused")
    private String ПубличноеHeaderИмя = null;
    @SuppressWarnings("unused")
    private String ОшибкаВМетодеdoPOST = new String();
    private int КоличествоСтрокКоторыеМыОтправимНаКлиент;
    private String ИмяПолученныйИзSQlServerПосик = null;
    @SuppressWarnings("unused")
    private String ПарольПолученныйОтКлиента = null;
    private String ЛогинПолученныйОтКлиента = null;

    private Integer ПараметрТекущийПользователь = 0;  //TODO ТЕКУЩИЙ ПОЛЬЗОВАТЕЛЬ
    @SuppressWarnings("unused")
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StoredProcedureQuery queryprocedure = null;

    @Inject
    SubClassWriterErros subClassWriterErros;

    @Inject
    @ProducedGeneratorJson2
    GeneratorJsonWriteValue generatorJsonWriteValue;

    @Inject
    @ProducedGeneratorJson3
    GeneratorJsonSequenceWriter generatorJsonSequenceWriter;


    @Inject
    BEANCallsBack beanCallsBack;

    Session session;


    public GeneratorDataFromAndroid() {

        System.out.println("Конструктор  GeneratorDataFromAndroid");

    }

    @SuppressWarnings({ "unused", "deprecation", "rawtypes", "unchecked" })
    public byte[] ГлавныйМетод_МетодаGETService(@NotNull HttpServletRequest request,
                                                @NotNull ServletContext ЛОГ ,@NotNull Session session) throws SecurityException, SQLException {
        // TODO Auto-generated method stub
        System.out.println("Конструктор  ЗАПУСК МЕТОДА ИЗ GET ()  ГлавныйМетод_МетодаGET()");
        byte[] БуферCallsBackДляAndroid=null;
        try   {
            this.ЛОГ = ЛОГ;
            // TODO
            this.request = request;
            this.session=session;
            // TODO получаем session
            ЛОГ.log("ЗАПУСКАЕТСЯ....... ГЛАВНЫЙ МЕТОД GET() СЕРВЛЕТА " + new Date() + "\n" + ЛОГ.getServerInfo()
                    + "  request " + request + " response " + response + " ЛОГ" + ЛОГ);
            // TODO ГАЛВНЫЙ МЕТОД GET НАЧИНАЕТ РАБОТАТЬ
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "  session  " +  session
                    + " ЛОГИН "+ЛОГ.getAttribute("ЛогинПолученныйОтКлиента")+
                    " ID ТЕЛЕФОНА "+  ЛОГ.getAttribute("АдуДевайсяКлиента"));
            /// TODO ПАРАМЕНТ #1

            Object IdUserПред =   Optional.ofNullable(ЛОГ.getAttribute("IdUser") ).orElse("0");
            Integer IdUser = Optional.ofNullable( IdUserПред.toString()).stream() .mapToInt(Integer::new).findFirst().orElse(0);
            /// TODO ПАРАМЕНТ #2
            String     NameTable = Optional.ofNullable(request.getParameter("NameTable")).map(String::new).orElse("");
            /// TODO ПАРАМЕНТ #3
            String      JobForServer = Optional.ofNullable(request.getParameter("JobForServer")).map(String::new).orElse("");
            /// TODO ПАРАМЕНТ #4
            Long VersionData = Optional.ofNullable(request.getParameter("VersionData")).map(Long::new).orElse(0l);


            System.out.println("  IdUser " + IdUser);
            org.hibernate.Query queryДляHiberite = null;
            org.hibernate.Criteria criteriaquery=null;
            List<?> ЛистДанныеОтHibenide  = new ArrayList<>();

            if (IdUser>0) {
                // TODO: 10.03.2023 получение сессиии HIREBIANTE
                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + "    session.getTransaction().getStatus() " +   session.getTransaction().getStatus());

                // TODO: 17.03.2023 ЗАПУСКАЕТ ТРАНЗАКЦИЮ BEGIN
                ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+ " session " +session  + " session.getTransaction() " + session.getTransaction());
                /// TODO КОНЕЦ  НОВЫЕ ПАРАМЕТРЫ HIREBIANTE
            }

            switch (JobForServer) {
                // TODO ЗАДАНИЯ ДЛЯ СЕРВЕРА НЕТУ
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #1
                case "Хотим Получить Версию Данных Сервера":
                    ЛистДанныеОтHibenide = МетодДляКлиентаMODIFITATION_Server();
                    ЛОГ.log("Хотим Получить Версию Данных Сервера" + new Date() + " ПараметрФильтрЗадааниеДляСервлета "
                            + ЛистДанныеОтHibenide + "  ЛистДанныеОтHibenide ");
                    break;
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #3
                case "Хотим Получить ID для Генерации  UUID":
                    ЛистДанныеОтHibenide = Метод_МетодаGETОтпалавляемПубличныйIDПользователюАндройду();
                    ЛОГ.log(" ЛистДанныеОтHibenide "
                            + ЛистДанныеОтHibenide);
                    break;
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #4
                case "Хотим Получить Статус Блокировки Пользователя по ID":
                    // TODO ОПРЕДЕЛЯЕМ СТАТУС ПОЛЬЗОВАТЕЛЯ
                    ЛистДанныеОтHibenide = Метод_МетодаСтатусЗаблорированогоКлиента( IdUser, session);
                    ЛОГ.log(" Отправили  Хотим Получить Статус Блокировки Пользователя по ID "
                            + JobForServer + " ЛистДанныеОтHibenide " + ЛистДанныеОтHibenide.size() + " IDПолученныйИзSQlServerПосик "+IdUser);
                    break;
                // TODO ЗАДАНИЯ ДЛЯ СЕРВЕРА НЕТУ
                default:
                    ЛОГ.log("\n"+"  default:  Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                            "Метод_РеальнаяСтатусSqlServer  ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.size());
                    break;
            }




            //TODO ГЕНЕРАЦИЯ JSON ПО НОВОМУ  byte
            БуферCallsBackДляAndroid=      generatorJsonFinal(  ЛистДанныеОтHibenide,NameTable );


            //// TODO ЗАКРЫЫВАЕМ КУРСОРЫ ПОСЛЕ ГЕНЕРАЦИИ JSON ДЛЯ КЛИЕНТА
            // TODO
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " БуферCallsBackДляAndroid " + БуферCallsBackДляAndroid.toString() +
                    "  session  " +  session
                    + " ЛОГИН "+ЛОГ.getAttribute("ЛогинПолученныйОтКлиента")+
                    " ID ТЕЛЕФОНА "+  ЛОГ.getAttribute("АдуДевайсяКлиента"));

            //// TODO ЗАКРЫЫВАЕМ КУРСОРЫ ПОСЛЕ ГЕНЕРАЦИИ JSON ДЛЯ КЛИЕНТА
            // TODO
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " БуферCallsBackДляAndroid " + БуферCallsBackДляAndroid.toString() +
                    "  session  " +  session
                    + " ЛОГИН "+ЛОГ.getAttribute("ЛогинПолученныйОтКлиента")+
                    " ID ТЕЛЕФОНА "+  ЛОГ.getAttribute("АдуДевайсяКлиента"));
        } catch (Exception e) {
            ЛОГ.log("\n" + " ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return БуферCallsBackДляAndroid; // TODO return new
        // AsyncResult<StringBuffer>(БуферCallsBackДляAndroid);
    }


























    // TODO: 06.09.2023  ТОЛЬКО JSON JAJKSON
    @SuppressWarnings({ "unused", "deprecation", "rawtypes", "unchecked" })
    public byte[] ГлавныйМетод_МетодаGETByte(@NotNull HttpServletRequest request,
                                             @NotNull ServletContext ЛОГ,
                                             @NotNull  HttpServletResponse response ,
                                             @NotNull Session session) throws SecurityException, SQLException {
        // TODO Auto-generated method stub
        System.out.println("Конструктор  ЗАПУСК МЕТОДА ИЗ GET ()  ГлавныйМетод_МетодаGET()");
        byte[] БуферCallsBackДляAndroid=null;
        try   {
            this.ЛОГ = ЛОГ;
            // TODO
            this.request = request;
            /// TODO
            this.response = response;

            this.session=session;
            // TODO получаем session
            ЛОГ.log("ЗАПУСКАЕТСЯ....... ГЛАВНЫЙ МЕТОД GET() СЕРВЛЕТА " + new Date() + "\n" + ЛОГ.getServerInfo()
                    + "  request " + request + " response " + response + " ЛОГ" + ЛОГ);
            // TODO ГАЛВНЫЙ МЕТОД GET НАЧИНАЕТ РАБОТАТЬ
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "  session  " +  session
                    + " ЛОГИН "+ЛОГ.getAttribute("ЛогинПолученныйОтКлиента")+
                    " ID ТЕЛЕФОНА "+  ЛОГ.getAttribute("АдуДевайсяКлиента"));
            /// TODO ПАРАМЕНТ #1

            Object IdUserПред =   Optional.ofNullable(ЛОГ.getAttribute("IdUser") ).orElse("0");
            Integer IdUser = Optional.ofNullable( IdUserПред.toString()).stream() .mapToInt(Integer::new).findFirst().orElse(0);
            /// TODO ПАРАМЕНТ #2
            String     NameTable = Optional.ofNullable(request.getParameter("NameTable")).map(String::new).orElse("");
            /// TODO ПАРАМЕНТ #3
            String      JobForServer = Optional.ofNullable(request.getParameter("JobForServer")).map(String::new).orElse("");
            /// TODO ПАРАМЕНТ #4
            Long VersionData = Optional.ofNullable(request.getParameter("VersionData")).map(Long::new).orElse(0l);


            System.out.println("  IdUser " + IdUser);
            org.hibernate.Query queryДляHiberite = null;
            org.hibernate.Criteria criteriaquery=null;
            List<?> ЛистДанныеОтHibenide  = new ArrayList<>();

            if (IdUser>0) {
                // TODO: 10.03.2023 получение сессиии HIREBIANTE



                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + "    session.getTransaction().getStatus() " +   session.getTransaction().getStatus());

                // TODO: 17.03.2023 ЗАПУСКАЕТ ТРАНЗАКЦИЮ BEGIN
                /// TODO КОНЕЦ  НОВЫЕ ПАРАМЕТРЫ HIREBIANTE
            }

            switch (JobForServer) {
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #2
                case "Хотим Получить  JSON":
                    ЛОГ.log("Хотим Получить  JSON" + new Date() + " JobForServer "
                            + JobForServer+"  VersionData" + VersionData
                            + " NameTable " + NameTable);
                    LockOptions lockOptionsREAD=new LockOptions();
                    lockOptionsREAD.setLockMode(LockMode. READ);
                    lockOptionsREAD.setScope(true);

                    ////////////// ГЕНЕРАЦИЯ JSON ДЛЯ ВСЕХ  ТАБЛИЦ
                    // TODO ГЛАВНЫЙ РАСПРЕДЕЛИТЕЛЬ КАКАЯ ТЕКУЩАЯ ТАБЛИЦА ОБРАБАТЫВАЕМСЯ
                    switch (NameTable.trim()) {
                        case "organization":
                            // TODO
                            queryДляHiberite  =  session.createQuery(
                                    " SELECT DISTINCT o FROM  Organization o WHERE o.currentTable > :id  ORDER BY o.currentTable  ").setLockOptions(lockOptionsREAD);
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData ));//
                            ЛистДанныеОтHibenide =( List<model.Organization>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite+ " NameTable " +NameTable);//gson Gson
                            break;
                        case "depatment":
                            // TODO
                            queryДляHiberite =  session.createQuery(
                                    "SELECT  DISTINCT  d FROM  Depatment d   WHERE d.currentTable > :id  ORDER BY d.currentTable  ").setLockOptions(lockOptionsREAD);

                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData ));
                            ЛистДанныеОтHibenide =( List<model.Depatment>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "fio":
                            // TODO
                            queryДляHiberite =  session.createQuery(
                                    "SELECT   DISTINCT f FROM Fio f   WHERE f.currentTable > :id  ORDER BY f.currentTable    ").setLockOptions(lockOptionsREAD);
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData ));//
                            ЛистДанныеОтHibenide =( List<model.Fio>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "region":
                            // TODO
                            queryДляHiberite =  session.createQuery(
                                    "SELECT  DISTINCT  r  FROM Region r   WHERE r.currentTable > :id ORDER BY r.currentTable  ").setLockOptions(lockOptionsREAD);
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData ));//
                            ЛистДанныеОтHibenide =( List<model.Region>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "cfo":
                            // TODO
                            queryДляHiberite =  session.createQuery(
                                    "SELECT  DISTINCT c FROM Cfo  c  WHERE c.currentTable > :id  AND c.closed=:closed  ORDER BY c.currentTable  ").setLockOptions(lockOptionsREAD);
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData ));//
                            queryДляHiberite.setParameter("closed",   new Boolean(false));//
                            ЛистДанныеОтHibenide =( List<model.Cfo>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "settings_tabels":
                            // TODO
                            queryДляHiberite =  session.createQuery(
                                    "SELECT  DISTINCT  st FROM Settingtab  st  WHERE st.currentTable > :id  AND  st.userUpdate=:user_update ORDER BY st.currentTable  ").setLockOptions(lockOptionsREAD);
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData ));//
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Settingtab>) queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "notifications":
                            // TODO
                            queryДляHiberite =  session.createQuery(
                                    " SELECT  DISTINCT notif FROM Notification  notif  WHERE notif.currentTable > :id "
                                            + " AND   notif.userUpdate=:user_update   "
                                            + "  OR notif.currentTable > :id  AND     notif.idUser=:id_user  ORDER BY notif.currentTable  ").setLockOptions(lockOptionsREAD);
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
                            queryДляHiberite.setParameter("id_user",IdUser);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Notification>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        /// TODO
                        case "data_notification":
                            // TODO
                            queryДляHiberite =  session.createQuery(
                                    " SELECT  DISTINCT da  FROM  DataNotification da WHERE"
                                            + "  da. currentTable > :id "
                                            + "  AND da.uuidNotifications "
                                            + " IN (SELECT     no.uuid FROM" +
                                            "    Notification no  WHERE   no.userUpdate=:user_update   OR  no .idUser=:id_user )  ORDER BY  da.currentTable  ").setLockOptions(lockOptionsREAD);
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
                            queryДляHiberite.setParameter("id_user",IdUser);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.DataNotification>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "templates":
                            // TODO
                            queryДляHiberite =  session.createQuery(
                                    " SELECT  DISTINCT te FROM Template  te WHERE te.currentTable > :id  AND te.userUpdate=:user_update ORDER BY te.currentTable   ").setLockOptions(lockOptionsREAD);
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Template>) queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ. log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+
                                    " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "fio_template":
                            // TODO
                            queryДляHiberite =  session.createQuery(
                                    " SELECT  DISTINCT fiot FROM FioTemplate  fiot  WHERE fiot.currentTable > :id   AND fiot.userUpdate=:user_update  ORDER BY fiot.currentTable   ").setLockOptions(lockOptionsREAD);
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.FioTemplate>) queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ. log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+
                                    " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;

                        case "chat_users":
                            // TODO
                            queryДляHiberite =  session.createQuery(
                                    " SELECT  DISTINCT  ca FROM ChatUser ca  WHERE ca .currentTable > :id  ORDER BY ca.currentTable  ").setLockOptions(lockOptionsREAD);
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.ChatUser>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "chats":
                            // TODO
                            queryДляHiberite =  session.createQuery(
                                    " SELECT  DISTINCT  cat FROM Chat  cat WHERE cat .currentTable > :id "
                                            + " AND   cat .userUpdate=:user_update"
                                            + " OR "
                                            + " cat .currentTable > :id AND   cat .idUser=:id_user   ORDER BY cat.currentTable    ").setLockOptions(lockOptionsREAD);
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
                            queryДляHiberite.setParameter("id_user",IdUser);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Chat>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "data_chat":
                            // TODO
                            queryДляHiberite =  session.createQuery( " SELECT DISTINCT   da FROM  DataChat da WHERE  da. currentTable > :id "
                                    + "							  AND da.chatUuid "
                                    + "							 IN (SELECT    ch.uuid FROM    Chat  ch"
                                    + "  WHERE  ch.userUpdate=:user_update  OR ch.idUser=:id_user )   ORDER BY da.currentTable    ").setLockOptions(lockOptionsREAD);
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
                            queryДляHiberite.setParameter("id_user",IdUser);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.DataChat>)  queryДляHiberite.setFirstResult(0).setMaxResults(1) .setCacheable(true).getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "tabel":
                            // TODO
                            queryДляHiberite =  session.createQuery(
                                    "SELECT  DISTINCT  tab FROM Tabel tab  WHERE tab .currentTable > :id  AND tab.userUpdate=:user_update " +
                                            " AND tab.statusSend!=:statusSend  ORDER BY tab.currentTable   ").setLockOptions(lockOptionsREAD);
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
                            queryДляHiberite.setParameter("statusSend","Удаленная");//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Tabel>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "data_tabels":
                            // TODO
                            queryДляHiberite =  session.createQuery(
                                    "SELECT DISTINCT  dat FROM DataTabel dat WHERE dat .currentTable > :id  AND dat.userUpdate=:user_update" +
                                            "  AND dat.statusSend!=:statusSend   ORDER BY dat.currentTable   ").setLockOptions(lockOptionsREAD);
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
                            queryДляHiberite.setParameter("statusSend","Удаленная");//8641 8625
                            ЛистДанныеОтHibenide =( List<model.DataTabel>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "view_onesignal":
                            // TODO
                            queryДляHiberite =  session.createQuery(
                                    " SELECT   DISTINCT  viewone FROM ViewOnesignal viewone WHERE viewone .currentTable > :id  ORDER BY viewone.currentTable  ").setLockOptions(lockOptionsREAD);

                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.ViewOnesignal>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "nomen_vesov":
                            // TODO
                            queryДляHiberite =  session.createQuery(
                                    " SELECT   DISTINCT nome FROM NomenVesov nome WHERE nome .currentTable > :id   ORDER BY nome.currentTable  ").setLockOptions(lockOptionsREAD);
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.NomenVesov>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "type_materials":
                            // TODO
                            queryДляHiberite =  session.createQuery(
                                    " SELECT  DISTINCT typem FROM TypeMaterial typem  WHERE typem .currentTable > :id  ORDER BY typem.currentTable   " ).setLockOptions(lockOptionsREAD);

                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.TypeMaterial>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "get_materials_data":
                            // TODO
                            queryДляHiberite =  session.createQuery(
                                    " SELECT  DISTINCT  getmat FROM  GetMaterialsData  getmat  WHERE getmat .currentTable > :id  AND getmat.userUpdate=:user_update  " +
                                            " AND getmat.statusSend!=:statusSend  ORDER BY getmat.currentTable   ").setLockOptions(lockOptionsREAD);

                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
                            queryДляHiberite.setParameter("statusSend","Удаленная");//8641 8625
                            ЛистДанныеОтHibenide =( List<model.GetMaterialsData>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "company":
                            // TODO
                            queryДляHiberite =  session.createQuery(
                                    " SELECT  DISTINCT  comp FROM  Company  comp  WHERE comp .currentTable > :id  ORDER BY comp.currentTable  ").setLockOptions(lockOptionsREAD);

                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Company>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "track":
                            // TODO
                            queryДляHiberite =  session.createQuery(
                                    " SELECT   DISTINCT  tr FROM  Track tr  WHERE tr .currentTable > :id   ORDER BY tr.currentTable  ").setLockOptions(lockOptionsREAD);

                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Track>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;

                        case "prof":
                            // TODO
                            queryДляHiberite =  session.createQuery(
                                    " SELECT   DISTINCT  pr FROM Prof pr  WHERE pr .currentTable > :id   ORDER BY pr.currentTable  ").setLockOptions(lockOptionsREAD);

                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Prof>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;

                        case "order_tc":
                            // TODO
                            criteriaquery =  session.createCriteria(OrderTc.class);
                            criteriaquery .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                            criteriaquery.add(org.hibernate.criterion.Restrictions.gt("currentTable", new BigDecimal(VersionData)));
                            criteriaquery.add(org.hibernate.criterion.Restrictions.eq("userUpdate", IdUser));
                            criteriaquery.add(org.hibernate.criterion.Restrictions.ne("status", 5));
                            criteriaquery.addOrder(Order.asc("currentTable"));
                            criteriaquery.setLockMode(LockMode. READ);
                            ЛистДанныеОтHibenide = ( List<model.OrderTc>)   criteriaquery.setFirstResult(0).setMaxResults(1000).setCacheable(true).list();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;

                        case "vid_tc":
                            // TODO
                            criteriaquery=  session.createCriteria(VidTc.class);
                            criteriaquery .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                            criteriaquery.add(org.hibernate.criterion.Restrictions.gt("currentTable", new BigDecimal(VersionData)));
                            criteriaquery.addOrder(Order.asc("currentTable"));
                            criteriaquery.setLockMode(LockMode. READ);
                            ЛистДанныеОтHibenide =  ( List<model.VidTc>)     criteriaquery.setFirstResult(0).setMaxResults(1000).setCacheable(true).list();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;

                        case "materials_databinary":
                            // TODO  byte
                            criteriaquery =  session.createCriteria(MateriBinary.class);
                            criteriaquery .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); //criteriaquery.setProjection(Projections.distinct(Projections.property("id")));
                            criteriaquery.add(org.hibernate.criterion.Restrictions.gt("currentTable", new BigDecimal(VersionData)));
                            criteriaquery.addOrder(Order.asc("currentTable"));
                            criteriaquery.setLockMode(LockMode. READ);
                            criteriaquery.add(org.hibernate.criterion.Restrictions.eq("userUpdate", IdUser));
                            ЛистДанныеОтHibenide = ( List<MateriBinary>)   criteriaquery.setFirstResult(0).setMaxResults(1).setCacheable(true).list();
                           // ЛистДанныеОтHibenide = ( List<MateriBinary>)   criteriaquery .setCacheable(true).list();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                    /*        // TODO: 14.07.2023 TEST code
                            DecodeByteArray_Image decodeByteArray_image=new DecodeByteArray_Image();
                            decodeByteArray_image.методDecodeByteImage(ЛОГ,ЛистДанныеОтHibenide);*/

                            break;


                    }//TODO КОНЕЦ РАСПРЕДЕНИЕ ТАБЛИЦ 	switch (NameTable.trim()) {
                    ЛОГ.log(" КоличествоСтрокКоторыеМыОтправимНаКлиент  " + КоличествоСтрокКоторыеМыОтправимНаКлиент+
                            " NameTable " +NameTable);
                default:
                    ЛОГ.log("\n"+"  default:  Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                            "Метод_РеальнаяСтатусSqlServer  ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.size());
                    break;
            }




            //TODO ГЕНЕРАЦИЯ JSON ПО НОВОМУ  byte
            БуферCallsBackДляAndroid=    generatorJsonFinal(  ЛистДанныеОтHibenide,NameTable );


            //// TODO ЗАКРЫЫВАЕМ КУРСОРЫ ПОСЛЕ ГЕНЕРАЦИИ JSON ДЛЯ КЛИЕНТА
            // TODO
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " БуферCallsBackДляAndroid " + БуферCallsBackДляAndroid.toString() +
                    "  session  " +  session
                    + " ЛОГИН "+ЛОГ.getAttribute("ЛогинПолученныйОтКлиента")+
                    " ID ТЕЛЕФОНА "+  ЛОГ.getAttribute("АдуДевайсяКлиента"));
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return БуферCallsBackДляAndroid; // TODO return new
        // AsyncResult<StringBuffer>(БуферCallsBackДляAndroid);
    }


    // TODO: 30.10.2023 метод изходя из теукущей таблицыв выбираем размер данных для генерации Jakson
    byte[] generatorJsonFinal(@NotNull  List<?> ЛистДанныеОтHibenide
            , @NotNull String NameTable) {
        byte[] БуферCallsBackДляAndroid=null;
        try{
            //TODO ГЕНЕРАЦИЯ JSON ПО НОВОМУ  byte
            if (ЛистДанныеОтHibenide!=null && ЛистДанныеОтHibenide.size()>0) {
               // БуферCallsBackДляAndroid=      responseToAndroid.
         ///     responseToAndroid.МетодГенерацияJSONJacksonByte(ЛистДанныеОтHibenide,ЛОГ);
               // БуферCallsBackДляAndroid=        generatorJsonWriteValue.getGeneratorJson(ЛистДанныеОтHibenide ,ЛОГ);
               БуферCallsBackДляAndroid=        generatorJsonSequenceWriter.getGeneratorJson(ЛистДанныеОтHibenide ,ЛОГ);
                // TODO: 30.10.2023 Когда нет данных
            }else {
                БуферCallsBackДляAndroid=new byte[0];
            }

            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " БуферCallsBackДляAndroid " +БуферCallsBackДляAndroid);
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return  БуферCallsBackДляAndroid;
    }





















    // todo ЕЩЕ ОДИН КОД ПЕРЕНЕСЛИВ МЕТОД GET()


    protected     List<model.UsersEntitySuccess>  Метод_МетодаGETОтпалавляемПубличныйIDПользователюАндройду() throws IOException {
        List<model.UsersEntitySuccess> ЛистДанныеОтHibenide  = new ArrayList<>();
        try {
            Object IdUserПред =   Optional.ofNullable(ЛОГ.getAttribute("IdUser") ).orElse("0");
            Integer IdUser = Optional.ofNullable( IdUserПред.toString()).stream() .mapToInt(Integer::new).findFirst().orElse(0);

            LockOptions lockOptionsREAD=new LockOptions();
            lockOptionsREAD.setLockMode(LockMode. READ);
            lockOptionsREAD.setScope(true);


            ЛистДанныеОтHibenide =( List<model.UsersEntitySuccess>)   session.createQuery("SELECT   usersentity. id FROM model.UsersEntitySuccess" +
                    "  as  usersentity WHERE usersentity.id =:id   ") .setLockOptions(lockOptionsREAD)
                    .setTimeout(3)
                    .setCacheable(true)
                    .setParameter("id",IdUser).setMaxResults(1).getResultList();

            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    " ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.toString());

        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletAuntification.txt");
        }
        return ЛистДанныеОтHibenide;
    }


    // todo МЕТОД генерируем для килента MODIFITATIONServer
    protected    List<model.ModificationServerEntity> МетодДляКлиентаMODIFITATION_Server( ) {
        /////// ВЕРСИЮ ДАННЫХ НА СЕРВЕРЕ
        List<model.ModificationServerEntity> ЛистДанныеОтHibenide  = new ArrayList<>();
        try {
            LockOptions lockOptions=new LockOptions(LockMode.READ);
            lockOptions.setAliasSpecificLockMode( "vd", LockMode.READ );
            lockOptions.setScope(true);

            ЛистДанныеОтHibenide =( List<model.ModificationServerEntity> )     session.createQuery(
                    "SELECT vd FROM model.ModificationServerEntity  vd ")
                    .setTimeout(3)
                    .setLockOptions(lockOptions)
                    .setCacheable(true).getResultList();

            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    " ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.toString());
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return ЛистДанныеОтHibenide;

    }
    // TODO еще генерируем заблокирваный статус клиента
    protected List<model.UsersEntity> Метод_МетодаСтатусЗаблорированогоКлиента(@javax.validation.constraints.NotNull   Integer  IDПолученныйИзSQlServerПосик,
                                                                               @javax.validation.constraints.NotNull Session session ) {
        List<model.UsersEntity> ЛистДанныеОтHibenide  = new ArrayList<>();
        try {


            LockOptions lockOptionsREAD=new LockOptions();
            lockOptionsREAD.setLockMode(LockMode. READ);
            lockOptionsREAD.setScope(true);

            ЛистДанныеОтHibenide =( List<model.UsersEntity>)   session.createQuery("SELECT us  FROM model.UsersEntity  us WHERE us.id  = :id ").setLockOptions(lockOptionsREAD)
                    .setTimeout(3)
                    .setCacheable(true)
                    .setParameter("id",new Integer(IDПолученныйИзSQlServerПосик)).getResultList();//8641 8625

            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    " ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.toString());
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return ЛистДанныеОтHibenide  ;

    }



}
