package M1.voyage.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;


@MappedSuperclass
public abstract class BaseModel implements Serializable
{
	@Id
	@GenericGenerator(name = "increment", strategy = "increment")
	@GeneratedValue(generator = "increment")
	private int id;

	public BaseModel(){}

	public BaseModel(int id){
		this.id=id;
	}

	public int getId(){
		return this.id;
	}

	public void setId(int id){
		this.id=id;
	}
}
