package my.wicket.app.test;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.fluentlenium.core.annotation.Page;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import my.wicket.app.po.SignUpPO;
import my.wicket.app.po.TopPagePO;

@RunWith(Enclosed.class)
public class SignUpPageTest {

	@RunWith(Parameterized.class)
	public static class PasswordValidation extends MyFluentTest {

		@Page
		SignUpPO signUpPO;

		@Page
		TopPagePO topPagePO;

		private String name;
		private String email;
		private String password;
		private String message;

		@Parameters(name = "message: {3}, pass: {2}")
		public static Iterable<Object[]> getParameters() {
			return Arrays.asList(new Object[][] {
				{ "name", "email", "", "'Password' field is required" },
				{ "name", "email", "password", "You need to have at least one numeral in your password." },
				{ "name", "email", "999", "You need to have at least one lower letter in your password." },
				{ "name", "email", "hoge42", "You need to have more than 7 character in your password." }
			});
		}

		public PasswordValidation(String name, String email, String password, String message) {
			this.name = name;
			this.email = email;
			this.password = password;
			this.message = message;
		}

		@Before
		public void setUp() {
			setScreenshotPath("/Users/k_chan/Desktop/Screenshots/");
		}

		@Test
		public void requireName() {
			goTo("http://localhost:8080/");
			topPagePO.goToSignInPage();
			signUpPO.setCredential(name, email, password);
			signUpPO.setAgreeByModal(true);
			signUpPO.submit();
			await().atMost(2, TimeUnit.SECONDS)
				.until(signUpPO.findFeedBackMessageByWicketId("passwordFeedbackPanel")).present();
			takeScreenShot(getScreenshotFileName());
			assertThat(signUpPO.getMessageOnPasswordFeedbackPanel()).isEqualTo(message);
		}
	}

}
