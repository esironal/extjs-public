/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007, 2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.extjs.gxt.samples.explorer.client.pages;

import com.extjs.gxt.samples.resources.client.Folder;
import com.extjs.gxt.samples.resources.client.TestData;
import com.extjs.gxt.ui.client.binder.TreeBinder;
import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelStringProvider;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.data.TreeModel;
import com.extjs.gxt.ui.client.data.TreeModelReader;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.StoreFilterField;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.tree.Tree;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;

public class TreeFilterPage extends LayoutContainer implements EntryPoint {

  public void onModuleLoad() {
    RootPanel.get().add(this);
  }

  @Override
  protected void onRender(Element parent, int pos) {
    super.onRender(parent, pos);

    Tree tree = new Tree();

    TreeLoader loader = new BaseTreeLoader(new TreeModelReader());
    TreeStore<ModelData> store = new TreeStore<ModelData>(loader);

    TreeBinder binder = new TreeBinder(tree, store);
    binder.setAutoLoad(true);
    binder.setDisplayProperty("name");
    binder.setIconProvider(new ModelStringProvider() {

      public String getStringValue(ModelData model, String property) {
        if (((TreeModel) model).isLeaf()) {
          return "icon-music";
        }
        return null;
      }

    });

    loader.load(TestData.getTreeModel());

    StoreFilterField filter = new StoreFilterField<ModelData>() {

      @Override
      protected boolean doSelect(Store store, ModelData parent, ModelData record, String property,
          String filter) {
        // only match leaf nodes
        if (record instanceof Folder) {
          return false;
        }
        String name = record.get("name");
        name = name.toLowerCase();
        if (name.startsWith(filter.toLowerCase())) {
          return true;
        }
        return false;
      }

    };
    filter.bind(store);

    VerticalPanel panel = new VerticalPanel();
    panel.addStyleName("x-small-editor");
    panel.setSpacing(8);

    panel.add(new Html("<span class=text>Enter a search string such as 'vio'</span>"));
    panel.add(filter);
    panel.add(tree);

    add(panel);
  }

}
