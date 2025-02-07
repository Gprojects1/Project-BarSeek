package com.example.barseek_bar_mservice.controller;


import com.example.barseek_bar_mservice.model.Bar;
import com.example.barseek_bar_mservice.service.BarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("bar-service-api/v1")
public class BarController {

    private final BarService barService;

    @PostMapping("/bars")
    public ResponseEntity<String> addNew(@RequestBody Bar bar) {
        try{
            barService.addNewBar(bar);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("New bar created with name" + bar.getName(),HttpStatus.CREATED);

    }

    @GetMapping("/bars/{barId}")
    public ResponseEntity<Bar> findById(@PathVariable("barId") Long id) {
        try {
            Bar barResp = barService.findBarById(id);
            return barResp == null ?
                    new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                    ResponseEntity.ok(barResp);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bars/{barName}")
    public ResponseEntity<List<Bar>> findByName(@PathVariable("barName") String name) {
        try{
            List<Bar> barsResp = barService.findBarByName(name);
            return barsResp.isEmpty() ?
                    new ResponseEntity("No bars found", HttpStatus.NO_CONTENT) :
                    ResponseEntity.ok(barsResp);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/bars/{barId}")
    public ResponseEntity<String> deleteById(@PathVariable("barId") Long id) {
        try {
            Bar barToDelete = barService.findBarById(id);
            if(barToDelete == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            barService.deleteBarById(id);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Bar was deleted, id : " + id, HttpStatus.OK);
    }

    @PutMapping("/bars/{barId}")
    public ResponseEntity<String> updateById(@PathVariable("barId") Long id,@RequestBody Bar updatedBar) {
        try {
            Bar exBar = barService.findBarById(id);
            if(exBar == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            updatedBar.setId(id);
            barService.updateBarById(updatedBar);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Bar was updated and saved, id : " + updatedBar.getId(), HttpStatus.OK);


    }

}
