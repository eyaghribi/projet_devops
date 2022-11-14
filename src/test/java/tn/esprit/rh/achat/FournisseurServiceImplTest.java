package tn.esprit.rh.achat.services;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.rh.achat.entities.Fournisseur;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
 class FournisseurServiceImplTest {
	
	
	@Autowired
    IFournisseurService Ot;

    @Test
    @Order(1)
    void retrieveAllFournisseurs() {
        List<Fournisseur> listFournisseurs = Ot.retrieveAllFournisseurs();
        //assertEquals(0, listFournisseurs.size());
        assertNotNull(listFournisseurs);
    }

    @Test
    @Order(2)
    void addingFournisseur() {
        Fournisseur r1 = new Fournisseur();
        r1.setNom("Fournisseur9");
        r1.setPassword("secret9");
        r1.setPrenom("Telecm9");
        Fournisseur savedFournisseur1= Ot.addFournisseur(r1);
        assertEquals(r1.getNom(), savedFournisseur1.getNom());
    }

    @Test
    @Order(3)
    void retrievingFournisseur() {
        assertNotNull(Ot.retrieveFournisseur(4L));
    }
 
    
    @Test
    @Order(4)
	void ModifyingFournisseur()   {
		
		Fournisseur r = new Fournisseur( "test 4", "test -2", "test -99"); 
		r.setIdFournisseur(4L);
		Fournisseur Fournisseur2  = Ot.updateFournisseur(r); 
		Assertions.assertEquals(r.getNom(), Fournisseur2.getNom());
	}

	
//	@Test
//	@Order(5)
//	void DeletingFournisseur() {
//		Ot.deleteFournisseur(9L);
//		Assertions.assertNull(Ot.retrieveFournisseur(9L));
//	} 
//   

}