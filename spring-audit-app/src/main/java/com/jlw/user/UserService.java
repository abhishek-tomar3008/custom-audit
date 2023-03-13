package com.jlw.user;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jlw.audit.AuditService;
import com.jlw.audit.AuditTrail;
import com.jlw.audit.AuditTrailRepository;
import com.jlw.bank.Bank;
import com.jlw.bank.BankRepository;


@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuditService auditService;


	

	
	
	public User createUser(User user) {
		User createdUser=userRepository.save(user);
		
		
		
	

	 
	  auditService.auditTrailOfCreation(user);
	  
	 

	
		
		return createdUser;
		
	}
	
	public User UpdateUser( User user) throws UserPrincipalNotFoundException {
		System.out.println(user.toString());
	  Optional<User> userDb= userRepository.findById(user.getId());
	  System.out.println(userDb.get().toString());

	       
	  if(userDb.get()!=null) {
		  
		 
		 
		  auditService.auditForUpdate(userDb.get(),user);
		  
		  
		  
		  return userRepository.save(user);
	  }
	  else {
		  throw new UserPrincipalNotFoundException("User not found with the id "+user.getId());
	  }
		
		
		
	}

	public String deleteUser(int id) throws UserPrincipalNotFoundException {
		Optional<User> userDb= userRepository.findById(id);
		
		
		
		
		
		 if(userDb.get()!=null) {
			 
		auditService.auditForDelete(userDb.get());
			 
			   userRepository.deleteById(id);
			   return "user deleted sucessfully";
		  }
		  else {
			  throw new UserPrincipalNotFoundException("User not found with the id "+id);
		  }
	  }
}
