package org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui;

import com.vaadin.shared.communication.ServerRpc;

public interface FlexibleOptionGroupItemComponentServerRpc extends ServerRpc {

	public void selected(boolean selected);
}