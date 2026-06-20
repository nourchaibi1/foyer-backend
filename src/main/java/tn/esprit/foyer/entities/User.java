package tn.esprit.foyer.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password; // Will hold the hashed password

    @Enumerated(EnumType.STRING)
    private Role role;

    // Optional: Link directly to the student profile if it's a student account
    @OneToOne
    @JoinColumn(name = "etudiant_cin", referencedColumnName = "cin")
    private Etudient etudiant;
}
