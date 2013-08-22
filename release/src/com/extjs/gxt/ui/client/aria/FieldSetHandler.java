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
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.google.gwt.user.client.ui.Widget;

public class FieldSetHandler extends FocusHandler {

  @Override
  public boolean canHandleKeyPress(Component component, PreviewEvent pe) {
    return component instanceof FieldSet || (component instanceof ToolButton && component.getParent() instanceof FieldSet);
  }
  
  @Override
  public void onTab(Component component, PreviewEvent pe) {
    FieldSet fs = (FieldSet) ((component instanceof FieldSet) ? component : component.getParent());
    if (pe.isShiftKey()) {
      Widget w = findPreviousWidget(fs);
      if (isContainer(w) && !w.getElement().getClassName().contains("x-panel-collapsed")) {
        stepInto(w, pe, false);
      } else {
        focusWidget(w);
      }
    } else {
      if (!fs.isExpanded()) {
        focusNextWidget(fs);
        return;
      }
      stepInto(fs, pe, true);
    }
  }

}
