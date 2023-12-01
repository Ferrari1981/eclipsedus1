package businesslogic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@Named("transationCompleteSession")
public class TransationCompleteSession {

    @Inject
    private SubClassWriterErros subClassWriterErros;

    public   Session startingSession(@NotNull ServletContext ЛОГ, @NotNull SessionFactory sessionFactory){
        Session session=null;
        try{
            if (sessionFactory.isOpen()) {
                session=   sessionFactory.openSession();
                // TODO: 31.10.2023
            }

            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                + "session  "  +session +" sessionFactory.isOpen() "+sessionFactory.isOpen() );
    } catch (Exception e) {
            subClassWriterErros.
                МетодаЗаписиОшибкиВЛог(e,
                        Thread.currentThread().
                                getStackTrace(),
                        ЛОГ, "ErrorsLogs/ErrorJbossServletUpdatePO.txt");

    }
        return  session;

    }

    public   void startingTransastion(@NotNull ServletContext ЛОГ, @NotNull Session session){
        try{
            Transaction transaction=null;
                // TODO: 31.10.2023
                if (session.isOpen()) {
                     transaction= session.getTransaction() ;
                    if (transaction.getStatus()== TransactionStatus.NOT_ACTIVE) {
                        transaction.setTimeout(180000);
                        transaction.begin();
                        // TODO: 17.11.2023
                    }
                }
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " session  "  +session.isOpen() );
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ, "ErrorsLogs/ErrorJbossServletUpdatePO.txt");

        }
    }

    public  void commitingTransastion(@NotNull ServletContext ЛОГ,@NotNull Session session){
        try{
            Transaction transaction= null;
            if (session.isOpen()) {
                transaction = session.getTransaction();

            if (transaction.getStatus()!= TransactionStatus.COMMITTED) {
                if (session.isDirty()) {
                    session.flush();
                }
                transaction.commit();
            }}
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + "+ \" session  \"  +session.isOpen()" + " session  "  +session.isOpen() );
    } catch (Exception e) {
        subClassWriterErros.
        МетодаЗаписиОшибкиВЛог(e,
        Thread.currentThread().
        getStackTrace(),
        ЛОГ, "ErrorsLogs/ErrorJbossServletUpdatePO.txt");

        }
    }


    public  void erroringTransastion(@NotNull ServletContext ЛОГ,@NotNull Session session){
        try{
            Transaction transaction= null;
            if (session.isOpen()) {
                transaction = session.getTransaction();
                if (transaction.getStatus()!= TransactionStatus.ROLLED_BACK) {
                    transaction.rollback();
                }
            }
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " transaction.getStatus() " +transaction.getStatus() );
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ, "ErrorsLogs/ErrorJbossServletUpdatePO.txt");

        }
    }


}
