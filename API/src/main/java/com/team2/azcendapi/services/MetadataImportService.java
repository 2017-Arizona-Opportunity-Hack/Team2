package com.team2.azcendapi.services;

import org.springframework.web.multipart.MultipartFile;
import org.supercsv.cellprocessor.ift.CellProcessor;

import java.util.List;

public interface MetadataImportService {

    <T> List<T> readEntitiesFromCsv(MultipartFile file, CellProcessor[] processors, String[] headers, Class<T> model);
    <T> StringBuffer writeEntitiesToCsv(boolean includeHeader, CellProcessor[] processors, String[] headers, List<T> entities);
}
