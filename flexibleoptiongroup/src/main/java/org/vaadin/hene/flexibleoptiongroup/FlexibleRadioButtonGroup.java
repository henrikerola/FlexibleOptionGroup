package org.vaadin.hene.flexibleoptiongroup;

import java.util.Collection;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.ui.RadioButtonGroup;

/**
 * @author Henri Kerola / Vaadin
 */
public class FlexibleRadioButtonGroup<T> extends RadioButtonGroup<T> {

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
    }

    /**
     * Constructs a new RadioButtonGroup.
     */
    public FlexibleRadioButtonGroup() {
    }
}
