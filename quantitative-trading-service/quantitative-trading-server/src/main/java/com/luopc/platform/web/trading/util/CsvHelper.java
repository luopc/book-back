package com.luopc.platform.web.trading.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CsvHelper {
//
//    public static <T> List<T> readCsvFile(String fileName, Class<T> target) throws Exception {
//        List<T> list = new ArrayList<>();
//        File file = new File(fileName);
//        try (CsvReader<NamedCsvRecord> csvReader = CsvReader.builder().ofNamedCsvRecord(file.toPath())) {
//            for (NamedCsvRecord record : csvReader) {
//                Map<String, String> map = record.getHeader()
//                        .stream()
//                        .collect(Collectors.toMap(String::toString, record::getField));
//                list.add(mapToObj(map, target));
//            }
//        } catch (IOException e) {
//            log.error("unable to open file {}", fileName, e);
//        }
//        return list;
//    }
//
//    public static <T> T mapToObj(Map source, Class<T> target) throws Exception {
//        Field[] fields = target.getDeclaredFields();
//        T o = target.newInstance();
//        for (Field field : fields) {
//            Object val;
//            if ((val = source.get(field.getName())) != null) {
//                field.setAccessible(true);
//                field.set(o, val);
//            }
//        }
//        return o;
//    }


}
