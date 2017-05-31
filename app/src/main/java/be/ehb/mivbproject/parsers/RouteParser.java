package be.ehb.mivbproject.parsers;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import be.ehb.mivbproject.source.Route;
import be.ehb.mivbproject.util.DatabaseDAO;


/**
 * Created by mobapp10 on 11/05/17.
 */

public class RouteParser {
    private static final RouteParser ourInstance = new RouteParser();

    public static RouteParser getInstance() {
        return ourInstance;
    }

    private RouteParser() {
    }
    //needed stuff
    private ArrayList<Route> mRouteList = new ArrayList<> ();
    private final String TAG = "Route";

    public void parseRoute(FileInputStream rid, Context c) {
        BufferedReader rawReader = new BufferedReader(new InputStreamReader(rid));
        String line = "";
        try {
            while((line = rawReader.readLine()) != null) {
                mRouteList.add(new Route(line));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //first row in file are columns
        mRouteList.remove(0);

        DatabaseDAO dao = new DatabaseDAO(c);
        dao.insertAllRoutes(mRouteList);
        dao.close();
    }

    private void printRoute() {
        for (Route route : mRouteList)
            Log.i(TAG, "name " + route.getRoute_short_name() + "\n");
    }

    public ArrayList<Route> getmRouteList() {
        return mRouteList;
    }
}
