package pl.org.napierala.budget.krowka.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import pl.org.napierala.budget.krowka.validation.FieldMatchValidator;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
public @interface FieldMatch {

	String message() default "{constraints.fieldmatch}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String[] value();

}
