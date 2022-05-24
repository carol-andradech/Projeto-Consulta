package br.edu.iff.projetoConsulta.annotation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public abstract class CEPValidator implements ConstraintValidator<CEPValidation, String>{
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null) return false;
        if(value.contains(" ")) return false;
        return value.matches("(^[0-9]{5})-?([0-9]{3}$)");
    }
}
