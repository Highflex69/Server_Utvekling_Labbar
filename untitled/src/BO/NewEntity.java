package BO;



import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Teddy on 2016-11-18.
 */
@Entity
@Table(name="T_TEST")
public class NewEntity implements Serializable{

    private Long id;
    public void setId(Long id)
    {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId(){
        return this.id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash+=(id!=null?id.hashCode():0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof NewEntity))
        {
            return false;
        }

        NewEntity other = (NewEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.NewEntity[id="+id+"]";
    }
}
