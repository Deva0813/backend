package org.filmrental.com.controller;

import java.util.List;

import java.util.Optional;

import org.filmrental.com.dto.FindAssignAddressToStaff;
import org.filmrental.com.dto.FindBy;
import org.filmrental.com.dto.SearchByName;
import org.filmrental.com.dto.SearchByNames;
import org.filmrental.com.dto.SearchByPhone;
import org.filmrental.com.dto.UpdateBy;
import org.filmrental.com.exception.DataNotFoundException;
import org.filmrental.com.exception.IdNotFoundException;
import org.filmrental.com.exception.NoActiveSessionException;
import org.filmrental.com.exception.UpdationErrorException;
import org.filmrental.com.model.Address;
import org.filmrental.com.model.City;
import org.filmrental.com.model.Staff;
import org.filmrental.com.model.Store;
import org.filmrental.com.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:4200" }, allowCredentials = "true")
@RequestMapping("/api/staff")
@RestController
public class StaffRestController {
	@Autowired
	private IStaffService staffservice;

	@GetMapping("/logout")
	public ResponseEntity<String> logOutCustomer(HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession(false);
		httpSession.invalidate();
		return new ResponseEntity<String>("Customer Logout Successfully!",
				HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<Staff> loginStaffDetails(
			@RequestBody Staff customer, HttpServletRequest httpServletRequest) {
		Staff staff2 = staffservice
				.loginStaff(customer.getEmail(), customer.getPassword());

		if (staff2 != null) {
			HttpSession httpSession = httpServletRequest.getSession();
			httpSession.setAttribute("Staff", staff2);
			return new ResponseEntity<Staff>(staff2, HttpStatus.OK);
		} else {
			throw new UpdationErrorException("Updation Error");

		}

	}

	@GetMapping("/all/{id}")
	public ResponseEntity<Staff> getAllStaff(@PathVariable("id") byte id) {
		Staff staffList = staffservice.getAllStaff(id);
		return new ResponseEntity<Staff>(staffList, HttpStatus.OK);
	}

	// @PostMapping("/")
	// public ResponseEntity<Staff> addStaff(@RequestBody Staff staff,
	// HttpServletRequest httpServletRequest) {
	//
	// Staff savedStaff = staffservice.addStaff(staff);
	// if (savedStaff != null) {
	// return new ResponseEntity<>(savedStaff, HttpStatus.CREATED);
	// } else {
	// // If saving fails, return error
	// throw new UpdationErrorException("Updation Error");
	// }
	// }
	//

	@PostMapping("/")
	public ResponseEntity<String> addStaff(@RequestParam("firstName") String firstname,
			@RequestParam("lastName") String lastname, @RequestParam("picture") MultipartFile file,
			@RequestParam("email") String email) throws IOException, SerialException, SQLException {

		byte[] bytes = file.getBytes();
		Blob blob = new SerialBlob(bytes);

		Staff staff = new Staff();

		staff.setFirstName(firstname);
		staff.setLastName(lastname);
		staff.setEmail(email);
		staff.setActive(1);
		staff.setPicture(blob);

		staffservice.addStaff(staff);

		return new ResponseEntity<>("Record Created Successfully", HttpStatus.CREATED);
	}

	@GetMapping("/picture")
	public ResponseEntity<byte[]> getMethodName(@RequestParam("firstname") String firstName)
			throws IOException, SQLException {
		List<SearchByName> staffList = staffservice.getStaffByFirstName(firstName);
		System.out.println(firstName);
		SearchByName staff = staffList.get(0);
		byte[] imageBytes = null;
		imageBytes = staff.picture().getBytes(1, (int) staff.picture().length());
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}

	// // Assuming this endpoint is for retrieving staff picture by firstName
	// @GetMapping("/picture")
	// public ResponseEntity<byte[]> getStaffPicture(@RequestParam("firstName")
	// String firstName) {
	//
	// Staff staff = new Staff();
	// try {
	// // Fetch staff by firstName
	// List<SearchByNames> staff3 = staffservice.getStaffByFirstNames(firstName);
	//
	// // Check if staff exists
	// if (staff == null) {
	// return ResponseEntity.notFound().build();
	// }
	//
	// // Convert blob to byte array
	// byte[] imageBytes = ((Staff) staff3).getPicture().getBytes(1, (int)
	// staff.getPicture().length());
	//
	// return
	// ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	// } catch (SQLException e) {
	// // Log the exception for debugging purposes
	// e.printStackTrace();
	//
	// }
	// return null;
	// }

	// Endpoint to get staff by first name
	@GetMapping("/firstname/{firstName}")
	public ResponseEntity<List<SearchByName>> getStaffByFirstName(@PathVariable("firstName") String firstName,
			HttpServletRequest httpServletRequest) {

		List<SearchByName> staffList = staffservice.getStaffByFirstName(firstName);
		if (staffList.isEmpty()) {
			throw new DataNotFoundException("Entered Firstname = " + firstName + " is not present.");
		}
		return new ResponseEntity<>(staffList, HttpStatus.OK);

	}

	// Endpoint to get staff by last name
	@GetMapping("/lastname/{lastName}")
	public ResponseEntity<List<SearchByName>> getStaffByLastName(@PathVariable("lastName") String lastName) {
		List<SearchByName> staffList = staffservice.getStaffByLastName(lastName);
		if (staffList.isEmpty()) {
			throw new DataNotFoundException("Entered Lasttname = " + lastName + " is not present.");
		}
		return new ResponseEntity<>(staffList, HttpStatus.OK);
	}

	// Endpoint to get staff by email
	@GetMapping("/email/{email}")
	public ResponseEntity<List<SearchByName>> getStaffByEmail(@PathVariable("email") String email) {
		List<SearchByName> staffList = staffservice.getStaffByEmail(email);
		if (staffList.isEmpty()) {
			throw new DataNotFoundException("Entered Email = " + email + " is not present.");
		}

		return new ResponseEntity<>(staffList, HttpStatus.OK);
	}

	// Endpoint to get staff by city
	@GetMapping("/city/{city}")
	public ResponseEntity<List<FindBy>> getStaffByCity(@PathVariable String city) {
		List<FindBy> staffList = staffservice.getStaffByCity(city);
		if (staffList.isEmpty()) {
			throw new DataNotFoundException("City Not found");
		}
		return new ResponseEntity<>(staffList, HttpStatus.OK);

	}

	// Endpoint to get staff by country
	@GetMapping("/country/{country}")
	public ResponseEntity<List<FindBy>> getStaffByCountry(@PathVariable("country") String country) {
		List<FindBy> staffList = staffservice.searchStaffByCountry(country);
		if (staffList.isEmpty()) {
			throw new DataNotFoundException("Country Not found");
		}
		return new ResponseEntity<>(staffList, HttpStatus.OK);
	}

	// Endpoint to get staff by phone number
	@GetMapping("/phone/{phone}")
	public ResponseEntity<SearchByPhone> searchStaffByPhone(@PathVariable("phone") String phone) {
		SearchByPhone staff = staffservice.searchStaffByPhone(phone);
		if (staff != null) {
			return new ResponseEntity<>(staff, HttpStatus.OK);
		} else {
			throw new DataNotFoundException("PhoneNumber Not found");
		}
	}

	public Byte getStaffId(Staff staff) {

		if (staff.getStaffId() == null) {
			return Byte.valueOf((byte) 1); // or any other default value
		}
		return staff.getStaffId();
	}

	@PutMapping("/update/fn/{id}")
	public ResponseEntity<UpdateBy> updateStaffFirstName(
			@PathVariable("id") Byte staffId,
			@RequestBody Staff staff) {
		// Ensure staffId is provided in the request path
		if (staffId == null) {
			throw new IllegalArgumentException("Staff ID must be provided in the request path");
		}

		// Set the staffId in the staff object
		staff.setStaffId(staffId);

		UpdateBy updatedStaff = staffservice.updateStaffFirstName(staff.getFirstName(), staff.getStaffId());
		if (updatedStaff != null) {
			return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
		} else {
			throw new DataNotFoundException("Failed to update staff's first name");
		}
	}

	// Endpoint to update staff's last name
	@PutMapping("/update/ln/{id}")
	public ResponseEntity<UpdateBy> updateStaffLasttName(@PathVariable("id") Byte staffId,
			@RequestBody Staff staff) {
		if (staffId == null) {
			throw new IllegalArgumentException("Staff ID must be provided in the request path");
		}

		// Set the staffId in the staff object
		staff.setStaffId(staffId);
		UpdateBy updatedStaff = staffservice.updateStaffLasttName(staff.getLastName(), staff.getStaffId());

		if (updatedStaff != null) {
			return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
		} else {
			throw new DataNotFoundException("Failed to update staff's first name");
		}

	}

	// Endpoint to update staff's email
	@PutMapping("/update/email/{id}")
	public ResponseEntity<UpdateBy> updateStaffemail(@PathVariable("id") Byte staffId,
			@RequestBody Staff staff) {
		if (staffId == null) {
			throw new IllegalArgumentException("Staff ID must be provided in the request path");
		}

		// Set the staffId in the staff object
		staff.setStaffId(staffId);
		UpdateBy updatedStaff = staffservice.updateStaffEmail(staff.getEmail(), staff.getStaffId());

		if (staffservice.getStaffById(staff.getStaffId()) != null) {
			if (updatedStaff != null) {
				return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
			} else {
				throw new IdNotFoundException("Staff not found with Id Updation Error");
			}
		} else {
			throw new UpdationErrorException(" Updation Error");

		}

	}

	// Endpoint to assign address to staff
	@PutMapping("/{id}/address")
	public ResponseEntity<FindAssignAddressToStaff> assignAddressToStaff(@PathVariable("id") byte staffId,
			@RequestBody Address address, HttpServletRequest httpServletRequest) {
		// Assign the address to the staff
		FindAssignAddressToStaff updatedStaff = staffservice.assignAddressToStaff(staffId, address);

		if (updatedStaff != null) {
			// Update the staff
			return new ResponseEntity<>(updatedStaff, HttpStatus.OK);

		}
		throw new UpdationErrorException(" Cannot assign address to staff");
	}

	// Endpoint to assign store to staff
	@PutMapping("{id}/store")
	public ResponseEntity<Staff> assignStoreToStaff(@PathVariable("id") byte staffId,
			@RequestBody Store store, HttpServletRequest httpServletRequest) {

		// Assign the store to the staff
		Staff updatedStaff = staffservice.assignStoreToStaff(staffId, store);
		if (updatedStaff != null) {
			// Update the staff
			return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
		}

		throw new UpdationErrorException(" Cannot assign Store to staff");

	}

	// Endpoint to update staff's phone number
	@PutMapping("/update/phone/{id}")
	public ResponseEntity<SearchByPhone> updateStaffPhone(@PathVariable("id") byte id, @RequestBody Address address,
			HttpServletRequest httpServletRequest) {

		SearchByPhone updatedStaff = staffservice.updateStaffPhone(address.getPhone(), id);
		if (updatedStaff != null) {
			return ResponseEntity.ok(updatedStaff);
		} else {
			throw new UpdationErrorException("Updation Error");
		}
	}

}

// used while reterving the picture
// @GetMapping("/{id}/picture")
// public ResponseEntity<byte[]> getStaffPictureById(@PathVariable("id") Long
// id) {
// Optional<byte[]> pictureData = staffservice.getStaffPictureById(id);
// if (pictureData.isPresent()) {
// HttpHeaders headers = new HttpHeaders();
// headers.setContentType(MediaType.IMAGE_JPEG); // Assuming JPEG format, change
// if necessary
// return new ResponseEntity<>(pictureData.get(), headers, HttpStatus.OK);
// } else {
// return new ResponseEntity<>(HttpStatus.NOT_FOUND);
// }
// }

// @PostMapping("/login")
// public ResponseEntity<> loginCustomerDetails(
// @RequestBody Customer customer,HttpServletRequest httpServletRequest){
// Customer customer2= customerDBService
// .loginCustomer(customer.getEmailId(), customer.getCustomerPassword());
//
//
// if(customer2!=null)
// {
// HttpSession httpSession=httpServletRequest.getSession();
// httpSession.setAttribute("customer", customer2);
// }
//
// return new ResponseEntity<Customer>(customer2, HttpStatus.OK);
// }
//
// @GetMapping("/logout")
// public ResponseEntity<String> logOutCustomer(HttpServletRequest
// httpServletRequest) {
// HttpSession httpSession= httpServletRequest.getSession(false);
// httpSession.invalidate();
// return new ResponseEntity<String>("Customer logout Successfully!",
// HttpStatus.OK);
// }
