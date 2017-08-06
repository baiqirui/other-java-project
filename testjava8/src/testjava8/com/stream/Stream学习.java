package testjava8.com.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;
import java.util.stream.Stream;

public class Stream学习
{
    public static void main(String[] args)
    {
        
        List<Dish> dishList = initDish();
        //需求1：筛选热量calories > 400的前三名菜品(第一种写法)
        List<Dish> resultDishs = dishList.stream().filter((Dish d) -> d.getCalories() > 400).sorted((d1, d2) -> d2.getCalories() - d1.getCalories()).limit(3).collect(Collectors.toList());
        //需求1：筛选热量calories > 400的前三名菜品(第二种写法)
        List<Dish> resultDishs1 = dishList.parallelStream().filter(d -> d.getCalories() > 400).sorted(Comparator.comparingInt(Dish::getCalories).reversed()).limit(3).collect(Collectors.toList());
         //需求1.1：筛选热量calories > 400的前三名菜品,只需要菜品名称(第一种写法);
        Function<Dish, String> f = (Dish d) -> {return d.getName();};
        Function<Dish, String> f1 = Dish::getName;
        List<String> resultDishNames = dishList.parallelStream().filter(d -> d.getCalories() > 400).sorted(Comparator.comparingInt(Dish::getCalories).reversed()).limit(3).map(f).collect(Collectors.toList());
        //需求1.1：筛选热量calories > 400的前三名菜品,只需要菜品名称(第二种写法);
        List<String> resultDishNames1 = dishList.parallelStream().filter(d -> d.getCalories() > 400).sorted(Comparator.comparingInt(Dish::getCalories).reversed()).limit(3).map(Dish::getName).collect(Collectors.toList());

        
        
        //流只能消费1次(切记！！！)
        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
//        s.forEach(System.out::println);
//        s.forEach(System.out::println);
        

       //需求2：统计每个单词的字符个数;
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream().map(String::length).collect(Collectors.toList());
//        System.out.println(wordLengths);
        words = Arrays.asList("hello", "world");
        Function<String[],  Stream<String>> f2 = (String[] s1) -> Arrays.stream(s1);
        
        List<String> words1 = words.stream()
        .map(word -> word.split(""))
        .flatMap(Arrays::stream)
        .distinct()
        .collect(Collectors.toList());
        
//        System.out.println(words1);
        
        //需求3：找出每道菜品的名称长度
        List<Integer> dishNameLength = dishList.stream().map(Dish::getName).map(String::length).collect(Collectors.toList());
//        System.out.println(dishNameLength);
        
        //需求4：给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？例如，给定[1, 2, 3, 4, 5]，应该返回[1, 4, 9, 16, 25]
        List<Integer> nums = Arrays.asList(1, 2, 3, 4 ,5);
        List<Integer> nums1 = nums.stream().map(x -> x * x).collect(Collectors.toList());
        System.out.println(nums1);
        
        //需求5：给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
        nums = Arrays.asList(1, 2, 3);
        List<Integer> nums2 = Arrays.asList(3, 4);
        
        
        
        dishList.stream().filter(d -> d.getCalories() > 400).map(Dish::getName).collect(Collectors.toList());
        
        //需求6：将List转换成对应的Map
        Map<String, Dish> dishMap = dishList.parallelStream().collect(Collectors.toMap(Dish::getName, o -> o));
        System.out.println(dishMap);
        
        //需求7：归约函数;
        long howManyDishes = dishList.stream().collect(Collectors.counting());
       
        //相当于 
//        dishList.stream().count()
//        dishList.stream().collect(Collectors.reducing(0, e -> 1L, (i,  j) -> i + j));
//        long totalCalories1 = dishList.stream().collect(Collectors.reducing(0, Dish::getCalories, (i,  j) -> i + j));
//        long totalCalories2 = dishList.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        long totalCalories3= dishList.stream().mapToInt(Dish::getCalories).sum();
//        Dish totalCalories4= dishList.stream().reduce((d1, d2) -> d1.getCalories() + d2.getCalories()).get();
        
        
        
//        long mostCalories1= dishList.stream().collect(Collectors.reducing(0, Dish::getCalories, (i,  j) -> i > j ? i : j));
//        System.out.println("mostCalories1 === " + mostCalories1);
        Optional<Dish> od = dishList.stream().collect(Collectors.reducing( (Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println(od.get());
        long mostCalories2= dishList.stream().collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories))).get().getCalories();
        long mostCalories3 = dishList.stream().mapToInt(Dish::getCalories).max().getAsInt();
        long mostCalories4 = dishList.stream().reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2).get().getCalories();
        System.out.println("mostCalories3 === " + mostCalories3);
//        dishList.stream().reduce(0, e -> 1L, Long::sum);
//        Collectors.re
        
        //这个收集器会把所有这些信息收集到一个叫作 IntSummaryStatistics 的类里，它提供了方便的取值（getter）方法来访问结果
        IntSummaryStatistics summary = dishList.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(summary);
        
        String shortMenu = dishList.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println(shortMenu);
        
        
        
        //分组
        //按菜品类型分组;
       Map<DishType, List<Dish>> dishMap1 = dishList.stream().collect(groupingBy(Dish::getType));
        //按菜品热量分组
//       你可能想把热量不到400卡路里的菜划分为“低热量”（diet），热量400到700
//       卡路里的菜划为“普通”（normal），高于700卡路里的划为“高热量”（fat）
       Map<String, List<Dish>> dishMap2 = dishList.stream().collect(groupingBy((Dish dish) ->
       {
           if (dish.getCalories() < 400)
           {
               return "diet";
           }
           else if (dish.getCalories() >= 400 && dish.getCalories() < 700)
           {
               return "normal";
           }
           else
           {
               return "fat";
           }
       }));
       System.out.println("dishMap2 === " + dishMap2);
       //按菜品类型分组，并统计每种菜品类型的总个数;
       Map<DishType, Long> dishMap3 = dishList.stream().collect(groupingBy(Dish::getType, counting()));
       
       //多级分组， 先按菜品类型分组，然后再按菜品热量分组
       Map<DishType, Map<String, List<Dish>>> dishMap0 = dishList.stream().collect(groupingBy(Dish::getType,
           groupingBy((Dish dish) ->
           {
               if (dish.getCalories() < 400)
               {
                   return "diet";
               }
               else if (dish.getCalories() >= 400 && dish.getCalories() < 700)
               {
                   return "normal";
               }
               else
               {
                   return "fat";
               }
           })
           ));
       
       //查找按DishType分组，并找出每个子组中热量最高的 Dish
       Map<DishType, Optional<Dish>> dishMap5 = dishList.stream().collect(groupingBy(Dish::getType, maxBy(comparing(Dish::getCalories))));
       Map<DishType, Dish> dishMap6 = dishList.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(comparing(Dish::getCalories)), Optional::get)));
       
       //分区
       Map<Boolean, List<Dish>> partitionedMenu = dishList.stream().collect(partitioningBy(Dish::isVegetarian));
       System.out.println("partitionedMenu === " + partitionedMenu);
       
       Map<Boolean, List<Dish>> partitionedMenu1 = dishList.stream().collect(groupingBy(Dish::isVegetarian));
       System.out.println("partitionedMenu1 === " + partitionedMenu1);

    }
    
    private static List<Dish> initDish()
    {
        List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, DishType.MEAT),
            new Dish("beef", false, 700, DishType.MEAT),
            new Dish("chicken", false, 400, DishType.MEAT),
            new Dish("french fries", true, 530, DishType.OTHER),
            new Dish("rice", true, 350, DishType.OTHER),
            new Dish("season fruit", true, 120, DishType.OTHER),
            new Dish("pizza", true, 550, DishType.OTHER),
            new Dish("prawns", false, 300, DishType.FISH),
            new Dish("salmon", false, 450, DishType.FISH));
        return menu;
    }
}
