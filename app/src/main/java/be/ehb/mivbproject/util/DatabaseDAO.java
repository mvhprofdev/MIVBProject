package be.ehb.mivbproject.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.AutoCompleteTextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import be.ehb.mivbproject.R;
import be.ehb.mivbproject.fragments.ZoekenFragment;
import be.ehb.mivbproject.source.Agency;
import be.ehb.mivbproject.source.Calendar;
import be.ehb.mivbproject.source.Calendar_Dates;
import be.ehb.mivbproject.source.Route;
import be.ehb.mivbproject.source.Shape;
import be.ehb.mivbproject.source.Stop;
import be.ehb.mivbproject.source.Stoptime;
import be.ehb.mivbproject.source.Trip;


/**
 * Created by mobapp10 on 17/05/17.
 */

public class DatabaseDAO {

    private SQLiteHelper dbHelper;
    private SQLiteDatabase db;


    public DatabaseDAO(Context c) {
        dbHelper = new SQLiteHelper(c);
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertEntry() {
        //TODO dit nog in orde brengen
        if(db == null) {
            ContentValues mValues = new ContentValues();

            return false;
        }
        return true;

    }

    //AGENCY----------------------------------------------------------------------------------------
/*
    public ArrayList<Agency> getAllAgency() {
        ArrayList<Agency> allAgency = new ArrayList<>();

        Cursor cursorAgency = db.query(SQLiteHelper.getTableAgency(), SQLiteHelper.getColumnsAgency(),
                null, null, null, null, null, null);
        cursorAgency.moveToFirst();

        while (!cursorAgency.isAfterLast()) {
            Agency temp = cursorToAgency(cursorAgency);
            allAgency.add(temp);
            cursorAgency.moveToNext();
        }
        cursorAgency.close();

        return allAgency;
    }

    public boolean insertAllAgency (ArrayList<Agency> agencyArrayList) {

        ContentValues mValues = new ContentValues();
        for(Agency a : agencyArrayList) {
            mValues.put(SQLiteHelper.getColumnAgencyName(), a.getAgency_name());
            mValues.put(SQLiteHelper.getColumnAgencyUrl(), a.getAgency_url());
            mValues.put(SQLiteHelper.getColumnAgencyTimezone(), a.getAgency_timezone());
            mValues.put(SQLiteHelper.getColumnAgencyLang(), a.getAgency_lang());
            mValues.put(SQLiteHelper.getColumnAgencyPhone(), a.getAgency_phone());

            long resultID = db.insert(SQLiteHelper.getTableAgency(), null, mValues);

            if(resultID == -1)
                return false;
        }
        return true;
    }

    private Agency cursorToAgency (Cursor cursorAgency) {
        Agency temp = new Agency();

        temp.setAgency_name(cursorAgency.getString(0));
        temp.setAgency_url(cursorAgency.getString(1));
        temp.setAgency_timezone(cursorAgency.getString(2));
        temp.setAgency_lang(cursorAgency.getString(3));
        temp.setAgency_phone(cursorAgency.getString(4));

        return temp;
    }
*/
    //CALENDAR--------------------------------------------------------------------------------------

    public ArrayList<Calendar> getAllCalendars() {
        ArrayList<Calendar> allCalendars = new ArrayList<>();

        Cursor cursorCalendar = db.query(SQLiteHelper.getTableCalendar(), SQLiteHelper.getColumnsCalendars(),
                null, null, null, null, null, null);
        cursorCalendar.moveToFirst();

        while (!cursorCalendar.isAfterLast()) {
            Calendar temp = cursorToCalendar(cursorCalendar);
            allCalendars.add(temp);
            cursorCalendar.moveToNext();
        }
        cursorCalendar.close();

        return allCalendars;
    }

    public boolean insertAllCalendars(ArrayList<Calendar> calendarArrayList) {

        ContentValues mValues = new ContentValues();
        for(Calendar c : calendarArrayList) {
            mValues.put(SQLiteHelper.getColumnServiceId(), c.getService_id());
            mValues.put(SQLiteHelper.getColumnMonday(), c.getMonday());
            mValues.put(SQLiteHelper.getColumnTuesday(), c.getTuesday());
            mValues.put(SQLiteHelper.getColumnWednesday(), c.getWednesday());
            mValues.put(SQLiteHelper.getColumnThursday(), c.getThursday());
            mValues.put(SQLiteHelper.getColumnFriday(), c.getFriday());
            mValues.put(SQLiteHelper.getColumnSaturday(), c.getSaturday());
            mValues.put(SQLiteHelper.getColumnSunday(), c.getSunday());
            mValues.put(SQLiteHelper.getColumnStartDate(), c.getStart_date());
            mValues.put(SQLiteHelper.getColumnEndDate(), c.getEnd_date());

            long resultID = db.insert(SQLiteHelper.getTableCalendar(), null, mValues);

            if(resultID == -1)
                return false;
        }
        return true;
    }

    private Calendar cursorToCalendar (Cursor cursorCalendar) {
        Calendar temp = new Calendar();

        temp.setService_id(cursorCalendar.getString(0));
        temp.setMonday(cursorCalendar.getString(1));
        temp.setTuesday(cursorCalendar.getString(2));
        temp.setWednesday(cursorCalendar.getString(3));
        temp.setThursday(cursorCalendar.getString(4));
        temp.setFriday(cursorCalendar.getString(5));
        temp.setSaturday(cursorCalendar.getString(6));
        temp.setSunday(cursorCalendar.getString(7));
        temp.setStart_date(cursorCalendar.getString(8));
        temp.setEnd_date(cursorCalendar.getString(9));

        return temp;
    }

    //CALENDAR_DATES--------------------------------------------------------------------------------
/*
    public ArrayList<Calendar_Dates> getAllCalendarDates() {
        ArrayList<Calendar_Dates> allCalendarDates = new ArrayList<>();

        Cursor cursorCalendarDates = db.query(SQLiteHelper.getTableCalendarDates(),
                SQLiteHelper.getColumnsCalendarDates(),
                null, null, null, null, null, null);
        cursorCalendarDates.moveToFirst();

        while (!cursorCalendarDates.isAfterLast()) {
            Calendar_Dates temp = cursorToCalendarDates(cursorCalendarDates);
            allCalendarDates.add(temp);
            cursorCalendarDates.moveToNext();
        }
        cursorCalendarDates.close();

        return allCalendarDates;
    }

    public boolean insertAllCalendarDates(ArrayList<Calendar_Dates> calendarDatesArrayList) {

        ContentValues mValues = new ContentValues();
        for(Calendar_Dates cd : calendarDatesArrayList) {
            mValues.put(SQLiteHelper.getColumnServiceId(), cd.getService_id());
            mValues.put(SQLiteHelper.getColumnMonday(), cd.getDate());
            mValues.put(SQLiteHelper.getColumnTuesday(), cd.getException_type());

            long resultID = db.insert(SQLiteHelper.getTableCalendarDates(), null, mValues);

            if(resultID == -1)
                return false;
        }
        return true;
    }

    private Calendar_Dates cursorToCalendarDates (Cursor cursor) {
        Calendar_Dates temp = new Calendar_Dates();

        temp.setService_id(cursor.getString(0));
        temp.setDate(cursor.getString(1));
        temp.setException_type(cursor.getString(2));

        return temp;
    }
*/
    //ROUTES----------------------------------------------------------------------------------------

    public ArrayList<Route> getAllRoutes() {
        ArrayList<Route> allRoutes = new ArrayList<>();

        Cursor cursor = db.query(SQLiteHelper.getTableRoutes(), SQLiteHelper.getColumnsRoutes(),
                null, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Route temp = cursorToRoute(cursor);
            allRoutes.add(temp);
            cursor.moveToNext();
        }
        cursor.close();

        return allRoutes;
    }

    public ArrayList<Route> getDistinctRoutes(){
        ArrayList<Route> allDistinctRoutes = new ArrayList<>();

        Cursor cursorDistinctRoutes = db.query(SQLiteHelper.getTableRoutes(),
                SQLiteHelper.getColumnsRoutes(), null, null,
                SQLiteHelper.getColumnRouteId(), null, null);

        cursorDistinctRoutes.moveToFirst();

        while (!cursorDistinctRoutes.isAfterLast()) {
            Route temp = cursorToRoute(cursorDistinctRoutes);
            allDistinctRoutes.add(temp);
            cursorDistinctRoutes.moveToNext();
        }
        cursorDistinctRoutes.close();

        return allDistinctRoutes;
    }

    public boolean insertAllRoutes(ArrayList<Route> routeArrayList) {

        ContentValues mValues = new ContentValues();
        for(Route r : routeArrayList) {
            mValues.put(SQLiteHelper.getColumnRouteId(), r.getRoute_id());
            mValues.put(SQLiteHelper.getColumnRouteShortName(), r.getRoute_short_name());
            mValues.put(SQLiteHelper.getColumnRouteLongName(), r.getRoute_long_name());
            mValues.put(SQLiteHelper.getColumnRouteDesc(), r.getRoute_desc());
            mValues.put(SQLiteHelper.getColumnRouteType(), r.getRoute_type());
            mValues.put(SQLiteHelper.getColumnRouteUrl(), r.getRoute_url());
            mValues.put(SQLiteHelper.getColumnRouteColor(), r.getRoute_color());
            mValues.put(SQLiteHelper.getColumnRouteTextColor(), r.getRoute_text_color());

            long resultID = db.insert(SQLiteHelper.getTableRoutes(), null, mValues);

            if(resultID == -1)
                return false;
        }
            return true;
    }

    private Route cursorToRoute (Cursor cursor) {
        Route temp = new Route();

        temp.setRoute_id(cursor.getString(0));
        temp.setRoute_short_name(cursor.getString(1));
        temp.setRoute_long_name(cursor.getString(2));
        temp.setRoute_desc(cursor.getString(3));
        temp.setRoute_type(cursor.getString(4));
        temp.setRoute_url(cursor.getString(5));
        temp.setRoute_color(cursor.getString(6));
        temp.setRoute_text_color(cursor.getString(7));

        return temp;
    }

    //SHAPE-----------------------------------------------------------------------------------------
/*
    public ArrayList<Shape> getAllShapes() {
        ArrayList<Shape> allShapes = new ArrayList<>();

        Cursor cursorShapes = db.query(SQLiteHelper.getTableShapes(), SQLiteHelper.getColumnsShapes(),
                null, null, null, null, null, null);
        cursorShapes.moveToFirst();

        while (!cursorShapes.isAfterLast()) {
            Shape temp = cursorToShape(cursorShapes);
            allShapes.add(temp);
            cursorShapes.moveToNext();
        }
        cursorShapes.close();

        return allShapes;
    }

    public boolean insertAllShapes(ArrayList<Shape> shapeArrayList) {

        ContentValues mValues = new ContentValues();
        for(Shape s : shapeArrayList) {
            mValues.put(SQLiteHelper.getColumnShapeId(), s.getShape_id());
            mValues.put(SQLiteHelper.getColumnShapePtLat(), s.getShape_pt_lat());
            mValues.put(SQLiteHelper.getColumnShapePtLon(), s.getShape_pt_lon());
            mValues.put(SQLiteHelper.getColumnShapePtSequence(), s.getShape_pt_sequence());

            long resultID = db.insert(SQLiteHelper.getTableShapes(), null, mValues);

            if(resultID == -1)
                return false;
        }
        return true;
    }

    private Shape cursorToShape (Cursor cursorShape) {
        Shape temp = new Shape();

        temp.setShape_id(cursorShape.getString(0));
        temp.setShape_pt_lat(cursorShape.getString(1));
        temp.setShape_pt_lon(cursorShape.getString(2));
        temp.setShape_pt_sequence(cursorShape.getString(3));

        return temp;
    }
*/
    //STOP------------------------------------------------------------------------------------------

    public ArrayList<Stop> getAllStops() {
        ArrayList<Stop> allStops = new ArrayList<>();

        Cursor cursorStop = db.query(SQLiteHelper.getTableStops(), SQLiteHelper.getColumnsStops(),
                null, null, null, null, null, null);
        cursorStop.moveToFirst();

        while (!cursorStop.isAfterLast()) {
            Stop temp = cursorToStop(cursorStop);
            allStops.add(temp);
            cursorStop.moveToNext();
        }
        cursorStop.close();

        return allStops;
    }

    public ArrayList<Stop> getDistinctStopNames(){
        ArrayList<Stop> allDistinctNames = new ArrayList<>();

        Cursor cursorDistinctStopNames = db.query(SQLiteHelper.getTableStops(),
                SQLiteHelper.getColumnsStops(), null, null,
                SQLiteHelper.getColumnStopName(), null, null);
        cursorDistinctStopNames.moveToFirst();

        while (!cursorDistinctStopNames.isAfterLast()) {
            Stop temp = cursorToStop(cursorDistinctStopNames);
            allDistinctNames.add(temp);
            cursorDistinctStopNames.moveToNext();
        }
        cursorDistinctStopNames.close();

        return allDistinctNames;
    }




    public ArrayList<Stop> getLatStop() {
        ArrayList<Stop> allLatStop = new ArrayList<>();

        Cursor cursorLatStop = db.query(SQLiteHelper.getTableStops(),
                SQLiteHelper.getColumnsStops(), null,
                null, null, null, null);
        cursorLatStop.moveToFirst();

        while (!cursorLatStop.isAfterLast()) {
            Stop temp = cursorToStop(cursorLatStop);
            allLatStop.add(temp);
            cursorLatStop.moveToNext();
        }
        cursorLatStop.close();

        return allLatStop;
    }

    public ArrayList<Stop> getLonStop() {
        ArrayList<Stop> allLonStop = new ArrayList<>();

        Cursor cursorLonStop = db.query(SQLiteHelper.getTableStops(),
                SQLiteHelper.getColumnsStops(), null,
                null, null, null, null);
        cursorLonStop.moveToFirst();

        while (!cursorLonStop.isAfterLast()) {
            Stop temp = cursorToStop(cursorLonStop);
            allLonStop.add(temp);
            cursorLonStop.moveToNext();
        }
        cursorLonStop.close();

        return allLonStop;
    }

    public boolean insertAllStops(ArrayList<Stop> stopArrayList) {

        ContentValues mValues = new ContentValues();
        for(Stop s : stopArrayList) {
            mValues.put(SQLiteHelper.getColumnStopId(), s.getStop_id());
            mValues.put(SQLiteHelper.getColumnStopCode(), s.getStop_code());
            mValues.put(SQLiteHelper.getColumnStopName(), s.getStop_name());
            mValues.put(SQLiteHelper.getColumnStopDesc(), s.getStop_desc());
            mValues.put(SQLiteHelper.getColumnStopLat(), s.getStop_lat());
            mValues.put(SQLiteHelper.getColumnStopLon(), s.getStop_lon());
            mValues.put(SQLiteHelper.getColumnZoneId(), s.getZone_id());
            mValues.put(SQLiteHelper.getColumnStopUrl(), s.getStop_url());
            mValues.put(SQLiteHelper.getColumnLocationType(), s.getLocation_type());

            long resultID = db.insert(SQLiteHelper.getTableStops(), null, mValues);

            if(resultID == -1)
                return false;
        }
        Log.i("Stops", "Stops zijn geladen");
        return true;
    }

    private Stop cursorToStop (Cursor cursor) {
        Stop temp = new Stop();

        temp.setStop_id(cursor.getString(0));
        temp.setStop_code(cursor.getString(1));
        temp.setStop_name(cursor.getString(2));
        temp.setStop_desc(cursor.getString(3));
        temp.setStop_lat(cursor.getString(4));
        temp.setStop_lon(cursor.getString(5));
        temp.setZone_id(cursor.getString(6));
        temp.setStop_url(cursor.getString(7));
        temp.setLocation_type(cursor.getString(8));

        return temp;
    }

    //STOPTIME--------------------------------------------------------------------------------------
/*
    public ArrayList<Stoptime> getAllStoptime() {
        ArrayList<Stoptime> allStoptimes = new ArrayList<>();

        Cursor cursorStoptime = db.query(SQLiteHelper.getTableStoptimes(), SQLiteHelper.getColumnsStopTimes(),
                null, null, null, null, null, null);
        cursorStoptime.moveToFirst();

        while (!cursorStoptime.isAfterLast()) {
            Stoptime temp = cursorToStoptime(cursorStoptime);
            allStoptimes.add(temp);
            cursorStoptime.moveToNext();
        }
        cursorStoptime.close();

        return allStoptimes;
    }

    public boolean insertAllStoptimes (ArrayList<Stoptime> stoptimeArrayList) {

        ContentValues mValues = new ContentValues();
        for(Stoptime st : stoptimeArrayList) {
            mValues.put(SQLiteHelper.getColumnTripId(), st.getTrip_id());
            mValues.put(SQLiteHelper.getColumnArrivalTime(), st.getArrival_time());
            mValues.put(SQLiteHelper.getColumnDepartureTime(), st.getDeparture_time());
            mValues.put(SQLiteHelper.getColumnStopId(), st.getStop_id());
            mValues.put(SQLiteHelper.getColumnStopSequence(), st.getStop_sequence());
            mValues.put(SQLiteHelper.getColumnPickupType(), st.getPickup_type());
            mValues.put(SQLiteHelper.getColumnDropOffType(), st.getDrop_off_type());

            long resultID = db.insert(SQLiteHelper.getTableStoptimes(), null, mValues);

            if(resultID == -1)
                return false;
        }
        return true;
    }

    private Stoptime cursorToStoptime (Cursor cursorStoptime) {
        Stoptime temp = new Stoptime();

        temp.setTrip_id(cursorStoptime.getString(0));
        temp.setArrival_time(cursorStoptime.getString(1));
        temp.setDeparture_time(cursorStoptime.getString(2));
        temp.setStop_id(cursorStoptime.getString(3));
        temp.setStop_sequence(cursorStoptime.getString(4));
        temp.setPickup_type(cursorStoptime.getString(5));
        temp.setDrop_off_type(cursorStoptime.getString(6));

        return temp;
    }
*/
    //TRIP------------------------------------------------------------------------------------------
/*
    public ArrayList<Trip> getAllTrips() {
        ArrayList<Trip> allTrips = new ArrayList<>();

        Cursor cursorTrip = db.query(SQLiteHelper.getTableTrips(), SQLiteHelper.getColumnsTrips(),
                null, null, null, null, null, null);
        cursorTrip.moveToFirst();

        while (!cursorTrip.isAfterLast()) {
            Trip temp = cursorToTrip(cursorTrip);
            allTrips.add(temp);
            cursorTrip.moveToNext();
        }
        cursorTrip.close();

        return allTrips;
    }

    public boolean insertAllTrips(ArrayList<Trip> tripArrayList) {

        ContentValues mValues = new ContentValues();
        for(Trip trip : tripArrayList) {
            mValues.put(SQLiteHelper.getColumnRouteId(), trip.getTrip_id());
            mValues.put(SQLiteHelper.getColumnServiceId(), trip.getService_id());
            mValues.put(SQLiteHelper.getColumnTripId(), trip.getTrip_id());
            mValues.put(SQLiteHelper.getColumnTripHeadsign(), trip.getTrip_headsign());
            mValues.put(SQLiteHelper.getColumnDirectionId(), trip.getDirection_id());
            mValues.put(SQLiteHelper.getColumnBlockId(), trip.getBlock_id());
            mValues.put(SQLiteHelper.getColumnShapeId(), trip.getShape_id());

            long resultID = db.insert(SQLiteHelper.getTableTrips(), null, mValues);

            if(resultID == -1)
                return false;
        }
        return true;
    }

    private Trip cursorToTrip (Cursor cursorTrip) {
        Trip temp = new Trip();

        temp.setRoute_id(cursorTrip.getString(0));
        temp.setService_id(cursorTrip.getString(1));
        temp.setTrip_id(cursorTrip.getString(2));
        temp.setTrip_headsign(cursorTrip.getString(3));
        temp.setDirection_id(cursorTrip.getString(4));
        temp.setBlock_id(cursorTrip.getString(5));
        temp.setShape_id(cursorTrip.getString(6));

        return temp;
    }
*/

}
