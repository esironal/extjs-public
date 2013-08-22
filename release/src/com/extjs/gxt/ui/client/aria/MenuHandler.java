/*
 * Sencha GXT 2.3.0 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.aria;

import com.extjs.gxt.ui.client.event.PreviewEvent;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.menu.Menu;

public class MenuHandler extends FocusHandler {

  @Override
  public boolean canHandleKeyPress(Component component, PreviewEvent pe) {
    return component instanceof Menu;
  }
  
  @Override
  public void onTab(Component component, PreviewEvent pe) {
    if (!isManaged()) return;
    Menu menu = (Menu)component;
    if (menu.isVisible()) {
      Button btn = menu.getData("parent");
      if (btn != null) {
        pe.preventDefault();
        btn.hideMenu();
        focusNextWidget(btn.getParent());
      }
    }
  }

}
