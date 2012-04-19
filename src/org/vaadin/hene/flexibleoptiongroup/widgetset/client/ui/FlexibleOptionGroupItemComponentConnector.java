package org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui;

import static org.vaadin.hene.flexibleoptiongroup.widgetset.client.VFlexibleOptionGroupItemComponentConstants.ATTR_MULTISELECT;
import static org.vaadin.hene.flexibleoptiongroup.widgetset.client.VFlexibleOptionGroupItemComponentConstants.ATTR_OWNER;
import static org.vaadin.hene.flexibleoptiongroup.widgetset.client.VFlexibleOptionGroupItemComponentConstants.VAADIN_ATTR_DISABLED;
import static org.vaadin.hene.flexibleoptiongroup.widgetset.client.VFlexibleOptionGroupItemComponentConstants.VAADIN_ATTR_IMMEDIATE;
import static org.vaadin.hene.flexibleoptiongroup.widgetset.client.VFlexibleOptionGroupItemComponentConstants.VAADIN_ATTR_READONLY;
import static org.vaadin.hene.flexibleoptiongroup.widgetset.client.VFlexibleOptionGroupItemComponentConstants.VAR_SELECTED;

import org.vaadin.hene.flexibleoptiongroup.FlexibleOptionGroupItemComponent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.SimpleRadioButton;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.UIDL;
import com.vaadin.terminal.gwt.client.communication.RpcProxy;
import com.vaadin.terminal.gwt.client.ui.Connect;
import com.vaadin.terminal.gwt.client.ui.Vaadin6Connector;


@Connect(FlexibleOptionGroupItemComponent.class)
public class FlexibleOptionGroupItemComponentConnector extends
	Vaadin6Connector /*implements ComponentCheckedListener*/ {
	
	private FlexibleOptionGroupItemComponentServerRpc rpc = RpcProxy.create(FlexibleOptionGroupItemComponentServerRpc.class, this);

//	private boolean immediate;
//
////	@Override
////	public void init() {
////		super.init();
////		FlexibleOptionGroupItemComponentClientToServerRpc rpcProxy = GWT
////				.create(FlexibleOptionGroupItemComponentClientToServerRpc.class);
////		this.rpcProxy = initRPC(rpcProxy);
////
////	}
//
	@Override
	protected Widget createWidget() {
		VFlexibleOptionGroupItemComponent widget = GWT
				.create(VFlexibleOptionGroupItemComponent.class);
		//widget.setCheckedListener(this);
		return widget;
	}

	@Override
	public VFlexibleOptionGroupItemComponent getWidget() {
		return (VFlexibleOptionGroupItemComponent) super.getWidget();
	}

	//@Override
	//public FlexibleOptionGroupItemComponentState getState() {
	//	return (FlexibleOptionGroupItemComponentState) super.getState();
	//}
//
//	@Override
//	protected ComponentState createState() {
//		return GWT.create(FlexibleOptionGroupItemComponentState.class);
//	}
//
//	@Override
//	protected boolean delegateCaptionHandling() {
//		return false;
//	}
//
	@Override
	public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
		super.updateFromUIDL(uidl, client);
		if (!isRealUpdate(uidl)) {
			return;
		}

		// immediate = uidl.getBooleanAttribute(VAADIN_ATTR_IMMEDIATE);
		String ownerId = uidl.getStringAttribute(ATTR_OWNER);

		getWidget().setMultiSelect(uidl.hasAttribute(ATTR_MULTISELECT));

		getWidget().setOwnerId(ownerId);
		getWidget().setSelected(uidl.getBooleanVariable(VAR_SELECTED));
		getWidget().setEnabled(!uidl.hasAttribute(VAADIN_ATTR_DISABLED)
				&& !uidl.hasAttribute(VAADIN_ATTR_READONLY));
	}
	
//	public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
//		if (client.updateComponent(this, uidl, false)) {
//			return;
//		}
//		this.client = client;
//		paintableId = uidl.getId();
//
//		immediate = uidl.getBooleanAttribute(VAADIN_ATTR_IMMEDIATE);
//		String ownerId = uidl.getStringAttribute(ATTR_OWNER);
//
//		if (uidl.hasAttribute(ATTR_MULTISELECT)) {
//			checkbox = new SimpleCheckBox();
//		} else {
//			checkbox = new SimpleRadioButton(ownerId);
//		}
//		checkbox.setChecked(uidl.getBooleanVariable(VAR_SELECTED));
//		checkbox.setEnabled(!uidl.hasAttribute(VAADIN_ATTR_DISABLED)
//				&& !uidl.hasAttribute(VAADIN_ATTR_READONLY));
//
//		checkbox.addClickHandler(this);
//		panel.setWidget(checkbox);
//	}
	
	public void checked(boolean checked) {
		rpc.selected(checked);
	}
}
