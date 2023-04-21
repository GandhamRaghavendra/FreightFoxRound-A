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


## Getting Started

- Download and Run it on local system
- Two way of accessing service by `swagger-ui` and `postman`.
- Using swagger-ui (http://localhost:8080/swagger-ui/index.html#/)
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

<h3>Step-3()</h3>
<img src="/images/Screenshot4.png" alt="Alt text" style="width: 80%; display: block; margin: 0 auto;">



