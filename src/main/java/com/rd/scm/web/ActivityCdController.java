package com.rd.scm.web;

import java.net.URI;
import java.net.URISyntaxException;

import com.rd.scm.model.ActivityCdPK;
import com.rd.scm.service.ActivityCdService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ActivityCdController {
    
    private static final Logger log = LoggerFactory.getLogger(ActivityCdController.class);

    private final ActivityCdService activityCdService;

    public ActivityCdController(ActivityCdService activityCdService) {
        this.activityCdService = activityCdService;
    }

    
    @GetMapping("/activitycd/{id}")
    public ResponseEntity<?> getInventoryRecord(@PathVariable String id, @RequestParam String sdCd) {

        log.trace("doStuff needed more trace information - {}", sdCd);
        log.debug("doStuff needed to debug - {}", sdCd);
        log.info("doStuff took input - {}", sdCd);
        log.warn("doStuff needed to warn - {}", sdCd);
        log.error("doStuff encountered an error with value - {}", sdCd);

        return activityCdService.findById(new ActivityCdPK(sdCd, id))
                .map(activitycd -> {
                    try {
                        return ResponseEntity
                                .ok()
                                .location(new URI("/activitycd/" + activitycd.getActivityCd() + "?sdCd="+activitycd.getSdCd()))
                                .body(activitycd);
                    } catch (URISyntaxException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }
}