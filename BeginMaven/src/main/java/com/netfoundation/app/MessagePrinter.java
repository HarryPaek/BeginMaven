/**
 * 
 */
package com.netfoundation.app;

import org.springframework.beans.factory.annotation.Autowired;
import com.netfoundation.abstracts.IMessageService;

/**
 * @author HomeUser
 *
 */
public class MessagePrinter {
    final private IMessageService service;
	
	@Autowired
	public MessagePrinter(IMessageService service)
	{
		this.service = service;
	}
	
	public void printMessage()
	{
		System.out.println(this.service.getMessage());
	}

}
