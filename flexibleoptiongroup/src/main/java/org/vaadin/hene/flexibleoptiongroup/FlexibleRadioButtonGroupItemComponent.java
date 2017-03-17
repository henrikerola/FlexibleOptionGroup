package org.vaadin.hene.flexibleoptiongroup;

import org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui.FlexibleOptionGroupItemComponentServerRpc;
import org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui.FlexibleOptionGroupItemComponentState;

import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Notification;

/**
 * @author Henri Kerola / Vaadin
 */
public class FlexibleRadioButtonGroupItemComponent<T> extends AbstractComponent {

    private final FlexibleRadioButtonGroup<T> owner;
    private final T itemId;

    private FlexibleOptionGroupItemComponentServerRpc rpc = new FlexibleOptionGroupItemComponentServerRpc() {

        public void selected(boolean selected) {
            Notification.show("");
            if (selected) {
                owner.setSelectedItem(itemId);
            } else {
                // TODO?
            }
        }
    };

    protected FlexibleRadioButtonGroupItemComponent(FlexibleRadioButtonGroup owner,
                                               T itemId) {
        this.owner = owner;
        this.itemId = itemId;
        registerRpc(rpc);
    }

    @Override
    protected FlexibleOptionGroupItemComponentState getState() {
        return (FlexibleOptionGroupItemComponentState) super.getState();
    }

    @Override
    public void beforeClientResponse(boolean initial) {
        super.beforeClientResponse(initial);
        //		if (!owner.containsId(itemId)) {
        //			throw new IllegalStateException(
        //					"The owner FlexibleOptionGroup does not contain an item with itemId '"
        //							+ itemId + "'.");
        //		}
        //
        getState().ownerId = owner.id;
        getState().selected = owner.isSelected(itemId);
        getState().enabled = owner.isEnabled() && owner.getItemEnabledProvider().test(itemId);
        getState().readOnly = owner.isReadOnly();
    }
}
