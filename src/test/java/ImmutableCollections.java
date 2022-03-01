import io.vavr.collection.List;
import io.vavr.concurrent.Future;
import org.junit.Test;

import java.io.Serializable;

public class ImmutableCollections {


    /**
     * Create a List with "hello" "world" elements in an immutable collection.
     * Make composition with another List that contains "!!!" String
     * Transform the result in upperCase
     */
    @Test
    public void append() {

    }


    /**
     * Transform List of ["hello", "functional","world"] String into one String line in upperCase
     */
    @Test
    public void reduce() {

    }


    /**
     * Filter all non String types, remove the last one, and set in uuperCase
     */
    @Test
    public void filter() {
        List<? extends Serializable> mixOfTypes = List.of("hello", 1, "functional", 1000L, "collection", 10f, "Not good");
    }


}
