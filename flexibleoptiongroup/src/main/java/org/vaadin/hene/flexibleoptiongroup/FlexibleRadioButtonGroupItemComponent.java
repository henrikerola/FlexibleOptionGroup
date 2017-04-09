package org.vaadin.hene.flexibleoptiongroup;

import org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui.FlexibleOptionGroupItemComponentSelectedServerRpc;
import org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui.FlexibleOptionGroupItemComponentState;

import com.vaadin.ui.AbstractComponent;

/**
 * @author Henri Kerola / Vaadin
 */
public class FlexibleRadioButtonGroupItemComponent<T> extends AbstractComponent {

    private final FlexibleRadioButtonGroup<T> owner;
    private final T                           item;

    private FlexibleOptionGroupItemComponentSelectedServerRpc rpc = new FlexibleOptionGroupItemComponentSelectedServerRpc() {

        public void selected(boolean selected) {
            if (selected) {
                owner.setSelectedItem(item);
            } else {
                // TODO?
            }
        }
    };

    protected FlexibleRadioButtonGroupItemComponent(FlexibleRadioButtonGroup owner, T item) {
        this.owner = owner;
        this.item = item;
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
        getState().selected = owner.isSelected(item);
        getState().enabled = owner.isEnabled() && owner.getItemEnabledProvider().test(item);
        getState().readOnly = owner.isReadOnly();
    }
}
