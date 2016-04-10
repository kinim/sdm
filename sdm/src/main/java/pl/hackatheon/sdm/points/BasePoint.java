package pl.hackatheon.sdm.points;

import com.google.android.gms.maps.model.LatLng;

public class BasePoint {

    protected LatLng latLng;

    public BasePoint() {}

    public BasePoint(LatLng latLng) {
        this.latLng = latLng;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
