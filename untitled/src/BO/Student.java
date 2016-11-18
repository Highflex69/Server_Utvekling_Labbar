package BO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Teddy on 2016-11-18.
 */
@Entity
@Table(name = "T_STUDENT")
public class Student {
    //Student
    private long id;
    private String name;

    public Student()
    {

    }

    @Id
    public long getId()
    {
       return id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public  void setName(String name)
    {
        this.name = name;
    }


}
