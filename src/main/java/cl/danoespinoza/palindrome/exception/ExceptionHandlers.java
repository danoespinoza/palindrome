package cl.danoespinoza.palindrome.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlers {

	private Logger log = LoggerFactory.getLogger(ExceptionHandlers.class);
	
	@ExceptionHandler(IncompleteSearchTextException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorResponse handleContactMissingInformationException(final IncompleteSearchTextException ex) {
		log.error("search bad information thrown");
        return new ErrorResponse("BAD_INFORMATION", "You must include a search text with a minimum of 4 characters");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ErrorResponse handleThrowable(final Throwable ex) {
        log.error("Unexpected Error", ex);
        return new ErrorResponse("INTERNAL_SERVER_ERROR", "An unexpected internal server error occurred");
    }
}
