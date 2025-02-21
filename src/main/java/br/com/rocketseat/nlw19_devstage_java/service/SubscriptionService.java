package br.com.rocketseat.nlw19_devstage_java.service;

import br.com.rocketseat.nlw19_devstage_java.dto.SubscriptionRankingByUser;
import br.com.rocketseat.nlw19_devstage_java.dto.SubscriptionRankingItem;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

        User indicationUser = null;
        if (indicationUserId != null) {
            indicationUser = userRepository.findById(indicationUserId).orElse(null);
            if (indicationUser == null) {
                throw new IndicationUserNotFoundException("Usuário indicador não encontrado.");
            }
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

    public List<SubscriptionRankingItem> getCompleteRanking(String prettyName) {
        Event event = eventRepository.findByPrettyName(prettyName);
        if (event == null) {
            throw new EventNotFoundException("Não foi encontrado nenhum ranking para o evento: " + prettyName);
        }

        return subscriptionRepository.generateRanking(event.getEventId());
    }

    public SubscriptionRankingByUser getRankingByUser(String prettyName, Integer userId) {
        Event event = eventRepository.findByPrettyName(prettyName);
        if (event == null) {
            throw new EventNotFoundException("Não foi encontrado nenhum ranking para o evento: " + prettyName);
        }

        List<SubscriptionRankingItem> ranking = subscriptionRepository.generateRanking(event.getEventId());

        SubscriptionRankingItem item = ranking
                .stream()
                .filter(i -> i.userId().equals(userId))
                .findFirst()
                .orElse(null);

        if (item == null) {
            throw new IndicationUserNotFoundException("Não há inscrições com indicação do usuário: " + userId);
        }

        int position = IntStream
                .range(0, ranking.size())
                .filter(pos -> ranking.get(pos).userId().equals(userId))
                .findFirst()
                .getAsInt();

        return new SubscriptionRankingByUser(item, position + 1);
    }
}
