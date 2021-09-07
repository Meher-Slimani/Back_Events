package com.onegateafrica.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.onegateafrica.entity.Image;
import java.util.List;

@Repository
public interface ImageRepository extends MongoRepository<Image,String> {
	List<Image> findByOrderById();
}
