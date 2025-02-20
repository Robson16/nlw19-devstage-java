package br.com.rocketseat.nlw19_devstage_java.repo;

import br.com.rocketseat.nlw19_devstage_java.model.Event;
import br.com.rocketseat.nlw19_devstage_java.model.Subscription;
import br.com.rocketseat.nlw19_devstage_java.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
    public Optional<Subscription> findByEventAndSubscriber(Event event, User user);
}
