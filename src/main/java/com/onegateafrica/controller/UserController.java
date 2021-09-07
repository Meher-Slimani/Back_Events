package com.onegateafrica.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onegateafrica.entity.User;
import com.onegateafrica.repository.UserRepository;

@RestController
public class UserController {


  private final UserRepository userRepository;

  @Autowired
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
	
	/*@PostMapping("/employee")
	 public User addEmployee(@RequestBody User newEmployee)
	 //{User emp = new User( newEmployee.getName(), newEmployee.getDescription());
	  userRepository.insert(emp);
	//  return emp;
	 }*/


  @PostMapping("/add")
  public String saveUser(@RequestBody User user) {
    userRepository.save(user);
    return "Added Employee id ";
  }

  @PostMapping
      ("/addd")
  public String saveEmployee(@RequestBody User user) {

    userRepository.insert(user);

    return "Added Emploe id ";

  }
		 
		/* @GetMapping("/employees/{id}")
		    public ResponseEntity < User > getEmployeeById(@PathVariable(value = "id") String id)
		    {
		        User employee = userRepository.findById(id)
		           
		        return ResponseEntity.ok().body(employee);
		    }
		 
	/*	 @PostMapping("/tutorials")
		 public ResponseEntity<User> createTutorial(@RequestBody User user) {
		   try {
			   User _user = userRepository.save(new 	User(user.getName(), false));
		     return new ResponseEntity<>(_user, HttpStatus.CREATED);
		   } catch (Exception e) {
		     return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		   }
		 }*/

  @GetMapping("/all")

  public List<User> getAllEmployee() {

    List<User> allUsers = userRepository.findAll();

    return allUsers;

  }

  @GetMapping("/employee/{id}")
  public Optional<User> getEmployee(@PathVariable String id) {
    Optional<User> emp = userRepository.findById(id);
    return emp;
  }
		 
	/*	 @PutMapping("/employee/{id}")
		 public Optional<User> updateEmployee(@RequestBody User newEmployee, @PathVariable String id)
		 {
		  Optional<User> optionalEmp = userRepository.findById(id);
		  if (optionalEmp.isPresent()) {
		   User emp = optionalEmp.get();
		   emp.setName(newEmployee.getName());
		   
		   emp.setDescription(newEmployee.getDescription());
		   userRepository.save(emp);
		  }
		  return optionalEmp;
		 }*/
}
