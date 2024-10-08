package com.flexiblecalendar.view.impl;

import android.view.View;
import android.view.ViewGroup;

import com.flexiblecalendar.FlexibleCalendarView;
import com.flexiblecalendar.view.BaseCellView;
import com.flexiblecalendar.view.IWeekCellViewDrawer;


/**
 * Default week cell view drawer
 *
 * @author p-v
 */
public class WeekdayCellViewImpl implements IWeekCellViewDrawer {

    private FlexibleCalendarView.CalendarView calendarView;

    public WeekdayCellViewImpl(FlexibleCalendarView.CalendarView calendarView) {
        this.calendarView = calendarView;
    }

    @Override
    public void setCalendarView(FlexibleCalendarView.CalendarView calendarView) {
        this.calendarView = calendarView;
    }

    @Override
    public BaseCellView getCellView(int position, View convertView, ViewGroup parent) {
        return calendarView.getWeekdayCellView(position, convertView, parent);
    }

    @Override
    public String getWeekDayName(int dayOfWeek, String defaultValue) {
        return calendarView.getDayOfWeekDisplayValue(dayOfWeek, defaultValue);
    }
}
