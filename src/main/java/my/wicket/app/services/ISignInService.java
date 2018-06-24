package my.wicket.app.services;

import com.google.inject.ImplementedBy;

import my.wicket.app.entities.Account;

@ImplementedBy(SignInService.class)
public interface ISignInService {

	public Account findByEmail(String email);

}
