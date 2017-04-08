package org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;

/**
 * @author Henri Kerola / Vaadin Ltd
 * 
 */
public class VFlexibleCheckBoxGroupItemComponent extends Composite
		implements HasValueChangeHandlers<Boolean> {

	private CheckBox checkbox;

	public VFlexibleCheckBoxGroupItemComponent() {
		checkbox = new CheckBox();
		initWidget(checkbox);

		setStyleName(Constants.CLASSNAME_ITEM_COMPONENT);
		addStyleName(Constants.CLASSNAME_OPTION);
		addStyleName("v-checkbox");
	}

	public void setEnabled(boolean enabled) {
		checkbox.setEnabled(enabled);
	}

	public void setSelected(boolean selected) {
		checkbox.setValue(selected);
	}

	@Override
	public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<Boolean> handler) {
		return checkbox.addValueChangeHandler(handler);
	}
}
