package tn.esprit.foyer.services;


import tn.esprit.foyer.entities.Bloc;

import java.util.List;

public interface BlocService {
    Bloc saveBloc (Bloc bloc);
    void deleteBloc (Long idBloc);
    Bloc getBlocById (Long idBloc);
    List<Bloc> getAllBloc();

}
