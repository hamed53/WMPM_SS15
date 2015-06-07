package carrental.beans.pickupPoint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import carrental.Constants;
import carrental.beans.returnPoint.CarSimulation;
import carrental.model.pickupPoint.Claim;
import carrental.model.pickupPoint.ClaimType;
import carrental.model.pickupPoint.PickupProtocol;
import carrental.model.pickupPoint.Reservation;

/**
 * Created by Hamed on 14.05.2015.
 */
@Component
public class CarRejectionSimulation {

	private static final boolean ENABLE_CAR_REJECTION = false;

	@Autowired
	private ProducerTemplate producerTemplate;

	@Scheduled(fixedRate = 5000)
	public void carHandedOver() {
		if (ENABLE_CAR_REJECTION)
			return; // no simulation use real one

		PickupProtocol pickupProtocol = new PickupProtocol();
		pickupProtocol.setId(IdSimulator.getNewPickupProtocolId());

		LinkedList<Claim> claimsList = new LinkedList<>();
		Claim claim1 = new Claim();
		claim1.setClaimType(ClaimType.Mechanical);
		claim1.setDescription("Engine exploded");
		claimsList.add(claim1);
		pickupProtocol.setClaims(claimsList);

		Reservation reservation = new Reservation();
		reservation.setId(IdSimulator.getNewReservationId());
		reservation.setCarId(CarSimulation.getNewCarId());
		reservation.setCustomerId(IdSimulator.getNewCustomerId());
		String reservationDateString = "29-Apr-2015,13:00";
		String pickupDateString = "30-Apr-2015,13:00";
		DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm");
		Date reservationDate = new Date();
		Date pickupDate = new Date();
		try {
			reservationDate = formatter.parse(reservationDateString);
			pickupDate = formatter.parse(pickupDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		reservation.setReservationDate(reservationDate);
		pickupProtocol.setReservation(reservation);
		pickupProtocol.setPickupDate(pickupDate);

		System.out.println("Simulated Car Handed over and Pickup protocol forwarded to Car Return Point");
		producerTemplate.sendBodyAndHeader("direct:pickupPoint.cancel", pickupProtocol, "carId", pickupProtocol.getReservation().getCarId());
	}

}
