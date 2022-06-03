# Shortcut service

Technologies:

- Spring Boot (REST)
- Spring Data
- Spring Security
- PostgreSQL

Annotations:

- @Bean
- @ControllerAdvice
- @EnableWebSecurity
- @Entity
- @GeneratedValue
- @GetMapping
- @Id
- @JsonIgnore
- @Modifying
- @PathVariable
- @PostMapping
- @Query
- @RequestMapping
- @RequestParam
- @RestController
- @Service
- @SpringBootApplication
- @Table
- @Transactional
- @Validated

Description:

- REST application that codes all urls into 7-digit alpha-numeric codes (like NfZdtT8) for security reasons.


- The application can be used by various sites. Each is provided with login and password. Further authorization sends 
  JWT to client that is sent back in requests in HEAD section.


- Only registered sites can request coding of their urls. Redirection to the corresponding url of a coded one might 
  be performed without registration (authentication).


- In addition, statistics could be viewed of all sites' urls and number of requests to them (the mapping has no 
  authorization specified for simplicity).
  
