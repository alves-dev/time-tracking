package br.com.igorma.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.ZonedDateTime;

@Builder
@Getter
@Entity(name = "time_tracking")
@NoArgsConstructor
public class TimeTracking extends BaseEntity {

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @Column(name = "activity", length = 20)
    private String activity;

    @Column(name = "sub_activity", length = 20)
    private String subActivity;

    @Column(name = "start_time", nullable = false)
    private ZonedDateTime startTime;

    @Column(name = "end_time")
    private ZonedDateTime endTime;

    @Column(name = "description", length = 100)
    private String description;

    public TimeTracking(String type, String activity, String subActivity, ZonedDateTime startTime, ZonedDateTime endTime,
                        String description) {

        if (startTime == null)
            startTime = ZonedDateTime.now();
        else if (startTime.isAfter(ZonedDateTime.now()))
            throw new IllegalArgumentException("Start time cannot be in the future");

        if (endTime != null && endTime.isBefore(startTime)) throw new IllegalArgumentException("End time cannot be before start time");
        if (endTime != null && endTime.isAfter(ZonedDateTime.now())) throw new IllegalArgumentException("End time cannot be in the future");

        this.type = toFormatted(type);
        this.activity = toFormatted(activity);
        this.subActivity = toFormatted(subActivity);
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    private String toFormatted(String value) {
        if (value == null) return null;
        if (value.contains(" ")) throw new IllegalArgumentException("Value not  allowed to contain spaces");
        if (value.length() <= 2) throw new IllegalArgumentException("Value must be greater than 2 character");
        if (value.length() > 20) throw new IllegalArgumentException("Size cannot be greater than 20");
        return value.toUpperCase();
    }

    public void finish() {
        if (endTime != null) throw new IllegalArgumentException("Time tracking is already finished");
        endTime = ZonedDateTime.now();
    }
}
