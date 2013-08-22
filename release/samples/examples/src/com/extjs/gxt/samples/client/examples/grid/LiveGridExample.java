/*
 * Sencha GXT 2.3.0 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.samples.client.examples.grid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.extjs.gxt.samples.resources.client.Resources;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.DataField;
import com.extjs.gxt.ui.client.data.JsonPagingLoadResultReader;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.Loader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelType;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.ScriptTagProxy;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.LiveGridView;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.LiveToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;

public class LiveGridExample extends LayoutContainer {

  @Override
  protected void onRender(Element parent, int index) {
    super.onRender(parent, index);

    FlowLayout layout = new FlowLayout(10);
    setLayout(layout);

    String url = "http://www.extjs.com/forum/topics-browse-remote.php";
    ScriptTagProxy<PagingLoadResult<ModelData>> proxy = new ScriptTagProxy<PagingLoadResult<ModelData>>(url);

    ModelType type = new ModelType();
    type.setRoot("topics");
    type.setTotalName("totalCount");
    type.addField("title");
    type.addField("forumtitle");
    type.addField("forumid");
    type.addField("author");
    type.addField("replycount");
    type.addField("lastposter");
    type.addField("excerpt");
    type.addField("replycount");
    type.addField("threadid");

    DataField datefield = new DataField("lastpost");
    datefield.setType(Date.class);
    datefield.setFormat("timestamp");
    type.addField(datefield);

    JsonPagingLoadResultReader<PagingLoadResult<ModelData>> reader = new JsonPagingLoadResultReader<PagingLoadResult<ModelData>>(
        type);

    final PagingLoader<PagingLoadResult<ModelData>> loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy,
        reader);

    loader.addListener(Loader.BeforeLoad, new Listener<LoadEvent>() {
      public void handleEvent(LoadEvent be) {
        BasePagingLoadConfig m = be.<BasePagingLoadConfig> getConfig();
        m.set("start", m.get("offset"));
        m.set("ext", "js");
        m.set("lightWeight", true);
        m.set("sort", (m.get("sortField") == null) ? "" : m.get("sortField"));
        m.set("dir", (m.get("sortDir") == null || (m.get("sortDir") != null && m.<SortDir> get("sortDir").equals(
            SortDir.NONE))) ? "" : m.get("sortDir"));

      }
    });
    loader.setSortDir(SortDir.DESC);
    loader.setSortField("lastpost");

    loader.setRemoteSort(true);

    ListStore<ModelData> store = new ListStore<ModelData>(loader);

    List<ColumnConfig> columns = new ArrayList<ColumnConfig>();

    ColumnConfig title = new ColumnConfig("title", "Topic", 100);
    title.setRenderer(new GridCellRenderer<ModelData>() {

      public Object render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex,
          ListStore<ModelData> store, Grid<ModelData> grid) {
        return "<b><a style=\"color: #385F95; text-decoration: none;\" href=\"http://extjs.com/forum/showthread.php?t="
            + model.get("threadid")
            + "\" target=\"_blank\">"
            + model.get("title")
            + "</a></b><br /><a style=\"color: #385F95; text-decoration: none;\" href=\"http://extjs.com/forum/forumdisplay.php?f="
            + model.get("forumid") + "\" target=\"_blank\">" + model.get("forumtitle") + " Forum</a>";
      }

    });
    columns.add(title);
    columns.add(new ColumnConfig("replycount", "Replies", 50));

    ColumnConfig last = new ColumnConfig("lastpost", "Last Post", 200);
    last.setRenderer(new GridCellRenderer<ModelData>() {

      public Object render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex,
          ListStore<ModelData> store, Grid<ModelData> grid) {
        return model.get("lastpost") + "<br/>by " + model.get("lastposter");
      }

    });
    columns.add(last);

    ColumnModel cm = new ColumnModel(columns);

    final Grid<ModelData> grid = new Grid<ModelData>(store, cm);
    grid.setBorders(true);
    grid.setAutoExpandColumn("title");
    grid.setLoadMask(true);
    grid.setStripeRows(true);

    LiveGridView liveView = new LiveGridView();
    liveView.setEmptyText("No rows available on the server.");
    liveView.setRowHeight(32);
    grid.setView(liveView);

    ContentPanel panel = new ContentPanel();
    panel.setFrame(true);
    panel.setCollapsible(true);
    panel.setAnimCollapse(false);
    panel.setIcon(Resources.ICONS.table());
    panel.setHeadingHtml("LiveGrid Grid");
    panel.setLayout(new FitLayout());
    panel.add(grid);
    panel.setSize(600, 350);
    grid.getAriaSupport().setLabelledBy(panel.getHeader().getId() + "-label");
    
    ToolBar toolBar = new ToolBar();
    toolBar.add(new FillToolItem());

    LiveToolItem item = new LiveToolItem();
    item.bindGrid(grid);

    toolBar.add(item);
    panel.setBottomComponent(toolBar);

    add(panel);

  }

}
