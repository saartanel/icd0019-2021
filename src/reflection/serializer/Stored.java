package reflection.serializer;
//
//public @interface Stored {
//    String value() default "";
//}
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Stored{
    String value() default "";
}