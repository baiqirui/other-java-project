import java.util.Comparator;

public class Person  
{
    // ID
    private Integer id;
    
    // 姓名
    private String name;
    
    // 年龄
    private Integer age;
    
    // 性别
    private String sex;
    
    private Double amount;
    
    private Comparator<Person> byWeight =
        (Person p11, Person p22) -> p11.getAge().compareTo(p22.getAge());
    
    public Person()
    {
        super();
    }
    
    public Person(Integer age)
    {
        super();
        this.age = age;
    }
    
    public Person(Integer id, String name)
    {
        super();
        this.id = id;
        this.name = name;
    }

    public Person(Integer id, String name, Integer age, String sex)
    {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    
    public Double getAmount()
    {
        return amount;
    }

    public void setAmount(Double amount)
    {
        this.amount = amount;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    @Override
    public String toString()
    {
        return "Person [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + "]";
    }
   
    
    public int compare(Comparator<Person> c)
    {
        return c.compare(this, this);
    }

}
