package br.com.rocketseat.nlw19_devstage_java.service;

import br.com.rocketseat.nlw19_devstage_java.dto.SubscriptionResponse;
import br.com.rocketseat.nlw19_devstage_java.exception.EventNotFoundException;
import br.com.rocketseat.nlw19_devstage_java.exception.IndicationUserNotFoundException;
import br.com.rocketseat.nlw19_devstage_java.exception.SubscriptionConflictException;
import br.com.rocketseat.nlw19_devstage_java.model.Event;
import br.com.rocketseat.nlw19_devstage_java.model.Subscription;
import br.com.rocketseat.nlw19_devstage_java.model.User;
import br.com.rocketseat.nlw19_devstage_java.repo.EventRepository;
import br.com.rocketseat.nlw19_devstage_java.repo.SubscriptionRepository;
import br.com.rocketseat.nlw19_devstage_java.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public SubscriptionResponse createNewSubscription(String eventName, Integer indicationUserId, User user) {
        Event event = eventRepository.findByPrettyName(eventName);
        if (event == null) {
            throw new EventNotFoundException("Evento não encontrado para o nome: " + eventName);
        }

        User existingUser = userRepository.findByEmail(user.getEmail());
        User finalUser = (existingUser != null) ? existingUser : userRepository.save(user);

        User indicationUser = userRepository.findById(indicationUserId).orElse(null);
        if (indicationUser == null) {
            throw new IndicationUserNotFoundException("Usuário indicador não encontrado.");
        }

        Optional<Subscription> existingSubscription = subscriptionRepository.findByEventAndSubscriber(event, finalUser);
        if (existingSubscription.isPresent()) {
            throw new SubscriptionConflictException("Já existe uma inscrição para o usuário " + finalUser.getName() + " no evento " + event.getTitle());
        }

        Subscription subscription = new Subscription();
        subscription.setEvent(event);
        subscription.setSubscriber(finalUser);
        subscription.setIndication(indicationUser);

        Subscription result = subscriptionRepository.save(subscription);

        return new SubscriptionResponse(result.getSubscriptionNumber(), "http://devstage.com/" + result.getEvent().getPrettyName() + "/" + result.getSubscriber().getId());
    }
}
