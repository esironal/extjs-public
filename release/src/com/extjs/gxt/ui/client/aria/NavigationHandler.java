/*
 * Sencha GXT 2.3.0 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.aria;

import java.util.List;

import com.extjs.gxt.ui.client.widget.Component;
import com.google.gwt.user.client.ui.Widget;

/**
 * Navigation handlers are responsible for specifying the child widgets of a
 * container. In addition, the handler determines the order in which the
 * children are navigated.
 * 
 */
public interface NavigationHandler {

  /**
   * Determines if the this handler instance can handle the given component.
   * 
   * @param comp the target component
   * @return the list of ordered child widgets of the container
   */
  public boolean canHandleTabKey(Component comp);

  /**
   * Returns the list of ordered widgets.
   * 
   * @param widget the target widget
   * @return the list of ordered widgets
   */
  public List<Widget> getOrderedWidgets(Widget widget);

}
