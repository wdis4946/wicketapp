package my.wicket.app.modifiers;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.AbstractReadOnlyModel;

public class ErrorFormComponentAttributeModifier extends AttributeModifier {

	public ErrorFormComponentAttributeModifier(final FormComponent formComponent) {
		super("class", new AbstractReadOnlyModel() {
			public String getObject() {
				return (formComponent.isValid()) ? "form-control" : "form-control is-invalid";
			};

		});
	}

}
