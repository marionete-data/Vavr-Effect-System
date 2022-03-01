import io.vavr.Tuple2;
import io.vavr.collection.Queue;
import io.vavr.concurrent.Future;
import io.vavr.control.Try;
import org.junit.Test;

import java.util.List;
import java.util.Random;

public class Patterns {

    @Test
    public void parallelComputation() throws InterruptedException {
        Future<User> userFuture = Future.of(() -> getUser());
        Future<Account> account = Future.of(() -> getAccount());
        Future<UserAccount> outputFuture = Future.of(() -> new UserAccount(userFuture.get(), account.get()));
        outputFuture.onSuccess(s -> System.out.println(s));
        Thread.sleep(10000);
    }

    @Test
    public void raceComputation() throws InterruptedException {
        Future<String> ford = getCar("Ford");
        Future<String> ferrari = getCar("Ferrari");
        Future<String> toyota = getCar("Toyota");

        Future<String> winner = Future.firstCompletedOf(List.of(ford, ferrari, toyota));
        winner.onSuccess(car-> System.out.println("Winner car:" + car));
        Thread.sleep(2000);
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
