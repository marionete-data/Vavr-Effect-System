import io.vavr.Tuple2;
import io.vavr.collection.Queue;
import io.vavr.concurrent.Future;
import io.vavr.control.Try;
import org.junit.Test;

import java.util.Random;

public class Patterns {

    @Test
    public void parallelComputation() {

    }

    @Test
    public void raceComputation() {

    }

    @Test
    public void retryPolicy() {
        System.out.println(getImportantStuff());
        System.out.println(getImportantStuff());
        System.out.println(getImportantStuff());
    }

    Queue<Try<String>> queue = Queue.of(
            Try.failure(new IllegalStateException()),
            Try.failure(new IllegalStateException()),
            Try.success("Important stuff"));

    public Try<String> getImportantStuff() {
        Tuple2<Try<String>, Queue<Try<String>>> dequeue = queue.dequeue();
        queue = dequeue._2;
        return dequeue._1;
    }

    private Random rand = new Random();

    public Future<String> getCar(String name){
        return Future.of(() -> {
            Thread.sleep(rand.nextInt(2000));
            return name;
        });
    }

    public User getUser() {
        return new User("John Doe");
    }

    public Account getAccount() {
        return new Account("profile info");
    }

    record User(String name) {
    }

    record Account(String profile) {
    }

    record UserAccount(User user, Account account){}


}
