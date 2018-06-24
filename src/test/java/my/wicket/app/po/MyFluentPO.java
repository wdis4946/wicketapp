package my.wicket.app.po;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;

public class MyFluentPO extends FluentPage {

	public String getWicketComponentXpath(String wicketId) {
		return "//*[@*[local-name()='wicket:id']='" + wicketId + "']";
	}

	public By byWicketId(String wicketId) {
		return By.xpath(getWicketComponentXpath(wicketId));
	}

	public FluentWebElement findFeedBackMessageByWicketId(String wicketId){
		return el(By.xpath(getWicketComponentXpath(wicketId) + getWicketComponentXpath("message")));

	}

}