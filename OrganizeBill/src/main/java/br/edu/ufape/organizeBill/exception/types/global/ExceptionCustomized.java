package br.edu.ufape.organizeBill.exception.types.global;

import lombok.*;

@SuppressWarnings("serial")
@Getter @Setter
public class ExceptionCustomized extends RuntimeException{
	 private String code;
	 private String message;
	 
	 public ExceptionCustomized(String code, String message) {
	        super();
	        this.code = code;
	        this.message = message;
	    }
}