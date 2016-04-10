package pl.hackatheon.sdm.points.medical;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import pl.hackatheon.sdm.R;
import pl.hackatheon.sdm.points.util.MarkerUtils;

public class MedicalPointsMapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        setInitialCameraPosition();
        addMedicalPoints();
    }

    private void setInitialCameraPosition() {
        float zoom = 10.0f;
        LatLng target = new LatLng(50.059301, 19.938862);
        CameraPosition cameraPosition = new CameraPosition(target, zoom, 0.0f, 0.0f);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);
    }

    private void addMedicalPoints() {
        for (MedicalPoint medicalPoint : MarkerUtils.getStandardMedicalPoints()) {
            mMap.addMarker(new MarkerOptions()
                    .position(medicalPoint.getLatLng())
                    .title(medicalPoint.getTitle())
                    .snippet(medicalPoint.getAddress())
            );
        }
        for (MedicalPoint medicalPoint : MarkerUtils.getChildrenMedicalPoints()) {
            mMap.addMarker(new MarkerOptions()
                    .position(medicalPoint.getLatLng())
                    .title(medicalPoint.getTitle())
                    .snippet(medicalPoint.getAddress())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.baby_svg))
            );
        }
        for (MedicalPoint medicalPoint : MarkerUtils.getCare24HMedicalPoints()) {
            mMap.addMarker(new MarkerOptions()
                    .position(medicalPoint.getLatLng())
                    .title(medicalPoint.getTitle())
                    .snippet(medicalPoint.getAddress())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            );
        }
    }
}
