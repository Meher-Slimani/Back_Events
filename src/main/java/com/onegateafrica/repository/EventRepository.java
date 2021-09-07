package com.onegateafrica.repository;

import com.onegateafrica.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {

}
