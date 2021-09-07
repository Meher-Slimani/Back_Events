package com.onegateafrica.controller;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.onegateafrica.entity.Image;
import com.onegateafrica.messsage.Msg;
import com.onegateafrica.repository.ImageRepository;
import com.onegateafrica.service.CloudinaryService;
import com.onegateafrica.service.ImageService;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin


public class CloudinaryController {

	
	  @Autowired
	    CloudinaryService cloudinaryService;
	  
	    @Autowired
	    ImageService imageService;
	    
	    @Autowired
		 private ImageRepository imageRepository;
	  
	    @GetMapping("/list")
	    public ResponseEntity<List<Image>> list(){
	        List<Image> list = imageService.list();
	        return new ResponseEntity(list, HttpStatus.OK);
	    }

	 /*	@PostMapping("/employee")
	 public User addEmployee(@RequestBody User newEmployee)
	 {User emp = new User( newEmployee.getName(), newEmployee.getDescription());
	  userRepository.insert(emp);
	  return emp;
	 }*/
	    
	    
	  @PostMapping("/upload")
	    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile, Image im )throws IOException {
		  BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
	        if(bi == null){
	            return new ResponseEntity(new Msg("imagen no válida"), HttpStatus.BAD_REQUEST);
	        }
	        Map result = cloudinaryService.upload(multipartFile);
	        Image imagen =
	                new Image(   im.getNom() , (String)result.get("original_filename"),
	                        (String)result.get("url"),
	                        (String)result.get("public_id")
	                             )    ;
	        imageService.save(imagen);
	 
	        
	        
	        return new ResponseEntity(result, HttpStatus.OK);
	    }
	  
	  
	  
	  /*
	  @PostMapping("/upload")
	    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile)throws IOException {
		  BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
	        if(bi == null){
	            return new ResponseEntity(new Msg("imagen no válida"), HttpStatus.BAD_REQUEST);
	        }
	        Map result = cloudinaryService.upload(multipartFile);
	        Image imagen =
	                new Image(  (String)result.get("original_filename"),
	                        (String)result.get("url"),
	                        (String)result.get("public_id")
	                             );
	        imageService.save(imagen);
	        
	        return new ResponseEntity(result, HttpStatus.OK);
	    }
	  */
	  @DeleteMapping("/delete/{id}")
	    public ResponseEntity<Map> delete(@PathVariable("id") String id)throws IOException {
	       
	           
	        
	        Map result = cloudinaryService.delete(id);
	       
	        return new ResponseEntity(result, HttpStatus.OK);
	    }  

	  //  @Autowired
	   // ImageService imagenService;

	   /* @GetMapping("/list")
	    public ResponseEntity<List<Imagen>> list(){
	        List<Imagen> list = imagenService.list();
	        return new ResponseEntity(list, HttpStatus.OK);
	    }*/

	 /*   @PostMapping("/upload")
	    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile)throws IOException {
	        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
	        if(bi == null){
	            return new ResponseEntity(new Mensaje("imagen no válida"), HttpStatus.BAD_REQUEST);
	        }
	        Map result = cloudinaryService.upload(multipartFile);
	        Imagen imagen =
	                new Imagen((String)result.get("original_filename"),
	                        (String)result.get("url"),
	                        (String)result.get("public_id"));
	        imagenService.save(imagen);
	        return new ResponseEntity(new Mensaje("imagen subida"), HttpStatus.OK);
	    }

	 /*   @DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> delete(@PathVariable("id") int id)throws IOException {
	        if(!imagenService.exists(id))
	            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
	        Imagen imagen = imagenService.getOne(id).get();
	        Map result = cloudinaryService.delete(imagen.getImagenId());
	        imagenService.delete(id);
	        return new ResponseEntity(new Mensaje("imagen eliminada"), HttpStatus.OK);
	    }   */
}
