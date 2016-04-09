package pl.hackatheon.sdm;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import pl.hackatheon.sdm.medical_points.MedicalPoint;
import pl.hackatheon.sdm.medical_points.MedicalPointsMapsActivity;
import pl.hackatheon.sdm.medical_points.util.MarkerUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void startMapActivity(View view) {
        Intent intent = new Intent(this, MedicalPointsMapsActivity.class);
        startActivity(intent);
    }

    public void startLeadActivity(View view) {
        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = getLocationListener(locationManager);
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        } catch (SecurityException e) {
            Log.e(e.getLocalizedMessage(), e.getMessage());
        }
    }

    private LocationListener getLocationListener(final LocationManager locationManager) {
        return new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                MedicalPoint medicalPoint = MarkerUtils.getMedicalPointWithShortestPath(location.getLatitude(), location.getLongitude());
                leadToNearestMedicalPoint(medicalPoint);
                try {
                    locationManager.removeUpdates(this);
                } catch (SecurityException e) {
                    Log.getStackTraceString(e);
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
    }

    private void leadToNearestMedicalPoint(MedicalPoint medicalPoint) {
        final Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr="
                        + medicalPoint.getLatLng().latitude + "," + medicalPoint.getLatLng().longitude)
        );
        intent.setClassName("com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity");
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void startLeadToBatteryActivity(View view) {

    }

    public void startFlashLightActivity(View view) {

    }
}
