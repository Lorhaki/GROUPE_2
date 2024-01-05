package com.example.editeurservice.service;

import com.example.editeurservice.api.request.EditeurCreationRequest;
import com.example.editeurservice.api.request.EditeurUpdateRequest;
import com.example.editeurservice.entity.Editeur;
import com.example.editeurservice.repository.EditeurRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EditeurService {
    private final EditeurRepository editeurRepository;

    public Editeur getByNom(String editeurNom){
        return editeurRepository.findByNom(editeurNom);
    }
    public Editeur getById(String editeurId){
        return editeurRepository.findById(new ObjectId(editeurId))
                .orElse(null);
    }

    public List<Editeur> getAll(){
        return editeurRepository.findAll();
    }
    public Editeur create(EditeurCreationRequest request){
        List<Editeur> temp  = editeurRepository.findAll();
        Boolean b = false ;
        for(int i = 0 ; i < temp.size() ; i++){
            if (temp.get(i).getNom().equals(request.getNom())){
             b = true ;
            }
        }
        if (b == false) {
            final Editeur editeur = Editeur.builder()
                    .nom(request.getNom())
                    .description(request.getDescription())
                    .Ljv(new ArrayList<>())
                    .build();

            return editeurRepository.insert(editeur);
        } return null ;
    }

    public Editeur update(String editeurId, EditeurUpdateRequest request){
        return editeurRepository.findById(new ObjectId(editeurId))
                .map(editeur -> {
                    editeur.setNom(request.getNom());
                    editeur.setDescription(request.getDescription());

                    return editeurRepository.save(editeur);
                }).orElse(null);
    }
    public void delete(String editeurId) {
        editeurRepository.deleteById(new ObjectId(editeurId));
    }

}
