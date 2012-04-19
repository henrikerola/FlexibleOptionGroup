package org.vaadin.hene.flexibleoptiongroup;

import static org.vaadin.hene.flexibleoptiongroup.widgetset.client.VFlexibleOptionGroupItemComponentConstants.ATTR_MULTISELECT;
import static org.vaadin.hene.flexibleoptiongroup.widgetset.client.VFlexibleOptionGroupItemComponentConstants.ATTR_OWNER;
import static org.vaadin.hene.flexibleoptiongroup.widgetset.client.VFlexibleOptionGroupItemComponentConstants.VAADIN_ATTR_DISABLED;
import static org.vaadin.hene.flexibleoptiongroup.widgetset.client.VFlexibleOptionGroupItemComponentConstants.VAR_SELECTED;

import java.util.Map;

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
public class FlexibleOptionGroupItemComponent extends AbstractComponent implements Vaadin6Component {

	private final FlexibleOptionGroup owner;
	private final Object itemId;

	protected FlexibleOptionGroupItemComponent(FlexibleOptionGroup owner,
			Object itemId) {
		this.owner = owner;
		this.itemId = itemId;
	}

	public void paintContent(PaintTarget target) throws PaintException {
		if (!owner.containsId(itemId)) {
			throw new IllegalStateException(
					"The owner FlexibleOptionGroup does not contain an item with itemId '"
							+ itemId + "'.");
		}

		target.addAttribute(ATTR_OWNER, owner);

		target.addVariable(this, VAR_SELECTED, owner.isSelected(itemId));

		if (!owner.isEnabled() || !isEnabled()) {
			target.addAttribute(VAADIN_ATTR_DISABLED, true);
		}
		if (owner.isMultiSelect()) {
			target.addAttribute(ATTR_MULTISELECT, true);
		}
	}

	public void changeVariables(Object source, Map<String, Object> variables) {
		if (!isReadOnly() && variables.containsKey(VAR_SELECTED)) {
			final boolean selected = (Boolean) variables.get(VAR_SELECTED);
			if (selected != owner.isSelected(itemId)) {
				if (selected) {
					owner.select(itemId);
				} else {
					owner.unselect(itemId);
				}
			}

		}
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
	
//	@Override
//    public FlexibleOptionGroupItemComponentState getState() {
//        return (FlexibleOptionGroupItemComponentState) super.getState();
//    }
}