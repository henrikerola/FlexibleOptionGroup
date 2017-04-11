package org.vaadin.hene.flexibleoptiongroup;

import java.util.Iterator;

import com.vaadin.data.HasDataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.ui.Component;
import com.vaadin.ui.IconGenerator;

/**
 * @author Henri Kerola / Vaadin
 */
public interface FlexibleOptionGroup<C extends Component, T> extends HasDataProvider<T> {

    C getItemComponent(T item);

    Iterator<C> getItemComponentIterator();

    default boolean containsItem(T item) {
        if (getDataProvider() instanceof ListDataProvider) {
            ListDataProvider listDataProvider = (ListDataProvider) getDataProvider();
            return listDataProvider.getItems().contains(item);
        }
        throw new IllegalStateException("");
    }

    IconGenerator<T> getItemIconGenerator();

    void setItemIconGenerator(IconGenerator<T> itemIconGenerator);

    SerializablePredicate<T> getItemEnabledProvider();

    void setItemEnabledProvider(SerializablePredicate<T> itemEnabledProvider);

}
