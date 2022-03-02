package uno;

/**
 * classe representant le talon du jeu
 * @author louispuyo
 *
 */
public class Talon extends Pile {

	/**
	 * constructeur
	 * @param pioche : la pioche du jeu dont on doit prendre une carte pour quelle soit la 1ere carte du talon
	 */
	public Talon(Pioche pioche) {
		ajouterPremiereCarte(pioche);
	}
	
	/**
	 * permet d'ajouter la premiere carte ( au debut du jeu )
	 * @param pioche : la pioche d'oe on veut tirer la carte
	 */
	private void ajouterPremiereCarte(Pioche pioche) {
		empiler(pioche.premiereCarteTalon());
	
	}
	
	/**
	 * retourne une chaene decrivant le talon
	 */
	public String toString() {
		String str = "";
		if (cartes.isEmpty()) {
			str = "[VIDE]";
		}
		for(int i = 0; i < cartes.size(); ++i) {
			Carte carte = cartes.get(i);
			str = str + i + ") " + carte.toString();
			if (i != cartes.size() - 1) {	// si ce n'est pas la derniere iteration
				str = str + "\n";	// ajouter un retour chariot
			}
		}
		return str;
	}
	
	/**
	 * permet d'afficher le talon
	 */
	public void afficher() {
		System.out.println(this);
	}
	
	/**
	 * permet d'afficher la carte au sommet du talon
	 */
	public void afficherSommet() {
		System.out.println("Sommet talon : " + sommet().toString());
	}
	
}
