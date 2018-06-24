package my.wicket.app.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;

public class TopPage extends BasePage {

	public TopPage(PageParameters parameters) {
		super(parameters);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
	}

	@Override
	public String getTitle() {
		return "TopPage";
	}
}
