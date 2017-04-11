package org.vaadin.hene.flexibleoptiongroup;

import org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui.FlexibleOptionGroupItemComponentSelectedServerRpc;
import org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui.FlexibleOptionGroupItemComponentState;

import com.vaadin.server.Resource;
import com.vaadin.ui.AbstractComponent;

/**
 * @author Henri Kerola / Vaadin
 */
public class FlexibleRadioButtonGroupItemComponent<T> extends AbstractComponent {

    private final FlexibleRadioButtonGroup<T> owner;
    private final T item;

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
        if (!owner.containsItem(item)) {
            throw new IllegalStateException(
                    "The owner FlexibleRadioButtonGroup does not contain an item '" + item + "'");
        }
        getState().ownerId = owner.id;
        getState().selected = owner.isSelected(item);
        getState().enabled = owner.isEnabled() && owner.getItemEnabledProvider().test(item);
        getState().readOnly = owner.isReadOnly();
    }

    /**
     * Returns the item of this {@link FlexibleRadioButtonGroupItemComponent}.
     *
     * @return the item of {@link FlexibleRadioButtonGroupItemComponent}
     */
    public T getItem() {
        return item;
    }

    /**
     * Returns the owner {@link FlexibleRadioButtonGroup} of this
     * {@link FlexibleRadioButtonGroupItemComponent}. Should never return null.
     *
     * @return the owner {@link FlexibleRadioButtonGroup} of this {@link FlexibleRadioButtonGroupItemComponent}
     */
    public FlexibleRadioButtonGroup<T> getOwner() {
        return owner;
    }

    @Override
    public String getCaption() {
        return owner.getItemCaptionGenerator().apply(getItem());
    }

    @Override
    public void setCaption(String caption) {
        // TODO
    }

    @Override
    public Resource getIcon() {
        return owner.getItemIconGenerator().apply(getItem());
    }

    @Override
    public void setIcon(Resource icon) {
        // TODO
    }

    @Override
    public boolean isEnabled() {
        return owner.getItemEnabledProvider().test(getItem());
    }

    @Override
    public void setEnabled(boolean enabled) {
        // TODO
    }
}
