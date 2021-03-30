package me.lerndonmac.controls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import me.lerndonmac.model.Alarms;

import java.io.*;
import java.util.Date;

public class AlarmsWinController {

    @FXML
    private AnchorPane anchor;
    private int countOfAlarms;

    private BufferedReader reader;
    private FileWriter writer;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TilePane tilePane;

    public static ObservableList<Alarms> alarmsObserv = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws InterruptedException {
        LoadThread thread = new LoadThread();
        thread.start();
        while (thread.isAlive()) {
            Thread.sleep(100);
        }
        initCels();

    }
    private void exitEvent(){
        System.out.println("write");
        try {
            writer = new FileWriter(getClass().getResource("/alarms/alarmlist0.alttx").getFile());
            PrintWriter clearWriter = new PrintWriter(getClass().getResource("/alarms/alarmlist0.alttx").getFile());
            clearWriter.print("");
            clearWriter.close();
            for (Alarms alarms : alarmsObserv) {
                writer.write(alarms.toString());
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void initList() {
        if (!alarmsObserv.isEmpty()){
            alarmsObserv.clear();
    }
        try {
            reader = new BufferedReader(new FileReader(getClass().getResource("/alarms/alarmlist0.alttx").getFile()));
            while (reader.ready()) {
                countOfAlarms++;
                String alarm = reader.readLine();
                String[] alarmParams = alarm.split("\'");
                Date date = new Date();
                Alarms alarms = new Alarms(alarmParams[0], date, Boolean.getBoolean(alarmParams[3]));
                alarmsObserv.add(alarms);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initCels(){
        tilePane.setHgap(5);
        for (Alarms alarm : alarmsObserv){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cell.fxml"));
            try {
                Parent parent = loader.load();
                CellController cellController = loader.getController();
                cellController.initCell(alarm);

                tilePane.getChildren().add(parent);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    protected Boolean exitBull = false;
    public class LoadThread extends Thread{

        @Override
        public void run(){
            if (!exitBull){
                initList();
            }else {exitEvent();}

        }
    }

}
