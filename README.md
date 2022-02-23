# Shortcut service

The service provides API:

- to code your url into 7-digit alpha-numeric code
to securely replace the address by a coded string;
  
- auto-redirect by available url-code to its corresponding url-address;

- request statistics on all registered/coded urls and also the number of
redirections with each url that was provided by the service.
  
Stack:

- Java 17
- Spring Boot 2 (REST)
- Spring Security & JWT authorization
- Spring Data JPA
- PostgreSQL