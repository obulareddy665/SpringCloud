package com.ecommerce.admin.exceptionhandler;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CutomEr implements ErrorController {
	  
	
	  public String getErrorPath() {
		    return "/error";
		  }
	
}
