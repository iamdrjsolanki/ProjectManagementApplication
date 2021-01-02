package com.iamdrjsolanki.pma.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppErrorController implements ErrorController {
	
	@GetMapping("/error")
	public String handleError(HttpServletRequest req) {
		Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if(status!=null) {
			Integer statusCode = Integer.valueOf(status.toString());
			if(statusCode == HttpStatus.NOT_FOUND.value())
				return "errorpages/error-404";
			else if(statusCode == HttpStatus.FORBIDDEN.value())
				return "errorpages/error-403";
			else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value())
				return "errorpages/error-500";
		}
		return "errorpages/error";
	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}

}
