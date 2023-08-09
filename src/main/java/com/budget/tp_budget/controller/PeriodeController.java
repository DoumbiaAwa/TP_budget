package com.budget.tp_budget.controller;

import com.budget.tp_budget.service.PeriodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/periode")
public class PeriodeController {

  @Autowired
  private PeriodeService periodeService;

}
