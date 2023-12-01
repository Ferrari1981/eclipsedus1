package businesslogic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.SerializerCache;
import com.sun.istack.NotNull;
import dsu1glassfishatomic.workinterfaces.ProducedGeneratorJson1;
import dsu1glassfishatomic.workinterfaces.ProducedResponseService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;


@ProducedGeneratorJson1
public class ResponseToAndroid {
    @Inject
    ObjectMapper getGeneratorJacksonCbor;
    @Inject
    SubClassWriterErros subClassWriterErros;

    ObjectWriter writer;
    byte[] БуферСозданогоJSONJackson;

    // TODO ГЕНЕРАЦИЯ JSON ПО  НОВОМУ Jackson
    byte[] МетодГенерацияJSONJacksonByte(@NotNull List<?> listОтHiberideДляГенерации,
                                         @NotNull ServletContext ЛОГ) {
        try {
            ЛОГ.log(" listОтHiberideДляГенерации" + listОтHiberideДляГенерации );
            writer = getGeneratorJacksonCbor.writerWithDefaultPrettyPrinter();

            ByteArrayOutputStream   byteArrayOutputStream=new ByteArrayOutputStream(2048);
            writer.writeValue(byteArrayOutputStream,listОтHiberideДляГенерации);
            БуферСозданогоJSONJackson=byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();

            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+ " writer " +writer);
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return БуферСозданогоJSONJackson;
    }


    // TODO: 07.11.2023  класс азные види ГЕНЕРАЦИИ JSON

    //TODO  public class GeneratorJsonWriteValue
}//TODO public class GeneratorJsonWriteValue



