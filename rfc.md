# Friends Toy

## Purpose
This is a simple toy app designed to increase my familiarity with scala. 
The basic purpose is to model friendships between people. 

## Description
For now all people will be held in a single "table" and will be self-joined in order to model relationships.
We are also avoiding using an actual database (for now), choosing to focus initially on increasing familiarity with scala.
The only initial piece of information we are holding onto about our "people" and "friends" is their names. For now we assume that names are unique.
Upon creation, each person will be assigned a UUID that will be used to identify them.

## Endpoints

All `500` responses will return with `{error: 'Internal Error'}`

- GET /people -> Returns a list of people
    - `200` `[{uuid: <UUID>, name: <NAME>}, {uuid: <UUID>, name: <NAME>}, ...]`

- GET /people/:name -> returns a person, including uuid
    - `200` `{uuid: <UUID>, name: <NAME>}`
    - `404` `{error: 'Person Does Not Exist'}`

- POST /people/:name -> creates a person and returns that object with a UUID
    - `201` `{uuid: <UUID>, name: <NAME>}`
    - `403` `{error: 'Person already exists'}`
  
- PUT /people/:uuid/delete -> marks a person and their friendships as removed
    - `204` Nothing will be returned on success
    - `404` `{error: 'Person cannot be found'}`

- GET /people/:uuid/friends -> returns a list of that person's friends
    - `200` `{uuid: <UUID>, name: <NAME>, friends: [{uuid: <UUID>, name: <NAME>}, ...]}`
    - `404` `{error: 'Person cannot be found'}`

- POST /people/:uuid/friends -> creates a friendship
    - `Body` `{uuid: <FRIEND UUID>}`
    - `201` `{uuid: <UUID>, name: <NAME>, friends: [{uuid: <UUID>, name: <NAME>}, ...]}`
    - `404` Either `{error: 'Person cannot be found'}` or `{error: 'Potential Friend does not exist'}`
    - `403` `{error: 'These people are already friends'}`

- PUT /people/:uuid/friends/:friend-uuid -> Remove friendship
    - `200` `{uuid: <UUID>, name: <NAME>, friends: [{uuid: <UUID>, name: <NAME>}, ...]}`
    - `403` Either `{error: 'Person does not exist'}` or `{error: 'Friendship does not exist'}`

## Libraries

- Http4s v0.17
- logback

## Extensions

- DB (postgres)
- Deployment Tools (Docker)
- More information about people (ex. age, birthdays, gender)
- Bulk add Friends
- Paginate the main get request
- Create people and friends at the same time