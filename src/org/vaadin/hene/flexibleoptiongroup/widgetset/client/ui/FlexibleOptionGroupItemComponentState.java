package org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui;

import org.vaadin.hene.flexibleoptiongroup.FlexibleOptionGroup;

import com.vaadin.terminal.gwt.client.ComponentState;

public class FlexibleOptionGroupItemComponentState extends ComponentState {

	//private FlexibleOptionGroup owner;

	private boolean selected;

	private boolean multiSelect;

	//private boolean enabled = true;

	// public FlexibleOptionGroup getOwner() {
	// return owner;
	// }

	// public void setOwner(FlexibleOptionGroup owner) {
	// this.owner = owner;
	// }

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isMultiSelect() {
		return multiSelect;
	}

	public void setMultiSelect(boolean multiSelect) {
		this.multiSelect = multiSelect;
	}

//	public boolean isEnabled() {
//		return enabled;
//	}
//
//	public void setEnabled(boolean enabled) {
//		this.enabled = enabled;
//	}
}
