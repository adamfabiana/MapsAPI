/**
 * trebuie importata libraria jxmaps-win-1.3.2.jar
 */
package Project;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.Circle;
import com.teamdev.jxmaps.CircleOptions;
import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapComponentType;
import com.teamdev.jxmaps.MapEvent;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.swing.MapView;
/**
 * clasa Mapview este mostenita din clasa Mapa
 */
public class Mapa extends MapView {

	
/**
 * Am declarat un identificator de versiune ce are ca scop sa verifice 
 * daca avem o clasa Serializable(isi poate converti starea intr-un flux 
 * de octeti, astel incat fluxul de octeti sa se poata reconverti intr 
 * o copie a obiectului).Deserializarea foloseste acest identificator pentru a 
 * se asigura ca o clasa incarcata corespunde exact unui obiect serializat.
 */
	private static final long serialVersionUID = 1L;
/**
 * 
 * 
 * permite setarea vizualizarii mapei
 */
	public Mapa(MapViewOptions options) {
/**
 * face referire la clasa parinte
 */

		super(options);
		/**
		 * initializare harta
		 */
		 setOnMapReadyHandler(new MapReadyHandler() {
	            @Override
	            public void onMapReady(MapStatus status) {
	                if (status == MapStatus.MAP_STATUS_OK) {
	                     Map map = getMap();
	                    map.setZoom(13.0);

	                    @SuppressWarnings("deprecation")
						GeocoderRequest request = new GeocoderRequest(map);
	                    request.setAddress("Iasi, Romania");



	                    getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
	                        @Override
	                        public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
	                            if (status == GeocoderStatus.OK) {
	                                map.setCenter(result[0].getGeometry().getLocation());
	                                /*Marker marker = new Marker(map);
	                                marker.setPosition(result[0].getGeometry().getLocation());
	                                final InfoWindow window = new InfoWindow(map);
	                                window.setContent("Campus Tudor Vladimirescu Camin T17!");
	                                window.open(map, marker);*/
/**
 * cere coordonatele din fisier
 */
	                                String file_path="src/file.txt";

	                              ArrayList<String[]> coords = null;
	                              try {
                              	coords = read_coordinates(file_path);

	                              	for(String[] element:coords){
	                              	   Circle circle = new Circle(map);
		                              	float x = Float.parseFloat(element[0]);
		                              	float y = Float.parseFloat(element[1]);
		                              	/**
		                              	 * seteaza centrul in functie de long si lat
		                              	 */
	                         		circle.setCenter(new LatLng(x,y));
	                         		/**
	                         		 * transformare in radiani
	                         		 */
	                         		circle.setRadius(30);
	                         		circle.setVisible(true);
	                         		//circle.setOptions(settingsCircle);

	                         		@SuppressWarnings("deprecation")
									CircleOptions co = new CircleOptions(map);
	                         		co.setFillColor("#B713EE");
	                         		co.setFillOpacity(0.35);
	                         		co.setStrokeColor("#B713EE");

	                         		circle.setOptions(co);






	                              	}
	                              } catch (IOException e) {
	                              	e.printStackTrace();
	}

	                            }
	                        }
	                    });

	                }}


		});




	}
/**
 * citire fisier si stocare intr un array
 * @param file_path
 * @return
 * @throws IOException
 */
    public static ArrayList<String[]> read_coordinates(String file_path) throws IOException{
    	BufferedReader br= new BufferedReader(new FileReader(file_path));
    	ArrayList<String[]> result = new ArrayList<String[]>();

    String st;
    // Condition holds true till
    // there is character in a string
    while ((st = br.readLine()) != null){

        result.add(st.split(","));
    }
    br.close();
    return result;
    }    
	public static void main(String[] args) throws IOException {

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

		try {
			/**
			 * Se creeaza un robot cu ajutorul caruia se face printscreenul ,de asemenea foloseste un obiect rectangle 
			 * care poate fi modificat astfel incat sa printeze doar o parte a 
			 * ecranului,iar robot.delay e folosit pentru a temporiza durata printscreenului 
			 * adica dupa cat timp face printscreen
			 */
            Robot robot = new Robot();
            robot.delay(10000);
            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
            File file = new File("screen-cachgcpture.png");
            boolean status = ImageIO.write(bufferedImage, "png", file);
            System.out.println("Screen Captured ? " + status + " File Path:- " + file.getAbsolutePath());

        } catch (AWTException | IOException ex) {
            System.err.println(ex);











	}

}}