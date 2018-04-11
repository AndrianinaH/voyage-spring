package M1.voyage.models;

import M1.voyage.utilitaire.Fonction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation")
@AttributeOverride(name="id",column = @Column(name="id_reservation"))
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)

public class Reservation extends BaseModel
{

	@Column(name = "date_debut")
	private Date date_debut;

	@Column(name = "date_fin")
	private Date date_fin;

	@Column(name = "id_chambre")
	private int id_chambre;

	@Column(name = "id_client")
	private int id_client;
	
	public Reservation(){super();}
	
	public Reservation(int id_reservation) {
		super(id_reservation);
	}
	
	public Reservation(Date date_debut, Date date_fin,int id_chambre,int id_client) {
		super();
		this.setId_chambre(id_chambre);
		this.setId_client(id_client);
		this.date_debut = date_debut;
		this.date_fin = date_fin;
	}
	
	public Reservation(int id_reservation, Date date_debut, Date date_fin,int id_chambre,int id_client) {
		super(id_reservation);
		this.setId_chambre(id_chambre);
		this.setId_client(id_client);
		this.date_debut = date_debut;
		this.date_fin = date_fin;
	}
 
	public Date getDate_debut() {
		return date_debut;
	}

	public String formatDate_debut() {
		return Fonction.formatDate(date_debut);
	}

	public String formatDate_fin() {
		return Fonction.formatDate(date_fin);
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	public int getId_chambre() {
		return id_chambre;
	}

	public void setId_chambre(int id_chambre) {
		this.id_chambre = id_chambre;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

}
