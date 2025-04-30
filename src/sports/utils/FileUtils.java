package sports.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileUtils {
    private FileUtils() {}

    public interface ListParent {

        String getOutTagName();

        String getName();

        List<Object> getChild();
    }

    public static class FileOutParamter {
        List<Object> list;

        String title;

        public FileOutParamter(String title) {
            this.list = new ArrayList<>();
            this.title = title;
        }

        static public FileOutParamter create(String title) {
            return new FileOutParamter(title);
        }

        public FileOutParamter add(Object item) {
            list.add(item);
            return this;
        }

        public FileOutParamter addAll(java.util.Collection<?> items) {
            return addAll(items, false);
        }

        public FileOutParamter addAll(java.util.Collection<?> items, boolean inputAndClear) {
            list.addAll(items);
            if (inputAndClear)
                items.clear();
            return this;
        }

        public FileOutParamter addAll(Iterable<?> items) {
            for (Object i : items)
                list.add(i);
            return this;
        }
    }

    // FileOutParamters p=FileUtils.createFile(dwqdwq,qwdq,FileOutParamter.create().addALl(), FileOutParamter.new).commit();

    public static void createFile(String prefixText, boolean withTime,
                                  FileOutParamter... paramters) {
        String time = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String fileName;

        if (withTime) {
            fileName = prefixText + "_" + time + ".txt";
        } else {
            fileName = prefixText + ".txt";
        }

        boolean isFirstOut = true;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            for (FileOutParamter paramter : paramters) {
                if (isFirstOut) {
                    isFirstOut = false;
                    writer.write(paramter.title + "\n");
                } else {
                    writer.write("\n" + paramter.title + "\n");
                }
                for (Object item : paramter.list) {
                    __writeListItem(writer, item, "");
                }
            }
            writer.flush();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    private static void __writeListItem(BufferedWriter writer, Object item, String header) throws IOException {
        if (item instanceof ListParent team) {
            writer.write(team.getOutTagName() + ": " + team.getName() + "\n");

            boolean isFirst = true;
            for (Object p : team.getChild()) {
                if (isFirst) {
                    isFirst = false;
                    __writeListItem(writer, p, "");
                } else {
                    __writeListItem(writer, p, "\t");
                }
            }
            writer.write("\n");
        } else {
            writer.write(header + item.toString() + "\n");
        }
    }
}
