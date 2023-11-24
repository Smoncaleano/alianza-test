package com.gml.alianza.alianza.core;


import com.gml.alianza.alianza.core.dto.GenericResponseDTO;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;



@ControllerAdvice
@Log4j2
public class ExceptionGlobalResponse  {

	GenericResponseDTO result;

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<GenericResponseDTO> runtimeException(RuntimeException e) {
		result = new GenericResponseDTO("Error" , false, "[RuntimeException Response] - Exception: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({Exception.class})
	public ResponseEntity<GenericResponseDTO> exception(Exception e) {
		result = new GenericResponseDTO("Error" , false, "[Exception Response] - Exception: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@ExceptionHandler(HttpServerErrorException.class)
	protected ResponseEntity<Object> handleHttpServer(HttpServerErrorException ex) {

		result = new GenericResponseDTO("Error" , false, "[Exception Response] - Exception: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);

	}


	@ExceptionHandler(ResponseStatusException.class)
	protected ResponseEntity<Object> handleHttpServer(ResponseStatusException ex) {
		log.info("ERROR");
		result = new GenericResponseDTO("Error" , false, ex.getReason(), (HttpStatus) ex.getStatusCode());
		return new ResponseEntity<>(null,  ex.getStatusCode());

	}



	

}
