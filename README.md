# Virtual Bank RESTful API
This is a prototype for a virtual, or online, bank's REST API I created as an exercise to cement SpringBoot concepts. I thought of the idea after finishing a project for one of my college courses of the same name. Essentially, this project takes the idea of the college project but transforms it into an API. It performs all standard bank transactions such as balance inquiry, depositing, withdrawing, opening or closing, creating a new Account or deleting an existing one. There are more transactions to be added in the future such as clearing a check, producing transaction history, sending money to other accounts etc. I also aim to add a frontend componenet using Angular or React in the future.

### Technologies Used:
* Springboot
* Postman
* H2 console for mySQL

### Concepts
* Spring mvc
* RESTful API

## Models
Models are basically POJO's(Plain old java objects) that are used to represent data. The models I used are detailed below. 

***SessionTicket:***
```java
    String firstName;
    String lastName;
    int accBalance; - 
    Account account; - 
    boolean flag;
```

Methods: Standard getters/setters, parameterized constructor and a toString method.

### Important info about the SessionTicket:
Each session must first be authorized through a login.  A login will create a SessionTicket object to be stored inside the HttpSession variable. This SessionTicket object will be created inside a function named login within the LoginService class.
This ticket MUST be checked before any operation inside the AccountOperations class to make sure that the person accessing it is authorized to access the information.  This allows us to avoid repeat accesses to our database to retrieve account numbers and authorize a transaction for an account.

***Account:***

```java
  int accountNum;  
  String accounttype;
  String accountStatus;
  int accountBalance;
```
Methods:Standard getters/setters, parameterized constructor and toString method.

***PersonalInfo:***
```java
    private int accountNum;    
    private String accType;
    private String firstName;
    private String lastName;
    private String SSN;
    private String streetName;
    private String city;
    private String state;
    private String zipcode;
```
This model will hold the customers personal info

Methods:
Standard getters/setters, parameterized constructor and toString method.

## DAO/DAL(Data Access Object/Layer)
A data access layer holds all our database access within a springboot application. This layer is separate from a Service layer, which will be later detailed. The purpose of this separation is to keep business logic and data logic separate. This is done so business logic does not impede data logic and vice versa. It allows for better scalability. Say in the future we want to change business logic, using this philosophy we only need to make changes to one layer rather than change up both data logic AND business logic.

***AccountDB:***
Holds the Account class as an Entity
    
    Methods:
    
     findById(int accNum) - takes int holding account number to find the appropriate account in the Account table
     findByAccountBalance(int accNum) - finds account balance of matching account number. Used for SessionTicket
     changeAccBalance(int accNum) - Used to change Account Balance for deposit and withdraw transactions
     setAccStatus(int accNum) - changes account status from open/close to opposing status. 
     deleteAcc(int accNum) - deletes account with corresponding account number

***PersonalInfoDB:***
Holds PersonalInfo class as an entity
```
Methods:

findByfirstName(int accNum) - finds first name of the account with given int account number
findBylastName(int accNum) - finds last name of the account with given int account number
```

## SERVICE layer
The service layer of a springboot application holds the business logic of the application. 

AccountOperations:
Holds AccountInfoDB and PersonalInfoDB object to be used for data logic.

Methods:

 ***Deposit***
   ```
   Allows user to deposit cash into their account. Does not allow a negative value to be deposited
    
   ```
  ***Withdrawal***
   ```
   Allows user to withdraw cash. User can only withdraw appropriate amounts. Appropriate amounts means only cash that the user has available, no negative values, no over draws.
  
   ``` 
  ***Open Account***
    
    Allows user to define account as currently open which allows transactions and inquiries
  
  ***Close Account***
    
    Allows user to define account as currently closed which disallows transactions and inquiries
    
 ***Create an Account***
 
    Allows user to create an account.
    Requirements for account creation:
    First Name
    Last Name
    Social Security Number
    Address
    Phone Number
    Type of Account they want to open
    ***Does not require a SessionTicket true flag***
    
  ***Delete an existing Account***
    
    Allows user to delete their existing account. Returns users transaction history before the account is deleted.
  

### Extra private methods to replace hard to read code:

  ***getRandomNumber***
  
    Generates a 6 digit random number to use for new account creation

  ***validateWithdrawal***
  
    Checks the withdrawal sum to see if it is a valid sum. Checks if it is non-negative and not greater than the current account balance
 
 ***isAccOpen***
  
    Checks to see if the current account that is being used is open or closed.
    If open transactions are authorized to take place
    If closed transactions are not authorized to take place.
    
  ***getTicket***

    Returns a SessionTicket Object after it retrieves it from a HttpSession Object. Mainly used to make code more readable.

***LoginService:***
Holds AccountInfoDB and PersonalInfoDB objects to be used for data logic.

    Login:
    Takes - AccNum
    Checks AccountInfoDB for the accnum provided to it
    If it finds an account number it stores the first name, last name, current balance, account number,account object, and flag into the session ticket.
    If no account with AccNum was found then it instantiates a sessionticket with no first or last name, -1 for account balance and account num, and a false flag.


## REST Controllers
Controllers which will be used to route our requests to proper Service method functions.

***LoginController:***
Supports REST method: GET

    GET: login
      Login function which routes request to LoginService method login
      Returns a SessionTicket object. Sets SessionTicket object within HttpSession object.

***AccountController:***
Supports REST method: GET, POST, PUT , DELETE

    GET: balanceInquiry 
      Returns balance of account
    PUT: makeDeposit
      Returns new balance of account after making deposit. Also replaces account balance in SessionTicket with new account balance.
    PUT: makeWithdrawal 
      Returns new balance of account after making withdrawal. Also replaces account balance in SessionTicket with new account balance.
    PUT: changeAccStatus
      Changes Account status from open/close to opposing status
    POST: createAcc
      Creates a new Account and adds to Account Database and PersonalInfo database after taking necessary information
    DELETE: deleteAcc
      Deletes account details from database removes all data from the SessionTicket object

## Database design:

***Personal Information Table:***

    Account Number(Primary/Foreign key)
    First Name
    Last Name
    Social Security 
    Street Name
    State 
    Zipcode
   
***Account Table:***

    Account Number(Primary/Foreign key)
    Balance
    Status(Open/Close)
    

