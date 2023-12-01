package businesslogic.genertoringjson;

import businesslogic.SubClassWriterErros;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.sun.istack.NotNull;
import dsu1glassfishatomic.workinterfaces.ProducedGeneratorJson3;
import model.ModificationServerEntity;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.io.ByteArrayOutputStream;
import java.util.List;


@ProducedGeneratorJson3
public class GeneratorJsonSequenceWriter  extends  GeneratorJsonAbstract  {
    @Inject
    ObjectMapper getGeneratorJacksonCbor;
    @Inject
    SubClassWriterErros subClassWriterErros;

    ObjectWriter writer;


    @Override
    public byte[] getGeneratorJson(List<?> listОтHiberideДляГенерации, ServletContext ЛОГ) {
        byte[] БуферСозданогоJSONJackson=null;
        try {
            ЛОГ.log(" listОтHiberideДляГенерации" + listОтHiberideДляГенерации
                    +" JVM freeMemory  " +Runtime.getRuntime().freeMemory()
                    +" JVM maxMemory  " +Runtime.getRuntime().maxMemory()
                    +" JVM totalMemory  " +Runtime.getRuntime().totalMemory());
            writer = getGeneratorJacksonCbor.writerWithDefaultPrettyPrinter();
            ByteArrayOutputStream   byteArrayOutputStream=new ByteArrayOutputStream(2048);


            SequenceWriter sequenceWriter = writer .writeValues(byteArrayOutputStream).init(true) .writeAll(listОтHiberideДляГенерации);

            sequenceWriter.flush();
            sequenceWriter.close();
            БуферСозданогоJSONJackson=byteArrayOutputStream.toByteArray();

            byteArrayOutputStream.flush();
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


   // TODO: 07.11.2023  класс азные види ГЕНЕРАЦИИ JSON

   //TODO  public class GeneratorJsonWriteValue
}//TODO public class GeneratorJsonWriteValue



