package org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui;

import org.vaadin.hene.flexibleoptiongroup.FlexibleOptionGroupItemComponent;
import org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui.VFlexibleOptionGroupItemComponent.ComponentCheckedListener;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@Connect(FlexibleOptionGroupItemComponent.class)
public class FlexibleOptionGroupItemComponentConnector extends
		AbstractComponentConnector implements ComponentCheckedListener {

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
		getWidget().setMultiSelect(getState().multiSelect);
		getWidget().setOwnerId("" + getState().ownerId);
		getWidget().setSelected(getState().selected);
//		getWidget().setEnabled(isEnabled() && !isReadOnly());

	}

	public void checked(boolean checked) {
		rpc.selected(checked);
	}
}
