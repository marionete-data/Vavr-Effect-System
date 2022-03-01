import io.vavr.concurrent.Future;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.TimeoutException;

import static io.vavr.API.*;

public class EffectSystem {

    @Test
    public void optionEffect() { //Null
        Option<String> program1 = Option.of(getMaybeString());

        String aDefault = Option.of(getMaybeString())
                .map(t -> t.toUpperCase())
                .flatMap(value -> program1.map(v -> v + value))
                .getOrElse("default");
        System.out.println(aDefault);
    }

    @Test //Throwable
    public void tryEffect() {
        Try<String> recoveringProgram = Try.of(() -> "recovering from error");

        Try<String> of1 = Try.of(() -> getStringOrError());
        Try<String> of = of1
                .map(t -> t.toUpperCase())
                .flatMap(value -> of1.map(v -> v + value))
                .recoverWith(t -> recoveringProgram);
        System.out.println(of);
    }


    @Test
    public void eitherEffect() {
        Either<CacheInformation, String> map = getEitherString()
                .map(db -> db.value.toUpperCase());

    }

    @Test
    public void futureEffect() throws InterruptedException {
        Future<String> of = Future.of(() -> "Hey I'm new Thread " + Thread.currentThread().getName());
        Future<String> of1 = Future.of(() -> "Hey I'm new Thread " + Thread.currentThread().getName());
        of.flatMap(v -> of1.map(v1 -> v + " " + v1))
                .onSuccess(d -> System.out.println(d))
                .onComplete(tryProgram -> {
            if(tryProgram.isSuccess()){
                System.out.println(tryProgram.get());
            }
        });
        Thread.sleep(10000);
    }

    @Test
    public void validateEffect() {

    }


    /**
     * Function that return side-effect of null
     */
    public static String getMaybeString() {
        if (new Random().nextBoolean()) {
            return "hello Vavr world";
        } else {
            return null;
        }
    }

    /**
     * Function that return side-effect of Throwable
     */
    public static String getStringOrError() {
        if (new Random().nextBoolean()) {
            return "hello Vavr world";
        } else {
            throw new CustomException();
        }
    }

    /**
     * Function that return side-effect of alternative flow
     */
    public static Either<CacheInformation, DBInformation> getEitherString() {
        if (new Random().nextBoolean()) {
            return Right(new DBInformation("John Doe"));
        } else {
            return Left(new CacheInformation("Old John Doe"));
        }
    }

    /**
     * Function that return side-effect of TimeoutException
     */
    public static Future<String> getFutureOrError() {
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

    public static class CustomException extends RuntimeException {
    }

}


