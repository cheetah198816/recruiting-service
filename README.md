**Simple Recruiting Backend Service using Event Sourcing and CQRS**

This microservice is divided into 3 parts :

1. **Command Services** : 
	1. Command Services of both offer and application save the events into the event 	store.
2. **Query Services** :
	1. Query Services of both offer and application query the database to fetch the results.
3. **Event Handler** : 
	1. Event Handlers of offer  and application listen to the events from the event store at a 	fixed rate of 1000 ms and write to the database.

The microservice contains one main module : 

1. **recruitment-microservice** - 
	1. It runs on port of 8080.
	2. The main class to start the module is RecruitmentApplication.java.

**Database** :

The database used is In Memory Database(H2). 

**Endpoints Test** : 

You can test the application REST endpoints through Postman.
Please install Postman and login using the below credentials and find the relevant endpoints in the Recruitment Microservice :

**Username** : cheetah198816

**Password** : abcd1234

