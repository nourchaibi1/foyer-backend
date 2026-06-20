package tn.esprit.foyer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Etudient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtudient;
    private String nomEt;
    private String prenom;
    private Long cin;
    private String ecole;
    private Date dateNaissance;

    @ManyToMany(mappedBy = "Etudients")
    private List<Reservation> Reservations;

}
