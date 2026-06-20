package tn.esprit.foyer.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.foyer.entities.Bloc;
import tn.esprit.foyer.entities.Chambre;
import tn.esprit.foyer.services.BlocService;
import tn.esprit.foyer.services.BlocServiceImpl;
import tn.esprit.foyer.services.ChamberService;

import java.util.List;
@Tag(name = "Gestion bloc" , description ="CRUD bloc")
@RestController
@RequestMapping("/blocs")
@CrossOrigin(origins = "http://localhost:3000")
public class BlocController {
    @Autowired
    private BlocServiceImpl blocService; // inajmou na3mlou il interface fi 3oud il service

    @PostMapping("/ajouterbloc")
    @Operation(summary="Ajouter un bloc")
    public Bloc createBloc (@RequestBody Bloc bloc)
    {
        return blocService.saveBloc(bloc);
    }

    @DeleteMapping("/deletebloc/{id}")
    @Operation(summary="Supprimer un bloc")
    public void deleteBloc (@PathVariable Long id)
    {
        blocService.deleteBloc(id);
    }

    @GetMapping("/getById/{id}")
    @Operation(summary="afficher un bloc par id")
    public Bloc getBlocById (@PathVariable Long id)
    {
        return blocService.getBlocById(id);
    }

    @GetMapping("/getAll")
    @Operation(summary="afficher tout les blocs")
    public List<Bloc> getAllBloc ()
    {
        return blocService.getAllBloc();
    }

    @PutMapping("/affecter-bloc-foyer/{idBloc}/{idFoyer}")
    public void affecterBlocAFoyer(
            @PathVariable Long idBloc,
            @PathVariable Long idFoyer) {
        blocService.affecterBlocAFoyer(idBloc, idFoyer);
    }

    @PutMapping("/affecter-blocs-foyer/{idFoyer}")
    public void affecterBlocsAFoyer(
            @PathVariable Long idFoyer,
            @RequestBody List<Integer> idsBloc) {

        blocService.affecterBlocsAFoyer(
                idsBloc.stream().map(Long::valueOf).toList(),
                idFoyer
        );
    }

    @PutMapping("/desaffecter/{idBloc}")
    public void desaffecterBloc(@PathVariable Long idBloc) {
        blocService.desaffecterBloc(idBloc);
    }
}
