package M1.voyage.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "reservation_view")
public class ReservationView extends Reservation
{
	@Column(name = "nom_chambre")
	private String nom_chambre;
	@Column(name = "nom_client")
	private String nom_client;
	
	public String getNom_chambre() {
		return nom_chambre;
	}
	public void setNom_chambre(String nom_chambre) {
		this.nom_chambre = nom_chambre;
	}
	public String getNom_client() {
		return nom_client;
	}
	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}
}
