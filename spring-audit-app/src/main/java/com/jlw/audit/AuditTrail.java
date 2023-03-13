package com.jlw.audit;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class AuditTrail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	

	
	
	  private String createdBy;

	 
	  private LocalDateTime createdAt;
       private String modifiedBy;
	   private LocalDateTime modifiedAt;
	   
	   private String modifiedfield;
		private String affectedClassName;
		
		private String affectedRecordId;
		
	    private String modifiedValue;
		
		private String ActionType;
		
		
		

		public AuditTrail(String createdBy, LocalDateTime createdAt, String affectedRecordId,
				 String actionType, String affectedClassName) {
			super();
			this.createdBy = createdBy;
			this.createdAt = createdAt;
			this.affectedClassName = affectedClassName;
			this.affectedRecordId = affectedRecordId;
		
			ActionType = actionType;
		}
		
		

		public AuditTrail(String modifiedBy, LocalDateTime modifiedAt, String modifiedfield,String modifiedValue, String affectedClassName,
				String affectedRecordId, String actionType) {
			super();
			this.modifiedBy = modifiedBy;
			this.modifiedAt = modifiedAt;
			this.modifiedfield = modifiedfield;
			this.affectedClassName = affectedClassName;
			this.affectedRecordId = affectedRecordId;
			this.modifiedValue=modifiedValue;
		
			ActionType = actionType;
		}
      


		
      
		
		

}
