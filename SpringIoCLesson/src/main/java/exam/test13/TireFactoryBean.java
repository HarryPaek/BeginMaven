package exam.test13;

import java.text.SimpleDateFormat;
import java.util.Properties;

import org.springframework.beans.factory.config.AbstractFactoryBean;

public class TireFactoryBean extends AbstractFactoryBean<Tire> {
	String maker;
	
	public void setMaker(String maker) {
		this.maker = maker;
	}

	@Override
	public Class<?> getObjectType() {
		return Tire.class;
	}

	@Override
	protected Tire createInstance() throws Exception {
		if(maker.equals("Hankook"))
			return createHankookTire();
		else
			return createKumhoTire();
	}

	private Tire createHankookTire() {
		Tire tire = new Tire();
		tire.setMaker("Hankook");
		
		Properties spec = new Properties();
		spec.setProperty("width","205");
		spec.setProperty("ratio","65");
		spec.setProperty("rim.diameter","14");
		tire.setSpec(spec);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			tire.setCreatedDate(dateFormat.parse("2016-12-14"));
		}
		catch (Exception e) {
		}
		
		return tire;
	}
	
	private Tire createKumhoTire() {
		Tire tire = new Tire();
		tire.setMaker("Kumho");
		
		Properties spec = new Properties();
		spec.setProperty("width","185");
		spec.setProperty("ratio","75");
		spec.setProperty("rim.diameter","16");
		tire.setSpec(spec);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			tire.setCreatedDate(dateFormat.parse("2016-11-14"));
		}
		catch (Exception e) {
		}
		
		return tire;
	}
}

