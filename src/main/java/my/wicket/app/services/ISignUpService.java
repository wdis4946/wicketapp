package my.wicket.app.services;

import com.google.inject.ImplementedBy;

import my.wicket.app.entities.Account;

@ImplementedBy(SignUpService.class)
public interface ISignUpService {

	public void save(Account account);

}
