package my.wicket.app.modals;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;

public class TermsModal extends WebPage {

	private ModalWindow modal;
	private boolean isAgreed;

	public TermsModal(final boolean isAgreed, final ModalWindow modal) {
		this.isAgreed = isAgreed;
		this.modal = modal;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(createForm());
	}

	private Form createForm() {
		Form form = new Form("form");
		form.add(createAgree());
		form.add(createDisagree());
		return form;
	}

	private AjaxButton createAgree() {
		AjaxButton button = new AjaxButton("agree") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				isAgreed = true;
				modal.close(target);
			}
		};
		return button;
	}

	private AjaxButton createDisagree() {
		AjaxButton button = new AjaxButton("disagree") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				isAgreed = false;
				modal.close(target);
			}
		};
		return button;
	}

	public boolean isAgreed() {
		return isAgreed;
	}
}
