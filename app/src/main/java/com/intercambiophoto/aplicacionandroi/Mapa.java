package com.intercambiophoto.aplicacionandroi;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

public class Mapa extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        MapView mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);


        double santiagoLatitude = -33.4489;
        double santiagoLongitude = -70.6693;

        double valparaisoLatitude = -33.036;
        double valparaisoLongitude = -71.62963;

        double lapintanaLatitude = -33.58331;
        double lapintanaLongitude = -70.63419;

        GeoPoint santiagoPoint = new GeoPoint(santiagoLatitude, santiagoLongitude);
        GeoPoint valparaisoPoint = new GeoPoint(valparaisoLatitude, valparaisoLongitude);
        GeoPoint lapintanaPoint = new GeoPoint(lapintanaLatitude, lapintanaLongitude);

        Marker santiagoMarker = new Marker(mapView);
        santiagoMarker.setPosition(santiagoPoint);
        santiagoMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        santiagoMarker.setTitle("Santiago, Chile");
        santiagoMarker.setSnippet("Capital de Chile");

        Marker valparaisoMarker = new Marker(mapView);
        valparaisoMarker.setPosition(valparaisoPoint);
        valparaisoMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        valparaisoMarker.setTitle("Valparaíso, Chile");
        valparaisoMarker.setSnippet("Puerto principal de Chile");

        Marker lapintanaMarker = new Marker(mapView);
        valparaisoMarker.setPosition(lapintanaPoint);
        valparaisoMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        valparaisoMarker.setTitle("La pintana, Chile");
        valparaisoMarker.setSnippet("Comunade de Santiago, Chile");

        mapView.getOverlays().add(santiagoMarker);
        mapView.getOverlays().add(valparaisoMarker);
        mapView.getOverlays().add(lapintanaMarker);

        Polyline polyline = new Polyline();
        polyline.addPoint(santiagoPoint);
        polyline.addPoint(valparaisoPoint);
        polyline.addPoint(lapintanaPoint);
        polyline.setColor(0xFF0000FF);
        polyline.setWidth(5);

        mapView.getOverlayManager().add(polyline);

        double distance = CalcularDistancia.CalcularDistancia(santiagoPoint, valparaisoPoint);
        TextView distanceTextView = findViewById(R.id.distanceTextView);
        distanceTextView.setText("Distancia entre Santiago y Valparaíso: " +
                String.format("%.2f", distance) + " km");


        IMapController mapController = mapView.getController();
        mapController.setCenter(santiagoPoint);
        mapController.setZoom(14);
    }
}
