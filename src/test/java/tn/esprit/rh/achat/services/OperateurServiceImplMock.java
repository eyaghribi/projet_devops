package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class OperateurServiceImplMock {

	    @Mock
	    OperateurRepository OperateurRepository;
	    
	    @InjectMocks
	    OperateurServiceImpl MySevice;

	    Operateur Operateur = new Operateur("Op1", "surname1", "pwd1");
	    List<Operateur> listOperateurs = new ArrayList<Operateur>() {
	        {
	            add(new Operateur("Op2", "surname2", "pwd2"));
	            add(new Operateur("Op3", "surname3", "pwd3"));
	        }
	    };

	    @Test
	    void retrieveOperateurTestM() {
	        Mockito.when(OperateurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(Operateur));
	        Operateur Operateur1 = MySevice.retrieveOperateur(0L);
	        Assertions.assertNotNull(Operateur1);
	    }
	    @Test
	    void addOperateurTestM() {
	        Operateur Operateur = new Operateur("Op3", "surname4", "pwd9");
	        Mockito.when(OperateurRepository.save(ArgumentMatchers.any(Operateur.class))).thenReturn(Operateur);
	        Operateur created = MySevice.addOperateur(Operateur);
	        Assertions.assertSame(created,Operateur);
	        Mockito.verify(OperateurRepository).save(Operateur);
	    }
	    @Test
	    void updateOperateurTestM() {
	    
	       Operateur Operateur2 = new Operateur("Op9", "surname1", "pwd1");
	       Operateur2.setIdOperateur(25L);
	       Mockito.lenient().when(OperateurRepository.findById(Operateur2.getIdOperateur())).thenReturn(Optional.of(Operateur2));
      when(OperateurRepository.save(Operateur2)).thenReturn(Operateur2);
	        MySevice.updateOperateur(Operateur2);
        Mockito.verify(OperateurRepository).save(Operateur2);

    }
//	    @Test
//	    void updateOperateur() {
//	    	 Operateur mockOperateur = new Operateur("vito", "test456", "mumbai");
//	    	 
//	         when(OperateurRepository.findById(mockOperateur.getIdOperateur())).thenReturn(Optional.of(mockOperateur));
//
//	         mockOperateur.setPassword("test987");
//	         when(OperateurRepository.save(mockOperateur)).thenReturn(mockOperateur);
//	         Boolean value = mockOperateur != null ? true : false;
//
//	         Operateur mockOperateurDTO = modelMapper.map(mockOperateur, Operateur.class);
//
//	         assertEquals(value, MySevice.updateOperateur(mockOperateurDTO));
//	         verify(OperateurRepository, times(1)).save(mockOperateur);
//	    }
	    @Test
	     void deleteOperateurTestM() {

	        Operateur Operateur2Delete = new Operateur("TestDel1", "test678", "secret");
	        Mockito.lenient().when(OperateurRepository.findById(Operateur2Delete.getIdOperateur())).thenReturn(Optional.of(Operateur2Delete));

	        MySevice.deleteOperateur(Operateur2Delete.getIdOperateur());

	        verify(OperateurRepository).deleteById(Operateur2Delete.getIdOperateur());
	    }

	}

