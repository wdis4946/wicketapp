package my.wicket.app.services;

import my.wicket.app.dao.AccountDao;
import my.wicket.app.entities.Account;

public class SignInService implements ISignInService {

	public Account findByEmail(String email) {
		AccountDao accountDao = new AccountDao();
		return (Account) accountDao.findByEmail(email).get(0);
	}

}
