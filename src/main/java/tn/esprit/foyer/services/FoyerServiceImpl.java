package tn.esprit.foyer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.foyer.entities.Foyer;
import tn.esprit.foyer.entities.Universite;
import tn.esprit.foyer.repositories.FoyerRepository;
import tn.esprit.foyer.repositories.UniversiteRepository;

import java.util.List;

@Service

public class FoyerServiceImpl implements FoyerService {
    @Autowired
    private FoyerRepository foyerRepository;

    private UniversiteRepository universiteRepository;
    @Override
    public Foyer saveFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    @Override
    public void deleteFoyer(Long idFoyer) {
        foyerRepository.deleteById(idFoyer);
    }
    @Override
    public Foyer getFoyerById(Long idFoyer) {
        return foyerRepository.findById(idFoyer).get();
    }

    @Override
    public List<Foyer> getAllFoyers() {
        return foyerRepository.findAll();
    }

    public Universite affecterFoyerToUniversite(Foyer foyer, Long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite) .get();
        Foyer foyer1 = foyerRepository.save(foyer);
        universite.setF(foyer1);
        universiteRepository. save(universite);
        return universite;

    }

}
