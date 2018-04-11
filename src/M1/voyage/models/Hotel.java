package M1.voyage.models;

import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "hotel")
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@AttributeOverride(name="id",column = @Column(name="id_hotel"))

public class Hotel extends BaseModel
{
	@Column(name = "nom_hotel")
	private String nom_hotel;
	@Column(name = "description")
	private String description;
	@Column(name = "image")
	private String image;
	@Column(name = "id_destination")
	private int id_destination;
	@Column(name = "id_commodite")
	private int id_commodite;
	
	public Hotel(){super();}
	
	public Hotel(int id_hotel) {
		super(id_hotel);
	}
	public Hotel(String nom_hotel, String description, String image,int id_destination,int id_commodite) {
		super();
		this.nom_hotel = nom_hotel;
		this.description = description;
		this.image = image;
		this.id_destination = id_destination;
		this.id_commodite = id_commodite;
	}
	
	public Hotel(int id_hotel, String nom_hotel, String description, String image,int id_destination,int id_commodite) {
		super(id_hotel);
		this.nom_hotel = nom_hotel;
		this.description = description;
		this.image = image;
		this.id_destination = id_destination;
		this.id_commodite = id_commodite;
	}

	public int getId_destination() {
		return id_destination;
	}

	public void setId_destination(int id_destination) {
		this.id_destination = id_destination;
	}

	public String getNom_hotel() {
		return nom_hotel;
	}

	public void setNom_hotel(String nom_hotel) {
		this.nom_hotel = nom_hotel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getId_commodite() {
		return id_commodite;
	}

	public void setId_commodite(int id_commodite) {
		this.id_commodite = id_commodite;
	}

}
