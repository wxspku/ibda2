package net.ibda.dataanalysis.firstapp;

import tech.tablesaw.api.Table;

import java.io.IOException;
import java.io.InputStream;

public class FirstApp {
    public static void main(String[] args) throws IOException {
        InputStream stream = null;
        try{
            stream = FirstApp.class.getResourceAsStream("/data/baseball.csv");
            Table tornadoes = Table.read().csv(stream);
            System.out.println("Column Names:" + tornadoes.columnNames());
            System.out.println("Data Shape:" + tornadoes.shape());
        }
        finally {
            stream.close();
        }
    }
}
