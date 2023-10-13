package br.com.igorma.service;

import br.com.igorma.model.TimeTracking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@ApplicationScoped
public class Service {

    @Transactional
    public void save(){

//        TimeTracking time = new TimeTracking("ESTUDANDO");
//        time.setActivity("meter_pe");
//        time.setSubActivity("quarkus");
//        time.setDescription("Estudando Quarkus");
//
//        time.persist();

        TimeTracking time = TimeTracking.builder()
                .type("ESTUDANDO")
                .activity("meter_pe")
                .subActivity("quarkus")
                .startTime(ZonedDateTime.now().minusHours(3))
                .endTime(ZonedDateTime.now())
                .description("Estudando Quarkus")
                .build();
        time.persist();
    }

    public List<TimeTracking> getAll() {
        return TimeTracking.findAll().list();
    }
}
