# Book Libarary

## Table of Contents
  - [Table of Contents](#table-of-contents)
  - [General Information](#general-information)
  - [Business Requirements](#business-requirements)
  - [Git flow](#git-flow)
  - [Architecture](#architecture)
  - [Database Model](#database-model)
  - [Software Technologies](#software-technologies)
  - [Setup](#setup)
  - [Screenshots](#screenshots)
  - [Acknowledgements](#acknowledgements)
  - [Contact](#contact)
  

---
## General Information
The idea of the project is to develop a web-based library management system. This is a project that manages and stores information about books, users, authors and borrows. The system helps both users and admins to keep track of all the books that are available in the library at the current time. It also stores information about borrowed and returned books.  This task if carried out manually will be tedious and includes chances of mistakes. These errors are avoided by allowing the system to keep track of information such as date of borrow and date to return the book.

---
## Business Requirements
All users of the system should have the ability to:
- Create a list with the name
- Add a book to a specific list
- Delete a book from a specific list
- Add some notes for each book.
- Search for a Book by name in the list.
- Search a list by name
- The user can see all available books grouped by some criteria like author,genre, etc.
- The user should be able to add a new book. Every new book has to have the following information:
    - Author
    - Genre
    - Title
    - ISBN
  
---
## Git flow
The main branch contains production-ready code that can be released. The main branch was created at the beginning of the project and was maintained throughout the development process. 

Many feature branches were used to enable parallel development on the same or on completely different functionalities of the project. The first step is to checkout from the main branch. Once the changes are made to the code, the developer makes what is known as a pull request, or a request to have other developers on the team do a code review to ensure that the local branch does not have any errors, and also that it will not cause any errors when merged into the main branch. Once a branch has been thoroughly reviewed, it can then be merged into the main branch.

---
## Architecture


---
## Database Model

---
## Software Technologies
List of all software technologies used during the development process:
- Java 11
- Spring Boot
- Hibernate
- DB integration(MySQL)
- Git
- Angular
- Postman
- JUnit 5
- Mockito
- Asana

---
## Setup

1. Clone this repo
2. Open the back-end project in IDE
3. Press the `run button`
4. Open the front-end project in IDE
5. Run `npm install`
6. Run `ng serve`
7. Navigate to `http://localhost:4200/`

---
## Screenshots

---
## Acknowledgements
Many thanks to [@Georgi Minkov](https://github.com/GeorgiMinkov) for his guidance and support throughout the whole process of development.

---
## Contact
Created by [@Michael Ivanov](https://github.com/MishoCode) and [@Maria Markova](https://github.com/mimimkv)
