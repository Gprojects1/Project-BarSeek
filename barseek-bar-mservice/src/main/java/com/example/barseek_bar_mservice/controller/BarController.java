package com.example.barseek_bar_mservice.controller;


import com.example.barseek_bar_mservice.model.entity.Bar;
import com.example.barseek_bar_mservice.service.BarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bar-service-api/v1")
public class BarController {

    private final BarService barService;

    @PostMapping("/bars")
    public ResponseEntity<String> addNew(@RequestBody Bar bar) {

            Bar newBar = barService.addNewBar(bar);
            return new ResponseEntity<>("New bar created with name : " + newBar.getName(),HttpStatus.CREATED);

    }

    @GetMapping("/bars/{barId}")
    public ResponseEntity<Bar> findById(@PathVariable("barId") Long id) {

            Bar barResp = barService.findBarById(id);
            return ResponseEntity.ok(barResp);

    }

    @GetMapping("/bars/{barName}")
    public ResponseEntity<List<Bar>> findByName(@PathVariable("barName") String name) {

            List<Bar> barsResp = barService.findBarByName(name);
            return barsResp.isEmpty() ?
                    new ResponseEntity("No bars found", HttpStatus.NO_CONTENT) :
                    ResponseEntity.ok(barsResp);

    }

    @DeleteMapping("/bars/{barId}")
    public ResponseEntity<String> deleteById(@PathVariable("barId") Long id) {

            barService.deleteBarById(id);
            return new ResponseEntity<>("Bar was deleted, id : " + id, HttpStatus.OK);

    }

    @PutMapping("/bars/{barId}")
    public ResponseEntity<String> updateById(@PathVariable("barId") Long id,@RequestBody Bar updatedBar) {

            Bar newBar = barService.updateBarById(id, updatedBar);
            return new ResponseEntity<>("Bar was updated and saved, name : " + newBar.getName(), HttpStatus.OK);

    }

}
