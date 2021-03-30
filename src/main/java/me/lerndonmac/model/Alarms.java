package me.lerndonmac.model;

import lombok.*;

import java.io.File;
import java.util.Date;
import java.util.Set;

@Getter@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Alarms {
@NonNull
    private String name;
@NonNull
    private Date time;
    private String soundPath;
@NonNull
    private Boolean active;
private String question;
private Set<Alarms> subAlarms;



    public String getSound(){
    File file = new File(getSoundPath());
    return file.getName();
}

    @Override
    public String toString() {
        return name +'\''+ time  +'\''+ soundPath  +'\''+ active +"\n" ;
    }
}
