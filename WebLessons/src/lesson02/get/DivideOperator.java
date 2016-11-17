/**
 * 
 */
package lesson02.get;

/**
 * @author HomeUser
 *
 */
public class DivideOperator implements IOperator {

	@Override
	public String getName() {
		return "/";
	}

	@Override
	public double execute(double a, double b) throws Exception {
		return a / b;
	}
}

