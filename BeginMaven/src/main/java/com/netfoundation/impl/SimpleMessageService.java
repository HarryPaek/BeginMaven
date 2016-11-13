/**
 * 
 */
package com.netfoundation.impl;

import com.netfoundation.abstracts.IMessageService;

/**
 * @author HomeUser
 *
 */
public class SimpleMessageService implements IMessageService {

	/* (non-Javadoc)
	 * @see com.netfoundation.abstracts.IMessageService#getMessage()
	 */
	public String getMessage() {
		return "Hello World!";
	}
}
