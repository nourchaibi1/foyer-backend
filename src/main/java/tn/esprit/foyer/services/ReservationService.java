package tn.esprit.foyer.services;

import tn.esprit.foyer.entities.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation saveReservation (Reservation reservation);
    void deleteReservation (String idReservation);
    Reservation getReservationById (String idReservation);
    List<Reservation> getAllReservations();
    void reserverChambre(Long idChambre, Long cin);
    void libererChambre(Long idChambre, Long cin);
}
