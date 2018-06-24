package my.wicket.app.po;

public class TopPagePO extends MyFluentPO {

	public void goToSignInPage() {
		$(byWicketId("signUp")).click();
	}

}
