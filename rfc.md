# Friends Toy

## Purpose
This is a simple toy app designed to increase my familiarity with scala. 
The basic purpose is to model friendships between people. 

## Description
For now all people will be held in a single "table" and will be self-joined, using a table that contains two user ids and a deleted flag in order to model relationships.
We are also avoiding using an actual database (for now), choosing to focus initially on increasing familiarity with scala.
The only initial piece of information we are holding onto about our "people" and "friends" is their names. For now we assume that names are unique.
Upon creation, each person will be assigned a UUID that will be used to identify them.

## Entities
All entities should include timestamps for `created_at` and `last_modified`

### People
- `id`: pkey
- `uuid`: unique id generated at creation (string)
- `name`: the person's name (string) `UNIQUE` `REQUIRED`
- `deleted`: has this person been removed from the db (boolean) `DEFAULT FALSE`

### Friendships
- `id`: pkey
- `friend_a`: the `id` of a single person `REQUIRED` 
- `friend_b`: the `id` of a single person `REQUIRED`
- `deleted`: has this friendship been revoked (boolean) `DEFAULT FALSE`

## Endpoints

All unsuccessful responses  will return `500`, all other responses will return `200`. All requests will be implemented as `POST`.

### /get_people 
Returns a list of people
    
#### Success Response (Without a body)
```json
{
    "status": {
        "messages": [
            "People successfully found"
        ],
        "success": true
    },
    "people": [
      {"uuid": "<UUID>", "name": "<NAME>"},
      {"uuid": "<UUID>", "name": "<NAME>"},
      ...
    ]
}
```

### /get_person 
returns a person, including uuid

#### BODY
```json
{
  "name": "The person's name"
}
```

#### Success Response
```json
{
  "status": {
    "messages": [
      "Person was retrieved successfully"
    ],
    "success": true
  },
  "person": {
    "uuid": "<UUID>",
    "name": "<NAME>"
  }
}
```

#### Not Found Response
```json
{
  "status": {
    "messages": [
      "No person with that name could be found"
    ],
    "success": false
  }
}
```

### /create_person 
creates a person and returns that object with a UUID

#### BODY
```json
{
  "name": "The name of the person to create"
}
```

#### Success Response
```json
{
  "status": {
    "messages": [
      "Entry for person was successfully created"
    ],
    "success": true
  },
  "person": {
    "uuid": "<UUID>",
    "name": "<NAME>"
  }
}
```

#### Error Response (Already Exists)
```json
{
  "status": {
    "messages": [
      "Cannot create person, someone with that name already exists."
    ],
    "success": false
  }
}
```

### /delete_person 
marks a person and their friendships as removed

#### BODY
```json
{
  "uuid": "The person who you are deleting's uuid"
}
```
#### Successful Response
```json
{
  "status": {
    "messages": [
      "Person successfully removed"
    ],
    "success": true
  }
}
```
#### Error Response (Person doesn't exist)
```json
{
  "status": {
    "messages": [
      "No person with that id could be found"
    ],
    "success": false
  }
}
```

#### /get_friends 
returns a list of that person's friends

#### BODY
```json
  {
    "uuid": "The person whose friends you are querying's UUID"
  }
```
    
#### Successful Response
```json
{
  "status": {
    "messages": [
      "Person successfully found"
    ],
    "success": true
  },
  "person": {
    "uuid": "<UUID>",
    "name": "<NAME>"
  },
  "friends": [
    {
      "uuid": "<UUID>",
      "name": "<NAME>"
    },
    {
      "uuid": "<UUID>",
      "name": "<NAME>"
    },
    ...
  ]
}
```

#### Error Response (person cannot be found)
```json
{
  "status": {
    "messages": [
      "Person cannot be found"
    ],
    "success": false
  }
}
```

### /create_friendship 
creates a friendship

#### BODY
```json
{
  "uuids": ["first UUID", "second UUID"]
}
```

#### Successful Response
```json
{
  "status": {
    "messages": [
      "Friendship successfully created"
    ],
    "success": true
  },
  "person": {
    "uuid": "<UUID>",
    "name": "<NAME>"
  },
  "friends": [
    {
      "uuid": "<UUID>",
      "name": "<NAME>"
    }
  ]
}
```

#### Error Response (Person not found)
```json
{
  "status": {
    "messages": [
      "Person with UUID <UUID> could not be found"
    ],
    "success": false
  }
}
```

#### Error Response (Already friends)
```json
{
  "status": {
    "messages": [
      "These people are already friends"
    ],
    "success": false
  }
}
```

### /remove_friendship

#### BODY
```json
{
  "uuids": ["<UUID_1>", "<UUID_2>"]
}
```

#### Success Response
```json
{
  "status": {
    "messages": [
      "Friendship successfully removed"
    ],
    "success": true
  }
}
```

#### Error Response (a UUID doesn't exist)
```json
{
  "status": {
    "messages": [
      "Friendship could not be removed, <UUID> doesn't exist"
    ],
    "success": false
  }
}
```

#### Error Response (these people aren't friends)
```json
{
  "status": {
    "messages": [
      "Friendship could not be removed, <UUID_1> and <UUID_2> are not friends"
    ],
    "success": false
  }
}
```

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
