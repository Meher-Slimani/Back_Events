package com.onegateafrica.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onegateafrica.entity.Event;
import com.onegateafrica.entity.Guest;
import com.onegateafrica.entity.pojo.EmailAddresses;
import com.onegateafrica.exception.EventNotFoundException;
import com.onegateafrica.repository.EventRepository;
import com.onegateafrica.service.CloudinaryService;
import com.onegateafrica.service.EmailService;
import com.onegateafrica.utils.GuestExcelImporter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController()
@CrossOrigin("expo://192.168.1.7:19000")
@RequestMapping("/api")
public class EventController {

  private final EventRepository eventRepository;

  private final CloudinaryService cloudinaryService;

  private final EmailService emailService;


  ObjectMapper objectMapper = new ObjectMapper();

  /**
   * @param multipartFile
   * @param jsondata
   *
   * @return Event object
   *
   * @throws IOException
   */

  @PostMapping("/events")
  public ResponseEntity<Event> saveEvent(@RequestParam MultipartFile multipartFile, @RequestParam(value = "jsondata") String jsondata)
      throws IOException {
    Map result = cloudinaryService.upload(multipartFile);
    Event event = objectMapper.readValue(jsondata, Event.class);
    event.setBanner((String) result.get("secure_url"));
    Event newEvent = eventRepository.save(event);
    return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
  }

  /**
   * @param excelFile
   *
   * @return
   *
   * @throws IOException
   */

  @PostMapping("/events/guests/{eventId}")
  public ResponseEntity<?> uploadSingleEXCELFile(@RequestParam(value = "file") MultipartFile excelFile,
      @PathVariable("eventId") String eventId) {

    Event event = eventRepository.findById(eventId)
        .orElseThrow(() -> new EventNotFoundException("Event" + eventId + "Not found"));

    List<Guest> guestList = GuestExcelImporter.excelImport(excelFile);

    event.setGuests(guestList);

    eventRepository.save(event);

    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  /**
   *
   * @param addressList
   * @return
   */

  @PostMapping("/events/invitations")
  public ResponseEntity<?> sendInvitations( @RequestBody EmailAddresses addressList)  {
      String[] addresses = addressList.getAddresses().toArray(new String[0]);
    for (String address: addresses
    ) {
      emailService.send(address, "Invitation to our event 22222");
    }
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  /**
   *
   * @param eventId
   * @return
   */

  @GetMapping("/events/{eventId}")
  public ResponseEntity<Event> getEvents(@PathVariable("eventId") String eventId) {
    Event event = eventRepository.findById(eventId)
        .orElseThrow(() -> new EventNotFoundException("Event" + eventId + "Not found"));
    return new ResponseEntity<>(event, HttpStatus.OK);
  }

  @GetMapping("/events/{eventId}/invitations/status")
  public ResponseEntity<List<Guest>> getInvitationsStatus(@PathVariable("eventId") String eventId) {
    Event event = eventRepository.findById(eventId)
        .orElseThrow(() -> new EventNotFoundException("Event" + eventId + "Not found"));
    return new ResponseEntity<>(event.getGuests(), HttpStatus.OK);
  }

}
