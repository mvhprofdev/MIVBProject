package be.ehb.mivbproject.parsers;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import be.ehb.mivbproject.source.Calendar_Dates;
import be.ehb.mivbproject.util.DatabaseDAO;


/**
 * Created by mobapp10 on 11/05/17.
 */

public class Calendar_DatesParser {
    private static final Calendar_DatesParser ourInstance = new Calendar_DatesParser();

    public static Calendar_DatesParser getInstance() {
        return ourInstance;
    }

    private Calendar_DatesParser() {
    }
    //needed stuff
    private ArrayList<Calendar_Dates> mCalenderDatesList = new ArrayList<> ();
    private final String TAG = "CalDates";

    public void parseCalendarDates(FileInputStream rid, Context c) {
        BufferedReader rawReader = new BufferedReader(new InputStreamReader(rid));
        String line = "";
        try {
            while((line = rawReader.readLine()) != null) {
                mCalenderDatesList.add(new Calendar_Dates(line));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //first row in file are columns
        mCalenderDatesList.remove(0);

        DatabaseDAO dao = new DatabaseDAO(c);
        //dao.insertAllCalendarDates(mCalenderDatesList);
        dao.close();
    }

    public ArrayList<Calendar_Dates> getmCalenderDatesList() {
        return mCalenderDatesList;
    }
}
