package org.vaadin.hene.flexibleoptiongroup;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Henri Kerola / Vaadin Ltd
 * 
 */
@Title("FlexibleOptionGroup")
@Theme("valo")
@PreserveOnRefresh
public class FlexibleOptionGroupUI extends UI {
	
	@WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = FlexibleOptionGroupUI.class, widgetset = "org.vaadin.hene.flexibleoptiongroup.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

	@Override
	protected void init(VaadinRequest request) {

		Navigator navigator = new Navigator(this, this);
//		navigator.addView("", new DefaultView());
		navigator.addView("flexibleradiobuttongroup", new FlexibleRadioButtonGroupView());
		navigator.addView("flexiblecheckboxgroup", new FlexibleCheckBoxGroupView());

		navigator.navigateTo("flexibleradiobuttongroup");

		if (false) {
			VerticalLayout layout = new VerticalLayout();

			layout.addComponent(new Label("Hello"));

			RadioButtonGroup<String> radioButtonGroup = new RadioButtonGroup<>("My RadioButtonGroup");

			radioButtonGroup.setItemCaptionGenerator(s -> s + "!");

			radioButtonGroup.setItems("1", "3");

			//			layout.addComponent(radioButtonGroup);

			FlexibleRadioButtonGroup<String> flexibleRadioButtonGroup = new FlexibleRadioButtonGroup<>();
			flexibleRadioButtonGroup.setItemCaptionGenerator(s -> s + "!");
			flexibleRadioButtonGroup.setItems("1", "3");

			layout.addComponent(flexibleRadioButtonGroup.getFlexibleOptionGroupItem("1"));
			layout.addComponent(flexibleRadioButtonGroup.getFlexibleOptionGroupItem("2"));

			layout.addComponent(new Label("Moi"));

			setContent(layout);
			return;
		}
	}
}
