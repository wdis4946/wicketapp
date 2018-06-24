package my.wicket.app.validators;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class PasswordValidator implements IValidator {

	@Override
	public void validate(IValidatable validatable) {
		final String password = validatable.getValue().toString();
		if(!password.matches("(.*\\d.*){1}")) {
			error(validatable, "You need to have at least one numeral in your password.");
			return;
		} else if(!password.matches(".*[a-z].*")) {
			error(validatable, "You need to have at least one lower letter in your password.");
			return;
		} else if(password.length() < 7) {
			error(validatable, "You need to have more than 7 character in your password.");
			return;
		}
	}

	public void error(IValidatable validatable, String message) {
	      ValidationError error = new ValidationError();
	      error.setMessage(message);
	      validatable.error(error);
	}
}
