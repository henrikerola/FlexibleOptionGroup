package org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui;

import org.vaadin.hene.flexibleoptiongroup.FlexibleCheckBoxGroupItemComponent;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@Connect(FlexibleCheckBoxGroupItemComponent.class)
public class FlexibleCheckBoxGroupItemComponentConnector extends AbstractComponentConnector {

	@Override
	protected void init() {
		super.init();
		getWidget().addValueChangeHandler(this::onSelectionChange);
	}

	private void onSelectionChange(ValueChangeEvent<Boolean> valueChangeEvent) {
		final boolean checked = valueChangeEvent.getValue();
		getRpcProxy(FlexibleOptionGroupItemComponentSelectedServerRpc.class).selected(checked);
	}

	@Override
	public VFlexibleCheckBoxGroupItemComponent getWidget() {
		return (VFlexibleCheckBoxGroupItemComponent) super.getWidget();
	}

	@Override
	public FlexibleOptionGroupItemComponentState getState() {
		return (FlexibleOptionGroupItemComponentState) super.getState();
	}

	@Override
	public boolean delegateCaptionHandling() {
		return false;
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);
		getWidget().setSelected(getState().selected);
		getWidget().setEnabled(isEnabled() && !getState().readOnly);

	}
}
