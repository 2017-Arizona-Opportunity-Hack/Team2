package com.team2.azcendapi.services.implementation;

import com.team2.azcendapi.services.MetadataImportService;
import org.apache.commons.io.input.BOMInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;


@Component
public class MetadataImportServiceImpl implements MetadataImportService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public <T> List<T> readEntitiesFromCsv(MultipartFile file, CellProcessor[] processors, String[] headers, Class<T> model) {
        try {
            CsvBeanReader beanReader = setupReader(file);
            return readRows(beanReader, processors, headers, model);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private <T> List<T> readRows(CsvBeanReader beanReader, CellProcessor[] processors, String[] headers, Class<T> model) throws Exception {
        List<T> entities = new ArrayList<>();
        try {
            T entity;
            while( (entity = beanReader.read(model, headers, processors)) != null ) {
                entities.add(entity);
            }
        } finally {
            beanReader.close();
        }
        return entities;
    }

    private CsvBeanReader setupReader(MultipartFile file) throws Exception {
        BOMInputStream inputStreamWithoutBom = new BOMInputStream(file.getInputStream());
        logger.info("input file, {}, has BOM char: {}", file.getName(), inputStreamWithoutBom.hasBOM());
        Reader fileReader = new InputStreamReader(inputStreamWithoutBom);
        return new CsvBeanReader(fileReader, CsvPreference.STANDARD_PREFERENCE);
    }

    public <T> StringBuffer writeEntitiesToCsv(boolean includeHeader, CellProcessor[] processors, String[] headers, List<T> entities) {
        try {
            StringWriter stringWriter = new StringWriter();
            CsvBeanWriter beanWriter = new CsvBeanWriter(stringWriter, CsvPreference.STANDARD_PREFERENCE);

            if (includeHeader) {
                beanWriter.writeHeader(headers);
            }

            for(T entity : entities ) {
                beanWriter.write(entity, headers, processors);
            }
            beanWriter.close();
            return stringWriter.getBuffer();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } 
    }
}
