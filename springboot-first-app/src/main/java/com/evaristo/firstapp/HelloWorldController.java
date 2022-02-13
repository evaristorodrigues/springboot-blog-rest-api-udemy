/**
 * 
 */
package com.evaristo.firstapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author evari
 *
 */

@RestController
public class HelloWorldController {
	
	@GetMapping("/hello-world")
	public String helloWorld() {		
		return "helloWorld";
	}

}
