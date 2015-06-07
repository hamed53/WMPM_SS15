package carrental.routing.pickupPoint;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import carrental.beans.pickupPoint.RejectedCarQueue;
import carrental.model.pickupPoint.PickupProtocol;

/**
 * Created by Hamed on 14.05.2015.
 * 
 * INPUT-QUEUE: direct:pickupPoint.cancel
 * OUTPUT-QUEUE: seda:queue:carToInspectQueue
 */
@Component
public class CarRejectionRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:pickupPoint.cancel").process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				PickupProtocol pickupProtocol = exchange.getIn().getBody(PickupProtocol.class);
				RejectedCarQueue.getRejectedCarsList().add(pickupProtocol);
				System.out.println("Rejected car with id: " + pickupProtocol.getReservation().getCarId() + " for customer with id: "
						+ pickupProtocol.getReservation().getCustomerId() + " sent to carToFixClaimsQueue.");
			}
		}).to("seda:queue:carToInspectQueue");
	}
}
