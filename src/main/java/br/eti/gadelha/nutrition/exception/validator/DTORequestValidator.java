package br.eti.gadelha.nutrition.exception.validator;

import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestRole;

public class DTORequestValidator extends GenericValidator<DTORequestRole> {
    @Override
    protected DTORequestRole toLocalDate(DTORequestRole value) {
        return value;
    }
}