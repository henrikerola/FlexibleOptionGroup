package org.vaadin.hene.flexibleoptiongroup;

import java.util.Map;

import org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui.FlexibleOptionGroupItemComponentServerRpc;
import org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui.FlexibleOptionGroupItemComponentState;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.Vaadin6Component;
import com.vaadin.ui.AbstractComponent;

/**
 * {@link FlexibleOptionGroupItemComponent} represents a radio button or a check
 * box of an item in {@link FlexibleOptionGroup}.
 * 
 * @author Henri Kerola / Vaadin Ltd
 * 
 */
public class FlexibleOptionGroupItemComponent extends AbstractComponent
		implements Vaadin6Component {

	private final FlexibleOptionGroup owner;
	private final Object itemId;

	private FlexibleOptionGroupItemComponentServerRpc rpc = new FlexibleOptionGroupItemComponentServerRpc() {

		public void selected(boolean selected) {
			if (selected) {
				owner.select(itemId);
			} else {
				owner.unselect(itemId);
			}
		}
	};

	protected FlexibleOptionGroupItemComponent(FlexibleOptionGroup owner,
			Object itemId) {
		this.owner = owner;
		this.itemId = itemId;
		registerRpc(rpc);
	}

	public void paintContent(PaintTarget target) throws PaintException {
		if (!owner.containsId(itemId)) {
			throw new IllegalStateException(
					"The owner FlexibleOptionGroup does not contain an item with itemId '"
							+ itemId + "'.");
		}

		getState().setOwnerId(owner.id);
		getState().setSelected(owner.isSelected(itemId));
		getState().setEnabled(owner.isEnabled() && isEnabled());
		getState().setMultiSelect(owner.isMultiSelect());

		getState().setReadOnly(owner.isReadOnly());
	}

	public void changeVariables(Object source, Map<String, Object> variables) {
	}

	/**
	 * Returns the itemId of this FlexibleOptionGroupItemComponent.
	 * 
	 * @return the itemId of this FlexibleOptionGroupItemComponent
	 */
	public Object getItemId() {
		return itemId;
	}

	/**
	 * Returns the owner FlexibleOptionGroup of this
	 * FlexibleOptionGroupItemComponent. Should never return null.
	 * 
	 * @return the owner FlexibleOptionGroup of this
	 *         FlexibleOptionGroupItemComponent
	 */
	public FlexibleOptionGroup getOwner() {
		return owner;
	}

	/**
	 * Sets the caption of this FlexibleOptionGroupItemComponent. The method
	 * does the same as calling getOwner().setItemCaption(getItemId(), caption).
	 * 
	 * @param caption
	 *            the caption of this FlexibleOptionGroupItemComponent
	 */
	@Override
	public void setCaption(String caption) {
		owner.setItemCaption(itemId, caption);
	}

	/**
	 * Returns the caption of this FlexibleOptionGroupItemComponent. The method
	 * returns the same value as calling getOwner().getItemCaption(getItemId()).
	 * 
	 * @return the caption of this FlexibleOptionGroupItemComponent
	 */
	@Override
	public String getCaption() {
		return owner.getItemCaption(itemId);
	}

	@Override
	public void setIcon(Resource icon) {
		owner.setItemIcon(itemId, icon);
	}

	@Override
	public Resource getIcon() {
		return owner.getItemIcon(itemId);
	}

	@Override
	public boolean isEnabled() {
		return owner.isItemEnabled(itemId);
	}

	@Override
	public void setEnabled(boolean enabled) {
		owner.setItemEnabled(itemId, enabled);
	}

	@Override
	public boolean isReadOnly() {
		return owner.isReadOnly();
	}

	@Override
	public void setReadOnly(boolean readOnly) {
		owner.setReadOnly(readOnly);
	}

	@Override
	public void setImmediate(boolean immediate) {
		owner.setImmediate(immediate);
	}

	@Override
	public boolean isImmediate() {
		return owner.isImmediate();
	}

	@Override
	public FlexibleOptionGroupItemComponentState getState() {
		return (FlexibleOptionGroupItemComponentState) super.getState();
	}
}