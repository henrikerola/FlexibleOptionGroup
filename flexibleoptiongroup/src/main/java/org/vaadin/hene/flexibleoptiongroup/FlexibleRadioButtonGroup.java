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
public class FlexibleRadioButtonGroup<T> extends RadioButtonGroup<T>
        implements FlexibleOptionGroup<FlexibleRadioButtonGroupItemComponent<T>, T> {

    protected Map<T, FlexibleRadioButtonGroupItemComponent<T>> itemComponentMap = new HashMap<>();
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

    protected FlexibleRadioButtonGroupItemComponent<T> getFlexibleOptionGroupItem(
            T item) {
        if (!itemComponentMap.containsKey(item)) {
            FlexibleRadioButtonGroupItemComponent itemComponent = new FlexibleRadioButtonGroupItemComponent(this, item);
            itemComponentMap.put(item, itemComponent);
            return itemComponent;
        } else {
            return itemComponentMap.get(item);
        }
    }

    @Override
    public void setParent(HasComponents parent) {
        throw new UnsupportedOperationException(
                "The FlexibleOptionGroup component cannot be attached to an Application.");
    }

    @Override
    public FlexibleRadioButtonGroupItemComponent<T> getItemComponent(T item) {
        if (!containsItem(item)) {
            throw new IllegalArgumentException("Doesn't contain an item " + item);
        }
        return getFlexibleOptionGroupItem(item);
    }

    @Override
    public Iterator<FlexibleRadioButtonGroupItemComponent<T>> getItemComponentIterator() {
        return new Iterator<FlexibleRadioButtonGroupItemComponent<T>>() {

            private final Iterator<T> iterator = createItemsIterator();

            public boolean hasNext() {
                return iterator.hasNext();
            }

            public FlexibleRadioButtonGroupItemComponent<T> next() {
                return getFlexibleOptionGroupItem(iterator.next());
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * Returns an items {@link Iterator}.
     * 
     * @return an iterator
     */
    protected Iterator<T> createItemsIterator() {
        if (getDataProvider() instanceof ListDataProvider) {
            return ((ListDataProvider) getDataProvider()).getItems().iterator();
        }
        throw new IllegalStateException("DataProvider is not an instance of ListDataProvider");
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
            for (Map.Entry<T, FlexibleRadioButtonGroupItemComponent<T>> e : itemComponentMap
                    .entrySet()) {
                e.getValue().markAsDirty();
            }
        }
    }

}
