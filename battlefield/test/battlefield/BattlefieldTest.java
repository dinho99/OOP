package battlefield;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/* Modificare la classe Position affinche' 
 * il primo test abbia successo (vedi DOMANDA 1) 
 */
public class BattlefieldTest {
	
	private Battlefield field;
	
	@Before
	public void setUp() throws Exception {
		this.field = new Battlefield(2);
	}

	@Test
	public void testAddWalker() {
		assertEquals(0, this.field.getAllRobots().size());
		this.field.addRobot(new Walker(new Position(0,0)));
		assertEquals(1, this.field.getAllRobots().size());
	}
	
	@Test
	public void testRaggruppaRobotDiDueTipiDiversi() {
		Set<Walker> walkers = new HashSet<>();
		Walker w1 = new Walker(new Position(1, 1));
		Walker w2 = new Walker(new Position(2, 2));
		walkers.add(w2);
		walkers.add(w1);
		Chaser chaser = new Chaser(new Position(0, 0));
		Map<Class, Set<?>> expected = new HashMap<>();
		expected.put(Chaser.class, Collections.singleton(chaser));
		expected.put(Walker.class, walkers);
		field.addRobot(chaser);
		field.addRobot(w1);
		field.addRobot(w2);
		assertEquals(expected, this.field.raggruppaRobotPerTipo());
	}

}
