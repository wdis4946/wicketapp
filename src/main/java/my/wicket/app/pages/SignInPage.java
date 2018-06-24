package my.wicket.app.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import my.wicket.app.panels.AuthenticateUserPanel;

public class SignInPage extends BasePage {

	public SignInPage(PageParameters parameters) {
		super(parameters);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new AuthenticateUserPanel("authenticateUserPanel"));
	}

	@Override
	public String getTitle() {
		return "SignIn";
	}

}
