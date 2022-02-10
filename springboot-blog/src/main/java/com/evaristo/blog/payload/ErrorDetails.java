/**
 * 
 */
package com.evaristo.blog.payload;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author evari
 *
 */
@ApiModel(description = "Error Detail Model Information")
public class ErrorDetails {

	@ApiModelProperty(value = "Error Detail Local Date Time")
	private LocalDateTime localDateTime;
	
	@ApiModelProperty(value = "Error Detail Message")
	private String message;
	
	@ApiModelProperty(value = "Error Detail details")
	private String details;
	
	public ErrorDetails(LocalDateTime localDateTime, String message, String details) {
		super();
		this.localDateTime = localDateTime;
		this.message = message;
		this.details = details;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}	
	
	
}
