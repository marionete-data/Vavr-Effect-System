import io.vavr.Function1;
import io.vavr.Function2;
import org.junit.Test;

public class Functions {

    @Test
    public void funcFeatures() {
        Function1<String, Boolean> strBool = str -> str.length() == 4;
        System.out.println(strBool.apply("hell"));
    }

    @Test
    public void funcCompositionFeatures() {
        Function2<String, String, String> func2 = (str, str1) -> str.toUpperCase();
        System.out.println(func2.apply(" world").apply(""));
    }

    @Test
    public void funcMemorization() {

    }

}
