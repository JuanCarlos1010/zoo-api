package com.openx.zoo.api.controller;

import com.openx.zoo.api.dto.TicketDTO;
import com.openx.zoo.api.mapper.TicketMapper;
import com.openx.zoo.api.service.TicketService;
import com.openx.zoo.api.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    public TicketController(TicketService ticketService, TicketMapper ticketMapper) {
        this.ticketService = ticketService;
        this.ticketMapper = ticketMapper;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<TicketDTO>>> findAllTickets(){
        List<TicketDTO> response = ticketMapper.toDTO(ticketService.findAllTickets());
        ApiResponse<List<TicketDTO>> listApiResponse = new ApiResponse<>(response);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<TicketDTO>> getTicketById(@PathVariable Long id){
        TicketDTO response = ticketMapper.toDTO(ticketService.findTicketById(id));
        ApiResponse<TicketDTO> apiResponse = new ApiResponse<>(response);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "")
    public ResponseEntity<ApiResponse<TicketDTO>> createTicket(@RequestBody TicketDTO body){
        TicketDTO response = ticketMapper.toDTO(ticketService.createTicket(ticketMapper.toEntity(body)));
        ApiResponse<TicketDTO> apiResponse = new ApiResponse<>(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping(path = "")
    public ResponseEntity<ApiResponse<TicketDTO>> updateTicket(@RequestBody TicketDTO body){
        TicketDTO response = ticketMapper.toDTO(ticketService.updateTicket(ticketMapper.toEntity(body)));
        ApiResponse<TicketDTO> apiResponse = new ApiResponse<>(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteTicket(@PathVariable long id){
        boolean state = ticketService.deleteAnimal(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(state);
        return ResponseEntity.ok(apiResponse);
    }
}
