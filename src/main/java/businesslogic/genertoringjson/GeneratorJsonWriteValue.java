package businesslogic.genertoringjson;

import businesslogic.SubClassWriterErros;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sun.istack.NotNull;
import dsu1glassfishatomic.workinterfaces.ProducedGeneratorJson2;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.io.ByteArrayOutputStream;
import java.util.List;

@ProducedGeneratorJson2
public class GeneratorJsonWriteValue  extends  GeneratorJsonAbstract {
   @Inject
   ObjectMapper getGeneratorJacksonCbor;
   @Inject
   SubClassWriterErros subClassWriterErros;

   ObjectWriter writer;


    @Override
    public byte[] getGeneratorJson(List<?> listОтHiberideДляГенерации, ServletContext ЛОГ) {
        byte[] БуферСозданогоJSONJackson=null;
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
        //return super.getGeneratorJson(listОтHiberideДляГенерации, ЛОГ);
    }
}//TODO public class GeneratorJsonWriteValue



