package com.jpmc.theater.service.price;

import com.jpmc.theater.domain.Reservation;
import com.jpmc.theater.dto.PriceCalculationData;

import java.util.Collection;

public interface PriceCalculatorService {

    PriceCalculationData calculate(Reservation reservation);

    PriceCalculationData calculate(Collection<Reservation> reservation);

}
