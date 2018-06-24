package my.wicket.app.panels;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class InvalidFeedbackPanel extends FeedbackPanel {

	public InvalidFeedbackPanel(String id) {
		super(id);
	}

	public InvalidFeedbackPanel(String id, Component component) {
		super(id, new ComponentFeedbackMessageFilter(component));
		setOutputMarkupId(true);
	}

	@Override
	protected void onConfigure() {
		if (getDefaultModel() instanceof FormComponent) {
			if (((FormComponent) getDefaultModel()).isValid()) {
				this.setVisible(true);
			} else {
				this.setVisible(false);
			}
		}
	}

	@Override
	protected String getCSSClass(final FeedbackMessage message) {
		String cssClass;
		switch (message.getLevel()) {
		case FeedbackMessage.UNDEFINED:
			cssClass = getString(FeedbackMessage.UNDEFINED_CSS_CLASS_KEY);
			break;
		case FeedbackMessage.ERROR:
			cssClass = "my-invalid-feedback";
			break;
		case FeedbackMessage.FATAL:
			cssClass = "my-invalid-feedback";
			break;
		default:
			cssClass = "feedbackPanel" + message.getLevelAsString();
		}
		return cssClass;
	}

}
