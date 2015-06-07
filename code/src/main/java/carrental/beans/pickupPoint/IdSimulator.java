package carrental.beans.pickupPoint;

/**
 * Created by Hamed on 16.05.2015.
 */
public class IdSimulator {
	private static long carId = 1;
	private static long reservationId = 100;
	private static long pickupProtocolId = 1000;
	private static long customerId = 10000;

	public static synchronized long getNewCarId() {
		return carId++;
	}

	public static synchronized long getNewReservationId() {
		return reservationId++;
	}

	public static synchronized long getNewPickupProtocolId() {
		return pickupProtocolId++;
	}

	public static synchronized long getNewCustomerId() {
		return customerId++;
	}

}
