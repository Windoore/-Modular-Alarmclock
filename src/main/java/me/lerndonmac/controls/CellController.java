package me.lerndonmac.controls;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import me.lerndonmac.model.Alarms;

import java.text.SimpleDateFormat;

public class CellController {
    @FXML
    private CheckBox activeChoseBox;
    @FXML
    private Button deleteButt;
    @FXML
    private TextField alarmNameText;
    @FXML
    private TextField soundNameText;
    @FXML
    private Button selectSoundButt;
    @FXML
    private TextField timeText;
    @FXML
    private Button changeTimeButt;
    @FXML
    private Button editButt;

    private Alarms alarm;


    public void initCell(Alarms alarm){
        this.alarm = alarm;
        alarmNameText.setText(alarm.getName());
        activeChoseBox.setSelected(alarm.getActive());
        soundNameText.setText(alarm.getSound());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        timeText.setText(sdf.format(alarm.getTime()));
        initButtons();
    }
    public void initButtons(){
        editButt.setOnAction(actionEvent -> {
        });
        deleteButt.setOnAction(actionEvent -> {
            AlarmsWinController.alarmsObserv.remove(alarm);
        });

    }
}
