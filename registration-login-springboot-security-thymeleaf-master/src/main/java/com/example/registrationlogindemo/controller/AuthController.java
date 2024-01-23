package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.RoomDto;
import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.dto.MeetingDto;
import com.example.registrationlogindemo.entity.Meeting;
import com.example.registrationlogindemo.entity.Room;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.service.RoomService;
import com.example.registrationlogindemo.service.UserService;
import com.example.registrationlogindemo.service.MeetingService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;



@Controller
public class AuthController {

    private UserService userService;
    private MeetingService meetingService;
    private RoomService roomService;

    public AuthController(UserService userService, MeetingService meetingService, RoomService roomService) {
        this.userService = userService;
        this.meetingService = meetingService;
        this.roomService = roomService;
    }

    @GetMapping("index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model){

        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/meetingsall")
    public String listMeetings(Model model){
        List<Meeting> meetings = meetingService.findAllMeetings();
        model.addAttribute("meetings", meetings);
        return "meetingsall";
    }

    @GetMapping("/meetings")
    public String showMeetingForm(Model model) {
        MeetingDto meeting = new MeetingDto();
        model.addAttribute("meeting", meeting);
        return "meetingForm"; // Return the HTML form for creating a meeting
    }

    // Endpoint to handle the submission of the meeting creation form
    @PostMapping("/meetings/save")
    public String createMeeting(@Valid @ModelAttribute("meeting") MeetingDto meeting,
                                BindingResult result, Model model) {
        /*if(meeting.getEndTime().intern(LocalDate.now())){
            result.rejectValue("meetingDate", null, "Meeting date cannot be in the past");
        }*/

        //check if the meeting date is used(if it is in an interval of another meeting)
        List<Meeting> meetings = meetingService.findAllMeetings();
        for(Meeting m: meetings){
            if(meeting.getStartTime().isAfter(m.getStartTime()) && meeting.getEndTime().isBefore(m.getEndTime())){
                result.rejectValue("startTime", null, "Meeting time is already used");
            }
            if (meeting.getStartTime().isBefore(m.getStartTime()) && meeting.getEndTime().isAfter(m.getEndTime())){
                result.rejectValue("endTime", null, "Meeting time is already used");
            }
            if (meeting.getStartTime().isAfter(m.getStartTime()) && meeting.getStartTime().isBefore(m.getEndTime())){
                result.rejectValue("startTime", null, "Meeting time is already used");
            }
            if (meeting.getEndTime().isAfter(m.getStartTime()) && meeting.getEndTime().isBefore(m.getEndTime())){
                result.rejectValue("endTime", null, "Meeting time is already used");
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("meeting", meeting);
            return "meetingForm"; // Return the form again if there are validation errors
        }
        // Save the meeting using your meetingService
        // TODO add userid
        meetingService.saveMeeting(meeting);
        return "redirect:/meetings?success"; // Redirect to the meeting creation form with success message
    }

    @GetMapping("/addusertomeeting/{meetingId}")
    public String showAddUserToMeetingForm(@PathVariable int meetingId, Model model) {
        Meeting meeting = meetingService.findById(meetingId);
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("meeting", meeting);
        return "addUserToMeet"; // Return the name of the Thymeleaf template for the form
    }

    @PostMapping("/addusertomeeting/{meetingId}")
    public String addUserToMeeting(
            /*@RequestParam("meetingId") String addedMeetingId,*/
            @RequestParam("userId") String userId, @PathVariable int meetingId) {
        User user1 = userService.findById(Integer.parseInt(String.valueOf(userId)));
        Meeting meeting1 = meetingService.findById(meetingId);
        //Meeting meeting = meetingService.findById(meetingId);


        meetingService.addUser(user1, meeting1);
        //model.addAttribute("meeting", meeting);

        return "redirect:/addusertomeeting/{meetingId}"; // Redirect to a success page after adding user to meeting
    }



    @GetMapping("/usersall")
    public String listUsersInMeeting(Model model){
        /*List<User> users = meetingService.findAllUsersInMeeting(1);
        model.addAttribute("users", users);
        List<Meeting> meetings = userService.findAllMeetings(1);
        model.addAttribute("meetings", meetings);*/
        List<User> users1 = meetingService.findAllUsersInMeeting(2);
        model.addAttribute("users", users1);
        List<Meeting> meetings1 = userService.findAllMeetings(2);
        model.addAttribute("meetings", meetings1);
        return "usersall";
    }

    @GetMapping("/userDetails/{userId}")
    public String showUserDetails(@PathVariable int userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "userDetails";
    }

    @GetMapping("/editUser/{userId}")
    public String showEditUserForm(@PathVariable int userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/editUser/{userId}")
    public String editUser(@ModelAttribute UserDto userDto, @PathVariable int userId) {
        userService.editUser(userDto, userId);
        return "redirect:/userDetails/{userId}";
    }

    @GetMapping("/rooms")
    public String showRoomForm(Model model) {
        RoomDto room = new RoomDto();
        model.addAttribute("room", room);
        return "roomForm"; // Return the HTML form for creating a meeting
    }

    @PostMapping("/rooms/save")
    public String createRoom(@Valid @ModelAttribute("room") RoomDto room,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("room", room);
            return "roomForm"; // Return the form again if there are validation errors
        }
        // Save the meeting using your meetingService
        // TODO add userid
        roomService.saveRoom(room);
        return "redirect:/rooms?success"; // Redirect to the meeting creation form with success message
    }
    @GetMapping("/roomsall")
    public String listRooms(Model model){
        List<RoomDto> rooms = roomService.findAllRooms();
        model.addAttribute("rooms", rooms);
        return "roomsall";
    }

    @GetMapping("/addroomtomeeting")
    public String showAddRoomToMeetingForm(Model model) {
        return "addRoomToMeet"; // Return the name of the Thymeleaf template for the form
    }

    @PostMapping("/addroomtomeeting")
    public String addRoomToMeeting(
            @RequestParam("meetingId") String meetingId,
            @RequestParam("roomId") String roomId) {
        Room room1 = roomService.findById(Integer.parseInt(String.valueOf(roomId)));
        Meeting meeting1 = meetingService.findById(Math.toIntExact(Long.parseLong(meetingId)));

        meetingService.addRoom(room1, meeting1);

        return "redirect:/success-page"; // Redirect to a success page after adding user to meeting
    }

    @GetMapping("/meetingDetails/{meetingId}")
    public String showMeetingDetails(@PathVariable int meetingId, Model model) {
        Meeting meeting = meetingService.findById(meetingId);
        model.addAttribute("meeting", meeting);
        return "meetingDetails";
    }


    @GetMapping("/createmeeting")
    public String showCreateForm(Model model) {
        MeetingDto meeting = new MeetingDto();
        model.addAttribute("meeting", meeting);
        return "createMeeting"; // Return the HTML form for creating a meeting
    }

    // Endpoint to handle the submission of the meeting creation form
    @PostMapping("/createmeeting/save")
    public String createMeetingSave(@Valid @ModelAttribute("meeting") MeetingDto meeting,
                                BindingResult result, Model model) {
        /*if(meeting.getEndTime().intern(LocalDate.now())){
            result.rejectValue("meetingDate", null, "Meeting date cannot be in the past");
        }*/

        //check if the meeting date is used(if it is in an interval of another meeting)
        List<Meeting> meetings = meetingService.findAllMeetings();
        for(Meeting m: meetings){
            if(meeting.getStartTime().isAfter(m.getStartTime()) && meeting.getEndTime().isBefore(m.getEndTime())){
                result.rejectValue("startTime", null, "Meeting time is already used");
            }
            if (meeting.getStartTime().isBefore(m.getStartTime()) && meeting.getEndTime().isAfter(m.getEndTime())){
                result.rejectValue("endTime", null, "Meeting time is already used");
            }
            if (meeting.getStartTime().isAfter(m.getStartTime()) && meeting.getStartTime().isBefore(m.getEndTime())){
                result.rejectValue("startTime", null, "Meeting time is already used");
            }
            if (meeting.getEndTime().isAfter(m.getStartTime()) && meeting.getEndTime().isBefore(m.getEndTime())){
                result.rejectValue("endTime", null, "Meeting time is already used");
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("meeting", meeting);
            return "meetingForm"; // Return the form again if there are validation errors
        }
        // Save the meeting using your meetingService
        // TODO add userid
        meetingService.saveMeeting(meeting);
        return "redirect:/meetings?success"; // Redirect to the meeting creation form with success message
    }

    
}
