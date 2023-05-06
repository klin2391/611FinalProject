# CS611-Final Project
## Portfolio Management System
---------------------------------------------------------------------------
Kevin Lin
lin2391@bu.edu
U05482650

Wei-Tse Kao

Huy Phan

## Files
---------------------------------------------------------------------------

Grapher.java: This file is used to generate the graphs for the stock history

Main.java: This file is used to run the program

Manager.java: This class is the manager class. Used to manage the portfolio

Observer_Manager.java: This is the interface for observers who listen to manager changes

Observer_Stock.java: This is the interface for observers who listen to stock changes

Observer_User.java: This is the interface for observers who listen to user changes

Person.java: Abstract class used to represent a person. Used for user and manager

SQL.java: This class is used to connect to the database and interact with data

Stock.java: This class is used to represent a stock

TradeBehavior.java: Interface used to implement strategy pattern for buying and selling

TradeBehavior_Buy.java: Class used to implement strategy pattern for buying

TradeBehavior_Sell.java: Class used to implement strategy pattern for selling

User.java: This class is used to represent a user. User can buy and sell stocks as well as deposit and withdraw

User_Account.java: Interface for user account. Defines required methods for user account

Value.java: This class is used to represent value of stock. Contains current price and history

Window.java: This class is used to create a window for the GUI

Window_Apply.java: A panel following root. Used to apply to system

Window_BuySell.java: A panel used to buy or sell depending on strategy. Comes from Window_Trade

Window_EmailNotification.java: Window used to email a user of something happening

Window_Forgot.java: A panel used to remind a user of their password. Comes from Window_Login

Window_Funds.java: A panel used to deposit or withdraw funds. Comes from Window_User

Window_Login.java: A panel used to login to the system. Comes from Window root

Window_Manager.java: A panel used to manage the portfolio. Comes from Window login

Window_ManagerAddStock.java: A panel used to add a stock to the portfolio. Comes from Window_Manager

Window_ManagerRemoveStock.java: A panel used to remove a stock from the portfolio. Comes from Window_Manager

Window_ManagerApprove.java: A panel used to approve a user. Comes from Window_Manager

Window_ManagerProfitTable.java: A panel used to view the profit table to see each users real and unreal profit. Comes from Window_Manager

Window_UpdateStock.java: A panel used to update a stock price. Comes from Window_Manager

Window_ManagerUsers.java: A panel used to view all users. Comes from Window_Manager

Window_Root.java: A panel used to create the root window

Window_Settings: A window used to change account password

Window_Stock.java: A panel used to view a stock. Include price history

Window_Super.java: A panel used as home page for super users

Window_Trade.java: A panel used to see market and currently owned stocks

Window_User.java: A panel used to see user information

## Notes
---------------------------------------------------------------------------
1. The program is designed to be run through intelliJ
2. Program allows for multiple users to be logged in at once
3. Program allows for only one manager
4. Program allows live updates across windows using observer pattern
5. Program simulates an email notification system
6. Password recovery
7. Manager has the ability to approve/reject new applicants
8. Manager has the ability to see user profile
9. Manager has the ability to block users from trades and unblock them
10. Manager has the ability to update minimum profit to be a super user
11. Manager has teh ability to notify users of upgrade to super user
12. Manager has the ability to add/remove stocks from the market
13. Manager has the ability to update stock prices manually and randomly
14. Manager has the ability to see profit table
15. All people can change password
16. Customers can deposit/withdraw funds
17. Customers can view their own stocks with relevant information
18. Customers can view live market stocks
17. Customers can buy/sell stocks

## How to compile and run
---------------------------------------------------------------------------
1. Download latest SQLite JDBC Jar dependency file
2. [link](https://central.sonatype.com/artifact/org.xerial/sqlite-jdbc/3.41.2.1/overview)
3. Versions > Browse
4. Download `[sqlite-jdbc-3.41.2.1.jar](https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.41.2.1/sqlite-jdbc-3.41.2.1.jar)`
5. Put the .jar file into you project folder
6. File > Project Structure > Dependencies
7. Add the .jar file as a new dependency
8. Create the following tables with the commands:
9. create table Customers
   (
   id        integer           not null
   constraint id
   primary key autoincrement,
   firstName TEXT              not null,
   lastName  TEXT              not null,
   username  TEXT              not null,
   password  TEXT              not null,
   balance   integer REAL      not null,
   email     TEXT              not null,
   profit    integer default 0 not null
   );

10. create table Managers
   (
   id           integer               not null
   constraint id
   primary key autoincrement,
   email        TEXT                  not null,
   lastName     TEXT                  not null,
   username     TEXT                  not null,
   password     TEXT                  not null,
   minToBeSuper integer default 10000 not null
   );

11. create table OwnedStonks
    (
    Symbol        TEXT    not null,
    OwnerUsername TEXT    not null,
    PurchasePrice integer not null
    );

12. create table Stocks
    (
    id           integer           not null
    primary key autoincrement,
    name         TEXT              not null,
    symbol       TEXT              not null,
    available    integer default 1 not null,
    priceCurrent integer           not null,
    price1       integer,
    price2       integer,
    price3       integer,
    price4       integer,
    price5       integer,
    price6       integer,
    price7       integer,
    price8       integer,
    price9       integer
    );

13. create table Trades
    (
    stockID    integer not null
    constraint "stock id"
    references Stocks,
    customerID integer not null
    constraint "customer id"
    references Customers
    );

14. create table Blacklisted
    (
    username TEXT not null
    constraint username
    references Customers (username)
    );

15. create table EligibleSupers
    (
    username TEXT              not null
    constraint username
    references Customers (username),
    approved integer default 0 not null
    );

16. create table PendingCustomers
    (
    id        integer not null
    constraint id
    primary key autoincrement,
    firstName TEXT    not null,
    lastName  TEXT    not null,
    username  TEXT    not null,
    password  TEXT    not null,
    email     TEXT    not null
    );

17. Navigate to the directory "FinalProject" after unzipping the files
18. Change line 20 of SQL.java to the absolute path of the database file
19. Run through intelliJ
20. If above fails, Sorry :/ Try to run the following instructions:
   javac ./src/*.java -d bin
   java -cp ./bin Main

## Input/Output Example
---------------------------------------------------------------------------
Requires GUI interaction!