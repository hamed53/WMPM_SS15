package carrental.beans.pickupPoint;

import java.util.Iterator;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import carrental.beans.reservation.CarDTO;
import carrental.model.pickupPoint.PickupProtocol;
import carrental.model.reservation.Car;
import carrental.repository.reservation.CarRepository;

/**
 * Created by Hamed on 14.05.2015.
 */
@Component
public class CancelReservationBean {

	@Autowired
	private ProducerTemplate producerTemplate;

	@Autowired
	private CarRepository carRepo;

	private static RestTemplate restClient = new RestTemplate();

	@Scheduled(fixedRate = 2500)
	public void cancelReservation() {
		if (!RejectedCarQueue.getRejectedCarsList().isEmpty()) {
			for (Iterator<PickupProtocol> iterator = RejectedCarQueue.getRejectedCarsList().iterator(); iterator.hasNext();) {
				PickupProtocol pickupProtocol = (PickupProtocol) iterator.next();
				Car car = carRepo.findOne(pickupProtocol.getReservation().getCarId());
				if (car != null) {
					String url = "http://127.0.0.1:9000/availability?licensePlate=" + car.getLicensePlate() + "&state=AVAILABLE";
					CarDTO carDTO = restClient.getForObject(url, CarDTO.class);
					System.out.println("Car with licence plate = " + carDTO.getLicensePlate() + " cancelled and its status updated to " + carDTO.getCarState());
				}
				iterator.remove(); // Remove PickupProtocol from RejectedCarQueue.getRejectedCarsList()
			}
		}
	}
}
