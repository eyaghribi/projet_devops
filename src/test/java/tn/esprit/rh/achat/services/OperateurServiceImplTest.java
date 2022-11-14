package tn.esprit.rh.achat.services;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.rh.achat.entities.Operateur;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
 class OperateurServiceImplTest {
	
	
	@Autowired
    IOperateurService Ot;

    @Test
    @Order(1)
    void retrieveAllOperateurs() {
        List<Operateur> listOperateurs = Ot.retrieveAllOperateurs();
        //assertEquals(0, listOperateurs.size());
        assertNotNull(listOperateurs);
    }

    @Test
    @Order(2)
    void addingOperateur() {
        Operateur r1 = new Operateur();
        r1.setNom("Operateur9");
        r1.setPassword("secret9");
        r1.setPrenom("Telecm9");
        Operateur savedOperateur1= Ot.addOperateur(r1);
        assertEquals(r1.getNom(), savedOperateur1.getNom());
    }

    @Test
    @Order(3)
    void retrievingOperateur() {
        assertNotNull(Ot.retrieveOperateur(4L));
    }
 
    
    @Test
    @Order(4)
	void ModifyingOperateur()   {
		
		Operateur r = new Operateur( "test 4", "test -2", "test -99"); 
		r.setIdOperateur(4L);
		Operateur Operateur2  = Ot.updateOperateur(r); 
		Assertions.assertEquals(r.getNom(), Operateur2.getNom());
	}

	
//	@Test
//	@Order(5)
//	void DeletingOperateur() {
//		Ot.deleteOperateur(9L);
//		Assertions.assertNull(Ot.retrieveOperateur(9L));
//	} 
//   

}
