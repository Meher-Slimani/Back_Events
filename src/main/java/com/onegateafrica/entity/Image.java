package com.onegateafrica.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "images")
public class Image {
	 @Id
	 private String id;
	 private String nom;
	    private String name;
	    private String imagenUrl;
	    private String imagenId;





public Image() {
}

public Image(  String nom, String name, String imagenUrl, String imagenId) {
	this.nom = nom;
    this.name = name;
    this.imagenUrl = imagenUrl;
    this.imagenId = imagenId;
}

public String getId() {
    return id;
}


public String getNom() {
    return nom;
}

public void setNom(String nom) {
    this.nom = nom;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getImagenUrl() {
    return imagenUrl;
}

public void setImagenUrl(String imagenUrl) {
    this.imagenUrl = imagenUrl;
}

public String getImagenId() {
    return imagenId;
}

public void setImagenId(String imagenId) {
    this.imagenId = imagenId;
}
}