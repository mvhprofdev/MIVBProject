package be.ehb.mivbproject.parsers;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import be.ehb.mivbproject.source.Stoptime;
import be.ehb.mivbproject.util.DatabaseDAO;


/**
 * Created by mobapp10 on 11/05/17.
 */

public class StoptimeParser {
    private static final StoptimeParser ourInstance = new StoptimeParser();

    public static StoptimeParser getInstance() {
        return ourInstance;
    }

    private StoptimeParser() {
    }
    //needed stuff
    private ArrayList<Stoptime> mStoptimeList = new ArrayList<> ();
    private final String TAG = "Stoptime";

    public void parseStoptime(FileInputStream rid, Context c) {
        BufferedReader rawReader = new BufferedReader(new InputStreamReader(rid));
        String line = "";
        try {
            while((line = rawReader.readLine()) != null) {
                mStoptimeList.add(new Stoptime(line));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //first row in file are columns
        mStoptimeList.remove(0);

        DatabaseDAO dao = new DatabaseDAO(c);
        //dao.insertAllStoptimes(mStoptimeList);
        dao.close();
    }

    private void printStoptime() {
        for (Stoptime stoptime : mStoptimeList)
            Log.i(TAG, "name " + stoptime.getDeparture_time() + "\n");
    }

    public ArrayList<Stoptime> getmStoptimeList() {
        return mStoptimeList;
    }
}
