package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class FournisseurServiceImplTestMock {
    @Mock
    FournisseurRepository fournisseurRepository;

    @InjectMocks
    FournisseurServiceImpl fournisseurService;


    Fournisseur fournisseur = new Fournisseur(1L, "TN619","delice",CategorieFournisseur.CONVENTIONNE);
    List<Fournisseur> fournisseurList = new ArrayList<Fournisseur>(){
        {
            add(new Fournisseur(2L, "TN619","safia",CategorieFournisseur.ORDINAIRE));
            add(new Fournisseur(3L, "TN619","sabrine",CategorieFournisseur.CONVENTIONNE));
        }
    };
    @Test
    void retrieveAllFournisseurs() {
        Mockito.when(fournisseurRepository.findAll()).thenReturn(fournisseurList);
        List<Fournisseur> list = fournisseurService.retrieveAllFournisseurs();
        Assertions.assertNotNull(list);
    }

    @Test
    void addFournisseur() {
        Mockito.when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);
        Fournisseur actual = fournisseurService.addFournisseur(fournisseur);
        Assertions.assertEquals(actual.getIdFournisseur(),fournisseur.getIdFournisseur());
        Mockito.verify(fournisseurRepository).save(fournisseur);
    }

    @Test
    void updateFournisseur() {
    }

    @Test
    void deleteFournisseur() {
        Mockito.lenient().when(fournisseurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(fournisseur));
        fournisseurService.deleteFournisseur(fournisseur.getIdFournisseur());
        Mockito.verify(fournisseurRepository).deleteById(fournisseur.getIdFournisseur());


    }

    @Test
    void retrieveFournisseur() {
        Mockito.when(fournisseurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(fournisseur));
        Fournisseur fournisseur1 = fournisseurService.retrieveFournisseur(1L);
        Assertions.assertNotNull(fournisseur1);
    }


}