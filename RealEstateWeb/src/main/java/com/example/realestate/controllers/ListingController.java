package com.example.realestate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.realestate.dtos.listings.AddressDTO;
import com.example.realestate.dtos.listings.ApartmentDTO;
import com.example.realestate.dtos.listings.BuildingDTO;
import com.example.realestate.dtos.listings.ListingDTO;
import com.example.realestate.dtos.listings.ListingFormDTO;
import com.example.realestate.dtos.listings.SearchFormDTO;
import com.example.realestate.dtos.listings.TermsDTO;
import com.example.realestate.models.Address;
import com.example.realestate.models.Apartment;
import com.example.realestate.models.AppUser;
import com.example.realestate.models.Building;
import com.example.realestate.models.Item;
import com.example.realestate.models.Listing;
import com.example.realestate.models.Terms;
import com.example.realestate.security.SecurityUtil;
import com.example.realestate.services.AddressService;
import com.example.realestate.services.ApartmentService;
import com.example.realestate.services.BuildingService;
import com.example.realestate.services.ItemService;
import com.example.realestate.services.ListingService;
import com.example.realestate.services.TermsService;
import com.example.realestate.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/oglasi")
public class ListingController {
	@Autowired
	private AddressService addressServ;
	@Autowired
	private BuildingService buildingServ;
	@Autowired
	private ItemService itemServ;
	@Autowired
	private ApartmentService apartmentServ;
	@Autowired
	private TermsService termsServ;
	@Autowired
	private ListingService listingServ;
	// Teddy Smith
	@Autowired
	private UserService userService;

	@GetMapping("")
	public String listListings(@RequestParam(defaultValue = "0", required = false) int page, Model model) {
		Page<Listing> listingsPage = listingServ.findAll(page, 2);
		// BezKoder
		model.addAttribute("searchFormDTO", new SearchFormDTO());
		model.addAttribute("listingsPage", listingsPage);
		model.addAttribute("listings", listingsPage.getContent());
		return "listings/listing_list";
	}

	@GetMapping("/{id}")
	public String listingsDetail(@PathVariable int id, Model model) {
		String email = SecurityUtil.getSessionUser();
		AppUser user = email != null ? userService.findByEmail(email) : new AppUser();
		model.addAttribute("user", user);
		model.addAttribute("listing", listingServ.findById(id));
		return "listings/listing_detail";
	}

	@GetMapping("/postavka")
	public String createListingPage(Model model) {
		model.addAttribute("listingFormDTO", new ListingFormDTO());
		model.addAttribute("allCities", addressServ.allCities());
		model.addAttribute("allItems", itemServ.findAll());
		return "listings/listing_create";
	}

	@PostMapping("/postavka")
	public String createListing(@ModelAttribute @Valid ListingFormDTO listingFormDTO, BindingResult result,
		Model model) {
		if (result.hasErrors()) {
			model.addAttribute("listingFormDTO", listingFormDTO);
			model.addAttribute("allItems", itemServ.findAll());
			return "listings/listing_create";
		}

		AddressDTO addressDTO = listingFormDTO.getAddress();
		BuildingDTO buildingDTO = listingFormDTO.getBuilding();
		List<Long> itemIds = listingFormDTO.getItems();
		ApartmentDTO apartmentDTO = listingFormDTO.getApartment();
		TermsDTO termsDTO = listingFormDTO.getTerms();
		ListingDTO listingDTO = listingFormDTO.getListing();

		Address address = addressServ.findOrCreate(addressDTO);
		Building building = buildingServ.findOrCreate(address, buildingDTO);
		List<Item> items = itemServ.findByIdIn(itemIds);
		Apartment apartment = apartmentServ.findOrCreate(building, items, apartmentDTO);
		Terms terms = termsServ.findOrCreate(termsDTO);
		listingServ.create(apartment, terms, listingDTO);
		return "redirect:/oglasi";
	}

	@GetMapping("/pretraga")
	public String searchListings(@ModelAttribute SearchFormDTO searchFormDTO,
		@RequestParam(defaultValue = "0", required = false) int page, Model model) {
		String city = searchFormDTO.getCity();
		Integer numOfRooms = searchFormDTO.getNumOfRooms();
		Integer priceFrom = searchFormDTO.getPriceFrom();
		Integer priceTo = searchFormDTO.getPriceTo();
		Integer m2From = searchFormDTO.getM2From();
		Integer m2To = searchFormDTO.getM2To();

		Specification<Listing> specification = ListingSpecification.filter(
			city,
			numOfRooms,
			priceFrom,
			priceTo,
			m2From,
			m2To);

		// BezKoder
		Page<Listing> listingsPage = listingServ.filter(specification, page, 2);
		// BezKoder
		model.addAttribute("searchFormDTO", new SearchFormDTO());
		model.addAttribute("listingsPage", listingsPage);
		model.addAttribute("listings", listingsPage.getContent());
		return "listings/listing_list";
	}

	@GetMapping("/izmena/{id}")
	public String editListingPage(@PathVariable int id, Model model) {
		Listing listing = listingServ.findById(id);

		ListingFormDTO listingFormDTO = new ListingFormDTO();
		listingFormDTO.setAddress(addressServ.toDTO(listing.getApartment().getBuilding().getAddress()));
		listingFormDTO.setBuilding(buildingServ.toDTO(listing.getApartment().getBuilding()));
		listingFormDTO.setItems(itemServ.toIds(listing.getApartment().getItems()));
		listingFormDTO.setApartment(apartmentServ.toDTO(listing.getApartment()));
		listingFormDTO.setTerms(termsServ.toDTO(listing.getTerms()));
		listingFormDTO.setListing(listingServ.toDTO(listing));

		model.addAttribute("listing", listing);
		model.addAttribute("listingFormDTO", listingFormDTO);
		model.addAttribute("allCities", addressServ.allCities());
		model.addAttribute("allItems", itemServ.findAll());
		return "listings/listing_edit";
	}

	@PostMapping("/izmena/{id}")
	public String editListing(@PathVariable int id, @ModelAttribute ListingFormDTO listingFormDTO, Model model) {
		Listing listing = listingServ.findById(id);
		List<Item> items = itemServ.findByIdIn(listingFormDTO.getItems());
		listingServ.update(listing.getId(), listingFormDTO.getListing());
		termsServ.update(listing.getTerms().getId(), listingFormDTO.getTerms());
		apartmentServ.update(listing.getApartment().getId(), items, listingFormDTO.getApartment());
		buildingServ.update(listing.getApartment().getBuilding().getId(), listingFormDTO.getBuilding());
		addressServ.update(listing.getApartment().getBuilding().getAddress().getId(), listingFormDTO.getAddress());
		return "redirect:/oglasi/" + id;
	}

	@GetMapping("/brisanje/{id}")
	public String deleteListing(@PathVariable int id) {
		Listing listing = listingServ.findById(id);
		listingServ.delete(id);
		termsServ.delete(listing.getTerms().getId());
		apartmentServ.delete(listing.getApartment().getId());
		buildingServ.delete(listing.getApartment().getBuilding().getId());
		return "redirect:/oglasi";
	}
}
