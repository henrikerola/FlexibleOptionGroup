package org.vaadin.hene.flexibleoptiongroup;

import java.util.Iterator;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.HasValue;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Henri Kerola / Vaadin Ltd
 * 
 */
@Title("FlexibleOptionGroup")
@Theme("valo")
@PreserveOnRefresh
public class FlexibleOptionGroupUI extends UI {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5006953560867758089L;

	@WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = FlexibleOptionGroupUI.class, widgetset = "org.vaadin.hene.flexibleoptiongroup.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6453965251118941696L;
    }

	private static final String CAPTION_PROPERTY = "caption";
	private static final String ICON_PROPERTY = "icon";
	private static final String SELECTION_PROPERTY = "selection";

	private static final String[] DOCUMENTS = new String[] { "Word",
			"document-doc.png", "Image", "document-image.png", "PDF",
			"document-pdf.png", "PowerPoint", "document-ppt.png", "Text",
			"document-txt.png", "Web", "document-web.png", "Excel",
			"document-xsl.png" };

	private FlexibleOptionGroupPropertyEditor propertyEditor;

	@Override
	protected void init(VaadinRequest request) {
		if (true) {
			VerticalLayout layout = new VerticalLayout();

			layout.addComponent(new Label("Hello"));

			RadioButtonGroup<String> radioButtonGroup = new RadioButtonGroup<>("My RadioButtonGroup");

			radioButtonGroup.setItemCaptionGenerator(s -> s + "!");

			radioButtonGroup.setItems("1", "3");

//			layout.addComponent(radioButtonGroup);

			FlexibleRadioButtonGroup<String> flexibleRadioButtonGroup = new FlexibleRadioButtonGroup<>();
			flexibleRadioButtonGroup.setItemCaptionGenerator(s -> s + "!");
			flexibleRadioButtonGroup.setItems("1", "3");

			layout.addComponent(flexibleRadioButtonGroup.getFlexibleOptionGroupItem("1"));
			layout.addComponent(flexibleRadioButtonGroup.getFlexibleOptionGroupItem("2"));

			layout.addComponent(new Label("Moi"));

			setContent(layout);
			return;
		}

		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSizeFull();
		mainLayout.setMargin(true);
		setContent(mainLayout);

		Label headerLabel = new Label("FlexibleOptionGroup");
		headerLabel.setStyleName(ValoTheme.LABEL_H1);
		mainLayout.addComponent(headerLabel);

		final TabSheet ts = new TabSheet();
		ts.setSizeFull();
		ts.addComponent(new GridLayoutTab());
		ts.addComponent(new HorizontalOptionGroupTab());
		ts.addComponent(new AbsoluteLayoutTab());
		mainLayout.addComponent(ts);
		mainLayout.setExpandRatio(ts, 1);

		propertyEditor = new FlexibleOptionGroupPropertyEditor();
		propertyEditor.refresh((AbstractTab) ts.getSelectedTab());
		mainLayout.addComponent(propertyEditor);

//		ts.addSelectedTabChangeListener(new SelectedTabChangeListener() {
//			public void selectedTabChange(SelectedTabChangeEvent event) {
//				propertyEditor.refresh((AbstractTab) ts.getSelectedTab());
//			}
//		});
	}

//	private static Container createTestContainer() {
//		IndexedContainer cont = new IndexedContainer();
//		cont.addContainerProperty(CAPTION_PROPERTY, String.class, null);
//		cont.addContainerProperty(ICON_PROPERTY, Resource.class, null);
//
//		for (int i = 0; i < DOCUMENTS.length; i++) {
//			String name = DOCUMENTS[i++];
//			String id = DOCUMENTS[i];
//			Item item = cont.addItem(id);
//			valuateTestContainerItem(item, name, id);
//
//		}
//		return cont;
//	}

//	private static void valuateTestContainerItem(Item item, String name,
//			String iconName) {
//		item.getItemProperty(CAPTION_PROPERTY).setValue(name);
//		item.getItemProperty(ICON_PROPERTY).setValue(
//				new ThemeResource("../runo/icons/16/" + iconName));
//	}

	public static Label createCaptionLabel(FlexibleOptionGroupItemComponent fog) {
		Label captionLabel = new Label();
		captionLabel.setData(fog);
		captionLabel.setIcon(fog.getIcon());
		captionLabel.setCaption(fog.getCaption());
		captionLabel.setWidth(null);
		return captionLabel;
	}

	private static abstract class AbstractTab extends VerticalLayout {

		protected FlexibleOptionGroup flexibleOptionGroup;

		protected LayoutClickListener layoutClickListener = new LayoutClickListener() {

			public void layoutClick(LayoutClickEvent event) {
				FlexibleOptionGroupItemComponent c = null;
				boolean allowUnselection = false; //flexibleOptionGroup.isMultiSelect();
				if (event.getChildComponent() instanceof FlexibleOptionGroupItemComponent) {
					c = (FlexibleOptionGroupItemComponent) event
							.getChildComponent();
				} else if (event.getChildComponent() instanceof AbstractComponent) {
					Object data = ((AbstractComponent) event
							.getChildComponent()).getData();
					if (data instanceof FlexibleOptionGroupItemComponent) {
						c = (FlexibleOptionGroupItemComponent) data;
					}
					if (event.getChildComponent() instanceof HorizontalLayout) {
						allowUnselection = false;
					}
				}
				if (c != null) {
					Object itemId = c.getItemId();
					if (flexibleOptionGroup.isSelected(itemId)
							&& allowUnselection) {
						flexibleOptionGroup.deselect(itemId);
					} else {
						flexibleOptionGroup.select(itemId);
					}
				}
			}
		};

		public AbstractTab(String caption) {
			setCaption(caption);
			setMargin(true);
//			flexibleOptionGroup = new FlexibleOptionGroup(createTestContainer());
//			flexibleOptionGroup.setItemCaptionPropertyId(CAPTION_PROPERTY);
//			flexibleOptionGroup.setItemIconPropertyId(ICON_PROPERTY);
		}
	}

	private static class GridLayoutTab extends AbstractTab {

		private GridLayout layout;

		public GridLayoutTab() {
			super("GridLayout");

//			Item otherItem = flexibleOptionGroup.addItem("other");
//			valuateTestContainerItem(otherItem, "other", "document.png");

			layout = new GridLayout(2, 1);
			layout.setWidth("100%");
			layout.setColumnExpandRatio(1, 1);
//			layout.addLayoutClickListener(layoutClickListener);
			addComponent(layout);

			for (Iterator<FlexibleOptionGroupItemComponent> iter = flexibleOptionGroup
					.getItemComponentIterator(); iter.hasNext();) {
				FlexibleOptionGroupItemComponent c = iter.next();
				layout.addComponent(c);
				if ("other".equals(c.getItemId())) {
					layout.setComponentAlignment(c, Alignment.MIDDLE_CENTER);
//					HorizontalLayout otherLayout = createOtherItemLayout(otherItem);
//					otherLayout.setData(c);
//					layout.addComponent(otherLayout);
				} else {
					layout.addComponent(createCaptionLabel(c));
				}
				layout.newLine();
			}

		}

//		private HorizontalLayout createOtherItemLayout(Item otherItem) {
//			HorizontalLayout otherLayout = new HorizontalLayout();
//			Label otherIcon = new Label();
//			otherIcon.setWidth("16px");
//			otherIcon.setIcon((Resource) otherItem.getItemProperty(
//					ICON_PROPERTY).getValue());
//			otherLayout.addComponent(otherIcon);
//			otherLayout.setComponentAlignment(otherIcon,
//					Alignment.MIDDLE_CENTER);
//			TextField otherTextField = new TextField();
//			otherTextField.setInputPrompt("Other");
//			otherLayout.addComponent(otherTextField);
//			return otherLayout;
//		}

	}

	private static class HorizontalOptionGroupTab extends AbstractTab {

		private HorizontalLayout layout;

		public HorizontalOptionGroupTab() {
			super("HorizontalLayout");

			layout = new HorizontalLayout();
//			layout.addLayoutClickListener(layoutClickListener);
			addComponent(layout);

			for (Iterator<FlexibleOptionGroupItemComponent> iter = flexibleOptionGroup
					.getItemComponentIterator(); iter.hasNext();) {
				FlexibleOptionGroupItemComponent c = iter.next();
				layout.addComponent(c);
				layout.addComponent(createCaptionLabel(c));
			}

		}

	}

	private static class AbsoluteLayoutTab extends AbstractTab {

		private AbsoluteLayout layout;

		public AbsoluteLayoutTab() {
			super("AbsoluteLayout");
			setSizeFull();

			layout = new AbsoluteLayout();
//			layout.addLayoutClickListener(layoutClickListener);
			layout.setSizeFull();
			addComponent(layout);

			int x = 10;
			int y = 10;
			for (Iterator<FlexibleOptionGroupItemComponent> iter = flexibleOptionGroup
					.getItemComponentIterator(); iter.hasNext();) {
				FlexibleOptionGroupItemComponent c = iter.next();
				layout.addComponent(c, "top: " + y + "; left: " + x);
				layout.addComponent(createCaptionLabel(c), "top: " + (y + 15)
						+ "; left: " + (x + 20));
				x += 20;
				y += 20;
			}
		}

	}

	private static class FlexibleOptionGroupPropertyEditor extends
			VerticalLayout implements HasValue.ValueChangeListener {

		private AbstractTab tab;

		// private CheckBox immediateCheckBox = new CheckBox("Immediate");
		private CheckBox enableCheckBox = new CheckBox("Enabled");
		private CheckBox readOnlyCheckBox = new CheckBox("Read-only");
		private CheckBox multiSelectCheckBox = new CheckBox("Multi-select");

		public FlexibleOptionGroupPropertyEditor() {
			// immediateCheckBox.addListener(this);
			// immediateCheckBox.setImmediate(true);
			// addComponent(immediateCheckBox);
//			enableCheckBox.addValueChangeListener(this);
			addComponent(enableCheckBox);
//			readOnlyCheckBox.addValueChangeListener(this);
			addComponent(readOnlyCheckBox);
//			multiSelectCheckBox.addValueChangeListener(this);
			addComponent(multiSelectCheckBox);
		}

		public void refresh(AbstractTab tab) {
			this.tab = tab;
			FlexibleOptionGroup fop = tab.flexibleOptionGroup;
			// immediateCheckBox.setValue(fop.isImmediate());
			enableCheckBox.setValue(fop.isEnabled());
			readOnlyCheckBox.setValue(fop.isReadOnly());
//			multiSelectCheckBox.setValue(fop.isMultiSelect());
		}

		public void valueChange(HasValue.ValueChangeEvent event) {
			FlexibleOptionGroup fop = tab.flexibleOptionGroup;
			// if (immediateCheckBox == event.getProperty()) {
			// fop.setImmediate(immediateCheckBox.getValue());
			// } else
			if (enableCheckBox == event.getComponent()) {
				fop.setEnabled(enableCheckBox.getValue());
			} else if (readOnlyCheckBox == event.getComponent()) {
				fop.setReadOnly(readOnlyCheckBox.getValue());
			} else if (multiSelectCheckBox == event.getComponent()) {
//				fop.setMultiSelect(multiSelectCheckBox.getValue());
			}

		}
	}
}
