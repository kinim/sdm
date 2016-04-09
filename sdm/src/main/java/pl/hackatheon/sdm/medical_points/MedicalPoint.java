package pl.hackatheon.sdm.medical_points;

import com.google.android.gms.maps.model.LatLng;

public class MedicalPoint {

    private LatLng latLng;
    private String title;
    private String address;

    public MedicalPoint() {}

    public MedicalPoint(LatLng latLng, String title, String address) {
        this.latLng = latLng;
        this.title = title;
        this.address = address;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
