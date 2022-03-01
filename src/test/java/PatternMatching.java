import io.vavr.API;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.Test;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.Patterns.*;

public class PatternMatching {

    @Test
    public void optionPattern() {
        var option = Option.of(EffectSystem.getMaybeString());
        String a = "";
        if(option.isDefined()){
            a= option.get();
        }else{
            a ="empty";
        }

        String empty = API.Match(option).of(
                Case($Some($()), vale -> vale.toUpperCase()),
                Case($None(), "empty")
        );

    }

    @Test
    public void tryPattern() {
        Try<String> of = Try.of(() -> EffectSystem.getStringOrError());
        String empty = API.Match(of).of(
                Case($Success($()), vale -> vale.toUpperCase()),
                Case($Failure($()), t ->  "empty")
        );
    }

    @Test
    public void eitherPattern() {
//        String empty = API.Match(EffectSystem.getEitherString()).of(
//                Case($Success($()), vale -> vale.toUpperCase()),
//                Case($Failure($()), t ->  "empty")
//        );
    }

}
