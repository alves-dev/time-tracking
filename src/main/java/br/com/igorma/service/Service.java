package br.com.igorma.service;

import br.com.igorma.controller.request.ActivityRequest;
import br.com.igorma.model.TimeTracking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class Service {

    @Transactional
    public TimeTracking create(ActivityRequest activityRequest) {
        TimeTracking.TimeTrackingBuilder timeBuilder = TimeTracking.builder()
                .type(activityRequest.getType())
                .activity(activityRequest.getActivity())
                .subActivity(activityRequest.getSubActivity())
                .description(activityRequest.getDescription());

        if (activityRequest.getStartTime() != null) timeBuilder.startTime(activityRequest.getStartTime());
        if (activityRequest.getEndTime() != null) timeBuilder.endTime(activityRequest.getEndTime());

        TimeTracking time = timeBuilder.build();
        time.persist();
        return time;
    }

    @Transactional
    public TimeTracking finish(UUID id) {
        TimeTracking time = TimeTracking.findById(id);
        time.finish();
        time.persist();
        return time;
    }

    public List<TimeTracking> findLast(int count) {
        if (count > 0) count = count - 1;
        return TimeTracking.find("ORDER BY startTime DESC")
                .range(0, count)
                .list();
    }

    public List<TimeTracking> findNotFinished() {
        return TimeTracking.find("endTime is null").list();
    }
}
