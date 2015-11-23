package de.bht.bobross.exception;

/**
 * Created by Dennis Ritter
 */
public class ParameterNullException extends IllegalArgumentException{

  /**
   * The constructor
   * @param     parameter   The String representation of the parameter name that is null
   */
  public ParameterNullException( final String parameter ){
    super("The parameter " + '"' + parameter + '"' + " can not be null.");
  }

}
