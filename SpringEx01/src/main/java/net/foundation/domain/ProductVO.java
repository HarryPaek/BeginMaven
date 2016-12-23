/**
 * 
 */
package net.foundation.domain;

/**
 * @author HarryPaek
 *
 */
public class ProductVO {
	private String name;
	private double price;
	
	public ProductVO(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return String.format("ProductVO [name=%s, price=%,.2f]", name, price);
	}

}
