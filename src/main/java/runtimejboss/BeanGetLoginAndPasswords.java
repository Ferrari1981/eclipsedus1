package runtimejboss;

import businesslogic.SubClassWriterErros;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dsu1glassfishatomic.workinterfaces.ProducedCard;
import org.hibernate.*;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.jboss.ejb3.annotation.TransactionTimeout;

import javax.ejb.*;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Session Bean implementation class BeanGetLoginAndPasswords
 */
@Stateful(mappedName = "beanGetLoginAndPasswords")
@LocalBean
@TransactionTimeout(value = 1, unit = TimeUnit.HOURS)
public class BeanGetLoginAndPasswords {

    @Inject
    SubClassWriterErros subClassWriterErros;


    @Inject
    ObjectMapper getGeneratorJackson;

    @Inject
    @ProducedCard
    private SessionFactory sessionSousJboss;





    /**
     * Default constructor.
     */
    public BeanGetLoginAndPasswords() {
        // TODO Auto-generated constructor stub
    }


    @SuppressWarnings("unused")
    public  Boolean МетодGetsLoginAndPassword(@NotNull ServletContext ЛОГ,
                                                    @NotNull HttpServletRequest request,
                                                    @NotNull HttpSession sessionEJB,
                                              @NotNull   Session session) {
        int РазрешонныеПрава = 2;
        Boolean РезультатАунтификацииПользователя=false;
        try {

            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+ ЛОГ +" request " + request );

            String    ЛогинОтКлиента =  Optional.ofNullable(((HttpServletRequest) request).getHeader("identifier") ).orElse("")     ;
            String ПарольОтКлиента =  Optional.ofNullable(((HttpServletRequest) request).getHeader("p_identifier") ).orElse("");
            String IDДевайсаКлиента =  Optional.ofNullable(((HttpServletRequest) request).getHeader("id_device_androis") ).orElse("");
            ЛОГ.log(" ЛогинОтКлиента " +ЛогинОтКлиента+" ЛогинОтКлиента " + ЛогинОтКлиента );
            ////// TODO полученный нданные от Клиента
            if (! ЛогинОтКлиента.trim().isEmpty() && !  ПарольОтКлиента.trim().isEmpty() &&  ! IDДевайсаКлиента.trim().isEmpty() ) {
                // TODO: 10.03.2023 получение сессиии HIREBIANTE
                // TODO: 10.03.2023 получение сессиии Transaction

                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + "    session.getTransaction().getStatus() " +   session.getTransaction().getStatus());


                LockOptions lockOptionsREAD=new LockOptions();
                lockOptionsREAD.setLockMode(LockMode. READ) ;
                lockOptionsREAD.setScope(true);

                // TODO: 17.03.2023 ЗАПУСКАЕТ ТРАНЗАКЦИЮ BEGIN
                // TODO: 02.04.2023 Проводим Аунтификаций через пароли логин
                List<model.UsersEntitySuccess>    ЛистДанныеОтHibenide =( List<model.UsersEntitySuccess>)    session.createQuery("SELECT " +
                        " us FROM model.UsersEntitySuccess us WHERE us.rights =:rights  AND us.login=:login AND us.password=:password ")
                        .setCacheable(true)
                        .setLockOptions(lockOptionsREAD)
                        .setTimeout(3)
                        .setParameter("rights",new Integer(2))
                        .setParameter("login",new String(ЛогинОтКлиента))
                        .setParameter("password",new String(ПарольОтКлиента)).setMaxResults(1).getResultList();//8641 8625

                // TODO: 11.07.2023 ЕСЛИ ЕСТь ЧТо то создаем JSON
                if (ЛистДанныеОтHibenide !=null && ЛистДанныеОтHibenide.size() > 0) {
                    // TODO: 02.04.2023 Вытаскиваем Из ПРишедзиъ данных логин и пароль
                    byte[] БуферСозданогоJSONJacksonАунтификация = МетодГенерацияJSONJackson(ЛОГ, ЛистДанныеОтHibenide);
                    // TODO: 25.09.2023  логин пароль
                    String ЛогинОтКлиентаИзSQlServer= ЛистДанныеОтHibenide.get(0).getLogin().trim();
                    String ПарольИзSQlServer= ЛистДанныеОтHibenide.get(0).getPassword().trim();
                    int IdUser= ЛистДанныеОтHibenide.get(0).getId();


                    ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                            " ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.toString()  +  "ЛогинОтКлиентаИзSQlServer "+ЛогинОтКлиентаИзSQlServer +
                            "ПарольИзSQlServer " +ПарольИзSQlServer  + " IdUser " +IdUser  );


                    //// TODO СЮДА ЗАХОДИМ КОГДА ПОЛЬЗОВАТЕЛЬ
                        if (ЛогинОтКлиента.compareTo(ЛогинОтКлиентаИзSQlServer.toString())==0
                                &&  ПарольОтКлиента.compareTo(ПарольИзSQlServer.toString())==0) { ///// TODO


                            //TODO ЗАПЫИСЫВАМ ПУБЛИЧНЫЙ В ЛОГ
                            ЛОГ.setAttribute("IdUser", IdUser);
                            ЛОГ.setAttribute("АдуДевайсяКлиента", IDДевайсаКлиента.trim());

                            //TODO меняем статут и пускак клиента на сервер ВАЖНО
                            РезультатАунтификацииПользователя=true;
                            ЛОГ.log("  ЛогинОтКлиента  "
                                    + ЛогинОтКлиента +
                                    " IdUser " +IdUser
                                    + " ПарольОтКлиента " +ПарольОтКлиента +
                                    " ЛогинОтКлиента " +ЛогинОтКлиента+ " IDДевайсаКлиента "+IDДевайсаКлиента);
                        }else{
                            //TODO меняем статут и пускак клиента на сервер
                            РезультатАунтификацииПользователя=false;
                            ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                                    +"\n"+
                                    " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()+  "РезультатАунтификацииПользователя " +РезультатАунтификацииПользователя);
                        }
                }else {
                    //TODO меняем статут и пускак клиента на сервер
                    РезультатАунтификацииПользователя=false;
                    ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                            +"\n"+
                            " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                            + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()+  "РезультатАунтификацииПользователя " +РезультатАунтификацииПользователя);
                }
                //TODO
                ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                        +"\n"+
                        " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                        + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber());
            }else {
                //TODO
                //TODO меняем статут и пускак клиента на сервер
                РезультатАунтификацииПользователя=false;
                ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                        +"\n"+
                        " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                        + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()+  "РезультатАунтификацииПользователя " +РезультатАунтификацииПользователя);
            }

        } catch (Exception e) {
            // TODO: 17.10.2023 rollback
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return     РезультатАунтификацииПользователя ;
    }





    public byte[]  МетодГенерацияJSONJackson(@NotNull ServletContext ЛОГ,
                                             @javax.validation.constraints.NotNull List<?> listОтHiberideДляГенерации) {
        byte[] БуферСозданогоJSONJackson = new byte[0];
        try {
            ЛОГ.log(" listОтHiberideДляГенерации" + listОтHiberideДляГенерации );
            ObjectWriter writer = getGeneratorJackson.writerWithDefaultPrettyPrinter();
          //  String Сгенерированыйjson = 	  writer.writeValueAsString(listОтHiberideДляГенерации);
            byte[] Сгенерированыйjson = 	  writer.writeValueAsBytes(listОтHiberideДляГенерации);
            ЛОГ.  log(" Сгенерированыйjson "+Сгенерированыйjson);//gson
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+
                    "БуферСозданогоJSONJackson " + БуферСозданогоJSONJackson.toString());

        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return БуферСозданогоJSONJackson;
    }

}
