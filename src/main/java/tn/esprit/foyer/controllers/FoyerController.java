package tn.esprit.foyer.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.foyer.entities.Foyer;
import tn.esprit.foyer.entities.Universite;
import tn.esprit.foyer.services.FoyerService;
import tn.esprit.foyer.services.UniversiteService;
import tn.esprit.foyer.services.UniversiteServiceImpl;

import java.util.List;
@Tag(name = "Gestion Foyer" , description ="CRUD foyer")
@RestController
@RequestMapping("/foyers")
@CrossOrigin(origins = "http://localhost:3000")

public class FoyerController {
    @Autowired
    private FoyerService foyerService; // inajmou na3mlou il interface fi 3oud il service
    private UniversiteServiceImpl universiteService; // inajmou na3mlou il interface fi 3oud il service

    @PostMapping("/ajouterfoyer")
    @Operation(summary="Ajouter un foyer")
    public Foyer createFoyer (@RequestBody Foyer foyer)
    {
        return foyerService.saveFoyer(foyer);
    }
    @DeleteMapping("/deletefoyer/{id}")
    @Operation(summary="Supprimer un foyer")
    public void deleteFoyer (@PathVariable Long id)
    {
        foyerService.deleteFoyer(id);
    }
    @GetMapping("/getById/{id}")
    @Operation(summary="Afficher un foyer par id")
    public Foyer getFoyerById (@PathVariable Long id)
    {
        return foyerService.getFoyerById(id);
    }

    @GetMapping("/getAll")
    @Operation(summary="Afficher tout les foyers")
    public List<Foyer> getAllFoyer ()
    {
        return foyerService.getAllFoyers();
    }


}
