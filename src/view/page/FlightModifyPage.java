package view.page;

import model.dao.FlightDAOFactory;
import model.flight.Flight;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlightModifyPage extends FlightEditPage {
    private static Flight flight;

    public static void setFlight(Flight f) { flight = f; }

    public FlightModifyPage() {
        super();
    }
    public FlightModifyPage(JComponent left) {
        super(left);
    }
    public FlightModifyPage(JComponent left, JComponent right) {
        super(left, right);
    }
    public FlightModifyPage(JComponent left, JComponent right, boolean displayTitle) {
        super(left, right, displayTitle);
    }

    @Override
    protected void setInit() {
        super.setInit();
        form.setTexts(
            flight.getId(),
            flight.getAirlineName(),
            flight.getDeparture().getKey(),
            String.valueOf(flight.getDepartureGateNo()),
            flight.getDepartureTime(),
            flight.getDestination().getKey(),
            String.valueOf(flight.getDestinationGateNo()),
            flight.getArrivalTime(),
            flight.getAirplane().getKey()
        );
    }

    @Override
    protected String getButtonText() { return "수정"; }

    @Override
    protected void submit() {
        FlightDetailPage.setFlight(extractFlight());
        FlightDAOFactory.getFactory().update(extractFlight());
    }

    @Override
    protected String getTitle() { return "항공편 수정"; }
}
