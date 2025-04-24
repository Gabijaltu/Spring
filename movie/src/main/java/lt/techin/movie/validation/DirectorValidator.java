package lt.techin.movie.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DirectorValidator implements ConstraintValidator<Director, String> {

@Override
  public boolean isValid(String director, ConstraintValidatorContext context){
  return director != null && director.matches("^[A-Z][a-z]+$");
}
}
