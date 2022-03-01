import io.vavr.concurrent.Future;
import io.vavr.control.Either;
import io.vavr.control.Validation;
import org.junit.Test;
import rx.internal.util.ExceptionsUtils;

import java.util.Random;
import java.util.concurrent.TimeoutException;

import static io.vavr.API.Left;
import static io.vavr.API.Right;

public class EffectSystem {


    @Test
    public void optionEffect() {

    }

    @Test
    public void tryEffect() {

    }

    @Test
    public void eitherEffect() {

    }

    @Test
    public void futureEffect() {

    }

    @Test
    public void validateEffect() {

    }

    /**
     * Function that return side-effect of null
     */
    private String getMaybeString() {
        if (new Random().nextBoolean()) {
            return "hello Vavr world";
        } else {
            return null;
        }
    }

    /**
     * Function that return side-effect of Throwable
     */
    private String getStringOrError() {
        if (new Random().nextBoolean()) {
            return "hello Vavr world";
        } else {
            throw new CustomException();
        }
    }

    /**
     * Function that return side-effect of alternative flow
     */
    private Either<CacheInformation, DBInformation> getEitherString() {
        if (new Random().nextBoolean()) {
            return Right(new DBInformation("John Doe"));
        } else {
            return Left(new CacheInformation("Old John Doe"));
        }
    }

    /**
     * Function that return side-effect of TimeoutException
     */
    private Future<String> getFutureOrError() {
        if (new Random().nextBoolean()) {
            return Future.of(() -> "hello Vavr world");
        } else {
            return Future.failed(new TimeoutException());
        }
    }

    /**
     * Function that return side-effect of invalid argument
     */
    private Validation<String, String> getValidationValue(String value) {
        if (new Random().nextBoolean()) {
            return Validation.valid(value);
        } else {
            return Validation.invalid(value);
        }
    }

    record DBInformation(String value) {
    }

    record CacheInformation(String value) {
    }

    private class CustomException extends RuntimeException {
    }

}


