/*
 * Sencha GXT 2.3.0 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget.grid;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;

/**
 * The GridViewConfig is used to return a CSS style name for rows in a Grid. See
 * {@link GridView#setViewConfig(GridViewConfig)}.
 */
public class GridViewConfig {

  /**
   * Returns one to many CSS style names separated by spaces.
   * @param model the model for the row
   * @param rowIndex the row index
   * @param ds the data store
   * @return the CSS style name(s) separated by spaces.
   */
  public String getRowStyle(ModelData model, int rowIndex, ListStore<ModelData> ds) {
    return "";
  }

}
