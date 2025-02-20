package br.com.rocketseat.nlw19_devstage_java.repo;

import br.com.rocketseat.nlw19_devstage_java.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {
    public Event findByPrettyName(String prettyName);
}
