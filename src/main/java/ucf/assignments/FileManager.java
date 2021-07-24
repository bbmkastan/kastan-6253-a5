package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Bao Kastan
 */

import javafx.collections.ObservableList;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class FileManager {
    public void saveAsTSV (String fileName, ObservableList<Item> list) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        for (Item item : list) {
            writer.write(item.getName() + "\t" + item.getSerialNum() + "\t" + item.getPrice()+"\n");
        }
        writer.close();
    }

    public void saveAsHTML (String fileName, ObservableList<Item> list) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write("<!DOCTYPE html>\n<html>\n<body>\n<table style = \"width:100%\">");
        for (Item item : list) {
            writer.write("\n<tr>\n<td>" + item.getName() + "</td>\n<td>"
                    + item.getSerialNum() + "</td>\n<td>" + item.getPrice()+"</td>\n<td>\n</tr>");
        }
        writer.write("\n<table>\n</body>\n</html>");
        writer.close();
    }

    public void saveAsJSON (String fileName, ObservableList<Item> list) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get(fileName).toFile(), list);
    }
}
