package com.atsiaras.matchodds.controllers;

import com.atsiaras.matchodds.dtos.MatchDTO;
import com.atsiaras.matchodds.services.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @Operation(
            summary = "Get all matches.",
            description = "Retrieve a list of all the matches.")
    @ApiResponse(
            responseCode = "200",
            description = "List of matches retrieved successfully!",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = MatchDTO.class))))
    @GetMapping
    public ResponseEntity<List<MatchDTO>> getAllMatches() {
        return ResponseEntity.ok(matchService.getAllMatches());
    }


    @Operation(
            summary = "Get match by id.",
            description = "Retrieve a match by its id.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Match retrieved successfully!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MatchDTO.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Match not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{matchId}")
    public ResponseEntity<MatchDTO> getMatchById(@PathVariable Long matchId) {
        return ResponseEntity.ok(matchService.getMatchById(matchId));
    }


    @Operation(
            summary = "Create a new match",
            description = "Create a match with required fields.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Match created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MatchDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/createMatch")
    public ResponseEntity<MatchDTO> createMatch(@Valid @RequestBody MatchDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matchService.createMatch(dto));
    }


    @Operation(
            summary = "Update an existing match")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Match updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MatchDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Match not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/updateMatch/{matchId}")
    public ResponseEntity<MatchDTO> updateMatchById(
            @PathVariable Long matchId,
            @Valid @RequestBody MatchDTO dto) {
        return ResponseEntity.ok(matchService.updateMatchById(matchId, dto));
    }


    @Operation(
            summary = "Delete a match")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Match deleted successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Match not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/deleteMatch/{matchId}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long matchId) {
        matchService.deleteMatchById(matchId);
        return ResponseEntity.noContent().build();
    }
}
