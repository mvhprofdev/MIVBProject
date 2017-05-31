package be.ehb.mivbproject.parsers;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

import be.ehb.mivbproject.source.Trip;
import be.ehb.mivbproject.util.DatabaseDAO;


/**
 * Created by mobapp10 on 11/05/17.
 */

public class TripParser {
    private static final TripParser ourInstance = new TripParser();

    public static TripParser getInstance() {
        return ourInstance;
    }

    private TripParser() {
    }
    //needed stuff
    private ArrayList<Trip> mTripList = new ArrayList<> ();
    private final String TAG = "Trip";

    public void parseTrip(FileInputStream rid, Context c) {
        BufferedReader rawReader = new BufferedReader(new InputStreamReader(rid));
        String line = "";
        try {
            while((line = rawReader.readLine()) != null) {
                mTripList.add(new Trip(line));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //first row in file are columns
        mTripList.remove(0);

        DatabaseDAO dao = new DatabaseDAO(c);
        //dao.insertAllTrips(mTripList);
        dao.close();
    }

    private void printRoute() {
        for (Trip trip : mTripList)
            Log.i(TAG, "name " + trip.getTrip_id() + "\n");
    }

    public ArrayList<Trip> getmTripList() {
        return mTripList;
    }
}
