package com.budget.tp_budget.service;

import com.budget.tp_budget.entity.Periode;
import com.budget.tp_budget.repository.PeriodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class PeriodeService {

  @Autowired
  private PeriodeRepository periodeRepository;

  /*public Periode modifier(Integer id, Periode periode) {
    Periode existingPeriode = periodeRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Période non trouvée pour l'ID: " + id));

    existingPeriode.setQuotidienne(periode.getQuotidienne());
    existingPeriode.setHebdomendaire(periode.getHebdomendaire());
    existingPeriode.setMensuelle(periode.getMensuelle());


    return periodeRepository.save(existingPeriode);
  }*/

}

