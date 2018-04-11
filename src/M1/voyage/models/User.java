package M1.voyage.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
@AttributeOverride(name="id",column = @Column(name="id_user"))
public class User extends BaseModel
{
	@Column(name = "nom_user")
	private String nom_user;
	@Column(name = "mdp_user")
	private String mdp_user;
	
	public User(){super();}
	
	public User(String nom_user, String mdp_user) {
		super();
		this.nom_user = nom_user;
		this.mdp_user = mdp_user;
	}


	public String getNom_user() {
		return nom_user;
	}
	public void setNom_user(String nom_user) {
		this.nom_user = nom_user;
	}
	public String getMdp_user() {
		return mdp_user;
	}
	public void setMdp_user(String mdp_user) {
		this.mdp_user = mdp_user;
	}
}
