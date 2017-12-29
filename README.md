**Simple Recruiting Backend Service using Event Sourcing and CQRS**

This microservice is divided into 3 parts :

1. **Command Services** : 
	1. Command Services of both offer and application save the events into the event 	store.
2. **Query Services** :
	1. Query Services of both offer and application query the database to fetch the results.
3. **Event Handler** : 
	1. Event Handlers of offer  and application listen to the events from the event store at a 	fixed rate of 1000 ms and write to the database.

**Notice** : **The event store and the database are contained in the same node**.

The microservice contains one main module: 

1. **recruitment-microservice** - 
	1. It runs on port of 8080.
	2. The main class to start the module is RecruitmentApplication.java.

The other two modules :
 
 **recruitment-api** : stores the dtos and events.
 
 **recruitment-model** : stores the entities.

**Event Types** :

1. **CreatedOfferEvent** : When a job offer is created. This event is fired and a notification is sent.

2. **ApplicationAppliedEvent** : When a user applies to a job offer. This event is fired and a notification is sent .

3. **ApplicationInvitedEvent** : When a user is invited for an interview. This event is fired and a notification is sent.

4. **ApplicationHiredEvent** : When a user is hired. This event is fired and a notification is sent.

5. **ApplicationRejectedEvent** : When a user is rejected. This event is fired and a notification is sent.

**Basic Authentication** :

Basic Authentication is supported. The credentials are 

**Username** : user

**Password** : abcd1234

**Database** :

The database used is In Memory Database(H2). 

**Endpoints Test** : 

You can test the application REST endpoints through Postman.
Please install Postman and login using the below credentials and find the relevant endpoints in the Recruitment Microservice :

**Username** : cheetah198816

**Password** : abcd1234

