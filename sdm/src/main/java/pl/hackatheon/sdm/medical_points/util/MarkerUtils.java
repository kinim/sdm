package pl.hackatheon.sdm.medical_points.util;

import com.google.android.gms.maps.model.LatLng;
import pl.hackatheon.sdm.medical_points.MedicalPoint;

import java.util.ArrayList;
import java.util.List;

public class MarkerUtils {

    private static List<MedicalPoint> standardMedicalPoints;
    private static List<MedicalPoint> childrenMedicalPoints;

    public static List<MedicalPoint> getStandardMedicalPoints() {
        if (standardMedicalPoints == null) {
            standardMedicalPoints = new ArrayList<>();
            standardMedicalPoints.add(new MedicalPoint(new LatLng(50.0752453, 19.933824), "5 Wojskowy Szpital Kliniczny z Polikliniką", "Wrocławska 1-3\t12 63 08 138"));
            standardMedicalPoints.add(new MedicalPoint(new LatLng(50.0826392, 19.9380104), "Szpital Miejski Specjalistyczny im. G.Narutowicza", "Prądnicka 35/37\t12 25 78 280"));
            standardMedicalPoints.add(new MedicalPoint(new LatLng(50.0657274, 20.0459716), "Szpital Specjalistyczny im. Stefana Żeromskiego", "Os. Na Skarpie 66\t12 62 29 260"));
            standardMedicalPoints.add(new MedicalPoint(new LatLng(50.0927913, 20.0200318), "Szpital Specjalistyczny im, Ludwika Rydygiera", "os. Złotej Jesieni 1\t12 64 68 680 "));
            standardMedicalPoints.add(new MedicalPoint(new LatLng(50.0647424, 19.956453), "Szpital Uniwersytecki w Krakowie", "Kopernika 50\t12 35 16 601"));
        }
        return standardMedicalPoints;
    }

    public static List<MedicalPoint> getChildrenMedicalPoints() {
        if (childrenMedicalPoints == null) {
            childrenMedicalPoints = new ArrayList<>();
            childrenMedicalPoints.add(new MedicalPoint(new LatLng(50.011377, 20.000529), "Szpitalny oddział ratunkowy uniwersyteckiego szpitala dziecięcego", "Wielicka 265\t12 658 20 11"));
        }
        return childrenMedicalPoints;
    }

    public static List<MedicalPoint> getAllMedicalPoints() {
        List<MedicalPoint> standard = getStandardMedicalPoints();
        List<MedicalPoint> children = getChildrenMedicalPoints();
        List<MedicalPoint> all = new ArrayList<>();
        all.addAll(standard);
        all.addAll(children);
        return all;
    }

    public static MedicalPoint getMedicalPointWithShortestPath(double latitude, double longitude) {
        List<MedicalPoint> allMedicalPoints = getAllMedicalPoints();
        MedicalPoint pointWithShortestPath = null;
        double shortestPath;
        for (MedicalPoint medicalPoint : allMedicalPoints) {
            shortestPath = Math.sqrt(Math.pow(medicalPoint.getLatLng().latitude - latitude, 2.0) + Math.pow(medicalPoint.getLatLng().longitude - longitude, 2.0));
            pointWithShortestPath = medicalPoint;
            for (MedicalPoint medicalPoint1 : allMedicalPoints) {
                double pathLength = Math.sqrt(Math.pow(medicalPoint1.getLatLng().latitude - latitude, 2.0) + Math.pow(medicalPoint1.getLatLng().longitude - longitude, 2.0));
                if (pathLength < shortestPath) {
                    shortestPath = pathLength;
                    pointWithShortestPath = medicalPoint1;
                }
            }
        }
        return pointWithShortestPath;
    }
}
