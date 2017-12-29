package com.genpact.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.collections.CollectionUtils;

import com.genpact.lambda.model.Artist;

public class A {
	
	
	/*public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		
		List<Artist> list = new ArrayList<Artist>();
		Artist a = null;
		a = new Artist("xsc", 10, "dl");
		a.setDate(new java.sql.Date(new Date().getTime()));
		list.add(a);

		a = new Artist("yyf", 11, "dl");
		a.setDate(new Date());
		list.add(a);
		
		Map<String, Method> map = Arrays.asList(Artist.class.getDeclaredMethods()).stream().collect(Collectors.toMap((Method m) ->m.getName(), (Method m) ->m));
		for (Artist artist : list) {
			for (Map.Entry<String, Method> entry : map.entrySet()) {
				String methodName = entry.getKey();
				if(methodName.startsWith("get")){
					Object v = entry.getValue().invoke(artist);
					if(v instanceof java.sql.Date){
						map.get(methodName.replaceFirst("g", "s")).invoke(artist, new Date(((java.sql.Date)v).getTime()));
					}
				}
				
			}
		}
		JsonConfig config = new JsonConfig();
		
		config.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (null == value) {
					return true;
				}
				return false;
			}
		});
		Collection<Artist> collection = JSONArray.toCollection(JSONArray.fromObject(list, config), Artist.class);
		for (Artist artist : collection) {
			System.out.println(artist);
		}
		
	}*/
	
	
	public void a(){
		List<Artist> list = new ArrayList<Artist>();
		Artist a = null;
		a = new Artist("xsc", 10, "dl");
		a.setDate(new java.sql.Date(new Date().getTime()));
		list.add(a);

		a = new Artist("yyf", 11, "dl");
		a.setDate(new Date());
		list.add(a);
		
		
		

		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {

			@Override
			public boolean apply(Object source, String name, Object value) {

				/*try {
					Method m = source.getClass().getMethod("get" + StringUtils.capitalize(name));
					if (m.getReturnType() == List.class) {
						return false;
					} 
					// if(m.getReturnType()==BigDecimal.class){ 
					// System.out.println(value); 
					// return false; 
					// }

				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}*/
				if (null == value) {
					return true;
				}
				return false;
			}
		});
		System.out.println(JSONArray.fromObject(list, config));
	}

}
