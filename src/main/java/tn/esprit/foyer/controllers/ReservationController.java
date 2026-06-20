package tn.esprit.foyer.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.foyer.services.BlocServiceImpl;
import tn.esprit.foyer.services.ReservationServiceImpl;
import tn.esprit.foyer.entities.Chambre;
import tn.esprit.foyer.repositories.EtudientRepository;
import tn.esprit.foyer.repositories.ReservationRepository;
import org.springframework.http.ResponseEntity;
import java.util.Map;

import java.util.List;

@Tag(name = "Gestion Reservation" , description ="CRUD Reservation")
@RestController
@RequestMapping("/Reservations")
@CrossOrigin(origins = "http://localhost:3000")

public class ReservationController {
    @Autowired
    private ReservationServiceImpl reservationService;
    @Autowired
    private EtudientRepository etudientRepo;
    @Autowired
    private ReservationRepository reservationRepo;

    @PutMapping("/affecter-reservation-chambre/{idReservation}/{idChambre}")
    @Operation(summary="affecter Reservation A Chambre")
    public void affecterReservationAChambre(
            @PathVariable String idReservation,
            @PathVariable Long idChambre) {
        reservationService.affecterReservationAChambre(idReservation, idChambre);
    }

    @PutMapping("/affecter-etudiant-reservation/{idEtudiant}/{idReservation}")
    @Operation(summary="affecter Etudiant A Reservation")
    public void affecterEtudiantAReservation(
            @PathVariable Long idEtudiant,
            @PathVariable String idReservation) {
        reservationService.affecterEtudiantAReservation(idEtudiant, idReservation);
    }

    @PutMapping("/affecter-etudiant-chambre")
    @Operation(summary="affecter Etudiant A Chambre")
    public void affecterEtudiantAChambre(
            @RequestParam Long idEtudiant,
            @RequestParam Long idChambre,
            @RequestParam String idReservation) {
        reservationService.affecterEtudiantAChambre(idEtudiant, idChambre, idReservation);
    }

    @PostMapping("/affecter-plusieurs-etudiants/{idChambre}")
    @Operation(summary="affecter Etudiants Selon Type")
    public void affecterEtudiantsSelonType(
            @PathVariable Long idChambre,
            @RequestBody List<Long> idsEtudiants) {
        reservationService.affecterEtudiantsSelonType(idChambre, idsEtudiants);
    }

    @DeleteMapping("/supprimer/{idReservation}")
    @Operation(summary="supprimer Reservation")
    public void supprimerReservation(@PathVariable String idReservation) {
        reservationService.supprimerReservation(idReservation);
    }

    @GetMapping("/ma-chambre")
    @Operation(summary = "Chambre de l'étudiant connecté")
    public ResponseEntity<?> getMaChambre(@RequestParam Long cin) {
        return etudientRepo.findByCin(cin)
                .flatMap(e -> e.getReservations().stream()
                        .filter(r -> r.isEstValide())
                        .map(r -> r.getChambre())
                        .findFirst())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/chambre/{id}/reserver")
    @Operation(summary = "Réserver une chambre")
    public ResponseEntity<?> reserver(@PathVariable Long id, @RequestParam Long cin) {
        reservationService.reserverChambre(id, cin);
        return ResponseEntity.ok(Map.of("message", "Réservation effectuée"));
    }

    @PutMapping("/chambre/{id}/liberer")
    @Operation(summary = "Libérer une chambre")
    public ResponseEntity<?> liberer(@PathVariable Long id, @RequestParam Long cin) {
        reservationService.libererChambre(id, cin);
        return ResponseEntity.ok(Map.of("message", "Chambre libérée"));
    }
}
