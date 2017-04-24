package org.vaadin.hene.flexibleoptiongroup.v7;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.vaadin.server.PaintException;
import com.vaadin.server.PaintTarget;
import com.vaadin.ui.HasComponents;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.v7.shared.ui.optiongroup.OptionGroupState;
import com.vaadin.v7.ui.OptionGroup;

/**
 * The goal of {@link FlexibleOptionGroup} is to provide a very flexible way to
 * layout OptionGroup radio buttons or check boxes. The
 * {@link FlexibleOptionGroup} component itself cannot be added to to any layout
 * ({@link ComponentContainer} ). Well, you can, but it will throw an
 * UnsupportedOperationException. Instead, add
 * {@link FlexibleOptionGroupItemComponent}s to your layout. Each
 * {@link FlexibleOptionGroupItemComponent} represents a radio button or a check
 * box of an item.
 *
 * To get {@link FlexibleOptionGroupItemComponent}s you can use the
 * {@link #getItemComponentIterator()} that returns an Iterator or use the
 * {@link #getItemComponent(Object itemId)} to get a
 * {@link FlexibleOptionGroupItemComponent} for a specific itemId. For the same
 * itemId, the same instance of {@link FlexibleOptionGroupItemComponent} is
 * always returned.
 *
 *
 * @author Henri Kerola / Vaadin Ltd
 * @author Maciej Przepióra / Vaadin Ltd
 *
 */
public class FlexibleOptionGroup extends OptionGroup {

    protected Map<Object, FlexibleOptionGroupItemComponent> itemComponentMap = new HashMap<Object, FlexibleOptionGroupItemComponent>();
    private static int nextId = 0;
    final int id;

    /**
     * Creates an empty {@link FlexibleOptionGroup}.
     */
    public FlexibleOptionGroup() {
        id = nextId++;
        setContainerDataSource(new IndexedContainer());
    }

    /**
     * Creates a {@link FlexibleOptionGroup} and binds it to the given
     * Container.
     *
     * @param dataSource
     */
    public FlexibleOptionGroup(Container dataSource) {
        id = nextId++;
        setContainerDataSource(dataSource);
    }

    public FlexibleOptionGroup(Collection<?> options) {
        id = nextId++;
        final Container c = new IndexedContainer();
        if (options != null) {
            for (final Iterator<?> i = options.iterator(); i.hasNext();) {
                c.addItem(i.next());
            }
        }
        setContainerDataSource(c);
    }

    @Override
    public void setContainerDataSource(Container newDataSource) {
        super.setContainerDataSource(newDataSource);
        if (itemComponentMap != null) {
            itemComponentMap.clear();
        }
    }

    protected FlexibleOptionGroupItemComponent getFlexibleOptionGroupItem(
            Object itemId) {
        if (!itemComponentMap.containsKey(itemId)) {
            FlexibleOptionGroupItemComponent flexibleOptionGroupItemComponent = new FlexibleOptionGroupItemComponent(
                    this, itemId);
            itemComponentMap.put(itemId, flexibleOptionGroupItemComponent);
            return flexibleOptionGroupItemComponent;
        } else {
            return itemComponentMap.get(itemId);
        }
    }

    @Override
    public boolean removeItem(Object itemId)
            throws UnsupportedOperationException {
        boolean returnValue = super.removeItem(itemId);
        if (returnValue) {
            itemComponentMap.remove(itemId);
        }
        return returnValue;
    }

    @Override
    public boolean removeAllItems() throws UnsupportedOperationException {
        boolean returnValue = super.removeAllItems();
        if (returnValue) {
            itemComponentMap.clear();
        }
        return returnValue;
    }

    @Override
    public void setParent(HasComponents parent) {
        throw new UnsupportedOperationException(
                "The FlexibleOptionGroup component cannot be attached to an Application.");
    }

    @Override
    public void paintContent(PaintTarget target) throws PaintException {
    }

    @Override
    public void changeVariables(Object source, Map<String, Object> variables) {
    }

    public FlexibleOptionGroupItemComponent getItemComponent(Object itemId) {
        if (!containsId(itemId)) {
            throw new IllegalArgumentException("");
        }
        return getFlexibleOptionGroupItem(itemId);
    }

    public Iterator<FlexibleOptionGroupItemComponent> getItemComponentIterator() {

        return new Iterator<FlexibleOptionGroupItemComponent>() {

            private Iterator<?> iterator = getItemIds().iterator();

            public boolean hasNext() {
                return iterator.hasNext();
            }

            public FlexibleOptionGroupItemComponent next() {
                return getFlexibleOptionGroupItem(iterator.next());
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public void markAsDirty() {
        super.markAsDirty();
        markItemComponentsAsDirty();
    }

    @Override
    protected OptionGroupState getState(boolean markAsDirty) {
        if (markAsDirty) {
            markItemComponentsAsDirty();
        }
        return super.getState(markAsDirty);
    }

    private void markItemComponentsAsDirty() {
        if (itemComponentMap != null) {
            for (Map.Entry<Object, FlexibleOptionGroupItemComponent> e : itemComponentMap
                    .entrySet()) {
                e.getValue().markAsDirty();
            }
        }
    }

}
