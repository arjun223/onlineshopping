package com.arjun.onlineshopping.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExecptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView NoHandlerFoundException() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitel", "the page is not constructed !");
		mv.addObject("errorDescription", "the page you are looking for is not available !");
		mv.addObject("Titel", "404 error page !");

		return mv;

	}
	
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView ProductNotFoundException() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitel", "Product is not available !");
		mv.addObject("errorDescription", "the Product you are looking for is not available right now  !");
		mv.addObject("Titel", " Product Unavailable !");

		return mv;

	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView HandlerException( Exception Ex) {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitel", "contact your Admin !");
		mv.addObject("errorDescription", Ex.toString());
		mv.addObject("Titel", " error !");

		return mv;

	}
}
