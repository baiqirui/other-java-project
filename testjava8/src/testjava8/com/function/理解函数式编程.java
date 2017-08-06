package testjava8.com.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 理解函数式编程
{
    public static void main(String[] args)
    {
        List<Integer> ids = Arrays.asList(1, 4, 9);
        List<List<Integer>> 子集 = new ArrayList<List<Integer>>();
        
        for (Integer id : ids)
        {
            List<Integer> 单子集 = new ArrayList<Integer>();
            List<Integer> 双子集 = new ArrayList<Integer>();
            单子集.add(id);
            子集.add(单子集);
        }
            
    }
}
