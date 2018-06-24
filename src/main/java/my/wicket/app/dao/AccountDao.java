package my.wicket.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import my.wicket.app.entities.Account;

public class AccountDao extends DaoSupport {

	public void save(Account account) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.save(account);
		transaction.commit();
		session.close();
	}

	public List findByEmail(String email) {
		Session session = getSession();
		Query query = session.getNamedQuery("findByEmail");
		query.setParameter("email", email);
		List<Account> list = query.list();
		session.close();
		return list;
	}
}
