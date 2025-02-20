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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {
    @Autowired
    private SubscriptionService service;

    @PostMapping({"/subscription/{prettyName}", "/subscription/{prettyName}/{indicationUserId}"})
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
}
