package org.vaadin.hene.flexibleoptiongroup;

/**
 * @author Henri Kerola / Vaadin
 */
public class FlexibleCheckBoxGroupView extends AbstractFlexibleOptionGroupView<FlexibleCheckBoxGroupItemComponent<String>> {

    public FlexibleCheckBoxGroupView() {
        super(new FlexibleCheckBoxGroup<>());
    }
}
