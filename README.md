# **REST API example application**

Our conference application REST API was designed with versatility in mind. Ideally, its flexibility should allow for usage in a variety of business contexts that require the management of events and the attendants of those events (professional conferences, concerts, etc.)

## **Install**

### **API**

Just build and run the application in your preferred IDE

### Database

Make sure you have Docker installed as well as one the latest Postgres images on a local container. Run an instance of a Postgres database by inputting the following in the command line:

`docker run --name conference -e POSTGRES_PASSWORD=root -d -p 5433:5432 postgres`

## **Usage**

The preferred way of testing the requests and application flow is by accessing the Swagger UI at the following link:

http://localhost:8081/swagger-ui.html#

## **Resources**

Below is an exhaustive list of CRUD requests for each application resource and a sample JSON input required to test functionality:


### **Conference**
**GET /conferences/ - Get all stored conferences**

Input: N/A

JSON: N/A

**POST /conferences/ - create a conference**

Input:

JSON: 
{
"days": [],
"description": "string",
"location": "string",
"participants": [],
"speakers": [],
"theme": "string"
}

**GET /conferences/{id} - Get a conference by id**

Input: 
conference UUID

JSON: N/A

**PUT /conferences/{id} - Rewrite a conference by id**

Input:
conference UUID

JSON:
{
"days": [],
"description": "string new",
"location": "string new",
"participants": [],
"speakers": [],
"theme": "string new"
}

**DELETE /conferences/{id} - Delete a conference by id**

Input:
conference UUID

JSON: N/A

### **Day**

**GET /days/ - Get all stored days**

Input: N/A

JSON: N/A

**GET /days/{id} - Get a day by id**

Input: day id

JSON: N/A

**POST /days/add-day - Create a day**

Input: N/A

JSON:
{}

**PUT /days/{id} - Rewrite a day by id**

Input: day id

JSON:
{}

**DELETE /days/{id} - Delete a day by id**

Input: day id

JSON: N/A

**GET /days/by-date - Get a day by date**

Input: day id

JSON: N/A

### **Participants**

**GET /conferences/{conference-id}/participants - Get all stored participants**

Input: conference id

JSON: N/A

**POST /conferences/{conference-id}/participants - Post a participant**

Input: conference id

JSON:
{
"email": "vluta@hotmail.com",
"firstName": "string",
"lastName": "string",
"organiser": true,
"password": "string",
"phoneNumber": "0730257597",
"speaker": true,
"title": "string",
"username": "string"
}

**GET /participants/{participant-id} - Get a participant by id**

Input: participant id

JSON: N/A

**PUT /participants/{participant-id} - Rewrite a participant by id**

Input: participant id

JSON:
{
"email": "vluta@hotmail.com",
"firstName": "string",
"lastName": "string",
"organiser": true,
"password": "string",
"phoneNumber": "0730257597",
"speaker": true,
"title": "string",
"username": "string"
}

**DELETE /participants/{participant-id} - Delete a participant by id**

Input: participant id

JSON: N/A

**PATCH /participants/{participant-id} - Modify a participant by id**

Input: participant id

JSON: to include any participant field that you want modified

### **Session**

**GET /sessions - Get all stored sessions**

Input: N/A

JSON: N/A

**GET /sessions/{session-id} - Get a session by id**

Input: session id

JSON: N/A

**PUT /sessions/{session-id} - Rewrite a session by id**

Input: session id

JSON: {}

**DELETE /sessions/{session-id} - Delete a session by id**

Input: session id

JSON: N/A

**POST /speakers/{speaker-id}/sessions/{session-id} - Add a speaker to session**

Input: 
speaker id;
session id

JSON: N/A

**DELETE /speakers/{speaker-id}/sessions/{session-id} - Delete a speaker to session**

Input:
speaker id;
session id

JSON: N/A

**POST /tracks/{track-id}/sessions - Create a session**

Input:
track id

JSON: {}

### **Session Types**

**GET /session_types - Get all stored session types**

Input: N/A

JSON: N/A

**POST /session_types - Post a session type**

Input: N/A

JSON: {}

**GET /session_types/{session-type-id} - Get a session type by id**

Input: session type id

JSON: N/A

**PUT /session_types/{session-type-id} - Rewrite a session type by id**

Input: session type id

JSON: {}

**DELETE /session_types/{session-type-id} - Delete a session type**

Input: session type id

JSON: N/A

### **Speakers**

**GET /conferences/{conference-id}/speakers - Get all speakers belonging to a conference**

Input: conference id

JSON: N/A

**POST /conferences/{conference-id}/speakers - Create a speaker for a certain conference**

Input: conference id

JSON:
{
    "biography": "string",
    "company": "string",
    "githubAcc": "string",
    "linkedinAcc": "string",
    "participantCreationDTO": {
    "email": "string2@hotmail.com",
    "firstName": "string",
    "lastName": "string",
    "organiser": true,
    "password": "string",
    "phoneNumber": "073025790",
    "speaker": true,
    "title": "string",
    "username": "string2"
    },
    "twitterAcc": "string"
}

**GET /speakers/{speaker-id} - Get a speaker by id**

Input: speaker id

JSON: N/A

**PUT /speakers/{speaker-id} - Rewrite a speaker by id**

Input: speaker id

JSON:
{
    "biography": "string new",
    "company": "string new",
    "githubAcc": "string new",
    "linkedinAcc": "string new",
    "twitterAcc": "string new"
}

**DELETE /speakers/{speaker-id} - Delete a speaker by id**

Input: speaker id

JSON: N/A

**PATCH /speakers/{speaker-id} - Modify a speaker by id**

Input: speaker id

JSON: to include any participant field that you want modified

### **Ticket**

**GET /tickets - Get all tickets**

Input: N/A

JSON: N/A

**POST /tickets - Create a ticket**

Input: N/A

JSON: {}

**GET /tickets/{id} - Get a ticket by id**

Input: ticket id

JSON: N/A

### **Track**

**GET /tracks - Get all tracks**

Input: N/A

JSON: N/A

**GET /tracks/{id} - Get a track by id**

Input: track id

JSON: N/A

**PUT /tracks/{id} - Rewrite a track by id**

Input: track id

JSON: {}

**DELETE /tracks/{id} - Delete a track by id**

Input: track id

JSON: {}

**POST /tracks/add-tracks - Create a track**

Input: N/A

JSON: {}