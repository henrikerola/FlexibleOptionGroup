package org.vaadin.hene.flexibleoptiongroup;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Henri Kerola / Vaadin
 */
public class FlexibleRadioButtonGroupTest {

    private FlexibleRadioButtonGroup<String> flexibleRadioButtonGroup;

    @Before
    public void setUp() throws Exception {
        flexibleRadioButtonGroup = new FlexibleRadioButtonGroup<>();
    }

    @Test
    public void getItemComponentIterator() throws Exception {
        flexibleRadioButtonGroup.setItems("Item 1", "Item 2");

        final Iterator<FlexibleRadioButtonGroupItemComponent<String>> iterator = flexibleRadioButtonGroup.getItemComponentIterator();
        assertEquals("Item 1", iterator.next().getItem());
        assertEquals("Item 2", iterator.next().getItem());
        assertFalse(iterator.hasNext());
    }
}