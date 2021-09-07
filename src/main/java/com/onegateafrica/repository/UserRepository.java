package com.onegateafrica.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.onegateafrica.entity.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends  MongoRepository<User,String>  {
	

}
