package M1.voyage.models;

import javax.persistence.*;

@Entity
@Table(name = "chambre")
@AttributeOverride(name="id",column = @Column(name="id_chambre"))
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Chambre extends BaseModel
{

	@Column(name = "nom_chambre")
	private String nom_chambre;
	@Column(name = "nbr_petit_lit")
	private int nbr_petit_lit;
	@Column(name = "nbr_grand_lit")
	private int nbr_grand_lit;
	@Column(name = "prix")
	private double prix;
	@Column(name = "id_hotel")
	private int id_hotel;

	public Chambre(){super();}
	public Chambre(int id_chambre) {
		super(id_chambre);
	}
	public Chambre(String nom_chambre, int nbr_petit_lit, int nbr_grand_lit,double prix,int id_hotel) {
		super();
		this.nom_chambre = nom_chambre;
		this.nbr_petit_lit = nbr_petit_lit;
		this.nbr_grand_lit = nbr_grand_lit;
		this.prix = prix;
		this.id_hotel = id_hotel;
	}
	
	public Chambre(int id_chambre, String nom_chambre, int nbr_petit_lit, int nbr_grand_lit,double prix,int id_hotel) {
		super(id_chambre);
		this.nom_chambre = nom_chambre;
		this.nbr_petit_lit = nbr_petit_lit;
		this.nbr_grand_lit = nbr_grand_lit;
		this.prix = prix;
		this.id_hotel = id_hotel;
	}

	public int getId_hotel() {
		return id_hotel;
	}

	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}

	public String getNom_chambre() {
		return nom_chambre;
	}

	public void setNom_chambre(String nom_chambre) {
		this.nom_chambre = nom_chambre;
	}

	public int getNbr_petit_lit() {
		return nbr_petit_lit;
	}

	public void setNbr_petit_lit(int nbr_petit_lit) {
		this.nbr_petit_lit = nbr_petit_lit;
	}

	public int getNbr_grand_lit() {
		return nbr_grand_lit;
	}

	public void setNbr_grand_lit(int nbr_grand_lit) {
		this.nbr_grand_lit = nbr_grand_lit;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

}
