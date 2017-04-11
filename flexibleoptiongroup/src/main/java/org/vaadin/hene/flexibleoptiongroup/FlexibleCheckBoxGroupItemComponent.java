package org.vaadin.hene.flexibleoptiongroup;

import org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui.FlexibleOptionGroupItemComponentSelectedServerRpc;
import org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui.FlexibleOptionGroupItemComponentState;

import com.vaadin.server.Resource;
import com.vaadin.ui.AbstractComponent;

/**
 * {@link FlexibleCheckBoxGroupItemComponent} represents a check
 * box of an item in {@link FlexibleCheckBoxGroup}.
 * 
 * @author Henri Kerola / Vaadin Ltd
 * 
 */
public class FlexibleCheckBoxGroupItemComponent<T> extends AbstractComponent {

	private final FlexibleCheckBoxGroup<T> owner;
	private final T item;

	private FlexibleOptionGroupItemComponentSelectedServerRpc rpc = new FlexibleOptionGroupItemComponentSelectedServerRpc() {

		public void selected(boolean selected) {
			if (selected) {
				owner.select(item);
			} else {
				owner.deselect(item);
			}
		}
	};

	protected FlexibleCheckBoxGroupItemComponent(FlexibleCheckBoxGroup<T> owner, T item) {
		this.owner = owner;
		this.item = item;
		registerRpc(rpc);
	}

	@Override
	public FlexibleOptionGroupItemComponentState getState() {
		return (FlexibleOptionGroupItemComponentState) super.getState();
	}

	@Override
	public void beforeClientResponse(boolean initial) {
		super.beforeClientResponse(initial);
		if (!owner.containsItem(item)) {
			throw new IllegalStateException(
					"The owner FlexibleCheckBoxGroup does not contain an item '" + item + "'");
		}
		getState().ownerId = owner.id;
		getState().selected = owner.isSelected(item);
		getState().enabled = owner.isEnabled() && owner.getItemEnabledProvider().test(item);
		getState().readOnly = owner.isReadOnly();
	}

	/**
	 * Returns the item of this {@link FlexibleCheckBoxGroupItemComponent}.
	 * 
	 * @return the item of this {@link FlexibleCheckBoxGroupItemComponent}
	 */
	public T getItem() {
		return item;
	}

	/**
	 * Returns the owner {@link FlexibleCheckBoxGroup} of this
	 * {@link FlexibleCheckBoxGroupItemComponent}. Should never return null.
	 * 
	 * @return the owner {@link FlexibleCheckBoxGroup} of this
	 *         {@link FlexibleCheckBoxGroupItemComponent}
	 */
	public FlexibleCheckBoxGroup<T> getOwner() {
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