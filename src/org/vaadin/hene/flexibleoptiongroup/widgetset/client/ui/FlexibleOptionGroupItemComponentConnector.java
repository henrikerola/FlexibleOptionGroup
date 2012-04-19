package org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui;

import org.vaadin.hene.flexibleoptiongroup.FlexibleOptionGroupItemComponent;
import org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui.VFlexibleOptionGroupItemComponent.ComponentCheckedListener;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.terminal.gwt.client.VConsole;
import com.vaadin.terminal.gwt.client.communication.RpcProxy;
import com.vaadin.terminal.gwt.client.communication.StateChangeEvent;
import com.vaadin.terminal.gwt.client.ui.Connect;
import com.vaadin.terminal.gwt.client.ui.Vaadin6Connector;

@Connect(FlexibleOptionGroupItemComponent.class)
public class FlexibleOptionGroupItemComponentConnector extends Vaadin6Connector
		implements ComponentCheckedListener {

	private FlexibleOptionGroupItemComponentServerRpc rpc = RpcProxy.create(
			FlexibleOptionGroupItemComponentServerRpc.class, this);

	@Override
	protected Widget createWidget() {
		VFlexibleOptionGroupItemComponent widget = GWT
				.create(VFlexibleOptionGroupItemComponent.class);
		widget.setCheckedListener(this);
		return widget;
	}

	@Override
	public VFlexibleOptionGroupItemComponent getWidget() {
		return (VFlexibleOptionGroupItemComponent) super.getWidget();
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

		getWidget().setMultiSelect(getState().isMultiSelect());
		getWidget().setOwnerId("" + getState().getOwnerId());
		getWidget().setSelected(getState().isSelected());
		getWidget().setEnabled(
				getState().isEnabled() && !getState().isReadOnly());
	}

	public void checked(boolean checked) {
		rpc.selected(checked);
	}
}
