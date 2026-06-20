package tn.esprit.foyer.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.foyer.entities.Etudient;
import tn.esprit.foyer.services.EtudientService;
import tn.esprit.foyer.services.ReservationServiceImpl;

import java.util.List;
@Tag(name = "Gestion Etudient" , description ="CRUD etudient")
@RestController
@RequestMapping("/etudients")
@CrossOrigin(origins = "http://localhost:3000")
//they say
public class EtudientController {

    @Autowired
    private EtudientService etudientService; // inajmou na3mlou il interface fi 3oud il service
    private ReservationServiceImpl reservationService;

    @PostMapping("/ajouteretudient")
    @Operation(summary="Ajouter un etudient")
    public Etudient createEtudient (@RequestBody Etudient etudient)
    {
        return etudientService.saveEtudient(etudient);
    }

    @DeleteMapping("/deleteetudient/{id}")
    @Operation(summary="Supprimer un etudient")
    public void deleteEtudient (@PathVariable Long id)
    {
        etudientService.deleteEtudient(id);
    }

    @GetMapping("/getById/{id}")
    @Operation(summary="Afficher un etudient par id")
    public Etudient getEtudientById (@PathVariable Long id)
    {
        return etudientService.getEtudientById(id);
    }

    @GetMapping("/getAll")
    @Operation(summary="Afficher tout les etudients")
    public List<Etudient> getAllEtudient ()
    {
        return etudientService.getAllEtudients();
    }


    @PutMapping("/desaffecter/{idEtudiant}/{idReservation}")
    @Operation(summary="desaffecter Etudiant")
    public void desaffecterEtudiant(
            @PathVariable Long idEtudiant,
            @PathVariable String idReservation) {
            reservationService.desaffecterEtudiant(idEtudiant, idReservation);
    }
}
