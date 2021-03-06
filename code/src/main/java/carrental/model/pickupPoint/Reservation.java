package carrental.model.pickupPoint;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Michael on 13.05.2015.
 */
@Document(collection = "reservation")
public class Reservation {

    private Long carId;

    private Long customerId;

    private Long reservationId;

    private String licensePlate;

    public String getLicensePlateUrl() {
        return getLicensePlate().replace(" ", "%20");
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Id
    private Long id;

    private Date reservationDate;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    @Override
    public String toString() {
        return "Reservation [" + this.getId() + ", Car: " + this.getCarId() + ", Reservation: " + this.getReservationId() + ", Customer: " + this.getCustomerId() + "]";
    }
}
