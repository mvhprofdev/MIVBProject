package be.ehb.mivbproject.parsers;

import android.content.Context;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import be.ehb.mivbproject.source.Calendar;
import be.ehb.mivbproject.util.DatabaseDAO;


/**
 * Created by mobapp10 on 11/05/17.
 */

public class CalendarParser {
    private static final CalendarParser ourInstance = new CalendarParser();

    public static CalendarParser getInstance() {
        return ourInstance;
    }

    private CalendarParser() {
    }

    private ArrayList<Calendar> mCalendarList = new ArrayList<>();
    private final String TAG = "GtfsDemo";

    public void parseCalendar(FileInputStream rid, Context c) {
        BufferedReader rawReader = new BufferedReader(new InputStreamReader(rid));
        String line = "";
        try {
            while((line = rawReader.readLine()) != null) {
                mCalendarList.add(new Calendar(line));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //first row in file are columns
        mCalendarList.remove(0);

        DatabaseDAO dao = new DatabaseDAO(c);
        dao.insertAllCalendars(mCalendarList);
        dao.close();
    }

    private void printAgency() {
        for (Calendar calendar : mCalendarList)
            Log.i(TAG, "name " + calendar.getService_id() + "\n");
    }

    public ArrayList<Calendar> getmCalendarList() {
        return mCalendarList;
    }
}
