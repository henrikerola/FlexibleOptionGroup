package org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui;

import com.vaadin.shared.communication.ServerRpc;

public interface FlexibleOptionGroupItemComponentSelectedServerRpc extends ServerRpc {

	void selected(boolean selected);
}