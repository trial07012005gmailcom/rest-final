package StepDefinitions;

import Entities.Booking;
import Entities.BookingDates;
import Utils.Request;
import Constants.BookingEndPoints;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.Matchers;


import java.util.List;

import static org.hamcrest.Matchers.hasKey;

public class BookingSteps {

    Response response;

    @When("I perform a GET call to the booking endpoint")
    public void getAllBookings() throws InterruptedException {
        Thread.sleep(5000);
        response = Request.get(BookingEndPoints.GET_BOOKINGS);
    }

    @And("I verify that the status code is {int}")
    public void verifyStatusCode(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }


    @When("I perform a GET call to the booking endpoint with id {string}")
    public void getBookingById(String id) {
        response = Request.getById(BookingEndPoints.GET_BOOKING, id);
    }

    @Then("I verify that the field {string} contains {string}")
    public void verifyFieldValue(String field, String value) {
        response.then().assertThat().body(field, Matchers.equalTo(value));
    }


    @When("I perform a POST call to the booking endpoint with the following data")
    public void createBooking(DataTable bookingData) throws Exception {
        Thread.sleep(5000);
        List<String> data = bookingData.transpose().asList(String.class);

        Booking booking = new Booking();
        booking.setFirstname(data.get(0));
        booking.setLastname(data.get(1));
        booking.setTotalprice(Integer.parseInt(data.get(2)));
        booking.setDepositpaid(Boolean.parseBoolean(data.get(3)));
        booking.setAdditionalneeds(data.get(6));

        BookingDates dates = new BookingDates();
        dates.setCheckin(data.get(4));
        dates.setCheckout(data.get(5));

        booking.setBookingdates(dates);

        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(booking);

        response = Request.post(BookingEndPoints.POST_BOOKING, payload);
    }

}