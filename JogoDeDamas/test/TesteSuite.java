
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;


/**
 *
 * @author felip
 */
public class TesteSuite extends TestCase {
    public TesteSuite(String testName) {
        super(testName);
    }

     public static TestSuite suite() {
        TestSuite suite = new TestSuite("TestandoEstrategiaJogoTest");
        return suite;
    }
}