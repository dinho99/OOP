package battlefield;

public abstract class Robot implements Comparable<Robot>{

	private Position posizione;
	private int longevita;
	
	public Robot(Position p) {
		this.posizione = p;
		this.longevita = 0 ;
	}
	
	public Position getPosizione() {
		return this.posizione;
	}
	
	public int incrementaLongevita() {
		return ++this.longevita;
	}
	
	public int getLongevita() {
		return this.longevita;
	}
	
	abstract public void passo(Battlefield field);

	@Override
	public int compareTo(Robot that) {
		return this.getLongevita()-that.getLongevita()+this.getPosizione().hashCode()-that.getPosizione().hashCode();
	}
}
