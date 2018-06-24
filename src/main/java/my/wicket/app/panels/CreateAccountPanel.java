package my.wicket.app.panels;

import org.apache.wicket.Page;
import org.apache.wicket.PageReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.inject.Inject;

import my.wicket.app.entities.Account;
import my.wicket.app.modals.TermsModal;
import my.wicket.app.modifiers.ErrorFormComponentAttributeModifier;
import my.wicket.app.pages.SignInPage;
import my.wicket.app.services.ISignUpService;
import my.wicket.app.validators.PasswordValidator;

public class CreateAccountPanel extends Panel {

	/**
	 * Services
	 */
	@Inject
	ISignUpService signUpService;

	/**
	 * Entities
	 */
	private Account account = new Account();

	/**
	 * Wicket Components
	 */
	private RequiredTextField<String> name;
	private RequiredTextField<String> email;
	private PasswordTextField password;
	private ModalWindow termsModalWindow;
	private PageReference termsModalPageReference;
	private CheckBox agree;
	private AjaxButton submit;

	public CreateAccountPanel(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(createForm());
	}

	private Form createForm() {
		Form form = new Form("createAccountForm");
		form.add(name = createNameField());
		form.add(email = createEmailField());
		form.add(password = createPasswordField());
		form.add(new InvalidFeedbackPanel("nameFeedbackPanel", name));
		form.add(new InvalidFeedbackPanel("emailFeedbackPanel", email));
		form.add(new InvalidFeedbackPanel("passwordFeedbackPanel", password));
		form.add(termsModalWindow = createTermsModal());
		form.add(agree = createAgree());
		form.add(createTermsLink());
		form.add(submit = createSubmitButton(form));
		return form;
	}

	private RequiredTextField<String> createNameField() {
		RequiredTextField<String> rtf = new RequiredTextField<String>("name",
				new PropertyModel<String>(this, "account.name"));
		rtf.add(new ErrorFormComponentAttributeModifier(rtf));
		rtf.setLabel(new Model<String>("Username"));
		return rtf;
	}

	private RequiredTextField<String> createEmailField() {
		RequiredTextField<String> rtf = new RequiredTextField<String>("email",
				new PropertyModel<String>(this, "account.email"));
		rtf.add(new ErrorFormComponentAttributeModifier(rtf));
		rtf.setLabel(new Model<String>("Email"));
		return rtf;
	}

	private PasswordTextField createPasswordField() {
		PasswordTextField ptf = new PasswordTextField("password", new PropertyModel<String>(this, "account.password"));
		ptf.add(new PasswordValidator());
		ptf.add(new ErrorFormComponentAttributeModifier(ptf));
		ptf.setLabel(new Model<String>("Password"));
		return ptf;
	}

	private AjaxButton createSubmitButton(Form form) {
		return new AjaxButton("submit", form) {

			@Override
			public void onConfigure() {
				this.setEnabled(agree.getModelObject());
			}

			@Override
			public void onSubmit(AjaxRequestTarget target, Form createAccountForm) {
				signUpService.save(account);
				setResponsePage(new SignInPage(new PageParameters()));
			}

			@Override
			public void onError(AjaxRequestTarget target, Form createAccountForm) {
				agree.setModel(new Model<Boolean>(false));
				target.add(createAccountForm);
			}
		};
	}

	private AjaxCheckBox createAgree() {
		AjaxCheckBox chk = new AjaxCheckBox("agree", new Model<Boolean>(false)) {
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				target.add(submit);
			}
		};
		chk.setOutputMarkupId(true);
		return chk;
	}

	private ModalWindow createTermsModal() {
		ModalWindow modalWindow = new ModalWindow("termsModal");
		modalWindow.setInitialHeight(250);
		modalWindow.setPageCreator(new ModalWindow.PageCreator() {

			@Override
			public Page createPage() {
				TermsModal termsModal = new TermsModal(agree.getModelObject(), modalWindow);
				termsModalPageReference = termsModal.getPageReference();
				return termsModal;
			}
		});
		modalWindow.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {

			@Override
			public void onClose(AjaxRequestTarget target) {
				TermsModal modal = (TermsModal)termsModalPageReference.getPage();
				agree.setModel(new Model<Boolean>(modal.isAgreed()));
				target.add(agree);
				target.add(submit);
			}
		});
		return modalWindow;
	}

	private AjaxLink createTermsLink() {
		return new AjaxLink("terms") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				termsModalWindow.show(target);
			}
		};
	}
}
