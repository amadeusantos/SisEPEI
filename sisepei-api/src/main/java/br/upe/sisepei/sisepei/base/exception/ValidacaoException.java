package br.upe.sisepei.sisepei.base.exception;

public class ValidacaoException extends Exception{
  private static final long serialVersionUID = 1L;
  
  public ValidacaoException(String mensagem){
    super(mensagem);
  }
}
