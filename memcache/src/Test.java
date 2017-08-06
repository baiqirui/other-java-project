import cn.com.memcache.MemcacheManager;

public class Test
{
    public static void main(String[] args)
    {
        
        MemcacheManager mm = MemcacheManager.getInstance();
        mm.add("name", "baiqirui");
        mm.add("age", 10);
        mm.incr("age", 20);
        System.out.println("name === " + mm.get("name"));
        System.out.println("before age=== " + mm.get("age"));
        System.out.println("increment age=== " + mm.get("age"));
        
        mm.decr("age", 5);
        System.out.println("decrement age=== " + mm.get("age"));
        //System.out.println(mm.getMuti(new String[]{"name", "age", "address"}));
        
        mm.add("incr" ,11);
        mm.incr("incr", 2);
        System.out.println("incr ==" + mm.get("incr"));//返回值为11+2=13
    }
}
