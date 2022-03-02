package uno;
import java.util.Random;
import java.util.Collections;

/**
 * cette classe presente les fonctionnalites communes entre la pioche et le talon
 * @author louispuyo
 *
 */
public class Pile extends Main {
	
	/**
	 * generateur de valeurs aleatoires
	 */
	protected Random rand = new Random();

	/**
	 * permet de depiler le sommet de la pile
	 * @return la carte supprimee
	 */
	public Carte depiler() {
		return cartes.remove(0);
	}
	
	/**
	 * permet d'empiler une carte
	 * @param carte : la carte e empiler
	 */
	public void empiler(Carte carte) {
		cartes.add(0, carte);
	}
	

	/**
	 * melange les cartes
	 */
	public void melanger() {
		
		Collections.shuffle(cartes);
	}
	
	/**
	 * @return une chaene de carateres representant la pile courante
	 */
	@Override
	public String toString() {
		return super.toString();
	}

	/**
	 * @return le sommet de la pile
	 */
	public Carte sommet() {
		return cartes.get(0);	
	}
	
}
