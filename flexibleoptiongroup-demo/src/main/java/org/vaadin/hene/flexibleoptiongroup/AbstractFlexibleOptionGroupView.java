package org.vaadin.hene.flexibleoptiongroup;

import java.util.HashSet;
import java.util.Set;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Henri Kerola / Vaadin
 */
public class AbstractFlexibleOptionGroupView<T extends Component> extends VerticalLayout implements View {

    private static final String ITEM_1 = "item-1";
    private static final String ITEM_2 = "item-2";
    private static final String ITEM_3 = "item-3";

    private Set<String> disabledItems = new HashSet<>();

    private final FlexibleOptionGroup<T, String> flexibleOptionGroup;

    public AbstractFlexibleOptionGroupView(FlexibleOptionGroup<T, String> flexibleOptionGroup) {

        this.flexibleOptionGroup = flexibleOptionGroup;
        this.flexibleOptionGroup.setItems(ITEM_1, ITEM_2, ITEM_3);
        this.flexibleOptionGroup.setItemEnabledProvider(s -> !disabledItems.contains(s));

        addComponent(new Button("Toggle enabled", e ->
                this.flexibleOptionGroup.setEnabled(!this.flexibleOptionGroup.isEnabled())));

        GridLayout gridLayout = new GridLayout(2, 3);

        gridLayout.addComponent(this.flexibleOptionGroup.getItemComponent(ITEM_1));
        gridLayout.addComponent(createToggleButton(ITEM_1));
        gridLayout.addComponent(this.flexibleOptionGroup.getItemComponent(ITEM_2));
        gridLayout.addComponent(createToggleButton(ITEM_2));
        gridLayout.addComponent(this.flexibleOptionGroup.getItemComponent(ITEM_3));
        gridLayout.addComponent(createToggleButton(ITEM_3));

        addComponent(gridLayout);
    }

    private Component createToggleButton(final String item) {
        return new Button("Toggle enabled", e -> {
            if (disabledItems.contains(item)) {
                disabledItems.remove(item);
            } else {
                disabledItems.add(item);
            }
            flexibleOptionGroup.markAsDirty();
        });
    }

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {

    }
}
