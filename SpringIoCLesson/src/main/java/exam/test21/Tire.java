package exam.test21;

import java.util.Date;
import java.util.Properties;

public class Tire {
	String     maker;
	Properties spec;
	Date       createdDate;

	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public Properties getSpec() {
		return spec;
	}
	public void setSpec(Properties spec) {
		this.spec = spec;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Override
	public String toString() {
		StringBuffer specInfo = new StringBuffer();
		
		if(spec != null)
			for (String key : spec.stringPropertyNames()) {
				specInfo.append(String.format(", %s:%s", key, spec.getProperty(key)));
			}
		
		return String.format("[Tire:%s, %s%s]", maker, specInfo.length() > 2 ? specInfo.substring(2) : "", (createdDate != null) ? String.format(", %1$tF %1$tT", createdDate) : "");
	}
}
