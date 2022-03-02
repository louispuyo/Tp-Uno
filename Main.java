package uno;
import java.util.ArrayList;

/**
 * cette classe represente la main de chaque joueur
 * @author louispuyo
 *
 */
public class Main {

	/**
	 * l'ensemble des cartes dans la main
	 */
	protected ArrayList<Carte> cartes;
	
	/**
	 * constructeur
	 */
	public Main() {
		cartes = new ArrayList<Carte>();
	}
	
	/**
	 * permet d'ajouter une carte e la main
	 * @param carte : la carte e ajouter
	 */
	public void ajouter(Carte carte) {
		cartes.add(carte);
	}
	
	/**
	 * permet de retirer une carte de la main
	 * @param num : l'indice de la carte e retirer dans la liste
	 * @return la carte retiree
	 */
	public Carte retirer(int num) {
		return cartes.remove(num);
	}
	
	/*
	public void retirer(Carte carte) {
		cartes.remove(carte);
	}
	*/
	
	/**
	 * permet de retourner l'ensemble des cartes dans la main
	 * @return l'ensemble des cartes dans la main
	 */
	public ArrayList<Carte> getCartes() {
		return cartes;
	}
	
	/**
	 * @return le nombre de cartes dans la main
	 */
	public int nbCartes() {
		return cartes.size();
	}

}
