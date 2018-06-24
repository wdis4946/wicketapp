package my.wicket.app.services;

import org.mindrot.jbcrypt.BCrypt;

import my.wicket.app.dao.AccountDao;
import my.wicket.app.entities.Account;

public class SignUpService implements ISignUpService {

	public void save(Account account) {
		account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt()));
		AccountDao accountDao = new AccountDao();
		accountDao.save(account);
	}

}
