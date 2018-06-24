package my.wicket.app;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;

import my.wicket.app.pages.SignInPage;
import my.wicket.app.pages.TopPage;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 *
 * @see my.wicket.app.Start#main(String[])
 */
public class WicketApplication extends AuthenticatedWebApplication {
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return TopPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();
		getComponentInstantiationListeners().add(new GuiceComponentInjector(this));
		getDebugSettings().setAjaxDebugModeEnabled(false);
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return MySession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return SignInPage.class;
	}

	/*
	 * @Override protected void onUnauthorizedPage(Page page) { throw new
	 * RestartResponseAtInterceptPageException(UnAuthorizedPage.class); }
	 */
}
