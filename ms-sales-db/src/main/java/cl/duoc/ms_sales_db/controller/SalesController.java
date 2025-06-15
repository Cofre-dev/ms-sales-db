package cl.duoc.ms_sales_db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ms_sales_db.model.dto.SalesDTO;
import cl.duoc.ms_sales_db.service.SalesService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/sales")
@CrossOrigin("*")
public class SalesController {

    @Autowired
    SalesService salesService;

    @GetMapping("/{id}")
    public ResponseEntity<SalesDTO> findSalesById(@PathVariable Long id) {
        SalesDTO salesDTO = salesService.findSalesById(id);
        return (salesDTO != null)?  new ResponseEntity<>(salesDTO, HttpStatus.OK) :
                                     new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    }

    @PostMapping("")
    public ResponseEntity<SalesDTO> postMethodName(@RequestBody SalesDTO salesDTO) {

        SalesDTO newSalesDTO = salesService.createSale(salesDTO);
       return (newSalesDTO != null)?  new ResponseEntity<>(newSalesDTO, HttpStatus.OK) :
                                     new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    }
    

}
