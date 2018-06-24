package my.wicket.app.test;

import java.time.LocalDateTime;

import org.fluentlenium.adapter.junit.FluentTest;

public class MyFluentTest extends FluentTest {

	public String getScreenshotFileName() {
		return  LocalDateTime.now() + ".PNG";
	}

}
