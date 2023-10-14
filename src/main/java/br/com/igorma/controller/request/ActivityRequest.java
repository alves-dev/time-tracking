package br.com.igorma.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class ActivityRequest {

    @Size(min = 3, max = 20)
    @NotBlank
    private String type;

    @Size(max = 20)
    private String activity;

    @Size(max = 20)
    private String subActivity;

    @Past
    private ZonedDateTime startTime;

    private ZonedDateTime endTime;

    private String description;
}
