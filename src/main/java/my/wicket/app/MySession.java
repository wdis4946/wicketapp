package my.wicket.app;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.mindrot.jbcrypt.BCrypt;

import com.google.inject.Inject;

import my.wicket.app.entities.Account;
import my.wicket.app.services.SignInService;

public class MySession extends AuthenticatedWebSession {

	@Inject
	private SignInService signInService;
	private Account account;
	private Roles roles;

	public MySession(Request request) {
		super(request);
	}

	public boolean authenticate(String email, String password) {
		account = signInService.findByEmail(email);
		if (BCrypt.checkpw(password, account.getPassword())) {
			this.roles = new Roles(new String[] { Roles.ADMIN, Roles.USER });
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Roles getRoles() {
		return roles;
	}

	public static MySession get() {
		return (MySession) Session.get();
	}

	public Account getAccount() {
		return this.account;
	}

}
