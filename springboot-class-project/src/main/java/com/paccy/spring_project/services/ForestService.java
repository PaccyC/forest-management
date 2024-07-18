package com.paccy.spring_project.services;

import com.paccy.spring_project.entities.Forest;
import com.paccy.spring_project.repository.ForestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ForestService {

    private final ForestRepository forestRepository;

   public Forest createForest(Forest forest){
       return  forestRepository.save(forest);
   }

   public Optional<Forest> getForestById(Long id){
       return  forestRepository.findById(id);
   }
//   Getting all forests
   public List<Forest> getAllForests(){
       return forestRepository.findAll();
   }

//   Updating forest by its ID
    public  Forest updateForest(Long id,Forest updatedForest){
       return  forestRepository.findById(id)
               .map(forest -> {
                   forest.setName(updatedForest.getName());
                   forest.setDescription(updatedForest.getDescription());
                   forest.setArea(updatedForest.getArea());
                   return forestRepository.save(forest);
               })
               .orElseThrow(()->new IllegalArgumentException("Forest not found with id: " + id));
    }

    public void deleteForest(Long id){
       forestRepository.deleteById(id);
    }

}
