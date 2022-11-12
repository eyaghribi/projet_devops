package com.esprit.examen.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.entities.dto.ProduitDTO;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.StockRepository;


@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
	@Mock
	private ProduitRepository produitRepository;
	@Mock
	private StockRepository stockrepository;

	@InjectMocks
	private ProduitServiceImpl produitService;
	@InjectMocks
	private StockServiceImpl stockServiceImpl;


	private Produit p1;
	private Produit p2;
	private Stock s1;
	ModelMapper modelMapper;

	@BeforeEach
	public void init() {
		this.p1 = new Produit();
		this.p1.setIdProduit(1L);
		this.p1.setPrix(100);
		this.p1.setLibelleProduit("test");
		this.p2 = new Produit();
		this.p2.setIdProduit(2L);
		this.p2.setPrix(100);
		this.p2.setStock(s1);
		this.p2.setLibelleProduit("test2");
		this.s1 = new Stock();
		this.s1.setIdStock(1L);
		this.s1.setQte(100);
		this.s1.setLibelleStock("Stock 1");
		this.modelMapper = new ModelMapper();
	}

	@Test
	public void testAddProduit() {
		init();
		when(produitRepository.save(any(Produit.class))).thenReturn(p1);
		ProduitDTO prm=modelMapper.map(p1, ProduitDTO.class);
		Produit pnew=produitService.addProduit(prm);
		assertNotNull(pnew);
		assertThat(pnew.getIdProduit()).isEqualTo(1L);
	}

	
	@Test
	public void getProduits() {
		init();
		List<Produit> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		when(produitRepository.findAll()).thenReturn(list);
		List<Produit> Produits = produitService.retrieveAllProduits();
		assertEquals(2, Produits.size());
		assertNotNull(Produits);
	}
	
	@Test
	public void getProduitById() {
		
		init();
		when(produitRepository.save(any(Produit.class))).thenReturn(p1);
		ProduitDTO prm=modelMapper.map(p1, ProduitDTO.class);
		Produit pnew=produitService.addProduit(prm);
		when(produitRepository.findById(anyLong())).thenReturn(Optional.of(p1));
		Produit existingProduit = produitService.retrieveProduit(pnew.getIdProduit());
		assertNotNull(existingProduit);
		assertThat(existingProduit.getIdProduit()).isNotNull();
	}
	
	@Test
	public void updateProduit() {
		init();
		when(produitRepository.findById(anyLong())).thenReturn(Optional.of(p1));
		
		when(produitRepository.save(any(Produit.class))).thenReturn(p1);
		p1.setLibelleProduit("Fantacy");
		ProduitDTO prm=modelMapper.map(p1, ProduitDTO.class);
		Produit exisitingProduit = produitService.updateProduit(prm);
		
		assertNotNull(exisitingProduit);
		assertEquals("Fantacy", exisitingProduit.getLibelleProduit());
	}
	@Test
	public void assignProduitToStock() {
		this.s1 = new Stock();
		this.s1.setIdStock(5L);
		
		this.p2 = new Produit();
		this.p2.setIdProduit(2L);
		this.p2.setStock(s1);
		
		this.modelMapper = new ModelMapper();
		
		produitService.assignProduitToStock(p2.getIdProduit(), p2.getStock().getIdStock());
		assertNotNull(p2);
		assertEquals(p2.getStock().getIdStock(),s1.getIdStock());
	}
	
	@Test
	public void deleteProduit() {
		init();
		Long ProduitId = 1L;
		when(produitRepository.findById(anyLong())).thenReturn(Optional.of(p1));
		doNothing().when(produitRepository).deleteById(anyLong());
		produitService.deleteProduit(ProduitId);
		verify(produitRepository, times(1)).deleteById(anyLong());
		
	}
}