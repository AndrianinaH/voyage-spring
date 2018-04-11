package M1.voyage.models;

import javax.persistence.*;
@Entity
@Table(name = "client")
@AttributeOverride(name="id",column = @Column(name="id_client"))
public class Client extends BaseModel
{
	@Column(name = "nom_client")
	private String nom_client;
	@Column(name = "email")
	private String email;
	@Column(name = "mdp")
	private String mdp;
	
	public Client(){super();}
	public Client(int id_client) {
		super(id_client);
	}
	public Client(String nom_client, String email, String mdp) {
		super();
		this.nom_client = nom_client;
		this.email = email;
		this.mdp = mdp;
	}

	
	public Client(int id_client, String nom_client, String email, String mdp) {
		super(id_client);
		this.nom_client = nom_client;
		this.email = email;
		this.mdp = mdp;
	}

	public String getNom_client() {
		return nom_client;
	}

	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
}
