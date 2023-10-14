package br.com.igorma.controller.response;

import br.com.igorma.model.TimeTracking;
import lombok.Data;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class ActivityResponse {

    private UUID id;
    private String type;
    private String activity;
    private String subActivity;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private String description;
    private long minutes;

    public ActivityResponse(TimeTracking timeTracking) {
        ZoneId zoneId = ZoneId.of("UTC-3");

        this.id = timeTracking.getId();
        this.type = timeTracking.getType();
        this.activity = timeTracking.getActivity();
        this.subActivity = timeTracking.getSubActivity();
        //this.startTime = timeTracking.getStartTime().withZoneSameInstant(zoneId).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.startTime = timeTracking.getStartTime();
        this.endTime = timeTracking.getEndTime();
        //this.endTime = timeTracking.getEndTime().withZoneSameInstant(zoneId).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.description = timeTracking.getDescription();
        if (timeTracking.getEndTime() != null)
            this.minutes = (timeTracking.getEndTime().toEpochSecond() - timeTracking.getStartTime().toEpochSecond()) / 60;
    }
}
