package tn.esprit.foyer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.foyer.entities.Bloc;
import tn.esprit.foyer.entities.Chambre;
import tn.esprit.foyer.entities.Foyer;
import tn.esprit.foyer.repositories.BlocRepository;
import tn.esprit.foyer.repositories.ChambreRepository;
import tn.esprit.foyer.repositories.FoyerRepository;

import java.util.List;

@Service

public class BlocServiceImpl implements BlocService{
    @Autowired
    private BlocRepository blocRepository;
    private FoyerRepository foyerRepository;
    private ChambreRepository chambreRepository;


    @Override
    public Bloc saveBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public void deleteBloc(Long idBloc) {
        blocRepository.deleteById(idBloc);

    }

    @Override
    public Bloc getBlocById(Long idBloc) {
        return blocRepository.findById(idBloc).get();
    }

    @Override
    public List<Bloc> getAllBloc() {
        return blocRepository.findAll();
    }
    public void affecterBlocAFoyer(Long idBloc, Long idFoyer) {
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);
        bloc.setFoyer(foyer);
        blocRepository.save(bloc);
    }
    public void affecterBlocsAFoyer(List<Long> idsBloc, Long idFoyer) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);

        idsBloc.forEach(id -> {
            Bloc bloc = blocRepository.findById(id).orElse(null);
            bloc.setFoyer(foyer);
            blocRepository.save(bloc);
        });
    }
    public void desaffecterBloc(Long idBloc) {
        Bloc b = blocRepository.findById(idBloc).orElse(null);
        b.setFoyer(null);
        blocRepository.save(b);
    }
    public Bloc addblocAChambreAssocier (Bloc bloc){
        Bloc bloc1=blocRepository.save(bloc);
        bloc1.getC().forEach(chambre ->
        {
            chambre.setB(bloc1);
            chambreRepository.save(chambre);
        });
        return bloc1;
    }

}
