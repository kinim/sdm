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
import pl.hackatheon.sdm.flashlight.FlashlightFullscreenActivity;
import pl.hackatheon.sdm.points.BasePoint;
import pl.hackatheon.sdm.points.battery.BatteryPointMapsActivity;
import pl.hackatheon.sdm.points.medical.MedicalPointsMapsActivity;
import pl.hackatheon.sdm.points.util.MarkerUtils;

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
        LocationListener locationListener = getLocationListener(locationManager, true);
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        } catch (SecurityException e) {
            Log.e(e.getLocalizedMessage(), e.getMessage());
        }
    }

    private LocationListener getLocationListener(final LocationManager locationManager, final boolean isMedical) {
        return new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                BasePoint point;
                if (isMedical) {
                    point = MarkerUtils.getMedicalPointWithShortestPath(location.getLatitude(), location.getLongitude());
                } else {
                    point = MarkerUtils.getBatteryPointWithShortestPath(location.getLatitude(), location.getLongitude());
                }
                leadToNearestMedicalPoint(point);
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

    private void leadToNearestMedicalPoint(BasePoint point) {
        final Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr="
                        + point.getLatLng().latitude + "," + point.getLatLng().longitude)
        );
        intent.setClassName("com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity");
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void startBatteryMapActivity(View view) {
        Intent intent = new Intent(this, BatteryPointMapsActivity.class);
        startActivity(intent);
    }

    public void startLeadToBatteryActivity(View view) {
        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = getLocationListener(locationManager, false);
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        } catch (SecurityException e) {
            Log.e(e.getLocalizedMessage(), e.getMessage());
        }
    }

    public void startFlashLightActivity(View view) {
        Intent intent = new Intent(this, FlashlightFullscreenActivity.class);
        startActivity(intent);
    }
}
