package br.edu.ufape.organizeBill.exception;

import br.edu.ufape.organizeBill.exception.types.NotFoundException;

@SuppressWarnings("serial")
public class ObjectNotFoundException extends NotFoundException {

	public ObjectNotFoundException(String Classe) {
		super("003", ("Objeto da Classe " +Classe + " não encontrado"));
	}

}
