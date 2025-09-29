package com.atsiaras.matchodds.controllers;

import com.atsiaras.matchodds.dtos.ErrorResponse;
import com.atsiaras.matchodds.dtos.MatchOddsDTO;
import com.atsiaras.matchodds.services.MatchOddsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches/{matchId}/odds")
public class MatchOddsController {

    private final MatchOddsService matchOddsService;

    public MatchOddsController(MatchOddsService matchOddsService) {
        this.matchOddsService = matchOddsService;
    }

    @Operation(
            summary = "Get all odds for a match",
            description = "Retrieve a list of the odds for a match.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "List of odds retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = MatchOddsDTO.class)))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Match not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<List<MatchOddsDTO>> getOddsByMatchId(@PathVariable Long matchId) {
        return ResponseEntity.ok(matchOddsService.getOddsByMatchId(matchId));
    }

    @Operation(
            summary = "Get specific odd by ID for a match",
            description = "Retrieve odd for a specific match by ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Odd retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MatchOddsDTO.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Odd not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Odd not found for this match.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{oddsId}")
    public ResponseEntity<MatchOddsDTO> getOddsById(@PathVariable Long matchId, @PathVariable Long oddsId) {
        return ResponseEntity.ok(matchOddsService.getOddsById(matchId, oddsId));
    }


    @Operation(
            summary = "Create a new odd.",
            description = "Create a new odd for a specific match.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Odd created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MatchOddsDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Odd not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Odd does not belong to this match",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/createOdd")
    public ResponseEntity<MatchOddsDTO> create(@PathVariable Long matchId, @Valid @RequestBody MatchOddsDTO dto) {
        MatchOddsDTO created = matchOddsService.createOdds(matchId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }



    @Operation(
            summary = "Update an odd.",
            description = "Update and odd for a specific match.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Odd updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MatchOddsDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Odd not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Odd does not belong to this match",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/updateOdd/{oddsId}")
    public ResponseEntity<MatchOddsDTO> update(
            @PathVariable Long matchId,
            @PathVariable Long oddsId,
            @Valid @RequestBody MatchOddsDTO dto) {
        MatchOddsDTO updated = matchOddsService.updateOddsById(matchId, oddsId, dto);
        return ResponseEntity.ok(updated);
    }


    @Operation(
            summary = "Delete an odd.",
            description = "Delete an odd for a specific match.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Odd deleted successfully!"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Odd not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Odd does not belong to this match",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/deleteOdd/{oddsId}")
    public ResponseEntity<Void> delete(@PathVariable Long matchId, @PathVariable Long oddsId) {
        matchOddsService.deleteOddsById(matchId, oddsId);
        return ResponseEntity.noContent().build();
    }
}
