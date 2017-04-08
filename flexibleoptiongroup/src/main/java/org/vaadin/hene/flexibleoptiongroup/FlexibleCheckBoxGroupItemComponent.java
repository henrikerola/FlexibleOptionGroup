package org.vaadin.hene.flexibleoptiongroup;

import org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui.FlexibleOptionGroupItemComponentServerRpc;
import org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui.FlexibleOptionGroupItemComponentState;

import com.vaadin.ui.AbstractComponent;

/**
 * {@link FlexibleCheckBoxGroupItemComponent} represents a radio button or a check
 * box of an item in {@link FlexibleCheckBoxGroup}.
 * 
 * @author Henri Kerola / Vaadin Ltd
 * 
 */
public class FlexibleCheckBoxGroupItemComponent<T> extends AbstractComponent {

	private final FlexibleCheckBoxGroup<T> owner;
	private final T itemId;

	private FlexibleOptionGroupItemComponentServerRpc rpc = new FlexibleOptionGroupItemComponentServerRpc() {

		public void selected(boolean selected) {
			if (selected) {
				owner.select(itemId);
			} else {
				owner.deselect(itemId);
			}
		}
	};

	protected FlexibleCheckBoxGroupItemComponent(FlexibleCheckBoxGroup<T> owner, T itemId) {
		this.owner = owner;
		this.itemId = itemId;
		registerRpc(rpc);
	}

	@Override
	public void beforeClientResponse(boolean initial) {
		super.beforeClientResponse(initial);
//		if (!owner.containsId(itemId)) {
//			throw new IllegalStateException(
//					"The owner FlexibleOptionGroup does not contain an item with itemId '"
//							+ itemId + "'.");
//		}
		getState().ownerId = owner.id;
		getState().selected = owner.isSelected(itemId);
		getState().enabled = owner.isEnabled() && owner.getItemEnabledProvider().test(itemId);
		getState().readOnly = owner.isReadOnly();
	}

	/**
	 * Returns the itemId of this FlexibleOptionGroupItemComponent.
	 * 
	 * @return the itemId of this FlexibleOptionGroupItemComponent
	 */
	public T getItemId() {
		return itemId;
	}

	/**
	 * Returns the owner FlexibleOptionGroup of this
	 * FlexibleOptionGroupItemComponent. Should never return null.
	 * 
	 * @return the owner FlexibleOptionGroup of this
	 *         FlexibleOptionGroupItemComponent
	 */
	public FlexibleCheckBoxGroup<T> getOwner() {
		return owner;
	}

	/**
	 * Sets the caption of this FlexibleOptionGroupItemComponent. The method
	 * does the same as calling getOwner().setItemCaption(getItemId(), caption).
	 * 
	 * @param caption
	 *            the caption of this FlexibleOptionGroupItemComponent
	 */
//	@Override
//	public void setCaption(String caption) {
//		owner.setItemCaption(itemId, caption);
//	}

	/**
	 * Returns the caption of this FlexibleOptionGroupItemComponent. The method
	 * returns the same value as calling getOwner().getItemCaption(getItemId()).
	 * 
	 * @return the caption of this FlexibleOptionGroupItemComponent
	 */
//	@Override
//	public String getCaption() {
//		return owner.getItemCaption(itemId);
//	}
//
//	@Override
//	public void setIcon(Resource icon) {
//		owner.setItemIcon(itemId, icon);
//	}
//
//	@Override
//	public Resource getIcon() {
//		return owner.getItemIcon(itemId);
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return owner.isItemEnabled(itemId);
//	}
//
//	@Override
//	public void setEnabled(boolean enabled) {
//		owner.setItemEnabled(itemId, enabled);
//	}
//
//	@Override
//	public boolean isReadOnly() {
//		return owner.isReadOnly();
//	}
//
//	@Override
//	public void setReadOnly(boolean readOnly) {
//		owner.setReadOnly(readOnly);
//	}

	@Override
	public FlexibleOptionGroupItemComponentState getState() {
		return (FlexibleOptionGroupItemComponentState) super.getState();
	}
}