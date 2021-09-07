package com.onegateafrica.entity;


import lombok.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
	
	   @Id
	    private String id;
	  
	     private  @NonNull String name;
	     private @NonNull String description;
	     


}
