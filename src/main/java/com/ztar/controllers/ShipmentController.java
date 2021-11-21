package com.ztar.controllers;

import java.util.HashMap;

import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ztar.config.Translator;
import com.ztar.model.dto.ShipmentDTO;
import com.ztar.model.services.ShipmentService;

/**
 * @author Waseem Zawaideh
 */
@RestController
@RequestMapping(value = "/ztar-shipping",
	produces = MediaType.APPLICATION_JSON_VALUE )
public class ShipmentController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/** Shipment service */
	private ShipmentService shipService;

	@Autowired
	public ShipmentController(ShipmentService service) {
		this.shipService = service;
	}

	/**
	 * Welcoming message
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Object> welcome() {
		String welcomeMsg = Translator.toLocale("ztar.greeting", null);
		logger.info("Welcome message: " + welcomeMsg);
		return generateResponse(welcomeMsg, HttpStatus.OK, null);
	}

	/**
	 * Create (Place) a shipment order
	 * 
	 * @param shipment
	 * @return
	 */
	@PostMapping(value = "create-shipment", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createShipment(@Valid @RequestBody ShipmentDTO shipment) {

		logger.info("Initial validation passed");
		shipService.createShipment(shipment);
		logger.info("Shipment created successfully");
		return generateResponse("Shipment has been placed successfully", HttpStatus.OK, shipment);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleGeneralException(Exception e) {
		return generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return generateResponse(errors.toString(), HttpStatus.BAD_REQUEST, null);
	}

	/**
	 * This method is used to generate a proper general response message
	 * 
	 * @param message
	 * @param status
	 * @param responseObj
	 * @return ResponseEntity
	 */
	private ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("status", status.value());

		if (responseObj != null) {
			map.put("shipment-order", responseObj);
		}

		return new ResponseEntity<Object>(map, status);
	}
}
