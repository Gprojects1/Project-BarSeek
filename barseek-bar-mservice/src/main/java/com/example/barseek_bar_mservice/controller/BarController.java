package com.example.barseek_bar_mservice.controller;


import com.example.barseek_bar_mservice.dto.UpdateBarRequest;
import com.example.barseek_bar_mservice.exception.customExceptions.UnauthorizedAccessException;
import com.example.barseek_bar_mservice.model.entity.Bar;
import com.example.barseek_bar_mservice.model.entity.Drink;
import com.example.barseek_bar_mservice.security.UserPrincipal;
import com.example.barseek_bar_mservice.service.BarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bar-service-api/v1/bars")
public class BarController {

    private final BarService barService;

    @PostMapping
    public ResponseEntity<String> addNew(@RequestBody Bar bar,
                                         @RequestHeader(value = "X-User-Id") String userId,
                                         @RequestHeader(value = "X-User-Roles") String roles
    ) {

            if(!(roles.contains("bar"))) throw new UnauthorizedAccessException("User is not a bar owner!");

            Long ownerId = Long.parseLong(userId);
            Bar newBar = barService.addNewBar(bar,ownerId);
            return new ResponseEntity<>("New bar created with name : " + newBar.getName(),HttpStatus.CREATED);
    }

    @GetMapping("/{barId}")
    public ResponseEntity<Bar> findById(@PathVariable("barId") Long id) {

            Bar barResp = barService.findBarById(id);
            return ResponseEntity.ok(barResp);
    }

    @GetMapping("search/{barName}")
    public ResponseEntity<List<Bar>> findByName(@PathVariable("barName") String name) {

            List<Bar> barsResp = barService.findBarByName(name);
            return barsResp.isEmpty() ?
                    new ResponseEntity("No bars found", HttpStatus.NO_CONTENT) :
                    ResponseEntity.ok(barsResp);
    }

    @DeleteMapping("/{barId}")
    public ResponseEntity<String> deleteById(@PathVariable("barId") Long id,
                                             @RequestHeader(value = "X-User-Id") String userId,
                                             @RequestHeader(value = "X-User-Roles") String roles

    ) {
            if(!(roles.contains("bar"))) throw new UnauthorizedAccessException("User is not a bar owner!");

            Long ownerId = Long.parseLong(userId);
            barService.deleteBarById(id,ownerId);
            return ResponseEntity.ok(("Bar was deleted, id : " + id));
    }

    @PutMapping("/{barId}")
    public ResponseEntity<String> updateById(@PathVariable("barId") Long id,
                                             @RequestBody UpdateBarRequest updateBarRequest,
                                             @RequestHeader(value = "X-User-Id") String userId,
                                             @RequestHeader(value = "X-User-Roles") String roles

    ) {

            if(!(roles.contains("bar"))) throw new UnauthorizedAccessException("User is not a bar owner!");

            Long ownerId = Long.parseLong(userId);
            Bar newBar = barService.updateBarById(id, updateBarRequest, ownerId);
            return new ResponseEntity<>("Bar was updated and saved, name : " + newBar.getName(), HttpStatus.OK);
    }

    @GetMapping("/{barId}/all")
    public ResponseEntity<List<Drink>> findAllDrinksById(@PathVariable("barId") Long id) {

            List<Drink> drinks = barService.findAllDrinksById(id);
            return drinks.isEmpty() ?
                    new ResponseEntity("No drinks in chosen bar!", HttpStatus.NO_CONTENT) :
                    ResponseEntity.ok(drinks);
    }

}
