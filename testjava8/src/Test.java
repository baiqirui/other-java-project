import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;

public class Test
{
    public static void main(String[] args)
        throws IOException
    {
        Person p1 = new Person(1, "aa", 20, "男");
        Person p2 = new Person(2, "bb", 30, "男");
        Person p3 = new Person(3, "cc", 40, "女");
        Person p4 = new Person(4, "dd", 50, "女");
        
        List<Person> inventory = new ArrayList<Person>();
        inventory.add(p1);
        inventory.add(p2);
        inventory.add(p3);
        inventory.add(p4);
//        inventory.add(null);
        
//        System.out.println(processFile());
//        String twoLines = processFile((BufferedReader br) -> br.readLine() + br.readLine());
//        System.out.println(twoLines);
        // List<Person> result = filterPersons(inventory, (person) -> "男".equals(person.getSex()));
        
        Predicate<Person> filterSex = (Person p) -> "男".equals(p.getSex());
//        filter(inventory, filterSex);
        Predicate<Person> notMan = filterSex.negate();
        Predicate<Person> oldMan = filterSex.and((Person p) -> p.getAge() > 60);
        Predicate<Person> otherMan = filterSex.and(p -> p.getAge() > 18).or(notMan);
        
        Consumer<Person> output = (Person p) -> {System.out.println("读取到列表中的数据" + p);};
//        forEach(inventory, output);
        inventory.forEach(output.andThen(p -> System.out.println("继续读取到列表中的数据" + p)));
        
        Function<Person, User> apply = (Person p) ->
        {
            if (null == p)
            {
                throw new RuntimeException();
            }
            User u = new User();
            u.setId(p.getId());
            u.setName(p.getName());
            return u;
        };
        List<User> resultList = map(inventory, apply);
        System.out.println(resultList);
        
        
        List<Integer> ages = Arrays.asList(7, 3, 4, 10);
        //第1种方式
        Function<Integer, Person> apply1 = (Integer age) ->
        {
            Person p = new Person();
            p.setAge(age);
            return p;
        };
      //第2种方式
        Function<Integer, Person> apply2 = age ->
        {
            Person p = new Person();
            p.setAge(age);
            return p;
        };
      //第3种方式
        Function<Integer, Person> apply3 = Person::new;
        map1(ages, apply3);
        map1(ages, Person::new);
        
        BiFunction<Integer, String, Person> apply0 = Person::new;
        List<Integer> ids = Arrays.asList(7, 3, 4, 10);
        List<Person> persons = new ArrayList<Person>();
        ids.stream().forEach(id ->
        {
            Person p = apply0.apply(id, "QQ");
            persons.add(p);
        });
        
        System.out.println("persons === " + persons);
        
        Supplier<Person> s = () -> {return null;};
        
        Collections.sort(inventory, new Comparator<Person>()
        {
            @Override
            public int compare(Person o1, Person o2)
            {
                return o1.getAge() - o2.getAge();
            }
        });
        Collections.sort(inventory, (Person o1, Person o2) -> {return o1.getAge() - o2.getAge();});
        Collections.sort(inventory, (Person o1, Person o2) -> {return o1.getAge() - o2.getAge();});
        Collections.sort(inventory, ( o1,  o2) -> {return o1.getAge() - o2.getAge();});
        inventory.sort((o1, o2) ->  {return o1.getAge() - o2.getAge();});
//        inventory.sort(Person::c);
        
        
        Function<Person, Integer> a = (Person p) -> {return p.getAge();};
        Comparator.comparing(a);
        
        
        
        
        List<String> str = Arrays.asList("a","b","A","B");
        str.sort((o1, o2) ->  {return o1.compareToIgnoreCase(o2);});
        str.sort(String::compareToIgnoreCase);
        //两种方式
        ToDoubleFunction<Person> t1 = (Person p) -> p.getAmount();
        ToDoubleFunction<Person> t2 = (p) -> p.getAmount();
        ToDoubleFunction<Person> t3 = p -> p.getAmount();
        ToDoubleFunction<Person> t4 = (Person::getAmount);
        
//        Function<String, Integer> stringToInteger = (String s) -> Integer.parseInt(s);
//        inventory.sort(comparing(Person::getAge));
    }
    
    public <U extends Comparable<? super U>>  Comparator<Person> comparing(Function<Person,? extends U> a)
    {
//        Function<Person, Integer> a = (Person p) -> {return p.getAge();};
//       return (Person o1, Person o2) -> {return a.apply(o1).compareTo(a.apply(o2));};
        return (o1, o2) -> a.apply(o1).compareTo(a.apply(o2));
       
    }
    
    
    public static String processFile()
        throws IOException
    {
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\de\\workspace_sts_2.8\\testjava8\\src\\data.txt")))
        {
            return br.readLine();
        }
    }
    
    public static String processFile(BufferedReaderProcessor p)
        throws IOException
    {
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\de\\workspace_sts_2.8\\testjava8\\src\\data.txt")))
        {
            return p.process(br);
        }
    }
    
    public static <T> List<T> filter(List<T> list, Predicate<T> p)
    {
        List<T> results = new ArrayList<>();
        for (T s : list)
        {
            if (p.test(s))
            {
                results.add(s);
            }
        }
        return results;
    }
    
    public static <T> void forEach(List<T> list, Consumer<T> c)
    {
        if (null != list && list.size() > 0)
        {
            list.forEach(t -> c.accept(t));
        }
    }
    
    public static <T, R> List<R> map(List<T> list, Function<T, R> f)
    {
        List<R> resultList = new ArrayList<R>();
        if (null != list && list.size() > 0)
        {
            list.forEach(t -> {
                 R r = f.apply(t);
                 resultList.add(r);
            });
        }
        return resultList;
    }
    
    public static  List<Person> map1(List<Integer> list, Function<Integer, Person> f)
    {
        List<Person> resultList = new ArrayList<Person>();
        if (null != list && list.size() > 0)
        {
            list.forEach(t -> {
                 Person r = f.apply(t);
                 resultList.add(r);
            });
        }
        return resultList;
    }
}
