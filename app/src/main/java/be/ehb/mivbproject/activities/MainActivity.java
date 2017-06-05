package be.ehb.mivbproject.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import be.ehb.mivbproject.R;
import be.ehb.mivbproject.fragments.ListFragment;
import be.ehb.mivbproject.fragments.MapFragment;
import be.ehb.mivbproject.fragments.RouteListFragment;
import be.ehb.mivbproject.fragments.SettingsFragment;
import be.ehb.mivbproject.fragments.ZoekenFragment;
import be.ehb.mivbproject.parsers.AgencyParser;
import be.ehb.mivbproject.parsers.CalendarParser;
import be.ehb.mivbproject.parsers.Calendar_DatesParser;
import be.ehb.mivbproject.parsers.RouteParser;
import be.ehb.mivbproject.parsers.ShapeParser;
import be.ehb.mivbproject.parsers.StopParser;
import be.ehb.mivbproject.parsers.StoptimeParser;
import be.ehb.mivbproject.parsers.TripParser;
import be.ehb.mivbproject.requests.InputStreamRequest;
import be.ehb.mivbproject.source.Stop;
import be.ehb.mivbproject.util.DatabaseDAO;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        ActivityCompat.OnRequestPermissionsResultCallback, ZoekenFragment.DataPassListener {

    ArrayList<Stop> mStopList = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private final String FRAGMENT_BACKSTACK = "fragment_backstack";
    private GoogleMap mMap;
    private RouteListFragment mRoute = RouteListFragment.newInstance();
    private MapFragment mapFragment = new MapFragment();
    private ZoekenFragment zoekenFragment = new ZoekenFragment();
    private SettingsFragment settingsFragment = new SettingsFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if(sharedPreferences.getBoolean("first", true)) {
            downloadZIP();
        }



        getFragmentManager().beginTransaction()
                .replace(R.id.container, zoekenFragment)
                .addToBackStack(FRAGMENT_BACKSTACK)
                .commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id)
        {
            //HOME = zoeken functie met vertrek & bestemmingspunt, datum en uur
            case R.id.nav_home:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, zoekenFragment)
                        .addToBackStack(FRAGMENT_BACKSTACK)
                        .commit();
                break;
            //Ga naar lijst weergave
            case R.id.nav_list:
                ListFragment listFragment = ListFragment.newInstance();
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, listFragment)
                        .addToBackStack(FRAGMENT_BACKSTACK)
                        .commit();
                break;
            //Ga naar Map weergave
            case R.id.nav_map:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, mapFragment)
                        .addToBackStack(FRAGMENT_BACKSTACK)
                        .commit();
                break;
            //Ga naar meer gedetailleerde listView van routes
            case R.id.nav_detaillist:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, mRoute)
                        .addToBackStack(FRAGMENT_BACKSTACK)
                        .commit();
                break;

            //Ga naar Opties (a.k.a. Settings, Preferences)
            case R.id.nav_settings:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, settingsFragment)
                        .addToBackStack(FRAGMENT_BACKSTACK)
                        .commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void downloadZIP() {
        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        //params voor header
        HashMap<String, String> params = new HashMap<>();

        params.put("Authorization:", "Bearer 81f11d4c20401a22a38e0138c3713d66");
        Toast.makeText(this, "download accessed", Toast.LENGTH_SHORT).show();
        //headers kan je niet setten, fast and dirty de klasse overschrijven
        InputStreamRequest getRequest = new InputStreamRequest(Request.Method.GET,
                "https://opendata-api.stib-mivb.be/Files/1.0/Gtfs",
                responseGETListener,
                responseGETErrorListener,
                params
        );
        Toast.makeText(this, "stream started", Toast.LENGTH_SHORT).show();
        mQueue.add(getRequest);
    }

    private Response.Listener<byte[]> responseGETListener = new Response.Listener<byte[]>() {
        @Override
        public void onResponse(byte[] response) {

            //http://stackoverflow.com/questions/8367126/how-can-i-convert-byte-array-to-zip-file
            //https://techstricks.com/download-file-using-android-volley/

            try {
                //set the path where we want to save the file
                //in this case, going to save it on the cache directory of the project
                File cacheDir = getCacheDir();

                ZipInputStream zipStream = new ZipInputStream(new ByteArrayInputStream(response));
                ZipEntry entry;

                while ((entry = zipStream.getNextEntry()) != null) {
                    //gets filenames from zip -> party
                    String entryName = entry.getName();

                    File f = new File(cacheDir + File.pathSeparator + entryName);
                    FileOutputStream out = new FileOutputStream(f);

                    byte[] byteBuff = new byte[4096];
                    int bytesRead = 0;
                    while ((bytesRead = zipStream.read(byteBuff)) != -1)
                    {
                        out.write(byteBuff, 0, bytesRead);
                    }
                    out.close();

                    zipStream.closeEntry();
                }
                zipStream.close();


            } catch (IOException e) {
                e.printStackTrace();
            }

            parseFiles();
        }
    };
    private Response.ErrorListener responseGETErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.i("error in response", error.toString());
        }
    };

    private void parseFiles() {
        DatabaseDAO dao = new DatabaseDAO(getApplication());
        try {
/*
            if (!sharedPreferences.getBoolean("done_agency", false)) {
                AgencyParser.getInstance()
                        .parseAgency(new FileInputStream(getCacheDir() + File.pathSeparator + "agency.txt"), this);
                dao.insertAllAgency(AgencyParser.getInstance().getmAgencyList());
                sharedPreferences.edit().putBoolean("done_agency", true).apply();
            }
*/
            if (!sharedPreferences.getBoolean("done_calendar", false)) {
                CalendarParser.getInstance()
                        .parseCalendar(new FileInputStream(getCacheDir() + File.pathSeparator + "calendar.txt"), this);
                dao.insertAllCalendars(CalendarParser.getInstance().getmCalendarList());
                sharedPreferences.edit().putBoolean("done_calendar", true).apply();
            }
/*
            if (!sharedPreferences.getBoolean("done_calendar_dates", false)) {
                Calendar_DatesParser.getInstance()
                        .parseCalendarDates(new FileInputStream(getCacheDir() + File.pathSeparator + "calendar_dates.txt"), this);
                dao.insertAllCalendarDates(Calendar_DatesParser.getInstance().getmCalenderDatesList());
                sharedPreferences.edit().putBoolean("done_calendar_dates", true).apply();
            }
*/
            if(!sharedPreferences.getBoolean("done_routes", false)) {
                RouteParser.getInstance()
                        .parseRoute(new FileInputStream(getCacheDir() + File.pathSeparator + "routes.txt"), this);
                dao.insertAllRoutes(RouteParser.getInstance().getmRouteList());
                sharedPreferences.edit().putBoolean("done_routes", true).apply();
            }
/*
            if (!sharedPreferences.getBoolean("done_shape", false)) {
                ShapeParser.getInstance()
                        .parseShape(new FileInputStream(getCacheDir() + File.pathSeparator + "shapes.txt"), this);
                dao.insertAllShapes(ShapeParser.getInstance().getmShapeList());
                sharedPreferences.edit().putBoolean("done_shape", true).apply();
            }
*/
            if(!sharedPreferences.getBoolean("done_stops", false)) {
                StopParser.getInstance()
                        .parseStop(new FileInputStream(getCacheDir() + File.pathSeparator + "stops.txt"));
                dao.insertAllStops(StopParser.getInstance().getmStopList());
                sharedPreferences.edit().putBoolean("done_stops", true).apply();
            }
/*
            if (!sharedPreferences.getBoolean("done_stop_times", false)) {
                StoptimeParser.getInstance()
                        .parseStoptime(new FileInputStream(getCacheDir() + File.pathSeparator + "stop_times.txt"), this);
                dao.insertAllStoptimes(StoptimeParser.getInstance().getmStoptimeList());
                sharedPreferences.edit().putBoolean("done_stop_times", true).apply();
            }
*/
/*
            if (!sharedPreferences.getBoolean("done_trip", false)) {
                TripParser.getInstance()
                        .parseTrip(new FileInputStream(getCacheDir() + File.pathSeparator + "trips.txt"), this);
                dao.insertAllTrips(TripParser.getInstance().getmTripList());
                sharedPreferences.edit().putBoolean("done_trip", true).apply();
            }
*/
            sharedPreferences.edit().putBoolean("first", false).apply();
            //mRoute.addAll();
            //mapFragment.getAllStopsOnMap();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        dao.close();

        Toast.makeText(this, "Download finished", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void passData(String data) {
        MapFragment mapFragment = new MapFragment ();
        Bundle args = new Bundle();
        args.putString(MapFragment.DATA_RECEIVE, data);
        mapFragment .setArguments(args);
        getFragmentManager().beginTransaction()
                .replace(R.id.container, mapFragment )
                .commit();
    }
}
