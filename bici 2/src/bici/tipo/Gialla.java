package bici.tipo;

import static bici.gui.LettoreImmagini.leggiImmagineBici;
import static bici.gui.CostantiGUI.RISORSA_IMMAGINE_BICI_GIALLA;

import java.awt.Image;
import java.util.List;
import java.util.Random;

import bici.sim.Coordinate;
import bici.sim.CostantiSimulazione;
import bici.sim.GeneratoreCasuale;
import bici.sim.Zona;

public class Gialla extends Bici{
	
	static private int idProg = 0;
	
	static final private Image IMMAGINE_BICI_GIALLA = leggiImmagineBici(RISORSA_IMMAGINE_BICI_GIALLA);
	
	static private List<Coordinate> parcoDestinazioniGialle = GeneratoreCasuale.generaNposizioniCasuali(CostantiSimulazione.N_DESTINAZIONI);

	private Random random;
	
	public Gialla(Zona zona) {
		super(zona,idProg++);
		this.random = new Random();
	}

	@Override
	public Image getImmagine() {
		return IMMAGINE_BICI_GIALLA;
	}
	
	@Override
	protected Coordinate decidiProssimaDestinazione() {
		final int indice = this.random.nextInt(parcoDestinazioniGialle.size());
		return parcoDestinazioniGialle.get(indice);
	}
	public static List<Coordinate> getParcodestinazionigialle() {
		return parcoDestinazioniGialle;
	}
	
	public static void setParcoDestinazioniGialle(List<Coordinate> parcoDestinazioniGialle) {
		Gialla.parcoDestinazioniGialle = parcoDestinazioniGialle;
	}

}
