package org.vaadin.hene.flexibleoptiongroup;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.vaadin.data.HasDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.shared.ui.optiongroup.CheckBoxGroupState;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HasComponents;

/**
 * The goal of {@link FlexibleCheckBoxGroup} is to provide a very flexible way to
 * layout OptionGroup radio buttons or check boxes. The
 * {@link FlexibleCheckBoxGroup} component itself cannot be added to to any layout
 * ({@link ComponentContainer} ). Well, you can, but it will throw an
 * UnsupportedOperationException. Instead, add
 * {@link FlexibleCheckBoxGroupItemComponent}s to your layout. Each
 * {@link FlexibleCheckBoxGroupItemComponent} represents a radio button or a check
 * box of an item.
 * 
 * To get {@link FlexibleCheckBoxGroupItemComponent}s you can use the
 * {@link #getItemComponentIterator()} that returns an Iterator or use the
 * {@link #getItemComponent(Object itemId)} to get a
 * {@link FlexibleCheckBoxGroupItemComponent} for a specific itemId. For the same
 * itemId, the same instance of {@link FlexibleCheckBoxGroupItemComponent} is
 * always returned.
 * 
 * 
 * @author Henri Kerola / Vaadin Ltd
 * @author Maciej Przepióra / Vaadin Ltd
 * 
 */
public class FlexibleCheckBoxGroup<T> extends CheckBoxGroup<T>
		implements FlexibleOptionGroup<FlexibleCheckBoxGroupItemComponent<T>, T> {

	private static int nextId = 0;

	private final Map<Object, FlexibleCheckBoxGroupItemComponent> itemComponentMap = new HashMap<>();
	final int id;

	/**
	 * Constructs a new {@link FlexibleCheckBoxGroup} with caption.
	 *
	 * @param caption
	 *            caption text
	 */
	public FlexibleCheckBoxGroup(String caption) {
		this();
		setCaption(caption);
	}

	/**
	 * Constructs a new {@link FlexibleCheckBoxGroup} with caption and {@link DataProvider}.
	 *
	 * @param caption
	 *            the caption text
	 * @param dataProvider
	 *            the data provider, not null
	 * @see HasDataProvider#setDataProvider(DataProvider)
	 */
	public FlexibleCheckBoxGroup(String caption, DataProvider<T, ?> dataProvider) {
		this(caption);
		setDataProvider(dataProvider);
	}

	/**
	 * Constructs a new {@link FlexibleCheckBoxGroup} with caption and DataProvider containing
	 * given items.
	 *
	 * @param caption
	 *            the caption text
	 * @param items
	 *            the data items to use, not null
	 * @see #setItems(Collection)
	 */
	public FlexibleCheckBoxGroup(String caption, Collection<T> items) {
		this(caption, DataProvider.ofCollection(items));
	}


	/**
	 * Creates an empty {@link FlexibleCheckBoxGroup}.
	 */
	public FlexibleCheckBoxGroup() {
		id = nextId++;
	}

	protected FlexibleCheckBoxGroupItemComponent getFlexibleOptionGroupItem(
			T item) {
		if (!itemComponentMap.containsKey(item)) {
			FlexibleCheckBoxGroupItemComponent flexibleOptionGroupItemComponent = new FlexibleCheckBoxGroupItemComponent(
					this, item);
			itemComponentMap.put(item, flexibleOptionGroupItemComponent);
			return flexibleOptionGroupItemComponent;
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
	public FlexibleCheckBoxGroupItemComponent getItemComponent(T item) {
//		if (!containsId(item)) {
//			throw new IllegalArgumentException("");
//		}
		return getFlexibleOptionGroupItem(item);
	}

	public Iterator<FlexibleCheckBoxGroupItemComponent<T>> getItemComponentIterator() {

        return new Iterator<FlexibleCheckBoxGroupItemComponent<T>>() {

            private final Iterator<T> iterator = createItemsIterator();

            public boolean hasNext() {
                return iterator.hasNext();
            }

            public FlexibleCheckBoxGroupItemComponent<T> next() {
                return getFlexibleOptionGroupItem(iterator.next());
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
	}

    /**
     * Returns an items {@link Iterator}.
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
	protected CheckBoxGroupState getState(boolean markAsDirty) {
		if (markAsDirty) {
			markItemComponentsAsDirty();
		}
		return super.getState(markAsDirty);
	}

	private void markItemComponentsAsDirty() {
		if (itemComponentMap != null) {
			for (Entry<Object, FlexibleCheckBoxGroupItemComponent> e : itemComponentMap
					.entrySet()) {
				e.getValue().markAsDirty();
			}
		}
	}

}
