package com.paccy.spring_project.controllers;

import com.paccy.spring_project.entities.Forest;
import com.paccy.spring_project.repository.ForestRepository;
import com.paccy.spring_project.services.ForestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/forests")
@RequiredArgsConstructor
public class ForestController {

    private final ForestService forestService;
    private final ForestRepository forestRepository;

    @PostMapping
   public Forest createForest(@RequestBody Forest forest){
       return forestService.createForest(forest);

   }
   @GetMapping()
   public List<Forest> getAllForests(){
       System.out.println("getAllForests API endpoint called");
       return forestService.getAllForests();
   }

   @GetMapping("/{id}")
   public ResponseEntity<Forest> getForestById(@PathVariable Long id){
        return forestService.getForestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
   }


//   Updating forest data

    @PutMapping("/{id}")
   public ResponseEntity<Forest> updateForest(
           @PathVariable Long id,
            @RequestBody Forest updatedForest){

        try {
            return  ResponseEntity.ok(forestService.updateForest(id, updatedForest));
        }
        catch (IllegalArgumentException e){
            return  ResponseEntity.notFound().build();
        }
   }


   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteForest(@PathVariable Long id){
        forestService.deleteForest(id);
        return  ResponseEntity.notFound().build();
   }

//   using native queries

    @GetMapping("/find")
    public ResponseEntity<Forest> findByNameAndLocation(@RequestParam String name, @RequestParam String location) {
        return forestRepository.findByNameAndLocation(name, location)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteByName(@RequestParam String name) {
        forestRepository.deleteByName(name);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateByName(
            @RequestParam String name,
            @RequestParam String newName,
            @RequestParam String description,
            @RequestParam String location,
            @RequestParam int area) {
        forestRepository.updateByName(name, newName, description, location, area);
        return ResponseEntity.ok().build();
    }

}
