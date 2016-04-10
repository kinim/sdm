package pl.hackatheon.sdm.points.medical;

import com.google.android.gms.maps.model.LatLng;
import pl.hackatheon.sdm.points.BasePoint;

public class MedicalPoint extends BasePoint {

    private String title;
    private String address;

    public MedicalPoint() {}

    public MedicalPoint(LatLng latLng, String title, String address) {
        this.latLng = latLng;
        this.title = title;
        this.address = address;
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
