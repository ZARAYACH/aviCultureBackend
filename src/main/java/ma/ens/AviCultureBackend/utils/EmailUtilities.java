package ma.ens.AviCultureBackend.utils;

import org.apache.commons.validator.routines.EmailValidator;

public class EmailUtilities {

	private boolean isValidEmail(String email) {
		return EmailValidator.getInstance().isValid(email);
	}
}
