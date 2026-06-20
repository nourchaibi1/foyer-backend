package tn.esprit.foyer.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.foyer.entities.Bloc;
import tn.esprit.foyer.entities.Chambre;
import tn.esprit.foyer.entities.TypeChambre;
import tn.esprit.foyer.repositories.BlocRepository;
import tn.esprit.foyer.repositories.ChambreRepository;
import tn.esprit.foyer.repositories.ReservationRepository;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
// @AllArgsConstructor ken mat7ebech dzid constructeur fil methode 2
public class ChambreServiceImpl implements ChamberService{

    //methode 1
    @Autowired //pour faire la liaison entre service et repo on peux aussi la faire avec le constructeur ou mutatteur (set)
    private ChambreRepository chambreRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private BlocRepository blocRepository;

    //methode 2 constructeur bil constructeur qualite du code 5ir
    /* private final ChambreRepository chambreRepository2;

    public ChambreServiceImpl(ChambreRepository chambreRepository2) {
        this.chambreRepository2 = chambreRepository2;
    }*/


   /* @Scheduled(initialDelay = 10,fixedRate = 1, timeUnit = TimeUnit.SECONDS)
    public void reportTask(){
        System.out.println("Rapport execute");
    }
    @Scheduled(
            fixedRate = 2,
            timeUnit = TimeUnit.SECONDS
    )
    public void refreshCache(){
        System.out.println("Rafraichissement du cache");
    }

    @Scheduled(fixedDelay = 10000)
    public void fixecDelayMethod(){
        System.out.println("Methode with fixed delay");
    }
    @Scheduled(fixedRate = 10000)
    public void fixedRateMethod(){
        System.out.println("Methode with fixed Rate");
    }*/

//done
    @Override
    public Chambre saveChambre(Chambre chambre) {

        return chambreRepository.save(chambre);
    }
//done
    @Override
    public void deleteChambre(Long idChambre) {
        chambreRepository.deleteById(idChambre);

    }

    @Override
    public Chambre getChambreById(Long idChambre) {

        return chambreRepository.findById(idChambre).get();
    }

    @Override
    public List<Chambre> getAllChambres()
    {
        return chambreRepository.findAll();
    }

    @Override
    public List<Chambre> getChambreByType(TypeChambre type) {

        return chambreRepository.findByTypeC(type);
    }

    @Override
    public List<Chambre> getChambreByBloc(Long idBloc) {

        return chambreRepository.findByBIdBloc(idBloc);
    }

    @Override
    public List<Chambre> getChambreByNumero(Long numero) {

        return chambreRepository.findByNumeroChambre(numero);
    }

    @Override
    public List<Chambre> getByNumeroAndType(Long numero, TypeChambre type) {
        return chambreRepository.findByNumeroChambreAndTypeC(numero, type);
    }

    @Override
    public List<Chambre>findBybnomBloc(String nomBloc) {
        return chambreRepository.findByBNomBloc(nomBloc);
    }

    @Override
    public List<Chambre> findAllByTypeC (TypeChambre typeChambre){
        return chambreRepository.findAllByTypeC(typeChambre);
    }
    @Override
    public Chambre findChambresByNumeroChambre(Long numeroChambre){
        return chambreRepository.findChambresByNumeroChambre(numeroChambre);
    }

    /*public void affecterChambreABloc(Long idChambre, Long idBloc) {
        Chambre chambre = chambreRepository.findById(idChambre).orElse(null);
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);

        chambre.setB(bloc);
        chambreRepository.save(chambre);
    }
    public void affecterChambresABloc(List<Long> idsChambre, Long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);

        idsChambre.forEach(id -> {
            Chambre c = chambreRepository.findById(id).orElse(null);
            c.setB(bloc);
            chambreRepository.save(c);
        });
    }
    public void desaffecterChambre(Long idChambre) {
        Chambre c = chambreRepository.findById(idChambre).orElse(null);
        c.setB(null);
        chambreRepository.save(c);
    }*/
    public Chambre addReservationAChambreAssocier (Chambre chambre){
        Chambre chambre1=chambreRepository.save(chambre);
        chambre1.getR().forEach(reservation  ->
        {
            reservation.setChambre(chambre1);
            reservationRepository.save(reservation);
        });
        return chambre1;
    }
    /*@Transactional
    @Scheduled(cron = "0 * * * * *")
    public void listeChambresParBloc() {

        List<Bloc> blocs = blocRepository.findAll();

        for (Bloc bloc : blocs) {

            log.info("Bloc => " + bloc.getNomBloc() +
                    " ayant une capacité " + bloc.getCapaciteBloc());

            if (bloc.getC() != null && !bloc.getC().isEmpty()) {

                log.info("La liste des chambres pour ce bloc :");

                for (Chambre chambre : bloc.getC()) {
                    log.info("NumChambre: " + chambre.getNumeroChambre()
                            + " type: " + chambre.getTypeC());
                }

            } else {
                log.info("Pas de chambre disponible dans ce bloc");
            }

            log.info("**********************");
        }
    }*/
    //@Transactional
    //@Scheduled(cron = "0 */5 * * * *")
    /*public void pourcentageChambreParTypeChambre() {

        List<Chambre> chambres = chambreRepository.findAll();

        int total = chambres.size();

        long simple = chambres.stream()
                .filter(c -> c.getTypeC().name().equals("SIMPLE"))
                .count();

        long doubleCh = chambres.stream()
                .filter(c -> c.getTypeC().name().equals("DOUBLE"))
                .count();

        long triple = chambres.stream()
                .filter(c -> c.getTypeC().name().equals("TRIPLE"))
                .count();

        log.info("Nombre total des chambres: " + total);

        if (total > 0) {
            double pSimple = (simple * 100.0) / total;
            double pDouble = (doubleCh * 100.0) / total;
            double pTriple = (triple * 100.0) / total;

            log.info("Le pourcentage des chambres pour le type SIMPLE est égale à " + pSimple);
            log.info("Le pourcentage des chambres pour le type DOUBLE est égale à " + pDouble);
            log.info("Le pourcentage des chambres pour le type TRIPLE est égale à " + pTriple);
        }
    }
   /* @Transactional
    @Scheduled(cron = "0 *5 * * * *")
    public void nbPlacesDisponibleParChambreAnneeEnCours() {
        List<Chambre> chambres = chambreRepository.findAll();
        int anneeActuelle = Calendar.getInstance().get(Calendar.YEAR);
        for (Chambre chambre : chambres) {
            int capacite = 0;
            switch (chambre.getTypeC()) {
                case SIMPLE: capacite = 1; break;
                case DOUBLE: capacite = 2; break;
                case TRIPLE: capacite = 3; break;
            }
            int nbEtudiants = 0;
            if (chambre.getR() != null) {
                nbEtudiants = chambre.getR().stream()
                        .filter(res -> {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(res.getAnneeUniversitaire());
                            return cal.get(Calendar.YEAR) == anneeActuelle;
                        })
                        .flatMap(res -> res.getEtudients().stream())

                        .toArray().length;
            }
            int placesDisponibles = capacite - nbEtudiants;

            if (placesDisponibles <= 0) {
                log.info("La chambre " + chambre.getTypeC() + " "
                        + chambre.getNumeroChambre() + " est complete");
            } else {
                log.info("Le nombre de place disponible pour la chambre "
                        + chambre.getTypeC() + " "
                        + chambre.getNumeroChambre() + " est "
                        + placesDisponibles);
            }
        }
    }*/
}
