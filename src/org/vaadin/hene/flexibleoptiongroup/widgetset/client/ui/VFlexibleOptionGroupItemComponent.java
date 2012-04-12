package org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui;

import static org.vaadin.hene.flexibleoptiongroup.widgetset.client.VFlexibleOptionGroupItemComponentConstants.*;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SimpleRadioButton;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.UIDL;

/**
 * @author Henri Kerola / Vaadin Ltd
 * 
 */
public class VFlexibleOptionGroupItemComponent extends Composite implements
		Paintable, ClickHandler {

	public static final String CLASSNAME = "v-flexibleoptiongroupitemcomponent";

	protected String paintableId;
	ApplicationConnection client;

	protected boolean immediate = false;

	protected SimplePanel panel;
	protected SimpleCheckBox checkbox;

	public VFlexibleOptionGroupItemComponent() {
		panel = new SimplePanel();
		initWidget(panel);

		setStyleName(CLASSNAME);
	}

	public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
		if (client.updateComponent(this, uidl, false)) {
			return;
		}
		this.client = client;
		paintableId = uidl.getId();

		immediate = uidl.getBooleanAttribute(VAADIN_ATTR_IMMEDIATE);
		String ownerId = uidl.getStringAttribute(ATTR_OWNER);

		if (uidl.hasAttribute(ATTR_MULTISELECT)) {
			checkbox = new SimpleCheckBox();
		} else {
			checkbox = new SimpleRadioButton(ownerId);
		}
		checkbox.setChecked(uidl.getBooleanVariable(VAR_SELECTED));
		checkbox.setEnabled(!uidl.hasAttribute(VAADIN_ATTR_DISABLED)
				&& !uidl.hasAttribute(VAADIN_ATTR_READONLY));

		checkbox.addClickHandler(this);
		panel.setWidget(checkbox);
	}

	public void onClick(ClickEvent event) {
		updateChecked(checkbox.isChecked());
	}

	protected void updateChecked(boolean checked) {
		client.updateVariable(paintableId, VAR_SELECTED, checked, immediate);
	}
}
