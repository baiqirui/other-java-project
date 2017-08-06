import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class CompareTest
{
    
    public static void main(String[] args)
        throws IOException
    {
        Person p1 = new Person(1, "aa", 20, "男");
        Person p2 = new Person(3, "bb", 60, "男");
        Person p3 = new Person(4, "cc", 18, "女");
        Person p4 = new Person(2, "dd", 30, "女");
        
        List<Person> persons = new ArrayList<Person>();
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        persons.add(p4);
        
        System.out.println("排序前：" + persons);
        Function<Person, Integer> ageSort = (Person p) -> p.getAge();
        persons.sort(comparing(ageSort));
        System.out.println("按年龄排序后" + persons);
//        persons.sort(comparing(age -> p.getAge()));
        persons.sort(comparing(Person::getId));
        System.out.println("按ID排序后" + persons);
    }
    
    public static <U extends Comparable<? super U>>  Comparator<Person> comparing(Function<Person,? extends U> a)
    {
        return (o1, o2) -> a.apply(o1).compareTo(a.apply(o2));
    }
}
