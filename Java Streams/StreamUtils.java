import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.*;

public class StreamUtils {
	
	/**
	* @param strings: the input collection of <code>String</code>s.
	* @return a collection of <code>String</code>s that start with a capital letter.
	*/
	public static Collection<String> capitalized(Collection<String> strings)
	{
		return (Collection<String>)strings.stream()
				.filter(str -> str.length() > 0)
				.filter(str -> str.charAt(0) < 'a')
				.collect(Collectors.toList());
	}
	
	/**
	* Find and return the longest <code>String</code> in a given collection of <code>String</code>s.
	*
	* @param strings: the given collection of <code>String</code>s.
	* @param from_start: a <code>boolean</code> flag that decides how ties are broken.
	If <code>true</code>, then the element encountered earlier in
	* the iteration is returned, otherwise the later element is returned.
	* @return the longest <code>String</code> in the given collection,
	* where ties are broken based on <code>from_start</code>.
	*/
	public static String longest(Collection<String> strings, boolean from_start)
	{	
		return (String)strings.stream()
				.reduce("", (String str1, String str2) ->  
				{
					if(from_start)
					{
						return str2.length() > str1.length() ? str2 : str1;
					}
					else
					{
						return str2.length() >= str1.length() ? str2 : str1;
					}
				});
	}
	
	/**
	* Find and return the least element from a collection of given elements that are comparable.
	*
	* @param items: the given collection of elements
	* @param from_start: a <code>boolean</code> flag that decides how ties are broken.
	* If <code>true</code>, the element encountered earlier in the
	* iteration is returned, otherwise the later element is returned.
	* @param <T>: the type parameter of the collection (i.e., the items are all of type T).
	* @return the least element in <code>items</code>, where ties are
	* broken based on <code>from_start</code>.
	*/
	public static <T extends Comparable<T>> T least(Collection<T> items, boolean from_start)
	{
		return (T)items.stream()
				.reduce((T t1, T t2) ->  
				{
					if(from_start)
					{
						return t1.compareTo(t2) >= 0 ? t2 : t1;
					}
					else
					{
						return t1.compareTo(t2) > 0 ? t2 : t1;
					}
				}).orElse(null);	
	}
	
	/**
	* Flattens a map to a stream of <code>String</code>s, where each element in the list
	* is formatted as "key -> value".
	*
	* @param aMap the specified input map.
	* @param <K> the type parameter of keys in <code>aMap</code>.
	* @param <V> the type parameter of values in <code>aMap</code>.
	* @return the flattened list representation of <code>aMap</code>.
	*/
	public static <K, V> List<String> flatten(Map<K, V> aMap)
	{
		return aMap.entrySet().stream()
				.map(elem -> "" + elem.getKey() + " -> " + elem.getValue())
				.collect(Collectors.toList());	
	}
}