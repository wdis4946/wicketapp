package my.wicket.app.pages;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import my.wicket.app.MySession;

public abstract class BasePage extends WebPage {

	public BasePage(final PageParameters parameters) {}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		addBaseComponents();
	}

	private void addBaseComponents() {
		add(new Label("pageTitle", getTitle()));
		add(createBrand());
		add(createSignInAndSignUp());
		add(createSignOut());
	}

	private Link createBrand() {
		return new Link("brand") {
			@Override
			public void onClick() {
				setResponsePage(new TopPage(new PageParameters()));
			}
		};
	}

	private WebMarkupContainer createSignInAndSignUp() {
		WebMarkupContainer signLinks = new WebMarkupContainer("signInAndSignUp") {
			@Override
			public void onConfigure() {
				if (!MySession.get().isSignedIn()) {
					setVisible(true);
				} else {
					setVisible(false);
				}
			}
		};
		signLinks.setRenderBodyOnly(true);

		signLinks.add(new Link("signIn") {
			@Override
			public void onClick() {
				setResponsePage(new SignInPage(new PageParameters()));
			}
		});

		signLinks.add(new Link("signUp") {
			@Override
			public void onClick() {
				setResponsePage(new SignUpPage(new PageParameters()));
			}
		});

		return signLinks;
	}

	private Link createSignOut() {
		return new Link("signOut") {
			@Override
			public void onClick() {
				MySession.get().signOut();
				setResponsePage(new TopPage(new PageParameters()));
			}

			@Override
			public void onConfigure() {
				if (MySession.get().isSignedIn()) {
					setVisible(true);
				} else {
					setVisible(false);
				}
			}
		};
	}

	abstract public String getTitle();
}