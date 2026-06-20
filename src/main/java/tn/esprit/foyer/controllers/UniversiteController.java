package tn.esprit.foyer.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.foyer.entities.Foyer;
import tn.esprit.foyer.entities.Universite;
import tn.esprit.foyer.services.EtudientService;
import tn.esprit.foyer.services.UniversiteServiceImpl;

@Tag(name = "Gestion Universite" , description ="CRUD Universite")
@RestController
@RequestMapping("/Uni")
@CrossOrigin(origins = "http://localhost:3000")

public class UniversiteController {
    @Autowired
    private UniversiteServiceImpl universiteService;
    @PutMapping("/affecter-foyer/{idFoyer}/{idUniversite}")
    @Operation(summary="affecter Foyer A Universite")
    public void affecterFoyerAUniversite(@PathVariable Long idFoyer,@PathVariable Long idUniversite) {
            universiteService.affecterFoyerAUniversite(idFoyer, idUniversite);
    }

    @PutMapping("/affecter-universite/{idUniversite}/{idFoyer}")
    @Operation(summary="affecter Universite A Foyer")
    public void affecterUniversiteAFoyer(@PathVariable Long idUniversite, @PathVariable Long idFoyer) {
        universiteService.affecterUniversiteAFoyer(idUniversite, idFoyer);
    }
    @PutMapping("/affecterFoyerToUniversite/{idUniversite}")
    @ResponseBody
    public Universite affecterFoyerToUniversite(@RequestBody Foyer foyer, @PathVariable("idUniversite") Long idUniversite) {
        Universite universite = universiteService.affecterFoyerToUniversite(foyer, idUniversite);
        return universite;

    }
    @PutMapping("/desaffecterFoyer/{idUniversite}")
    public void desaffecterFoyer(@PathVariable Long idUniversite) {
        universiteService.desaffecterFoyerDeUniversite(idUniversite);
    }
}
