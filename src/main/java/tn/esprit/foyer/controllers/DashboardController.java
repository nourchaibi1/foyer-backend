package tn.esprit.foyer.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.foyer.repositories.ChambreRepository;
import tn.esprit.foyer.repositories.EtudientRepository;
import tn.esprit.foyer.repositories.FoyerRepository;

import java.util.Map;

@RestController
@RequestMapping("/tpfoyer")
@AllArgsConstructor
public class DashboardController {

    private final FoyerRepository foyerRepo;
    private final EtudientRepository etudiantRepo;
    private final ChambreRepository chambreRepo;

    @GetMapping("/stats")
    public Map<String, Long> getStats() {
        return Map.of(
                "nbFoyers", foyerRepo.count(),
                "nbEtudiants", etudiantRepo.count(),
                "nbChambres", chambreRepo.count(),
                "chambresLibres", chambreRepo.countChambresLibres(),
                "chambresOccupees", chambreRepo.countChambresOccupees()
        );
    }
}
