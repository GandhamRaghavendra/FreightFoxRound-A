# Weather Information REST API
This project provides a REST API for getting weather information for a particular day and a pincode. The API retrieves the `latitude` and `longitude` of the pincode using `OpenWeather Geocoding API`, and then retrieves the weather information for that location using `World Weather API`.

## Technologies & Dependency's Used

- Java
- SpringBoot
- STS/Eclipse
- SpringWeb
- Spring Data JPA
- MySQL
- Spring DevTools
- Lombok
- Postman
- Swagger-ui

## API's Used

- **`OpenWeather Geocoding API`** (https://openweathermap.org/api)
  - This API used for getting `latitude` and `longitude` of given pincode
- **`World Weather API`** (https://www.worldweatheronline.com/weather-api)
  - This API used for getting weather information of that pincode on a perticular date.

## Getting Started

### Prerequisites
### Installing
### API Endpoints
- **`http://localhost:8185/weatherInfo`**</br>
Returns the weather information for a particular day and pincode.

- #### Request Body
  - **`pincode`**(required): The pincode for which to retrieve weather information.
  - **`date`**(required): The date for which to retrieve weather information. Format: **`YYYY-MM-DD`**.
  - **`Sample Data`**
  ```
  {
    "pincode":175103,
    "date":"2023-02-15"
  }
  ```
  
  

- #### Response
  - Returns a `JSON` object containing the following fields:
    - `pincode`: The pincode for which the weather information was retrieved.
    - `latitude`: The latitude of the location corresponding to the pincode.
    - `longitude`: The longitude of the location corresponding to the pincode.
    - `country`: The country in which the pincode is located.
    - `date`: The date for which the weather information was retrieved.
    - `sunrise`: The time of sunrise in the local timezone.
    - `sunset`: The time of sunset in the local timezone.
    - `moonrise`: The time of moonrise in the local timezone.
    - `moonset`: The time of moonset in the local timezone.
    - `maxTempC`: The maximum temperature in degrees Celsius.
    - `minTempC`: The minimum temperature in degrees Celsius.
    - `avgTempC`: The average temperature in degrees Celsius.
    - `totalSnowCm`: The total snowfall in centimeters.
    - `sunHour`: The number of hours of sunshine.
  - **`Response Body`**
  
  ```
  {
  "location": {
    "locationId": 7,
    "pincode": "175103",
    "latitude": 32.3143,
    "longitude": 77.1866,
    "contry": "IN"
  },
  "weatherInfo": {
    "weatherId": 8,
    "date": "2023-02-15",
    "sunrise": "07:04 AM",
    "sunset": "06:07 PM",
    "moonrise": "02:28 AM",
    "moonset": "12:30 PM",
    "maxTempC": 27,
    "minTempC": 11,
    "avgTempC": 17,
    "totalSnowCm": 0,
    "sunHour": 11
  }
  
  ```



### Saving Information to the Database
The API saves the pincode latitude and longitude, as well as the weather information for the requested date and pincode, to a MySQL database. This information is retrieved on subsequent API calls to optimize API calls and reduce load on the external weather APIs.
