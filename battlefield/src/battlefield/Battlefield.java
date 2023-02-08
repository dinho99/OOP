package battlefield;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Battlefield {

	static final private Class<?>[] TIPOLOGIE = { Walker.class, Chaser.class } ;

	static final private int NUMERO_TIPOLOGIE = TIPOLOGIE.length;

	static final private double OCCUPAZIONE_INIZIALE = 0.01d; // 1%

	private int dim;

	private Random random;
	
	private Map<Position, Robot> posizione2robot;

	public Battlefield(int dimensione) {
		this.dim = dimensione;
		this.posizione2robot = new HashMap<>();
		this.random = new Random();
	}

	public void addRobot(Robot r) {
		// (vedi DOMANDA 1)
		posizione2robot.put(r.getPosizione(), r);
	}

	public Robot getRobot(Position p) {
		return posizione2robot.get(p);
	}

	public Collection<Robot> getAllRobots() {
		return this.posizione2robot.values();
	}

	@SuppressWarnings("rawtypes")
	public Map<Class, Set<Robot>> raggruppaRobotPerTipo() {
		// (vedi DOMANDA 3)
		Map<Class, Set<Robot>> classe2tipo = new HashMap<>();
		for(Position p : posizione2robot.keySet()) {
			if(classe2tipo.containsKey(posizione2robot.get(p).getClass())) {
				classe2tipo.get(posizione2robot.get(p).getClass()).add(posizione2robot.get(p));
			} else {
				Set<Robot> s = new HashSet<>();
				s.add(posizione2robot.get(p));
				classe2tipo.put(posizione2robot.get(p).getClass(), s);
			}
		}
		return classe2tipo;
	}
	
	public List<Robot> getRobotOrdinatiPerPosizione() {
		// (vedi DOMANDA 4)
		List<Robot> ordinati = new LinkedList<>();
		ordinati.addAll(posizione2robot.values());
		Collections.sort(ordinati, new ComparatarePosizione());
		return ordinati;
	}
	
	public SortedSet<?> getRobotOrdinatiPerLongevita() {
		// (vedi DOMANDA 6)
		SortedSet<Robot> ordinati = new TreeSet<Robot>();
		//n.b. si utilizza il metodo compareTo di Robot
		ordinati.addAll(posizione2robot.values());
		return ordinati;
	}
	
	public List<Position> adiacenti(Position perno) {
		final List<Position> adiacenti = new LinkedList<>();
		int x = perno.getX();
		int y = perno.getY();

		for(int i = -1; i<2; i++) {
			for(int j = -1; j<2; j++) {
				Position adiacente = new Position(x+i, y+j);
				if (inCampo(adiacente))
					adiacenti.add(adiacente);
			}
		}
		adiacenti.remove(perno);  // coincidono: quindi non sono adiacenti
		
		Collections.shuffle(adiacenti); /* ordine casuale */
		return adiacenti;
	}

	private boolean inCampo(Position p) {
		return  p.getX()>=0 && p.getX()<this.dim && 
				p.getY()>=0 && p.getY()<this.dim  ;
				
	} 

	public Position posizioneLiberaVicino(Position posizione) {
		for(Position p : this.adiacenti(posizione)) {
			if (this.isLibera(p)) {
				return p;
			}
		}
		return null;
	}

	public boolean isLibera(Position posizione) {
		return ( this.getRobot(posizione)==null && this.getRobot(posizione)==null);
	}

	public int getDimensione() {
		return this.dim;
	}

	public void riempi() {
		long numeroIniziale = Math.round(OCCUPAZIONE_INIZIALE * dim * dim);
		for(int i=0 ; i<numeroIniziale; i++) {
			int x = this.random.nextInt(this.dim);
			int y = this.random.nextInt(this.dim);
			Position posizione = new Position(x, y);
			if (this.isLibera(posizione)) {
				switch (this.random.nextInt(NUMERO_TIPOLOGIE)) {
				case 0: Chaser chaser = new Chaser(posizione);
						this.addRobot(chaser);
				break;
				case 1: Walker walker = new Walker(posizione);
						this.addRobot(walker);
				break;
				//case: NUMERO_TIPOLOGIE-1...
				default: throw new IllegalStateException();
				} 
			}
		}
	}

}
