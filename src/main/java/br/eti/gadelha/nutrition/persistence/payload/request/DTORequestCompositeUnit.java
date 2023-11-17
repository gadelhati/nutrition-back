package br.eti.gadelha.nutrition.persistence.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Date;

@Getter
public class DTORequestCompositeUnit {

    @NotNull(message = "{unit.name.not.null}") @NotBlank(message = "{unit.name.not.blank}")
    private String name;
    @NotNull(message = "{unit.number.not.null}")
    private int number;
    private String value;
    @NotNull(message = "{unit.date.not.null}")
    private Date date;
}