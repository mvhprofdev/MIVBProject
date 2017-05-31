package be.ehb.mivbproject.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mobapp10 on 16/05/17.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "mivbDB.db";
    private final static int DB_VERSION = 1;

    //tabellen die we lokaal willen opslaan
    private final static String TABLE_AGENCY = "agency";
    private final static String TABLE_CALENDAR = "calendars";
    private final static String TABLE_CALENDAR_DATES = "calendar_dates";
    private final static String TABLE_ROUTES = "routes";
    private final static String TABLE_SHAPES = "shapes";
    private final static String TABLE_STOPS = "stops";
    private final static String TABLE_STOP_TIMES = "stoptimes";
    private final static String TABLE_TRIPS = "trips";
    private final static String TABLE_TRANSLATIONS = "translations";

    //kolommen van tabel agency
    private final static String COLUMN_AGENCY_NAME = "agency_id";
    private final static String COLUMN_AGENCY_URL = "agency_url";
    private final static String COLUMN_AGENCY_TIMEZONE = "agency_timezone";
    private final static String COLUMN_AGENCY_LANG = "agency_lang";
    private final static String COLUMN_AGENCY_PHONE = "agency_phone";

    private final static String[] COLUMNS_AGENCY = {COLUMN_AGENCY_NAME, COLUMN_AGENCY_URL,
            COLUMN_AGENCY_TIMEZONE, COLUMN_AGENCY_LANG, COLUMN_AGENCY_PHONE};

    //kolommen van tabel calendars
    private final static String COLUMN_SERVICE_ID = "service_id";
    private final static String COLUMN_MONDAY = "monday";
    private final static String COLUMN_TUESDAY = "tuesday";
    private final static String COLUMN_WEDNESDAY = "wednesday";
    private final static String COLUMN_THURSDAY = "thursday";
    private final static String COLUMN_FRIDAY = "friday";
    private final static String COLUMN_SATURDAY = "saturday";
    private final static String COLUMN_SUNDAY = "sunday";
    private final static String COLUMN_START_DATE = "start_date";
    private final static String COLUMN_END_DATE = "end_date";

    private final static String[] COLUMNS_CALENDARS = {COLUMN_SERVICE_ID, COLUMN_MONDAY,
            COLUMN_TUESDAY, COLUMN_WEDNESDAY, COLUMN_THURSDAY, COLUMN_FRIDAY, COLUMN_SATURDAY,
            COLUMN_SUNDAY, COLUMN_START_DATE, COLUMN_END_DATE};

    //kolommen van tabel calendar_dates
    //private final static String COLUMN_SERVICE_ID = "service_id";
    private final static String COLUMN_DATE = "date";
    private final static String COLUMN_EXCEPTION_TYPE = "exception_type";

    private final static String[] COLUMNS_CALENDAR_DATES = {COLUMN_SERVICE_ID, COLUMN_DATE,
            COLUMN_EXCEPTION_TYPE};

    //kolommen van tabel routes
    private final static String COLUMN_ROUTE_ID = "route_id";
    private final static String COLUMN_ROUTE_SHORT_NAME = "route_short_name";
    private final static String COLUMN_ROUTE_LONG_NAME = "route_long_name";
    private final static String COLUMN_ROUTE_DESC = "route_desc";
    private final static String COLUMN_ROUTE_TYPE = "route_type";
    private final static String COLUMN_ROUTE_URL = "route_url";
    private final static String COLUMN_ROUTE_COLOR = "route_color";
    private final static String COLUMN_ROUTE_TEXT_COLOR = "route_text_color";

    private final static String[] COLUMNS_ROUTES = {COLUMN_ROUTE_ID, COLUMN_ROUTE_SHORT_NAME,
            COLUMN_ROUTE_LONG_NAME, COLUMN_ROUTE_DESC, COLUMN_ROUTE_TYPE, COLUMN_ROUTE_URL,
            COLUMN_ROUTE_COLOR, COLUMN_ROUTE_TEXT_COLOR};

    //kolommen van tabel shapes
    private final static String COLUMN_SHAPE_ID = "shape_id";
    private final static String COLUMN_SHAPE_PT_LAT = "shape_pt_lat";
    private final static String COLUMN_SHAPE_PT_LON = "shape_pt_lon";
    private final static String COLUMN_SHAPE_PT_SEQUENCE = "shape_pt_sequence";

    private final static String[] COLUMNS_SHAPES = {COLUMN_SHAPE_ID, COLUMN_SHAPE_PT_LAT,
            COLUMN_SHAPE_PT_LON, COLUMN_SHAPE_PT_SEQUENCE};

    //kolommen van tabel stops
    private final static String COLUMN_STOP_ID = "stop_id";
    private final static String COLUMN_STOP_CODE = "stop_code";
    private final static String COLUMN_STOP_NAME = "stop_name";
    private final static String COLUMN_STOP_DESC = "stop_desc";
    private final static String COLUMN_STOP_LAT = "stop_lat";
    private final static String COLUMN_STOP_LON = "stop_lon";
    private final static String COLUMN_ZONE_ID = "zone_id";
    private final static String COLUMN_STOP_URL = "stop_url";
    private final static String COLUMN_LOCATION_TYPE = "location_type";

    private final static String[] COLUMNS_STOPS = {COLUMN_STOP_ID, COLUMN_STOP_CODE,
            COLUMN_STOP_NAME, COLUMN_STOP_DESC, COLUMN_STOP_LAT, COLUMN_STOP_LON, COLUMN_ZONE_ID,
            COLUMN_STOP_URL, COLUMN_LOCATION_TYPE};

    //kolommen van tabel stoptimes
    private final static String COLUMN_TRIP_ID = "trip_id";
    private final static String COLUMN_ARRIVAL_TIME = "arrival_time";
    private final static String COLUMN_DEPARTURE_TIME = "departure_time";
    //private final static String COLUMN_STOP_ID = "stop_id";
    private final static String COLUMN_STOP_SEQUENCE = "stop_sequence";
    private final static String COLUMN_PICKUP_TYPE = "pickup_type";
    private final static String COLUMN_DROP_OFF_TYPE = "drop_off_type";

    private final static String[] COLUMNS_STOP_TIMES = {COLUMN_TRIP_ID, COLUMN_ARRIVAL_TIME,
            COLUMN_DEPARTURE_TIME, COLUMN_STOP_ID, COLUMN_STOP_SEQUENCE, COLUMN_PICKUP_TYPE,
            COLUMN_DROP_OFF_TYPE};

    //kolommen van tabel trips
    //private final static String COLUMN_ROUTE_ID = "route_id";
    //private final static String COLUMN_SERVICE_ID = "service_id";
    //private final static String COLUMN_TRIP_ID = "trip_id";
    private final static String COLUMN_TRIP_HEADSIGN = "trip_headsign";
    private final static String COLUMN_DIRECTION_ID = "direction_id";
    private final static String COLUMN_BLOCK_ID = "block_id";
    //private final static String COLUMN_SHAPE_ID = "shape_id";

    private final static String[] COLUMNS_TRIPS = {COLUMN_ROUTE_ID, COLUMN_SERVICE_ID,
            COLUMN_TRIP_ID, COLUMN_TRIP_HEADSIGN, COLUMN_DIRECTION_ID, COLUMN_BLOCK_ID,
            COLUMN_BLOCK_ID, COLUMN_SHAPE_ID};


    //kolommen van tabel translations
    private final static String COLUMN_TRANS_ID = "trans_id";
    private final static String COLUMN_TRANSLATION = "translation";
    private final static String COLUMN_LANG = "lang";

    private final static String[] COLUMNS_TRANSLATIONS = {COLUMN_TRANS_ID, COLUMN_TRANSLATION,
            COLUMN_LANG};


    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO execsql beter maken
        db.execSQL("CREATE TABLE " + TABLE_AGENCY + " ("
                + COLUMN_AGENCY_NAME + "TEXT, "
                + COLUMN_AGENCY_URL + "TEXT, "
                + COLUMN_AGENCY_TIMEZONE + "TEXT, "
                + COLUMN_AGENCY_LANG + "TEXT, "
                + COLUMN_AGENCY_PHONE + "TEXT"
                + " );"
        );

        db.execSQL("CREATE TABLE " + TABLE_CALENDAR + " ("
                + COLUMN_SERVICE_ID + "TEXT NOT NULL, "
                + COLUMN_MONDAY + "TEXT NOT NULL, "
                + COLUMN_TUESDAY + "TEXT NOT NULL, "
                + COLUMN_WEDNESDAY + "TEXT NOT NULL, "
                + COLUMN_THURSDAY + "TEXT NOT NULL, "
                + COLUMN_FRIDAY + "TEXT NOT NULL, "
                + COLUMN_SATURDAY + "TEXT NOT NULL, "
                + COLUMN_START_DATE + "TEXT NOT NULL, "
                + COLUMN_END_DATE + "TEXT NOT NULL"
                + " );"
        );

        db.execSQL("CREATE TABLE " + TABLE_CALENDAR_DATES + " ("
                + COLUMN_SERVICE_ID + "TEXT NOT NULL, "
                + COLUMN_DATE + "TEXT NOT NULL, "
                + COLUMN_EXCEPTION_TYPE + "TEXT NOT NULL"
                + " );"
        );

        db.execSQL("CREATE TABLE " + TABLE_ROUTES + " ("
                + COLUMN_ROUTE_ID + " TEXT NOT NULL, "
                + COLUMN_ROUTE_SHORT_NAME + " TEXT NOT NULL, "
                + COLUMN_ROUTE_LONG_NAME + " TEXT NOT NULL, "
                + COLUMN_ROUTE_DESC + " TEXT, "
                + COLUMN_ROUTE_TYPE + " TEXT, "
                + COLUMN_ROUTE_URL + " TEXT, "
                + COLUMN_ROUTE_COLOR + " TEXT, "
                + COLUMN_ROUTE_TEXT_COLOR + " TEXT"
                + " );"
        );

        db.execSQL("CREATE TABLE " + TABLE_SHAPES + " ("
                + COLUMN_SHAPE_ID + "TEXT NOT NULL, "
                + COLUMN_SHAPE_PT_LAT + "TEXT NOT NULL, "
                + COLUMN_SHAPE_PT_LON + "TEXT NOT NULL, "
                + COLUMN_SHAPE_PT_SEQUENCE + "TEXT NOT NULL"
                + " );"
        );

        db.execSQL("CREATE TABLE " + TABLE_STOP_TIMES + " ("
                + COLUMN_TRIP_ID + "TEXT NOT NULL, "
                + COLUMN_ARRIVAL_TIME + "TEXT NOT NULL, "
                + COLUMN_DEPARTURE_TIME + "TEXT NOT NULL, "
                + COLUMN_STOP_ID + "TEXT NOT NULL, "
                + COLUMN_STOP_SEQUENCE + "TEXT NOT NULL, "
                + COLUMN_PICKUP_TYPE + "TEXT NOT NULL, "
                + COLUMN_DROP_OFF_TYPE + "TEXT NOT NULL"
                + " );"
        );

        db.execSQL("CREATE TABLE " + TABLE_STOPS + " ("
                + COLUMN_STOP_ID + " TEXT NOT NULL, "
                + COLUMN_STOP_CODE + " TEXT NOT NULL, "
                + COLUMN_STOP_NAME + " TEXT NOT NULL, "
                + COLUMN_STOP_DESC + " TEXT, "
                + COLUMN_STOP_LAT+ " TEXT NOT NULL, "
                + COLUMN_STOP_LON + " TEXT NOT NULL, "
                + COLUMN_ZONE_ID + " TEXT, "
                + COLUMN_STOP_URL + " TEXT, "
                + COLUMN_LOCATION_TYPE + " TEXT"
                + " );"
        );

        db.execSQL("CREATE TABLE " + TABLE_TRANSLATIONS + " ("
                + COLUMN_TRANS_ID + "TEXT, "
                + COLUMN_TRANSLATION + "TEXT, "
                + COLUMN_LANG + "TEXT"
                + " );"
        );

        db.execSQL("CREATE TABLE " + TABLE_TRIPS + " ("
                + COLUMN_ROUTE_ID + "TEXT, "
                + COLUMN_SERVICE_ID + "TEXT, "
                + COLUMN_TRIP_ID + "TEXT, "
                + COLUMN_TRIP_HEADSIGN + "TEXT, "
                + COLUMN_DIRECTION_ID + "TEXT, "
                + COLUMN_BLOCK_ID + "TEXT, "
                + COLUMN_SHAPE_ID + "TEXT"
                + " );"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AGENCY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALENDAR_DATES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALENDAR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSLATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHAPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROUTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STOP_TIMES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STOPS);

        onCreate(db);
    }


    public static String getDbName() {
        return DB_NAME;
    }

    public static int getDbVersion() {
        return DB_VERSION;
    }

    public static String getTableAgency() {
        return TABLE_AGENCY;
    }

    public static String getTableCalendar() {
        return TABLE_CALENDAR;
    }

    public static String getTableCalendarDates() {
        return TABLE_CALENDAR_DATES;
    }

    public static String getTableRoutes() {
        return TABLE_ROUTES;
    }

    public static String getTableShapes() {
        return TABLE_SHAPES;
    }

    public static String getTableStops() {
        return TABLE_STOPS;
    }

    public static String getTableStopTimes() {
        return TABLE_STOP_TIMES;
    }

    public static String getTableTrips() {
        return TABLE_TRIPS;
    }

    public static String getTableTranslations() {
        return TABLE_TRANSLATIONS;
    }

    public static String getColumnAgencyName() {
        return COLUMN_AGENCY_NAME;
    }

    public static String getColumnAgencyUrl() {
        return COLUMN_AGENCY_URL;
    }

    public static String getColumnAgencyTimezone() {
        return COLUMN_AGENCY_TIMEZONE;
    }

    public static String getColumnAgencyLang() {
        return COLUMN_AGENCY_LANG;
    }

    public static String getColumnAgencyPhone() {
        return COLUMN_AGENCY_PHONE;
    }

    public static String[] getColumnsAgency() {
        return COLUMNS_AGENCY;
    }

    public static String getColumnServiceId() {
        return COLUMN_SERVICE_ID;
    }

    public static String getColumnMonday() {
        return COLUMN_MONDAY;
    }

    public static String getColumnTuesday() {
        return COLUMN_TUESDAY;
    }

    public static String getColumnWednesday() {
        return COLUMN_WEDNESDAY;
    }

    public static String getColumnThursday() {
        return COLUMN_THURSDAY;
    }

    public static String getColumnFriday() {
        return COLUMN_FRIDAY;
    }

    public static String getColumnSaturday() {
        return COLUMN_SATURDAY;
    }

    public static String getColumnSunday() {
        return COLUMN_SUNDAY;
    }

    public static String getColumnStartDate() {
        return COLUMN_START_DATE;
    }

    public static String getColumnEndDate() {
        return COLUMN_END_DATE;
    }

    public static String[] getColumnsCalendars() {
        return COLUMNS_CALENDARS;
    }

    public static String getColumnDate() {
        return COLUMN_DATE;
    }

    public static String getColumnExceptionType() {
        return COLUMN_EXCEPTION_TYPE;
    }

    public static String[] getColumnsCalendarDates() {
        return COLUMNS_CALENDAR_DATES;
    }

    public static String getColumnRouteId() {
        return COLUMN_ROUTE_ID;
    }

    public static String getColumnRouteShortName() {
        return COLUMN_ROUTE_SHORT_NAME;
    }

    public static String getColumnRouteLongName() {
        return COLUMN_ROUTE_LONG_NAME;
    }

    public static String getColumnRouteDesc() {
        return COLUMN_ROUTE_DESC;
    }

    public static String getColumnRouteType() {
        return COLUMN_ROUTE_TYPE;
    }

    public static String getColumnRouteUrl() {
        return COLUMN_ROUTE_URL;
    }

    public static String getColumnRouteColor() {
        return COLUMN_ROUTE_COLOR;
    }

    public static String getColumnRouteTextColor() {
        return COLUMN_ROUTE_TEXT_COLOR;
    }

    public static String[] getColumnsRoutes() {
        return COLUMNS_ROUTES;
    }

    public static String getColumnShapeId() {
        return COLUMN_SHAPE_ID;
    }

    public static String getColumnShapePtLat() {
        return COLUMN_SHAPE_PT_LAT;
    }

    public static String getColumnShapePtLon() {
        return COLUMN_SHAPE_PT_LON;
    }

    public static String getColumnShapePtSequence() {
        return COLUMN_SHAPE_PT_SEQUENCE;
    }

    public static String[] getColumnsShapes() {
        return COLUMNS_SHAPES;
    }

    public static String getColumnStopId() {
        return COLUMN_STOP_ID;
    }

    public static String getColumnStopCode() {
        return COLUMN_STOP_CODE;
    }

    public static String getColumnStopName() {
        return COLUMN_STOP_NAME;
    }

    public static String getColumnStopDesc() {
        return COLUMN_STOP_DESC;
    }

    public static String getColumnStopLat() {
        return COLUMN_STOP_LAT;
    }

    public static String getColumnStopLon() {
        return COLUMN_STOP_LON;
    }

    public static String getColumnZoneId() {
        return COLUMN_ZONE_ID;
    }

    public static String getColumnStopUrl() {
        return COLUMN_STOP_URL;
    }

    public static String getColumnLocationType() {
        return COLUMN_LOCATION_TYPE;
    }

    public static String[] getColumnsStops() {
        return COLUMNS_STOPS;
    }

    public static String getColumnTripId() {
        return COLUMN_TRIP_ID;
    }

    public static String getColumnArrivalTime() {
        return COLUMN_ARRIVAL_TIME;
    }

    public static String getColumnDepartureTime() {
        return COLUMN_DEPARTURE_TIME;
    }

    public static String getColumnStopSequence() {
        return COLUMN_STOP_SEQUENCE;
    }

    public static String getColumnPickupType() {
        return COLUMN_PICKUP_TYPE;
    }

    public static String getColumnDropOffType() {
        return COLUMN_DROP_OFF_TYPE;
    }

    public static String[] getColumnsStopTimes() {
        return COLUMNS_STOP_TIMES;
    }

    public static String getColumnTripHeadsign() {
        return COLUMN_TRIP_HEADSIGN;
    }

    public static String getColumnDirectionId() {
        return COLUMN_DIRECTION_ID;
    }

    public static String getColumnBlockId() {
        return COLUMN_BLOCK_ID;
    }

    public static String[] getColumnsTrips() {
        return COLUMNS_TRIPS;
    }

    public static String getColumnTransId() {
        return COLUMN_TRANS_ID;
    }

    public static String getColumnTranslation() {
        return COLUMN_TRANSLATION;
    }

    public static String getColumnLang() {
        return COLUMN_LANG;
    }

    public static String[] getColumnsTranslations() {
        return COLUMNS_TRANSLATIONS;
    }
}
