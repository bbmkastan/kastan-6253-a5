package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Bao Kastan
 */

import javafx.collections.ObservableList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

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

    public void loadAsTSV (File file, ObservableList<Item> list) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        list.clear();
        while(scanner.hasNextLine()) {
            String string = scanner.nextLine();
            String[] parts = string.split("\t");
            Item item = new Item(parts[0],parts[1],parts[2]);
            list.add(item);
        }
    }

    public void loadAsHTML (File file, ObservableList<Item> list) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        StringBuilder html = new StringBuilder();
        while(scanner.hasNextLine()) {
            html.append(scanner.nextLine());
        }
        Document doc = Jsoup.parse(html.toString());
        Element table = doc.select("table").get(0);
        Elements rows = table.select("tr");

        for (Element row : rows) {
            Elements cols = row.select("td");
            String[] array = new String[4];
            for (int j = 0; j < cols.size(); ++j) {
                array[j] = cols.get(j).getElementsByTag("td").text();
            }
            Item item = new Item(array[0], array[1], array[2]);
            list.add(item);
        }
    }

    public void loadAsJSON (File file, ObservableList<Item> list) throws IOException {
        Scanner scanner = new Scanner(file);
        String json = scanner.nextLine();
        String string = json.replaceAll("[^a-zA-Z0-9:,.]","");
        String[] parts = string.split(",");
        for (int i = 0; parts.length > i; ++i) {
            parts[i] = parts[i].substring(parts[i].lastIndexOf(":") + 1);
        }
        int numItems = parts.length/3;
        int j = -1;
        for (int i = 0 ;numItems > i; ++i) {
            Item item = new Item(parts[++j],parts[++j],parts[++j]);
            list.add(item);
        }
    }
}
