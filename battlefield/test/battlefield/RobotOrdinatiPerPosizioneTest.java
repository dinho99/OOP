package battlefield;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RobotOrdinatiPerPosizioneTest {
	
	// (vedi DOMANDA 5)
	// scrivere dei test-case minimali per il metodo
	// Battlefield.getRobotOrdinatiPerPosizione()
private Battlefield field;
	
	private Position origine;
	private Position unitari;
	
	@Before
	public void setUp() throws Exception {
		this.field = new Battlefield(2);
		this.origine = new Position(0, 0);
		this.unitari = new Position(1, 1);
	}
	
	@Test
	public void testRobotOrdinatiPerPosizioneTest_campoVuoto() {
		List<Robot> expected = new ArrayList<>();
		assertEquals(expected, field.getRobotOrdinatiPerPosizione());
	}
	
	@Test
	public void testRobotOrdinatiPerPosizioneTest_singoloRobot() {
		Robot c = new Chaser(origine);
		field.addRobot(c);
		List<Robot> expected = new ArrayList<>();
		expected.add(c);
		assertEquals(expected, field.getRobotOrdinatiPerPosizione());
	}
	
	@Test
	public void testRobotOrdinatiPerPosizioneTest_dueRobotXDiversa() {
		Robot c = new Chaser(origine);
		Robot w = new Walker(new Position(1, 0));
		field.addRobot(c);
		field.addRobot(w);
		List<Robot> expected = new ArrayList<>();
		expected.add(c);
		expected.add(w);
		assertEquals(expected, field.getRobotOrdinatiPerPosizione());
	}
	
	@Test
	public void testRobotOrdinatiPerPosizioneTest_dueRobotYDiversa() {
		Robot c = new Chaser(origine);
		Robot w = new Walker(new Position(0, 1));
		field.addRobot(c);
		field.addRobot(w);
		List<Robot> expected = new ArrayList<>();
		expected.add(c);
		expected.add(w);
		assertEquals(expected, field.getRobotOrdinatiPerPosizione());
	}
	
	@Test
	public void testRobotOrdinatiPerPosizioneTest_quattroRobot() {
		Robot c = new Chaser(origine);
		Robot c2 = new Chaser(unitari);
		Robot w = new Walker(new Position(0, 1));
		Robot w2 = new Walker(new Position(3, 6));
		field.addRobot(c);
		field.addRobot(w);
		field.addRobot(c2);
		field.addRobot(w2);
		List<Robot> expected = new ArrayList<>();
		expected.add(c);
		expected.add(w);
		expected.add(c2);
		expected.add(w2);
		assertEquals(expected, field.getRobotOrdinatiPerPosizione());
	}
}
