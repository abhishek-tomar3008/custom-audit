package com.jlw.audit;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jlw.bank.Bank;
import com.jlw.bank.BankService;



@Service
public class AuditService {
	
	@Autowired
	AuditTrailRepository auditTrailRepository;

	
	
	//this method will make a record only when any record will gonna created
	public void auditTrailOfCreation(Object obj ) {
		
			                        
			                         
		
		String actionType="create";
		String creater=SecurityContextHolder.getContext().getAuthentication().getName();
		LocalDateTime creadeTime=LocalDateTime.now();
		
		
 String firstToString=obj.toString();
    	  
    	 
    	 String className="";
    	 
    	   HashMap<String, String> obj1=new HashMap<>();
    	 String id="";
    	 boolean flag=true;
    	   
    	   
    	     String name="";
    	     
    	     for(int i=0; i<firstToString.length(); i++) {
    	    	 if(!flag)break;
    	    	 
    	    	 //System.out.println(a.charAt(i));
    	    	 if(firstToString.charAt(i)=='(') {
    	    		 className=name;
    	    		 
    	    		 name="";
    	    		 //System.out.println(className);
    	    	 }
    	    	 else if(firstToString.charAt(i)=='=') {
    	    		 //System.out.println(name);
    	    		 String val="";
    	    		for(int j=i+1; j<firstToString.length(); j++) {
    	    			if(firstToString.charAt(j)==','||firstToString.charAt(j)==')') {
    	    				//System.out.println(val);
    	    				if(flag)id=val;flag=false;
    	    				
    	    				
    	    				obj1.put(name, val);
    	    				name="";
    	    				i=j;
    	    				break;
    	    			}
    	    			else {
    	    				val+=firstToString.charAt(j);
    	    			}
    	    		}
    	    	 }
    	    	 
    	    	 else {
    	    		 name+=firstToString.charAt(i);
    	    		
    	    	 }
    	    	 

    	     }
		
		
		
	AuditTrail auditTrail=	new AuditTrail( creater, creadeTime, className, id ,actionType);
		auditTrailRepository.save(auditTrail);
		
		
		
	}
	
		
	public List<AuditTrail> getAllAuditRecords(){
		
		return auditTrailRepository.findAll();
		
	}
	
	
	
     public List<AuditTrail> getAllAuditRecordswithcreater(String creater){
		
		return auditTrailRepository.findByCreatedBy(creater);
		
	 }
     
     
     public void auditForDelete(Object obj) {
    	 
    	 String actionType="delete";
 		String creater=SecurityContextHolder.getContext().getAuthentication().getName();
 		LocalDateTime creadeTime=LocalDateTime.now();
        String firstToString=obj.toString();
    	  
    	 
    	 String className="";
    	 
    	   HashMap<String, String> obj1=new HashMap<>();
    	 String id="";
    	 boolean flag=true;
    	   
    	   
    	     String name="";
    	     
    	     for(int i=0; i<firstToString.length(); i++) {
    	    	 if(!flag)break;
    	    	 
    	    	 
    	    	 //System.out.println(a.charAt(i));
    	    	 if(firstToString.charAt(i)=='(') {
    	    		 className=name;
    	    		 
    	    		 name="";
    	    		 //System.out.println(className);
    	    	 }
    	    	 else if(firstToString.charAt(i)=='=') {
    	    		 //System.out.println(name);
    	    		 String val="";
    	    		for(int j=i+1; j<firstToString.length(); j++) {
    	    			if(firstToString.charAt(j)==','||firstToString.charAt(j)==')') {
    	    				//System.out.println(val);
    	    				if(flag)id=val;flag=false;
    	    				
    	    				
    	    				obj1.put(name, val);
    	    				name="";
    	    				i=j;
    	    				break;
    	    			}
    	    			else {
    	    				val+=firstToString.charAt(j);
    	    			}
    	    		}
    	    	 }
    	    	 
    	    	 else {
    	    		 name+=firstToString.charAt(i);
    	    		
    	    	 }
 		
 		
    	     }
    	     auditTrailRepository.save(new AuditTrail(creater, LocalDateTime.now(),"all field","all field deleted", className, id,actionType));
    	 
     }
     
     public void auditForUpdate(Object first,Object second) {
    	 //System.out.println("+++++++++++++++++++++++++");
    	
    	 
    	 String actionType="update";
 		String creator=SecurityContextHolder.getContext().getAuthentication().getName();
 		LocalDateTime creadeTime=LocalDateTime.now();
    	 
    	 String firstToString=first.toString();
    	 String secondToString=second.toString();
    	  
    	 
    	 String className="";
    	 String id="";
    	 boolean flag=true;
    	 
    	 
    	   HashMap<String, String> obj1=new HashMap<>();
    	   HashMap<String, String> obj2=new HashMap<>();
    	   
    	   
    	     String name="";
    	     
    	     for(int i=0; i<firstToString.length(); i++) {
    	    	 
    	    	 
    	    	 //System.out.println(a.charAt(i));
    	    	 if(firstToString.charAt(i)=='(') {
    	    		 className=name;
    	    		 
    	    		 name="";
    	    		 //System.out.println(className);
    	    	 }
    	    	 else if(firstToString.charAt(i)=='=') {
    	    		 //System.out.println(name);
    	    		 String val="";
    	    		for(int j=i+1; j<firstToString.length(); j++) {
    	    			if(firstToString.charAt(j)==','||firstToString.charAt(j)==')') {
    	    				//System.out.println(val);
    	    				if(flag)id=val;flag=false;
    	    				obj1.put(name, val);
    	    				name="";
    	    				i=j;
    	    				break;
    	    			}
    	    			else {
    	    				val+=firstToString.charAt(j);
    	    			}
    	    		}
    	    	 }
    	    	 
    	    	 else {
    	    		 name+=firstToString.charAt(i);
    	    		
    	    	 }
    	    	 

    	     }
    	     System.out.println(obj1);
    	for(int i=0; i<secondToString.length(); i++) {
    	    	 
    	    	 
    	    	 //System.out.println(a.charAt(i));
    	    	 if(secondToString.charAt(i)=='(') {
    	    		 className=name;
    	    		 
    	    		 name="";
    	    		 //System.out.println(className);
    	    	 }
    	    	 else if(secondToString.charAt(i)=='=') {
    	    		 //System.out.println(name);
    	    		 String val="";
    	    		for(int j=i+1; j<secondToString.length(); j++) {
    	    			if(secondToString.charAt(j)==','||secondToString.charAt(j)==')') {
    	    				//System.out.println(val);
    	    				
    	    				obj2.put(name, val);
    	    				name="";
    	    				i=j;
    	    				break;
    	    			}
    	    			else {
    	    				val+=secondToString.charAt(j);
    	    			}
    	    		}
    	    	 }
    	    	 
    	    	 else {
    	    		 name+=secondToString.charAt(i);
    	    		
    	    	 }
    	    	 

    	     }
    
    	     System.out.println(obj2);
    	    Set<String>keys=obj2.keySet();
    	    
    	    String updatedValue=null;
    	    String updatedField=null;
    	    
    	    
    	    for(String key:keys) {
    	    	if(!obj1.get(key).equals(obj2.get(key))) {
    	    		
        	    	updatedField=key;
        	    	updatedValue=obj1.get(key)+" changes into "+obj2.get(key);
    	    	}
    	    	
    	    }
    	    
    	    auditTrailRepository.save(new AuditTrail(creator, LocalDateTime.now(),updatedField,updatedValue, className, id,actionType));
    	  

    	 }
     
  
}


    	 
    	 
 
