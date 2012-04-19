package org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui;

import com.vaadin.terminal.gwt.client.ComponentState;

public class FlexibleOptionGroupItemComponentState extends ComponentState {

	private int ownerId;

	private boolean selected;

	private boolean multiSelect;

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

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
}
