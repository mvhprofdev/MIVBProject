package be.ehb.mivbproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import be.ehb.mivbproject.R;
import be.ehb.mivbproject.source.Stop;
import be.ehb.mivbproject.util.DatabaseDAO;


/**
 * Created by mobapp10 on 15/05/17.
 */

public class MapFragment extends Fragment {

    private GoogleMap googleMap;
    private MapView mapView;
    ArrayList<Stop> mStop = new ArrayList<>();
    DatabaseDAO dao;

    public MapFragment() {
    }

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = (MapView) rootView.findViewById(R.id.mv_map);
        mapView.onCreate(savedInstanceState);

        mapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                LatLng belgCoord = new LatLng(50.85712, 4.34744);
                CameraUpdate updateMove = CameraUpdateFactory.newLatLngZoom(belgCoord, 14);
                googleMap.animateCamera(updateMove);

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(50.85712, 4.34744))
                        .title("Marker"));

                drawMarkers();
            }
        });


        dao = new DatabaseDAO(getActivity());

        getAllStopsOnMap();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public void getAllStopsOnMap() {
        mStop = dao.getAllStops();

        if (googleMap != null) {
            drawMarkers();
        }
    }

    private void drawMarkers() {

        for (int i = 0; i < mStop.size(); i++) {
            double lat = Double.parseDouble(mStop.get(i).getStop_lat());
            double lon = Double.parseDouble(mStop.get(i).getStop_lon());
            LatLng coord = new LatLng(lat, lon);

            googleMap.addMarker(new MarkerOptions()
                    .position(coord)
                    .title(mStop.get(i).getStop_name()));
        }
    }
}
