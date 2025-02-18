package br.com.rocketseat.nlw19_devstage_java.repo;

import org.springframework.data.repository.CrudRepository;

import br.com.rocketseat.nlw19_devstage_java.model.Event;

public interface EventRepo extends CrudRepository<Event, Integer> {
	public Event findByPrettyName(String prettyName);
}
