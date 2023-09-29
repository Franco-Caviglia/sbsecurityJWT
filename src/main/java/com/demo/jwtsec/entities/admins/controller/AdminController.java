package com.demo.jwtsec.entities.admins.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/admin")
@RequiredArgsConstructor
public class AdminController {

    //TODO acciones permitidas para admins -> readAllShifts(); markCompleteShifts(); readUserProfiles(); deleteShifts(); editShifts();
}
