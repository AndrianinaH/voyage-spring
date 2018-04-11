package M1.voyage.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "destination")
@AttributeOverride(name="id",column = @Column(name="id_destination"))
public class Destination extends BaseModel
{
	@Column(name = "nom_destination")
	private String nom_destination;

	public Destination(){super();}
	
	public Destination(int id_destination) {
		super(id_destination);
	}

	public Destination(String nom_destination) {
		super();
		this.nom_destination = nom_destination;
	}

	public Destination(int id_destination, String nom_destination) {
		super(id_destination);
		this.nom_destination = nom_destination;
	}

	public String getNom_destination() {
		return nom_destination;
	}

	public void setNom_destination(String nom_destination) {
		this.nom_destination = nom_destination;
	}



}
