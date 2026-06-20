package tn.esprit.foyer.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.foyer.entities.Chambre;
import tn.esprit.foyer.entities.TypeChambre;
import tn.esprit.foyer.services.ChamberService;
import tn.esprit.foyer.services.ChambreServiceImpl;

import java.util.List;
@Tag(name = "Gestion Chambre" , description ="CRUD chambre")
@RestController
@RequestMapping("/chambres")
@CrossOrigin(origins = "http://localhost:3000")
//they say
public class ChambreController {
    @Autowired
    private ChambreServiceImpl chamberService; // inajmou na3mlou il interface fi 3oud il service
    @PostMapping("/ajouterchambre")
    @Operation(summary="Ajouter une chambre")
    public Chambre createChambre (@RequestBody Chambre chambre)
    {
        return chamberService.saveChambre(chambre);
    }

    @DeleteMapping("/deletechambre/{id}")
    @Operation(summary="Supprimer une chambre")
    public void deleteChambre (@PathVariable Long id)
    {
        chamberService.deleteChambre(id);
    }
    @GetMapping("/getById/{id}")

    @Operation(summary="Afficher une chambre par id")
    public Chambre getChambreById (@PathVariable Long id)
    {
        return chamberService.getChambreById(id);
    }

    @GetMapping("/getAll")
    @Operation(summary="Afficher tout les chambres")
    public List<Chambre> getAllChambre ()
    {
        return chamberService.getAllChambres();
    }

    @GetMapping("/ByType/{type}")
    @Operation(summary="afficher chambre par type chambre")
    public List<Chambre> getByType(@PathVariable TypeChambre type){
        return chamberService.getChambreByType(type);
    }
    @GetMapping("/ByIdBloc/{idBloc}")
    @Operation(summary="afficher chambre par id de bloc")
    public List<Chambre> getByBloc(@PathVariable Long idBloc){
        return chamberService.getChambreByBloc(idBloc);
    }

    @GetMapping("/ByNumero/{numero}")
    @Operation(summary="afficher chambre par numero chambre")
    public List<Chambre> getByNumero(@PathVariable Long numero){
        return chamberService.getChambreByNumero(numero);
    }

    @GetMapping("/ByNumero/{numero}/ByType/{type}")
    @Operation(summary="afficher tout les chambres par type chambre et numero chambre")
    public List<Chambre> getByNumeroAndType(@PathVariable Long numero,
                                            @PathVariable TypeChambre type){
        return chamberService.getByNumeroAndType(numero, type);
    }
    @GetMapping("/ByBloc/Nom/{nom}")
    @Operation(summary="afficher chambre par nom bloc")
    public List<Chambre> getByNomBloc(@PathVariable String nom){
        return chamberService.findBybnomBloc(nom);
    }

    @GetMapping("/AllByType/{type}")
    @Operation(summary="afficher tout les chambres par type chambre")
    public List<Chambre> getChambresByType(@PathVariable TypeChambre type)
    {
        return chamberService.findAllByTypeC(type);
    }

    @GetMapping("/AllByNumero/{numero}")
    @Operation(summary="afficher tout les chambres par numero chambre")
    public Chambre getChambreByNumero(@PathVariable Long numero)
    {
        return chamberService.findChambresByNumeroChambre(numero);
    }
   /* @PutMapping("/affecter-chambre-bloc/{idChambre}/{idBloc}")
    public void affecterChambreABloc(@PathVariable Long idChambre,@PathVariable Long idBloc) {
        chamberService.affecterChambreABloc(idChambre, idBloc);
    }

    @PutMapping("/affecter-chambres-bloc/{idBloc}")
    public void affecterChambresABloc(
            @PathVariable Long idBloc,
            @RequestBody List<Long> idsChambre) {
        chamberService.affecterChambresABloc(idsChambre, idBloc);
    }

    @PutMapping("/desaffecter/{idChambre}")
    public void desaffecterChambre(@PathVariable Long idChambre) {
        chamberService.desaffecterChambre(idChambre);
    }
*/
    @PostMapping("/add-chambre-avec-reservations")
    public Chambre addReservationAChambreAssocier(@RequestBody Chambre chambre) {
        return chamberService.addReservationAChambreAssocier(chambre);
    }
}

