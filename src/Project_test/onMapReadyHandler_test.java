package Project_test;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.teamdev.jxmaps.MapViewOptions;

import Project.Mapa;

import org.apache.commons.io.FileUtils;


class onMapReadyHandler_test {
	
	private Mapa TestMapa;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testIfThereAreCoordinatesFileIsNotEmpty() {
		File file_path= new File("src/file.txt");
		assertNotEquals(file_path.length(),0);
				
		}
	@Test
	void testIfThereAreCoordinatesFileIsEmpty() {
		File file_path= new File("src/empty_file.txt");
		assertEquals(file_path.length(),0);
		}
	@Test
	void testIfTheCoordinatesAreTheOnesNeeded() throws IOException {
		File actualFile2 = new File("src/file.txt");
	    File expectedFile2 = new File("src/file2.txt");
	    assertEquals(FileUtils.contentEquals(actualFile2,expectedFile2),true);
	}
	@Test
	void testForDifferentCoordinates() throws IOException {
		File actualFile = new File("src/file.txt");
	    File expectedFile = new File("src/empty_file.txt");
	    assertNotEquals(FileUtils.contentEquals(actualFile,expectedFile),true);
	}
	
	@Test
    void testInvalidOnCompleteDelay() throws AWTException {
		MapViewOptions options = new MapViewOptions();
		options.importPlaces();
		options.setApiKey("");
		TestMapa = new Mapa(options);
	    Robot robot = new Robot();
	    robot.delay(0);
    }
	
	@Test
    void testNoAPIKey() throws AWTException {
	    Robot robot = new Robot();
		MapViewOptions options = new MapViewOptions();
		options.importPlaces();
		TestMapa = new Mapa(options);
	    robot.delay(0);
    }
	
	@Test
    void testValidOnCompleteDelay() throws AWTException {
		MapViewOptions options = new MapViewOptions();
		options.importPlaces();
		options.setApiKey("");
		TestMapa = new Mapa(options);
	    Robot robot2 = new Robot();
	    robot2.delay(5000);
    }	
	@Test
	void testMainCaller() throws IOException {
		Mapa.main(null);
	}
}
