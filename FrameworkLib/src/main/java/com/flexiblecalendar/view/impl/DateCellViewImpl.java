package com.flexiblecalendar.view.impl;

import android.view.View;
import android.view.ViewGroup;

import com.flexiblecalendar.FlexibleCalendarView;
import com.flexiblecalendar.view.BaseCellView;
import com.flexiblecalendar.view.IDateCellViewDrawer;

/**
 * Default date cell view drawer
 *
 * @author p-v
 */
public class DateCellViewImpl implements IDateCellViewDrawer {

    private FlexibleCalendarView.CalendarView calendarView;

    public DateCellViewImpl(FlexibleCalendarView.CalendarView calendarView) {
        this.calendarView = calendarView;
    }

    @Override
    public void setCalendarView(FlexibleCalendarView.CalendarView calendarView) {
        this.calendarView = calendarView;
    }

    @Override
    public BaseCellView getCellView(int position, View convertView, ViewGroup parent, @BaseCellView.CellType int cellType) {
        return calendarView.getCellView(position, convertView, parent, cellType);
    }
}
