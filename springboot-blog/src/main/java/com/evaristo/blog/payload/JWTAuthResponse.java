/**
 * 
 */
package com.evaristo.blog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author evari
 *
 */
@ApiModel(description = "JWT Auth Response Model Information")
@Getter
@Setter
public class JWTAuthResponse {
	
	@ApiModelProperty(value = "JWT Auth Response Access Token")
	private String accessToken;
	
	@ApiModelProperty(value = "JWT Auth Response Bearer")
	private String tokenType = "Bearer";
	
	public JWTAuthResponse(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

}
