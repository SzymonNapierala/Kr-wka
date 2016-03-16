package pl.org.napierala.budget.krowka.validation;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

import pl.org.napierala.budget.krowka.validation.constraints.FieldMatch;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

	@Override
	public void initialize(FieldMatch constraintAnnotation) {
		this.setFields(constraintAnnotation.value());
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String[] fields = this.getFields();
		for (int f = 1; f < fields.length; ++f) {
			try {
				Object valueFirst = BeanUtils.getProperty(value, fields[0]);
				Object valueSecond = BeanUtils.getProperty(value, fields[f]);
				if (((valueFirst != null) && (valueSecond != null)
						&& (!valueFirst.equals(valueSecond)))
						|| ((valueFirst == null) && (valueSecond != null))
						|| ((valueFirst != null) && (valueSecond == null))) {
					return false;
				}
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				return false;
			}
		}
		return true;
	}

	public String[] getFields() {
		return this.fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	private String[] fields;

}
