package org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RadioButton;

/**
 * @author Henri Kerola / Vaadin
 */
public class VFlexibleRadioButtonGroupItemComponent extends Composite {

    public static final String CLASSNAME_OPTION = "v-select-option";

    final private RadioButton radioButton;

    public VFlexibleRadioButtonGroupItemComponent() {

        radioButton = new RadioButton("");
        radioButton.setStyleName("v-radiobutton");
        radioButton.addStyleName(CLASSNAME_OPTION);

        initWidget(radioButton);

//        radioButton.setHTML(itemHtml);
//        radioButton.setValue(
//                item.getBoolean(ListingJsonConstants.JSONKEY_ITEM_SELECTED));
//        boolean optionEnabled = !item
//                .getBoolean(ListingJsonConstants.JSONKEY_ITEM_DISABLED);
//        boolean enabled = optionEnabled && !isReadonly() && isEnabled();
//        radioButton.setEnabled(enabled);
//        String key = item.getString(DataCommunicatorConstants.KEY);

//        if (requireInitialization) {
//            getWidget().add(button);
//
//        }
    }

    public void setOwnerId(String ownerId) {
//        this.ownerId = ownerId;
        radioButton.setName(ownerId);
    }

    public void setEnabled(boolean enabled) {
        radioButton.setEnabled(enabled);
    }

    public void setSelected(boolean selected) {
        radioButton.setValue(selected);
    }

    public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<Boolean> handler) {
        return radioButton.addValueChangeHandler(handler);
    }
}
