package com.federicovw.pedidos.controller;

import com.federicovw.pedidos.model.Opinion;
import com.federicovw.pedidos.service.OpinionService;
import com.federicovw.pedidos.util.OpinionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OpinionController {
    @Autowired
    OpinionService opinionService;

    @PostMapping(value = "/opinions")
    public ResponseEntity<Object> create(@RequestBody Opinion opinion) {
        Opinion savedOpinion;
        try {
            savedOpinion = opinionService.create(opinion);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedOpinion, HttpStatus.CREATED);
    }

    @DeleteMapping("/opinions/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            opinionService.delete(id);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/purchases/{purchaseId}/opinion")
    public ResponseEntity<Object> getOpinionFromPurchase(@PathVariable Long purchaseId) {
        Opinion opinion;
        try {
            opinion = opinionService.getOpinionByPurchase(purchaseId);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        HttpStatus status = opinion != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(opinion, status);
    }

    @PostMapping("/opinions/search")
    public ResponseEntity<Object> getOpinions(@RequestBody OpinionFilter opinionFilter) {
        List<Opinion> opinions;
        try {
            opinions = opinionService.getOpinions(opinionFilter);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(opinions, HttpStatus.OK);
    }
}
