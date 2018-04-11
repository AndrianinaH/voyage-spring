package M1.voyage.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "chambre_view")
public class ChambreView extends Chambre{

	@Column(name = "nom_hotel")
	private String nom_hotel;

	public String getNom_hotel() {
		return nom_hotel;
	}

	public void setNom_hotel(String nom_hotel) {
		this.nom_hotel = nom_hotel;
	}
	//CREATE OR REPLACE VIEW chambre_view AS SELECT chambre.id_chambre, hotel.id_hotel, chambre.nom_chambre,chambre.nbr_petit_lit,chambre.nbr_grand_lit,chambre.prix,hotel.nom_hotel FROM chambre JOIN hotel ON hotel.id_hotel = chambre.id_hotel;
}
//CREATE OR REPLACE VIEW hotel_view AS SELECT id_hotel,hotel.id_destination,hotel.id_commodite,nom_hotel,description,image,nom_destination,nom_commodite FROM hotel JOIN destination ON destination.id_destination=hotel.id_destination JOIN commodite ON commodite.id_commodite=hotel.id_commodite

//CREATE OR REPLACE VIEW reservation_view AS SELECT id_reservation,chambre.id_chambre,client.id_client,date_debut,date_fin,nom_chambre,nom_client FROM reservation JOIN chambre ON chambre.id_chambre = reservation.id_chambre JOIN client ON client.id_client = reservation.id_client;