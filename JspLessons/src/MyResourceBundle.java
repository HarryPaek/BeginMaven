import java.util.ListResourceBundle;

/**
 * 
 */

/**
 * @author HarryPaek
 *
 */
public class MyResourceBundle extends ListResourceBundle {

	/* (non-Javadoc)
	 * @see java.util.ListResourceBundle#getContents()
	 */
	protected Object[][] getContents() {
		return new Object[][] {
			{"OK", "확인"},
			{"Cancel", "취소"},
			{"Reset", "재설정"},
			{"Submit", "제출"}
		};
	}
}
