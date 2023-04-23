# Dynamic PDF Generation

A Spring Boot Application with REST API to generate PDF using Java Template
Engine.

## Table of Contents

- Technologies & Dependency's Used
- [Getting Started](#getting-started)


## Technologies & Dependency's Used

- Java
- SpringBoot
- STS/Eclipse
- SpringWeb
- Spring Data JPA
- MySQL
- Spring DevTools
- OpenPDF
- Lombok
- Postman
- Swagger-ui

## ER Diagram
<img src="/images/PdfGeneratorEr.png" alt="Alt text" style="width: 80%; display: block; margin: 0 auto;">


## Getting Started

- Clone repo to local system
- Change DB details (`DB_Name`,`Username`,`Password`) present in [application.propert](/images/dbProperty.png) file.
- Run the Sprinngboot app
- Two way of accessing service by `swagger-ui` and `postman`.
- Using swagger-ui (http://localhost:8080/swagger-ui/index.html#/)
### API Endpoint
   - **`http://localhost:8080/pdf/storeData`**
      - This API used for storing InvoiceData
      - In request body it will take InvoiceData Entity with Item's list
      - On successfull response it will provide `InvoiceId`
      </br>
   - **`http://localhost:8080/pdf/generate/{InvoiceId}`**
      - This API used for getting Pdf file for perticular `InvoiceId`
      - On successfull we can Download the file
<h3>Swagger</h3>
<img src="/images/Screenshot1.png" alt="Alt text" style="width: 80%; display: block; margin: 0 auto;">

<h3>Step-1(Click on Try-out)</h3>
<img src="/images/Screenshot2.png" alt="Alt text" style="width: 80%; display: block; margin: 0 auto;">

<h3>Step-2(Provide data)</h3>

### `Sample Data`

```ruby
{
"seller": "XYZ Pvt. Ltd.",
"sellerGstin": "29AABBCCDD121ZD",
"sellerAddress": "New Delhi, India",
"buyer": "Vedant Computers",
"buyerGstin": "29AABBCCDD131ZD",
"buyerAddress": "New Delhi, India",
"items": [
{
"name": "Product 1",
"quantity": "12 Nos",
"rate": 123.00,
"amount": 1476.00
}
]
}
```
<img src="/images/Screenshot3.png" alt="Alt text" style="width: 80%; display: block; margin: 0 auto;">

<h3>Step-3(On successfull call you will receive `InvoiceId`)</h3>
<img src="/images/Screenshot4.png" alt="Alt text" style="width: 80%; display: block; margin: 0 auto;">

<h3>Step-3(Use `InvoiceId` to get Pdf file)</h3>
<img src="/images/Screenshot5.png" alt="Alt text" style="width: 80%; display: block; margin: 0 auto;">

<h3>Step-3(On successfull call you can download file)</h3>
<img src="/images/Screenshot6.png" alt="Alt text" style="width: 80%; display: block; margin: 0 auto;">

<h3>Final Result</h3>
<img src="/images/resultPdf.png" alt="Alt text" style="width: 80%; display: block; margin: 0 auto;">

