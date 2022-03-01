import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.Test;

public class PatternMatching {

    @Test
    public void optionPattern() {
        var option = Option.of(EffectSystem.getMaybeString());

    }

    @Test
    public void tryPattern() {
        Try.of(() -> EffectSystem.getStringOrError());
    }

    @Test
    public void eitherPattern() {
        EffectSystem.getEitherString();
    }

}
