import java.util.Arrays;
import java.util.List;
import java.util.function.*;
public class HigherOrderUtils {
	
	interface NamedBiFunction<T, T1, T2> extends BiFunction<T, T1, T2> {
		String name();
	}
	
		public static NamedBiFunction<Double, Double, Double> add = new NamedBiFunction<>() 
		{
			public String name()
			{
				return "add";
			}

			//@Override
			public Double apply(Double x, Double y) {
				// TODO Auto-generated method stub
				return x + y;
			}
			
		};
		
		public static NamedBiFunction<Double, Double, Double> subtract = new NamedBiFunction<>() 
		{
			public String name()
			{
				return "diff";
			}
			
			@Override
			public Double apply(Double x, Double y) {
				// TODO Auto-generated method stub
				return x - y;
			}
		};
		
		public static NamedBiFunction<Double, Double, Double> multiply = new NamedBiFunction<>() 
		{
			public String name()
			{
				return "mult";
			}

			@Override
			public Double apply(Double x, Double y) {
				// TODO Auto-generated method stub
				return x * y;
			}
		};
		
		public static NamedBiFunction<Double, Double, Double> divide = new NamedBiFunction<>() 
		{
			public String name()
			{
				return "div";
			}

			@Override
			public Double apply(Double x, Double y) {
				// TODO Auto-generated method stub
				if(y == 0) throw new ArithmeticException();
				return x / y;
			}
		};
		
		/**
		* Applies a given list of bifunctions -- functions that take two arguments of a certain type
		* and produce a single instance of that type -- to a list of arguments of that type. The
		* functions are applied in an iterative manner, and the result of each function is stored in
		* the list in an iterative manner as well, to be used by the next bifunction in the next
		* iteration. For example, given
		* List<Integer> args = [1,1,3,0,4], and
		* List<BiFunction<Double, Double, Double>> bfs = [add, multiply, add, divide],
		* <code>zip(args, bfs)</code> will proceed iteratively as follows:
		* - index 0: the result of add(1,1) is stored in args[1] to yield args = [1,2,3,0,4]
		* - index 1: the result of multiply(2,3) is stored in args[2] to yield args = [1,2,6,0,4]
		* - index 2: the result of add(6,0) is stored in args[3] to yield args = [1,2,6,6,4]
		* - index 3: the result of divide(6,4) is stored in args[4] to yield args = [1,2,6,6,1]
		*
		* @param args: the arguments over which <code>bifunctions</code> will be applied.
		* @param bifunctions: the list of bifunctions that will be applied on <code>args</code>.
		* @param <T>: the type parameter of the arguments (e.g., Integer, Double)
		* @return the item in the last index of <code>args</code>, which has the final
		* result of all the bifunctions being applied in sequence.
		*/
		public static <T> T zip(List<T> args, List<NamedBiFunction<T, T, T>> bifunctions)
		{	
			int i = 0;
			while(i < bifunctions.size())
			{
				args.set(i + 1, (T)(bifunctions.get(i).apply(args.get(i), args.get(i + 1))));
				i++;
			}
			return (T)args.get(bifunctions.size());
		}
		
		public static class FunctionComposition<T, T1, T2>
		{
			BiFunction<Function<T, T1>, Function<T1, T2>, Function<T, T2>> compostion = (f, g) -> f.andThen(g);
		}
}