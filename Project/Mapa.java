package Project;

import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.swing.MapView;

import javax.swing.*;

import java.awt.*;

public class Mapa extends MapView {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Mapa(MapViewOptions options) {
        super(options);
        setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                if (status == MapStatus.MAP_STATUS_OK) {
                    final Map map = getMap();
                    map.setZoom(5.0);
                    
                    @SuppressWarnings("deprecation")
					GeocoderRequest request = new GeocoderRequest(map);
                    request.setAddress("Campus Tudor Vladimirescu Camin T17, Iasi, RO");

          
                    
                    getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
                        @Override
                        public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
                            if (status == GeocoderStatus.OK) {
                                map.setCenter(result[0].getGeometry().getLocation());
                                Marker marker = new Marker(map);
                                marker.setPosition(result[0].getGeometry().getLocation());

                                final InfoWindow window = new InfoWindow(map);
                                window.setContent("Campus Tudor Vladimirescu Camin T17!");
                                window.open(map, marker);
                            }
                        }
                    });
                
                    
                }
            }
        });
    }

    public static void main(String[] args) {

        MapViewOptions options = new MapViewOptions();
        options.importPlaces();
        options.setApiKey("AIzaSyBK2hBPZRWRP1p2m8tA7dj2ujV1HApML-g");
        final Mapa mapView = new Mapa(options);
        JFrame frame = new JFrame("Iasi - retea 5G");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(mapView, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}