package services;
   
   import io.restassured.response.Response;
   import models.Booking;
   
   public class BookingService extends BaseApiService {
       
       public Response getAllBookings() {
           return getRequestSpec()
                   .when()
                   .get("/booking");
       }
       
       public Response getBookingById(int bookingId) {
           return getRequestSpec()
                   .when()
                   .get("/booking/" + bookingId);
       }
       
       public Response createBooking(Booking booking) {
           return getRequestSpec()
                   .body(booking)
                   .when()
                   .post("/booking");
       }
       
       public Response updateBooking(int bookingId, Booking booking, String token) {
           return getRequestSpecWithAuth(token)
                   .body(booking)
                   .when()
                   .put("/booking/" + bookingId);
       }
       
       public Response deleteBooking(int bookingId, String token) {
           return getRequestSpecWithAuth(token)
                   .when()
                   .delete("/booking/" + bookingId);
       }
       
       public Response partialUpdateBooking(int bookingId, Object partialUpdate, String token) {
           return getRequestSpecWithAuth(token)
                   .body(partialUpdate)
                   .when()
                   .patch("/booking/" + bookingId);
       }
   }