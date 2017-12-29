package com.genpact.lambda;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;

import com.genpact.lambda.model.Artist;

/**
 * Hello world!
 *
 */
@SuppressWarnings("unused")
public class App {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// demo1();
		// demo2();
		// demo3();
		// demo4();
		// demo5();
		// demo6();
//		 demo7();
		// demo8();
//		 demo9();
		// demo10();
//		 demo11();
		// demo12();
//		demo13();
//		demo14();
//		demo15();
		
//		LocalDateTime date = LocalDateTime.now();
//		System.out.println(date);//date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
		System.out.println(CollectionUtils.subtract(Arrays.asList("1","2","3"),new ArrayList<String>()));
	}
	
	private static List<Artist> getData() {

		return Arrays.asList(new Artist("xsc", 10, "dl"), new Artist("wg", 11, "bj"), new Artist("cg", 12, "dl"));
	}

	private static List<Artist> getDataHasWorks() {
		return Arrays.asList(new Artist("xsc", 10, "dl", Arrays.asList(new Artist().new Works("work1", new Date()), new Artist().new Works("work2", new Date()), new Artist().new Works("work3", new Date()))), new Artist("wg", 11, "bj", Arrays.asList(new Artist().new Works("work4", new Date()), new Artist().new Works("work5", new Date()))), new Artist("cg", 12, "dl", Arrays.asList(new Artist().new Works("work4", new Date()))));
	}

	private static void demo1() {
		// Predicate 接口 只提供了一个方法 test 返回boolean
		Predicate<Integer> r = x -> x > 5;
		Predicate<Integer> r1 = x -> x < 9;
		System.out.println(r.and(r1).test(10));
	}

	private static void demo2() {
		// 定义的时候可以省略x y 的类型 类型判断系统会自动根据xy的类型 ，BinaryOperator 如果不指定泛型的话会报错
		BinaryOperator<Integer> r = (x, y) -> x + y;
		System.out.println(r.apply(3, 5));
	}

	private static void demo3() {
		Consumer<Object> r = x -> System.out.println(x);
		r.accept("123");
	}

	private static void demo4() {
		System.out.println(ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd")).get().format(new Date()));
	}

	private static void demo5() {
		List<Artist> artists = getData();
		// 这个理解为判断条件
		Predicate<Artist> p1 = x -> x.getAddress().contains("dl");
		Predicate<Artist> p2 = x -> x.getAge() >= 11;

		// p1.and(p2) 根据条件过滤 address.contains("dl") && age >= 11
		// System.out.println(artists.stream().filter(p1.and(p2)).count());
		// System.out.println(artists.stream().filter(p1.or(p2)).count());

		// negate 取 原集合和条件筛选后集合的差集
		System.out.println(artists.stream().filter(p1.and(p2).negate()).collect(Collectors.toList()));

	}

	// map 可以操作并且改变原来集合中的元素变成新的元素
	private static void demo6() {
		List<Integer> l = Stream.of("shengchun.xia", "gang.chen", "guan.wang").map(s -> s.indexOf(".")).collect(Collectors.toList());
		System.out.println(l);
	}

	private static void demo7() {
		System.out.println(Stream.of("shengchun.xia", "gang.chen", "guan.wang").min(Comparator.comparing((String item) -> item.length())).get());
	}

	private static void demo8() {
		System.out.println(addUp(Stream.of(1, 2, 3, 4)));
	}

	private static int addUp(Stream<Integer> numbers) {
		return numbers.reduce((a, b) -> a + b).get();
		// return numbers.mapToInt(x->x).sum();
	}

	private static void demo9() {
		List<Artist> artists = getData();
		// flatMap与map（Function）非常类似，区别在于传入方法的lambda表达式的返回类型。
		// map方法中的lambda表达式返回值可以是任意类型，在map函数返回之前会包装为Optional。
		// 但flatMap方法中的lambda表达式返回值必须是Optionl实例。
		System.out.println(artists.stream().flatMap(artist -> Stream.of(artist.getName(), artist.getAddress())).collect(Collectors.toList()));
	}
	
	private static void sort() {
		List<Artist> artists = getData();
		artists = artists.stream().sorted((o1,o2)->o1.getName().compareTo(o2.getName())).collect(Collectors.toList());
	}

	private static void demo10() {
		System.out.println(getDataHasWorks().stream().filter(artist -> artist.getWorks().size() <= 2).collect(Collectors.toList()));
	}

	private static void demo11() {
		System.out.println(getDataHasWorks().stream().map(artist -> artist.getWorks().size()).mapToInt(x -> x).sum());
		// System.out.println(getDataHasWorks().stream().flatMap(artist->
		// Stream.of(artist.getWorks().size())).mapToInt(x->x).sum());
	}

	private static void demo12() {
		String str = "abcDEfGHijkLmNOpqrstUvWxYz";
		System.out.println(str.chars().filter(x -> Character.isLowerCase((char) x)).count());
	}

	private static void demo13() {
		System.out.println(Arrays.asList("abcDEfGHijkLmNOpqrstUvWxYz", "abcDEF", "fadsfdasfdsadasfdafds", "").stream().max(Comparator.comparing((String s) -> s.length())));
	}
	
	//模仿蒙特卡罗算法
	private static void demo14() {
		Map<Integer, Double> map = IntStream.range(0,10).parallel().mapToObj(x->{
			int a = ThreadLocalRandom.current().nextInt(1, 7);
			int b = ThreadLocalRandom.current().nextInt(1, 7);
			return a+b;
		}).collect(Collectors.groupingBy(side->side, Collectors.summingDouble(r->Double.valueOf(r.toString()))));
		
		
		System.out.println(map);
	}
	
	
	
	
	
	
}
