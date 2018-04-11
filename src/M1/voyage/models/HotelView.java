package M1.voyage.models;

import javax.persistence.*;

@Entity
@Table(name = "hotel_view")
public class HotelView extends Hotel
{
	@Column(name = "nom_destination")
	private String nom_destination;
	@Column(name = "nom_commodite")
	private String nom_commodite;
	public String getNom_destination() {
		return nom_destination;
	}
	public void setNom_destination(String nom_destination) {
		this.nom_destination = nom_destination;
	}
	public String getNom_commodite() {
		return nom_commodite;
	}
	public void setNom_commodite(String nom_commodite) {
		this.nom_commodite = nom_commodite;
	}
}
