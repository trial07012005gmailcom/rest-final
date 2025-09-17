Feature: Booking endpoint

  Scenario: GET all bookings
    Given I perform a GET call to the booking endpoint
    Then I verify that the status code is 200


  Scenario: GET booking by inexistent id
    Given I perform a GET call to the booking endpoint with id "2978668"
    Then I verify that the status code is 404

  Scenario: GET booking by letters with id
    Given I perform a GET call to the booking endpoint with id "abcd"
    Then I verify that the status code is 404

  Scenario Outline: POST a new booking
    Given I perform a POST call to the booking endpoint with the following data
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    Then I verify that the status code is 200
    And I verify that the field "booking.firstname" contains "<firstname>"

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Cristian | Meneses    | 500        | true        | 2021-09-14 | 2024-09-14 | Desayuno        |

  Scenario Outline: POST a new booking with negative totalprice
    Given I perform a POST call to the booking endpoint with the following data
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    Then I verify that the status code is 400

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Cristian | Meneses    | -500         | true        | 2022-09-20 | 2023-09-25 | Desayuno        |

  Scenario Outline: POST a new booking with invalid bookingdates
    Given I perform a POST call to the booking endpoint with the following data
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    Then I verify that the status code is 400

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Cristian | Meneses    | 150        | true        | 2025-09-10 | 2020-09-25 | Desayuno               |
