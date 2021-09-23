#KTOR REST API FOR QUOTE
This is simple REST API using ktor framework, demonstration of Project Structure,
Code Quality, Architecture Pattern.

## Folder Structure
    ...
    ├── resources
    │   ├── db/migration    #Contain migration tables (Using Flydb)
    │   └── application.conf
    └── src
        ├── authorization   
        ├── data
        │    ├── models
        │    │    ├── dto
        │    │    └── entities
        │    ├── repository
        │    └── services
        ├── database
        │    ├── dao
        │    └── impl
        ├── errors
        ├── routes
        ├── utils
        └── vo
    ...

## REST API
 - Login
 - Register
 - Users (CRUD)
 - Quotes (CRUD)

## Docs

### Login

     POST Login / {{base_url}}/api/v1/login 
---
    Body:   { 
                "email" : "xyz@email",
                "password" : "123456"
            }

### Register

     POST Register / {{base_url}}/api/v1/register 
---
    Body:   { 
                "name" : "Syed",
                "email" : "xyz@email",
                "password" : "123456"
            }

### Users

     GET Users / {{base_url}}/api/v1/users 

     Header: Authorization bearer {token}

### User

     GET Users / {{base_url}}/api/v1/users/{id} 

     Header: Authorization bearer {token}

### Add User

     POST Users / {{base_url}}/api/v1/users 
---
    Body:   { 
                "name" : "Syed",
                "email" : "xyz@email",
                "password" : "123456"
            }

    Header: Authorization bearer {token}

### Update User

     PUT Users / {{base_url}}/api/v1/users/{id} 
---
    Body:   { 
                "name" : "Syed",
            }

    Header: Authorization bearer {token}

### Delete User

     DELETE Users / {{base_url}}/api/v1/users/{id} 

     Header: Authorization bearer {token}

## Quotes

     GET Quote / {{base_url}}/api/v1/quotes

### Quote

     GET Quote / {{base_url}}/api/v1/quotes/{id}

### Add Quote

     POST Quote / {{base_url}}/api/v1/quotes 
---
    Body:   { 
                "quote" : "Talk is cheap, show me the code",
                "author" : "Linus Torvalds"
            }

    Header: Authorization bearer {token}

### Update Quote

     PUT Quote / {{base_url}}/api/v1/quotes/{id} 
---
    Body:   { 
                "quote" : "Updated quote",
            }

    Header: Authorization bearer {token}

### Delete Quote

     DELETE Quote / {{base_url}}/api/v1/quotes/{id} 

     Header: Authorization bearer {token}