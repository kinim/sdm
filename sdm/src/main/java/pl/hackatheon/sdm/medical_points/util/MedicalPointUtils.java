package pl.hackatheon.sdm.medical_points.util;

import com.google.android.gms.maps.model.LatLng;
import pl.hackatheon.sdm.medical_points.MedicalPoint;

import java.util.ArrayList;
import java.util.List;

public class MedicalPointUtils {

    public static List<MedicalPoint> createAllMedicalPoints() {
        List<MedicalPoint> medicalPoints = new ArrayList<>();
        medicalPoints.add(new MedicalPoint(new LatLng(50.0752453, 19.933824), "5 Wojskowy Szpital Kliniczny z Polikliniką", "Wrocławska 1-3\t12 63 08 138"));
        medicalPoints.add(new MedicalPoint(new LatLng(50.0826392, 19.9380104), "Szpital Miejski Specjalistyczny im. G.Narutowicza", "Prądnicka 35/37\t12 25 78 280"));
        medicalPoints.add(new MedicalPoint(new LatLng(50.0657274, 20.0459716), "Szpital Specjalistyczny im. Stefana Żeromskiego", "Os. Na Skarpie 66\t12 62 29 260"));
        medicalPoints.add(new MedicalPoint(new LatLng(50.0927913, 20.0200318), "Szpital Specjalistyczny im, Ludwika Rydygiera", "os. Złotej Jesieni 1\t12 64 68 680 "));
        medicalPoints.add(new MedicalPoint(new LatLng(50.0647424, 19.956453), "Szpital Uniwersytecki w Krakowie", "Kopernika 50\t12 35 16 601"));
        return medicalPoints;
    }
}
