package tn.esprit.foyer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.foyer.entities.Chambre;
import tn.esprit.foyer.entities.Etudient;
import tn.esprit.foyer.entities.Reservation;
import tn.esprit.foyer.repositories.ChambreRepository;
import tn.esprit.foyer.repositories.EtudientRepository;
import tn.esprit.foyer.repositories.ReservationRepository;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ChambreRepository chambreRepository;
    @Autowired
    private EtudientRepository etudientRepository;

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(String idReservation) {
        reservationRepository.deleteById(idReservation);
    }

    @Override
    public Reservation getReservationById(String idReservation) {
        return reservationRepository.findById(idReservation).get();
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public void affecterReservationAChambre(String idReservation, Long idChambre) {
        Reservation r = reservationRepository.findById(idReservation).orElseThrow(() -> new RuntimeException("Reservation introuvable"));
        Chambre c = chambreRepository.findById(idChambre).orElseThrow(() -> new RuntimeException("Chambre introuvable"));
        r.setChambre(c);
        reservationRepository.save(r);
    }

    public void affecterEtudiantAReservation(Long idEtudiant, String idReservation) {
        Etudient e = etudientRepository.findById(idEtudiant).orElseThrow(() -> new RuntimeException("Etudiant introuvable"));
        Reservation r = reservationRepository.findById(idReservation).orElseThrow(() -> new RuntimeException("Reservation introuvable"));
        r.getEtudients().add(e);
        reservationRepository.save(r);
    }

    public void affecterEtudiantAChambre(Long idEtudiant, Long idChambre, String idReservation) {
        Etudient e = etudientRepository.findById(idEtudiant).orElseThrow(() -> new RuntimeException("Etudiant introuvable"));
        Chambre c = chambreRepository.findById(idChambre).orElseThrow(() -> new RuntimeException("Chambre introuvable"));
        Reservation r = reservationRepository.findById(idReservation).orElseThrow(() -> new RuntimeException("Reservation introuvable"));
        r.setChambre(c);
        r.getEtudients().add(e);
        reservationRepository.save(r);
    }

    public void affecterEtudiantsSelonType(Long idChambre, List<Long> idsEtudiants) {
        Chambre c = chambreRepository.findById(idChambre).orElseThrow(() -> new RuntimeException("Chambre introuvable"));
        int max = switch (c.getTypeC()) {
            case SIMPLE -> 1;
            case DOUBLE -> 2;
            case TRIPLE -> 3;
        };
        if (idsEtudiants.size() > max) {
            throw new RuntimeException("Capacité dépassée !");
        }
        Reservation r = new Reservation();
        r.setIdReservation(UUID.randomUUID().toString());
        r.setAnneeUniversitaire(new Date());
        r.setEstValide(true);
        r.setChambre(c);
        r.setEtudients(etudientRepository.findAllById(idsEtudiants));
        reservationRepository.save(r);
    }

    public void supprimerReservation(String idReservation) {
        reservationRepository.deleteById(idReservation);
    }

    public void desaffecterEtudiant(Long idEtudiant, String idReservation) {
        Reservation r = reservationRepository.findById(idReservation).orElseThrow(() -> new RuntimeException("Reservation introuvable"));
        r.getEtudients().removeIf(e -> e.getIdEtudient().equals(idEtudiant));
        reservationRepository.save(r);
    }

    // Nouveaux endpoints pour le frontend
    public void reserverChambre(Long idChambre, Long cin) {
        Chambre c = chambreRepository.findById(idChambre).orElseThrow(() -> new RuntimeException("Chambre introuvable"));
        Etudient e = etudientRepository.findByCin(cin).orElseThrow(() -> new RuntimeException("Etudiant introuvable"));

        boolean dejaReserve = e.getReservations().stream().anyMatch(Reservation::isEstValide);
        if (dejaReserve) throw new RuntimeException("Etudiant a déjà une chambre réservée");

        Reservation r = new Reservation();
        r.setIdReservation(UUID.randomUUID().toString());
        r.setAnneeUniversitaire(new Date());
        r.setEstValide(true);
        r.setChambre(c);
        r.setEtudients(List.of(e));
        reservationRepository.save(r);
    }

    public void libererChambre(Long idChambre, Long cin) {
        Etudient e = etudientRepository.findByCin(cin).orElseThrow(() -> new RuntimeException("Etudiant introuvable"));
        Reservation r = e.getReservations().stream()
                .filter(res -> res.isEstValide() && res.getChambre().getIdChambre().equals(idChambre))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Aucune réservation active"));
        r.setEstValide(false);
        reservationRepository.save(r);
    }
}