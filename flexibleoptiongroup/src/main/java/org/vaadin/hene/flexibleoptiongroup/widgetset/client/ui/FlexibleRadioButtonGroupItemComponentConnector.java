package org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui;

import org.vaadin.hene.flexibleoptiongroup.FlexibleRadioButtonGroupItemComponent;

import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

/**
 * @author Henri Kerola / Vaadin
 */
@Connect (FlexibleRadioButtonGroupItemComponent.class)
public class FlexibleRadioButtonGroupItemComponentConnector extends AbstractComponentConnector {

    @Override
    protected void init() {
        super.init();
        getWidget().addValueChangeHandler(valueChangeEvent -> {
            boolean checked = valueChangeEvent.getValue();
            getRpcProxy(FlexibleOptionGroupItemComponentServerRpc.class).selected(checked);
        });
    }

    @Override
    public VFlexibleRadioButtonGroupItemComponent getWidget() {
        return (VFlexibleRadioButtonGroupItemComponent) super.getWidget();
    }

    @Override
    public FlexibleOptionGroupItemComponentState getState() {
        return (FlexibleOptionGroupItemComponentState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        getWidget().setOwnerId(String.valueOf(getState().ownerId));
        getWidget().setSelected(getState().selected);
        getWidget().setEnabled(isEnabled() && !getState().readOnly);
    }
}
