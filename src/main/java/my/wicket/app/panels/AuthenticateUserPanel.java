package my.wicket.app.panels;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import my.wicket.app.MySession;
import my.wicket.app.entities.Account;
import my.wicket.app.pages.SignUpPage;
import my.wicket.app.pages.TopPage;

public class AuthenticateUserPanel extends Panel {

	private Account account = new Account();
	private RequiredTextField<String> email;
	private PasswordTextField password;

	public AuthenticateUserPanel(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(createForm());
	}


	private Form createForm() {
		Form form = new Form("authenticateUserForm");
		form.add(new InvalidFeedbackPanel("feedbackPanel").setMaxMessages(1));
		form.add(email = new RequiredTextField<String>("email",
				new PropertyModel<String>(this, "account.email")));
		form.add(password = new PasswordTextField("password",
				new PropertyModel<String>(this, "account.password")));
		form.add(createSubmitButton(form));
		form.add(new Link("signUp") {
			@Override
			public void onClick() {
				setResponsePage(new SignUpPage(new PageParameters()));
			}
		});
		return form;
	}

	private AjaxButton createSubmitButton(Form form) {
		return new AjaxButton("submit", form) {
			@Override
			public void onSubmit(AjaxRequestTarget target, Form createAccountForm) {
				if (!MySession.get().signIn(account.getEmail(), account.getPassword())) {
					error("Incorrect email or password.");
					target.add(createAccountForm);
					return;
				}
				setResponsePage(new TopPage(new PageParameters()));
			}

			@Override
			public void onError(AjaxRequestTarget target, Form createAccountForm) {
				target.add(createAccountForm);
			}
		};
	}

}
