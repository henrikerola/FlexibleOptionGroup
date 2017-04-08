package org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RadioButton;

/**
 * @author Henri Kerola / Vaadin
 */
public class VFlexibleRadioButtonGroupItemComponent extends Composite
        implements HasValueChangeHandlers<Boolean> {

    private final RadioButton radioButton;

    public VFlexibleRadioButtonGroupItemComponent() {
        radioButton = new RadioButton("");
        initWidget(radioButton);

        setStyleName(Constants.CLASSNAME_ITEM_COMPONENT);
        addStyleName(Constants.CLASSNAME_OPTION);
        addStyleName("v-radiobutton");
    }

    public void setOwnerId(String ownerId) {
        radioButton.setName(ownerId);
    }

    public void setEnabled(boolean enabled) {
        radioButton.setEnabled(enabled);
    }

    public void setSelected(boolean selected) {
        radioButton.setValue(selected);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<Boolean> handler) {
        return radioButton.addValueChangeHandler(handler);
    }
}
