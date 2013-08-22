/*
 * Sencha GXT 2.3.0 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.event;

import com.extjs.gxt.ui.client.data.ModelData;

/**
 * Check changed listener.
 */
public class CheckChangedListener<M extends ModelData> implements Listener<CheckChangedEvent<M>> {

  public void handleEvent(CheckChangedEvent<M> ce) {
    if (ce.getType() == Events.CheckChanged) {
      checkChanged(ce);
    }
  }

  /**
   * Fires when the check state has changed.
   * 
   * @param event the check changed event
   */
  public void checkChanged(CheckChangedEvent<M> event) {

  }

}
