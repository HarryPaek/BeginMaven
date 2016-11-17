/**
 * 
 */
package lesson02.get;

/**
 * @author HomeUser
 *
 */
public class PlusOperator implements IOperator {

	@Override
	public double execute(double a, double b) throws Exception{
		return a + b;
	}

	@Override
	public String getName() {
		return "+";
	}
}
