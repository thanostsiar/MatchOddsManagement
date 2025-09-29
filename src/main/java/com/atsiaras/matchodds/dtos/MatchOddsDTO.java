package com.atsiaras.matchodds.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchOddsDTO {

    private Long id;

    @NotBlank(message = "Specifier must not be blank")
    @Schema(example = "1")
    private String specifier;

    @NotNull(message = "Odd is required")
    @Positive(message = "Odd must be positive")
    @Schema(example = "1.5")
    private Double odd;
}
