package tn.esprit.foyer.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.foyer.entities.Foyer;
import tn.esprit.foyer.entities.Universite;
import tn.esprit.foyer.repositories.FoyerRepository;
import tn.esprit.foyer.repositories.UniversiteRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UniversiteServiceImpl implements UniversiteService{
    @Autowired
    private UniversiteRepository universiteRepository;
    private FoyerRepository foyerRepository;

    @Override
    public Universite saveUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }

    @Override
    public void deleteUniversite(Long idUniversite) {
        universiteRepository.deleteById(idUniversite);
    }

    @Override
    public Universite getUniversiteById(Long idUniversite) {
        return universiteRepository.findById(idUniversite).get();
    }

    @Override
    public List<Universite> getAllUniversites() {
        return universiteRepository.findAll();
    }
    public void affecterFoyerAUniversite(Long idFoyer, Long idUniversite) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);
        Universite u = universiteRepository.findById(idUniversite).orElse(null);

        u.setF(foyer);
        universiteRepository.save(u);
    }
    public void affecterUniversiteAFoyer(Long idUniversite, Long idFoyer) {
        affecterFoyerAUniversite(idFoyer, idUniversite);
    }
    public Universite affecterFoyerToUniversite(Foyer foyer, Long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite) .get();
        Foyer foyer1 = foyerRepository.save(foyer);
        universite.setF(foyer1);
        universiteRepository. save(universite);
        return universite;

    }
    public void desaffecterFoyerDeUniversite(Long idUniversite) {

        Universite u = universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new RuntimeException("Universite introuvable"));

        u.setF(null);

        universiteRepository.save(u); // ✅ sauvegarde
    }
}
