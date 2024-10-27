# Treatment Plan

### Guide to launch and use the application

### Prerequisites

* JDK 17
* IDE (I recommend IntelliJ IDE)
* Browser
* SQL DB (Optional. H2 is used by default)

### Steps to run

1. Clone the application from repository https://github.com/Ignatenko2207/treatment-plan
2. Open and build it in IDE (mvn clean install)
3. Execute method main in TreatmentPlanApplication

### Steps to use

1. Open browser and use link http://localhost:8080/swagger-ui/index.html to open swagger
2. Now you can create, update, get or delete entities
3. As a doctor you can create separate treatment for the patient or 
create treatment plan with several treatments within. Also, you can create tasks manually.
4. Tasks also are generated from treatment data - checks every 15 minutes. If we have a lot of treatments, it's better to use separate tables for users, tretment info etc. We set just ids of that references in the treatment in this case 
5. As a nurse you can get active tasks by user and time

### Other important information

1. Tests do not cover void or private methods. Tests do not cover repository without native SQL. At the same time I created tests to use Mock or Spring-boot tests (even for controllers)
2. Date format 'yyyy-MM-dd'
3. DateTime format 'yyyy-MM-dd HH:mm'

