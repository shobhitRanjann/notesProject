
# Notes Project

Project where you can 

* POST /api/auth/signup: create a new user account.
* POST /api/auth/login: log in to an existing user account and receive an access token.
Note Endpoints

* GET /api/notes: get a list of all notes for the authenticated user.
* GET /api/notes/ get a note by ID for the authenticated user.
* POST /api/notes: create a new note for the authenticated user.
* PUT /api/notes/ update an existing note by ID for the authenticated user.
* DELETE /api/notes/ delete a note by ID for the authenticated user.
* POST /api/notes/:id/share: share a note with another user for the authenticated user.
* GET /api/search?q=:query: search for notes based on keywords for the authenticated user.


# Setup Steps

* Use any IDE to run spring boot project, I would reccomend intelliJIdea(https://www.jetbrains.com/idea/download/).
* Download mysql, since I have used mysql drivers in project.
* Import the project one by one, Spring Boot will automatically download all the dependency it needs, since we provides all the details in pom.xml.
* Once everything is done, you can use postman to test the API.

## Important!

Frontend for this project is -> [here](https://github.com/shobhitRanjann/onlineStore-springBoot)
it is made in reactJs. All the steps to make it run are available there.

Happy Coding 😊
## API Reference

#### create a new user account

```http
  POST /api/auth/signup
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `Body` | `JSON` | **Required**. firstName, lastName, userName, password |

#### log in to an existing user account and receive an access token

```http
  POST /api/auth/login
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Body`      | `JSON` | **Required**. email, password |

### Note Endpoints
#### get a list of all notes for the authenticated user

```http
  GET /api/notes
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Header` | `Authorization` | **Required**. Header Your API key |

#### get a note by ID for the authenticated user

```http
  GET /api/notes/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Header` | `Authorization` | **Required**. Header Your API key |

#### create a new note for the authenticated user

```http
  POST /api/notes
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Header` | `Authorization` | **Required**. Header Your API key |
| `BODY` | `JSON` | **Required**. title, description |

#### update an existing note by ID for the authenticated user

```http
  PUT /api/notes/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Header` | `Authorization` | **Required**. Header Your API key |
| `BODY` | `JSON` | **Required**. id,title, description,date |

##### You can change the requred fields if needed

#### delete a note by ID for the authenticated user

```http
  DELETE /api/notes/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Header` | `Authorization` | **Required**. Header Your API key |


#### share a note with another user for the authenticated user.

```http
  POST /api/notes/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Header` | `Authorization` | **Required**. Header Your API key |

#### search for notes based on keywords for the authenticated user.

```http
  GET /api/search?q=query
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Header` | `Authorization` | **Required**. Header Your API key |

##### here search is based on all fields.
