package com.jlw.bank;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jlw.audit.AuditService;
import com.jlw.user.User;

@Service
@RestController
public class BankService {
	
	@Autowired
	BankRepository bankRepository;
	
	@Autowired
	AuditService auditService;

	
	@PostMapping("/bank")
	public Bank createBank( @RequestBody Bank bank) {
		Bank b= bankRepository.save(bank);
		auditService.auditTrailOfCreation(b);
		return b;
		
	}
	@PutMapping("/bank")
	public Bank updateBank(@RequestBody Bank bank) {
		Optional<Bank> b=bankRepository.findById(bank.getAccountNo());
		
		if(b.get()!=null) {
			
			Bank savedBank=bankRepository.save(bank);
			auditService.auditForUpdate(b.get(), bank);
			return savedBank;
		}
		
		return null;
	}
	

}
