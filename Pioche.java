package uno;

/**
 * classe representant la pioche du jeu
 * @author louispuyo
 *
 */
public class Pioche extends Pile {
	// TODO : on doit traiter le cas oe la pioche devient vide et on doit prendre une carte
	// dans ce cas, on a besoin de melanger le talon ( sauf le sommet ) et le deposer dans la pioche

	/**
	 * constructeur : permet de construire la pioche et d'y inserer toutes les cartes necessaires
	 */
	public Pioche() {
		for(Couleur couleur : Couleur.values()) {
			if (couleur == Couleur.NOIR) {
				for(int i = 0; i < 4; i++) {	// la pioche 4 cartes joker et 4 cartes +4
					ajouter(new CarteSpecial(Couleur.NOIR, Symbole.JOKER));
					ajouter(new CarteSpecial(Couleur.NOIR, Symbole.PLUS4));
				}
				continue;	// toutes les cartes noirs sont ajoutees, on passe e la couleur suivante
			}
			// 1 Carte 0 pour chaque couleur
			ajouter(new CarteBasique(couleur, 0));
			// 2 Cartes 1..9 pour chaque couleur
			for(int i = 1; i <= 9; i++) {
				ajouter(new CarteBasique(couleur, i));
				ajouter(new CarteBasique(couleur, i));
			}
			// 2 Cartes passer, inverser, +2 pour chaque couleur
			for (int i = 0; i < 2; i++) {
				ajouter(new CarteSpecial(couleur, Symbole.PASSER));
				ajouter(new CarteSpecial(couleur, Symbole.INVERSER));
				ajouter(new CarteSpecial(couleur, Symbole.PLUS2));
			}
		}
	}

	/**
	 * Cette methode est utilisee pour retourner une carte aleatoirement dans la pioche
	 * dans le cas oe la premiere carte est une carte speciale ( au debut du jeu )
	 * @param carte
	 */
	private void retournerCarte(Carte carte) {
		int i = rand.nextInt(cartes.size());	// Entier aleatoire entre 0 et cartes.size() - 1
		cartes.add(i, carte);
	}
	
	/**
	 * cette methode est appelee par le talon pour qu'elle lui retourne sa premiere carte
	 * la carte ne doit pas etre speciale
	 * @return
	 */
	public Carte premiereCarteTalon() {
		Carte carte;
		while (true) {
			carte = depiler();	// Retirer une carte
			if (carte instanceof CarteSpecial) {	// C'est une carte speciale
				// Il faut dans ce cas la rajouter aleatoirement dans la pioche
				retournerCarte(carte);
				//System.out.println("Oops carte special , ...");
				//System.out.println(carte);
			} else {
				return carte;
			}
		}
	}


}
