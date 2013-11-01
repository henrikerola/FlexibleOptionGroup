package org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SimpleRadioButton;

/**
 * @author Henri Kerola / Vaadin Ltd
 * 
 */
public class VFlexibleOptionGroupItemComponent extends Composite implements
		ClickHandler {

	public static final String CLASSNAME = "v-flexibleoptiongroupitemcomponent";

	protected String paintableId;

	protected SimplePanel panel;
	protected SimpleCheckBox checkbox;

	private String ownerId;
	private boolean multiSelect;

	private ComponentCheckedListener checkedListener;

	public VFlexibleOptionGroupItemComponent() {
		panel = new SimplePanel();
		initWidget(panel);

		setStyleName(CLASSNAME);
	}

	public void onClick(ClickEvent event) {
		updateChecked(checkbox.getValue());
	}

	public void setMultiSelect(boolean multiselect) {
		if (checkbox != null && this.multiSelect == multiselect) {
			return;
		}

		this.multiSelect = multiselect;
		if (multiselect) {
			checkbox = new SimpleCheckBox();
		} else {
			checkbox = new SimpleRadioButton(ownerId);
		}

		checkbox.addClickHandler(this);
		panel.setWidget(checkbox);
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
		if (checkbox instanceof SimpleRadioButton) {
			checkbox.setName(ownerId);
		}
	}

	public void setEnabled(boolean enabled) {
		if (checkbox != null) {
			checkbox.setEnabled(enabled);
		}
	}

	public void setSelected(boolean selected) {
		if (checkbox != null) {
			checkbox.setValue(selected);
		}
	}

	public interface ComponentCheckedListener {
		void checked(boolean checked);
	}

	protected void updateChecked(boolean checked) {
		if (checkedListener != null) {
			checkedListener.checked(checked);
		}
	}

	public void setCheckedListener(ComponentCheckedListener checkedListener) {
		this.checkedListener = checkedListener;
	}
}
