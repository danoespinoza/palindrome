package cl.danoespinoza.palindrome.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "You must include a search text with a minimum of 4 characters")
public class IncompleteSearchTextException extends RuntimeException {

	private static final long serialVersionUID = -5553410075039423930L;

}
