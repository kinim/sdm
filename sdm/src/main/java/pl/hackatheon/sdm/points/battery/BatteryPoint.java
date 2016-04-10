package pl.hackatheon.sdm.points.battery;

import com.google.android.gms.maps.model.LatLng;
import pl.hackatheon.sdm.points.BasePoint;

public class BatteryPoint extends BasePoint {

    public static final String TITLE = "Battery recharge point";

    public BatteryPoint() {}

    public BatteryPoint(LatLng latLng) {
        super(latLng);
    }
}
