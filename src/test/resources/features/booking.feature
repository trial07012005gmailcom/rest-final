Feature: Booking API Tests
  As an API consumer
  I want to manage bookings through the API
  So that I can create, read, update, and delete bookings

  Background:
    Given the API is available
    And I have a valid authentication token

  @smoke @get
  Scenario: Get all bookings
    When I send a GET request to "/booking"
    Then the response status code should be 200
    And the response should contain a list of booking IDs

  @crud @post
  Scenario: Create a new booking
    Given I have a booking payload with the following details:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | John      | Doe      | 150        | true        | 2025-10-01 | 2025-10-05 | Breakfast       |
    When I send a POST request to "/booking"
    Then the response status code should be 200
    And the response should contain the booking details
    And the response should match the JSON schema "booking-schema.json"

  @crud @get
  Scenario: Get a specific booking by ID
    Given a booking exists with ID 1
    When I send a GET request to "/booking/1"
    Then the response status code should be 200
    And the response should contain the booking details
    And the response should match the JSON schema "booking-schema.json"

  @crud @put
  Scenario: Update an existing booking
    Given a booking exists with ID 1
    And I have a booking payload with the following details:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Jane      | Smith    | 200        | false       | 2025-11-01 | 2025-11-07 | Dinner          |
    When I send a PUT request to "/booking/1" with authentication
    Then the response status code should be 200
    And the response should contain the updated booking details
    And the response should match the JSON schema "booking-schema.json"

  @crud @patch
  Scenario: Partially update an existing booking
    Given a booking exists with ID 1
    And I have a partial booking payload with the following details:
      | firstname | Jane      |
      | totalprice | 175       |
    When I send a PATCH request to "/booking/1" with authentication
    Then the response status code should be 200
    And the response should contain the updated booking details
    And the response should match the JSON schema "booking-schema.json"

  @crud @delete
  Scenario: Delete a booking
    Given a booking exists with ID 1
    When I send a DELETE request to "/booking/1" with authentication
    Then the response status code should be 201
    And the booking should no longer exist