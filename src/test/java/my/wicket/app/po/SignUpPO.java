package my.wicket.app.po;

import java.util.concurrent.TimeUnit;

public class SignUpPO extends MyFluentPO {

	public void setName(String name) {
		el(byWicketId("name")).fill().with(name);
	}

	public void setEmail(String email) {
		el(byWicketId("email")).fill().with(email);
	}

	public void setPassword(String password) {
		el(byWicketId("password")).fill().with(password);
	}

	public void setAgree(boolean agree) {
		if (el(byWicketId("agree")).selected() != agree)
			el(byWicketId("agree")).click();
	}

	public void setAgreeByModal(boolean agree) {
		el(byWicketId("terms")).click();
		switchTo($("iframe"));
		await().atMost(1, TimeUnit.SECONDS).until(el(byWicketId("agree"))).enabled();
		await().atMost(1, TimeUnit.SECONDS).until(el(byWicketId("agree"))).enabled();
		if (agree)
			el(byWicketId("agree")).click();
		else
			el(byWicketId("disagree")).click();
		switchToDefault();
	}

	public void submit() {
		el(byWicketId("submit")).submit();
	}

	public void setCredential(String name, String email, String password) {
		setName(name);
		setEmail(email);
		setPassword(password);
	}

	public String getMessageOnNameFeedbackPanel() {
		return findFeedBackMessageByWicketId("nameFeedbackPanel").text();
	}

	public String getMessageOnEmailFeedbackPanel() {
		return findFeedBackMessageByWicketId("emailFeedbackPanel").text();
	}

	public String getMessageOnPasswordFeedbackPanel() {
		return findFeedBackMessageByWicketId("passwordFeedbackPanel").text();
	}

}
