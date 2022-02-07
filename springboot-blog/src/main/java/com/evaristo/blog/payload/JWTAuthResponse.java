/**
 * 
 */
package com.evaristo.blog.payload;

import lombok.Getter;
import lombok.Setter;

/**
 * @author evari
 *
 */

@Getter
@Setter
public class JWTAuthResponse {
	
	private String accessToken;
	private String tokenType = "Bearer";
	
	public JWTAuthResponse(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

}
