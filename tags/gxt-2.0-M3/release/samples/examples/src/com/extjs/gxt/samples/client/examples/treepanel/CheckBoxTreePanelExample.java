/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.extjs.gxt.samples.client.examples.treepanel;

import com.extjs.gxt.samples.client.Examples;
import com.extjs.gxt.samples.resources.client.TestData;
import com.extjs.gxt.samples.resources.client.model.Folder;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.CheckChangedEvent;
import com.extjs.gxt.ui.client.event.CheckChangedListener;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Element;

public class CheckBoxTreePanelExample extends LayoutContainer {

  @Override
  protected void onRender(Element parent, int index) {
    super.onRender(parent, index);

    Folder model = TestData.getTreeModel();

    TreeStore<ModelData> store = new TreeStore<ModelData>();
    store.add(model.getChildren(), true);

    final TreePanel<ModelData> tree = new TreePanel<ModelData>(store);
    tree.setDisplayProperty("name");
    tree.getStyle().setLeafIcon(Examples.IMAGES.music());
    tree.setCheckable(true);
    
    // overall checked state changes
    tree.addCheckListener(new CheckChangedListener<ModelData>() {
      @Override
      public void checkChanged(CheckChangedEvent<ModelData> event) {
        
      }
    });
    
    // change in node check state
    tree.addListener(Events.CheckChange, new Listener<TreePanelEvent<ModelData>>() {
      public void handleEvent(TreePanelEvent<ModelData> be) {
        
      }
    });
    
    ButtonBar buttonBar = new ButtonBar();

    buttonBar.add(new Button("Get Checked", new SelectionListener<ButtonEvent>() {
      public void componentSelected(ButtonEvent ce) {
        StringBuffer sb = new StringBuffer();
        for (ModelData item : tree.getCheckedSelection()) {
          sb.append(", " + (String)item.get("name"));
        }
        String s = sb.toString();
        if (s.length() > 1) s = s.substring(2);
        if (s.length() > 100) s = s.substring(0, 100) + "...";
        Info.display("Checked Items", s, "");
      }
    }));

    add(buttonBar, new FlowData(10));
    
    add(tree, new FlowData(10));
  }
}
