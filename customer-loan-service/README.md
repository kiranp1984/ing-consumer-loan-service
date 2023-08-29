# ING Consumer Loan Service Application 

This Loan Request Service is used to store and retrieve one or more loan request for ING Customer.

When customer will request for loan, first it will be checked whether it is existing customer then only loan details will be added and if customer does not exist So both the customer and the loan will be created.

## About the Service
ING Consumer Loan request service Using Spring Boot and H2 Database.

## Get information about system health 
http://localhost:8092/actuator/health

## To view Swagger 2 API docs
Run the server and browse to http://localhost:8092/swagger-ui/index.html

## H2 Database
http://localhost:8092/h2-console/login.jsp

## Save Consumer Loan 
http://localhost:8092/loan/save
{
"customerId": 1,
"customerFullName": "Kiran",
"loanAmount": 500
}

## Get Loan By ConsumerId
http://localhost:8092/loan/101

