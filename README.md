# FreightFoxRound-A

## [Dynamic PDF Generation](/PdfGenerator)
  - This project is a Spring Boot application that provides a REST API to generate a PDF using a Java Template Engine. The application uses `iText` as the template engine   to generate the PDF based on the data received from the REST API. The generated PDF is then accessed using the provided endpoint.

## [Weather Info for Pincode](/WeatherInfoApp)
 - This project is a Spring Boot application that provides a REST API to retrieve weather information for a particular day and pincode. The API takes input parameters in the form of a `pincode` and a `date` and returns the weather information for that pincode on the specified date. The application uses the `World Weather API` to fetch the weather information and saves it in a RDBMS database along with the `latitude` and `longitude` coordinates of the pincode.
