package carrental.beans.pickupPoint;

import java.util.LinkedList;

import carrental.model.pickupPoint.PickupProtocol;

/**
 * Created by Hamed on 15.05.2015.
 */
public class RejectedCarQueue {

	public static LinkedList<PickupProtocol> rejectedCarsList = new LinkedList<>();

	public static LinkedList<PickupProtocol> getRejectedCarsList() {
		return rejectedCarsList;
	}
}
