package bici.tipo;

import static bici.gui.CostantiGUI.RISORSA_IMMAGINE_BICI_BIANCA;
import static bici.gui.LettoreImmagini.leggiImmagineBici;	

import java.awt.Image;

import bici.sim.Coordinate;
import bici.sim.GeneratoreCasuale;
import bici.sim.Zona;

/**
 * Modella le fasi del ciclo di vista di una bicicletta {@link Bianca}.
 * <B>(VEDI DOMANDA 2)</B>
 */
public class Bianca extends Bici {
	
	static final private Image IMMAGINE_BICI_BIANCA = leggiImmagineBici(RISORSA_IMMAGINE_BICI_BIANCA);

	static private int idProg = 0;
	
	public Bianca(Zona zona) {		
		super(zona,idProg++);
	}
	@Override
	public Image getImmagine() {
		return IMMAGINE_BICI_BIANCA;
	}

	@Override
	protected Coordinate decidiProssimaDestinazione() {
		return GeneratoreCasuale.posizioneCasuale();
	}

}
