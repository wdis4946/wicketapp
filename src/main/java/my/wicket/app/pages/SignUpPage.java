package my.wicket.app.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import my.wicket.app.panels.CreateAccountPanel;

public class SignUpPage extends BasePage {

	public SignUpPage(PageParameters parameters) {
		super(parameters);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new CreateAccountPanel("createAccountPanel"));
	}

	@Override
	public String getTitle() {
		return "SignUp";
	}

}
