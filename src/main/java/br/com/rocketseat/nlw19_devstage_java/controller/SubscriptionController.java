package br.com.rocketseat.nlw19_devstage_java.controller;

import br.com.rocketseat.nlw19_devstage_java.dto.ErrorMessage;
import br.com.rocketseat.nlw19_devstage_java.dto.SubscriptionResponse;
import br.com.rocketseat.nlw19_devstage_java.exception.EventNotFoundException;
import br.com.rocketseat.nlw19_devstage_java.exception.IndicationUserNotFoundException;
import br.com.rocketseat.nlw19_devstage_java.exception.SubscriptionConflictException;
import br.com.rocketseat.nlw19_devstage_java.model.User;
import br.com.rocketseat.nlw19_devstage_java.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubscriptionController {
    @Autowired
    private SubscriptionService service;

    @PostMapping({
            "/subscription/{prettyName}",
            "/subscription/{prettyName}/{indicationUserId}"
    })
    public ResponseEntity<?> createSubscription(@PathVariable String prettyName, @PathVariable(required = false) Integer indicationUserId, @RequestBody User subscriber) {
        try {
            SubscriptionResponse response = service.createNewSubscription(prettyName, indicationUserId, subscriber);

            if (response != null) {
                return ResponseEntity.ok(response);
            }
        } catch (EventNotFoundException | IndicationUserNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorMessage(e.getMessage()));
        } catch (SubscriptionConflictException e) {
            return ResponseEntity.status(409).body(new ErrorMessage(e.getMessage()));
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/subscription/{prettyName}/ranking")
    public ResponseEntity<?> getRankingByEvent(@PathVariable String prettyName) {
        try {
            return ResponseEntity.ok(service.getCompleteRanking(prettyName));
        } catch (EventNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorMessage(e.getMessage()));
        }
    }

    @GetMapping("/subscription/{prettyName}/ranking/{userId}")
    public ResponseEntity<?> getRankingByEventAndUser(@PathVariable String prettyName, @PathVariable Integer userId) {
        try {
            return ResponseEntity.ok(service.getRankingByUser(prettyName, userId));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ErrorMessage(e.getMessage()));
        }
    }
}
