package org.vaadin.hene.flexibleoptiongroup;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.shared.ui.optiongroup.RadioButtonGroupState;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.RadioButtonGroup;

/**
 * @author Henri Kerola / Vaadin
 */
public class FlexibleRadioButtonGroup<T> extends RadioButtonGroup<T> {

    protected Map<T, FlexibleRadioButtonGroupItemComponent> itemComponentMap = new HashMap<>();
    private static int nextId = 0;
    final int id;

    /**
     * Constructs a new FlexibleRadioButtonGroup with caption.
     *
     * @param caption
     *            caption text
     */
    public FlexibleRadioButtonGroup(String caption) {
        this();
        setCaption(caption);
    }

    /**
     * Constructs a new FlexibleRadioButtonGroup with caption and DataProvider.
     *
     * @param caption
     *            the caption text
     * @param dataProvider
     *            the data provider, not null
     * @see com.vaadin.data.HasDataProvider#setDataProvider(DataProvider)
     */
    public FlexibleRadioButtonGroup(String caption, DataProvider<T, ?> dataProvider) {
        this(caption);
        setDataProvider(dataProvider);
    }

    /**
     * Constructs a new FlexibleRadioButtonGroup with caption and DataProvider
     * containing given items.
     *
     * @param caption
     *            the caption text
     * @param items
     *            the data items to use, not null
     * @see #setItems(Collection)
     */
    public FlexibleRadioButtonGroup(String caption, Collection<T> items) {
        super(caption, DataProvider.ofCollection(items));
        id = nextId++;
    }

    /**
     * Constructs a new RadioButtonGroup.
     */
    public FlexibleRadioButtonGroup() {
        id = nextId++;
    }

    protected FlexibleRadioButtonGroupItemComponent getFlexibleOptionGroupItem(
            T itemId) {
        if (!itemComponentMap.containsKey(itemId)) {
            FlexibleRadioButtonGroupItemComponent flexibleOptionGroupItemComponent = new FlexibleRadioButtonGroupItemComponent(
                    this, itemId);
            itemComponentMap.put(itemId, flexibleOptionGroupItemComponent);
            return flexibleOptionGroupItemComponent;
        } else {
            return itemComponentMap.get(itemId);
        }
    }

    @Override
    public void setParent(HasComponents parent) {
        throw new UnsupportedOperationException(
                "The FlexibleOptionGroup component cannot be attached to an Application.");
    }

    public FlexibleRadioButtonGroupItemComponent getItemComponent(T itemId) {
        if (getDataProvider() instanceof ListDataProvider) {
            ListDataProvider listDataProvider = (ListDataProvider) getDataProvider();
            if (!listDataProvider.getItems().contains(itemId)) {
                throw new IllegalArgumentException("");
            }
        }

        return getFlexibleOptionGroupItem(itemId);
    }

    public Iterator<FlexibleOptionGroupItemComponent> getItemComponentIterator() {

//        		return new Iterator<FlexibleOptionGroupItemComponent>() {
//
//        			private Iterator<?> iterator = getItemIds().iterator();
//
//        			public boolean hasNext() {
//        				return iterator.hasNext();
//        			}
//
//        			public FlexibleOptionGroupItemComponent next() {
//        				return getFlexibleOptionGroupItem(iterator.next());
//        			}
//
//        			public void remove() {
//        				throw new UnsupportedOperationException();
//        			}
//        		};

        return null;
    }

    @Override
    public void markAsDirty() {
        super.markAsDirty();
        markItemComponentsAsDirty();
    }

    @Override
    protected RadioButtonGroupState getState(boolean markAsDirty) {
        if (markAsDirty) {
            markItemComponentsAsDirty();
        }
        return super.getState(markAsDirty);
    }

    private void markItemComponentsAsDirty() {
        if (itemComponentMap != null) {
            for (Map.Entry<T, FlexibleRadioButtonGroupItemComponent> e : itemComponentMap
                    .entrySet()) {
                e.getValue().markAsDirty();
            }
        }
    }

}
