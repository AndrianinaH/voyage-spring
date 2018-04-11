package M1.voyage.models;

import javax.persistence.*;

@Entity
@Table(name = "commodite")
@AttributeOverride(name="id",column = @Column(name="id_commodite"))
public class Commodite extends BaseModel
{
	@Column(name = "nom_commodite")
	private String nom_commodite;

	public Commodite(){super();}

	public Commodite(int id_commodite) {
		super(id_commodite);
	}

	public Commodite(String nom_commodite) {
		super();
		this.nom_commodite = nom_commodite;
	}
	
	public Commodite(int id_commodite, String nom_commodite) {
		super(id_commodite);
		this.setNom_commodite(nom_commodite);
	}

	public String getNom_commodite() {
		return nom_commodite;
	}

	public void setNom_commodite(String nom_commodite) {
		this.nom_commodite = nom_commodite;
	}

}
