# CS611-Final Project
## Portfolio Management System
Design Documentation
---------------------------------------------------------------------------
Kevin Lin
lin2391@bu.edu
U05482650

Wei-Tse Kao

Huy Phan

Classes Overview
---------------------------------------------------------------------------
Our project models a portfolio management system. There are Users, Stocks, and
a Manager. Stocks have a Value which includes the current prices as well as a 
history of the prices. The manager interacts with the stocks and the users while
users can buy and sell stocks. We also created a SQL class that allows us to interact
with a SQLite database that saves our state. The remaining classes are mainly used for
the GUI.

## Classes:

### Real Objects:
- Manager: This class represents the admin that can do basically everything. 
    Based on the specification, the manager can add stocks, remove stocks, update
    stocks and view users. They also have the ability to blacklist users, which prevents
    them from interacting from the system. The manager also is able to approve or deny
    both new users and users who qualify to become a super user by meeting a profit 
    threshold set by the manager. The manager inherits from the Person abstract class and
    implements the Observer_User interface. This interface allows the manager to see 
    live updates from the User, which then allows the manager to update their screen
    in real time.
- Person: This is an abstract class that specifies that everyone has a username, a password,
    and an email. This class also provides default methods to get and set these attributes.
- Stock: This class represents a stock. It has a name, a symbol, and a value. These are 
    created by the manager and the value is updated by the manager. The stocks can then be
    bought and sold by the users.
- User: This class represents a user. They can also deposit and withdraw funds. They can buy 
    and sell stocks. If they make enough profit, they can become a super user. The user inherits from
    the Person abstract class and implements the Observer_Stock interface. This interface
    allows the user to see live updates from the Stock when changed by the manager, which
    then allows the user to update their screen in real time. They also implement the User_Account
    interface which simply states some of the required methods for a user account.
- Value: This class represents the value of a stock. It has a current price and a history of prices.
    The history is used to generate a graph of the stock's price over time. The value is updated by
    the manager. As the current price is changed, the history is updated. The history is tracked
    as an ArrayList of Doubles.

### GUI Objects:
- Grapher: This class is used to create a graph of the stock's price over time. It is used by the
    Stock class to create a graph of the stock's price over time. This graph extends a JPanel so that
    it can be added to any JPanel or JFrame. This class only graphs the 10 most recent prices as those
    are the most relevant and in the database, we cannot save an ArrayList. 
- Window: The window is simply a JFrame that we create to a certain size and then can update with a specific
    JPanel. This allows us to keep one window open and just update the JPanel to show different screens and titles.
- Window_Apply: This class is a JPanel that is used to apply to become a user. It also has a button to submit
    the application. This class extends JPanel so that it can be added to any JPanel or JFrame. This class
    occurs after the root page. When applicants submit, they are added to the database as a pending user.
- Window_BuySell: This class is a JPanel that is used to buy and sell stocks. It has a dropdown menu to select
    the stock, a text field to enter the amount, and a button to submit the transaction. This class extends
    JPanel so that it can be added to any JPanel or JFrame. This class occurs after the Trade page. This class
    uses the strategy design pattern because the buy and sell pages are very similar. THe only difference is that
    one buys from a global market, while the other sells from a personal portfolio. THerefore, we use TradeBehavior
    interface that allows someone to either TradeBehavior_Buy or TradeBehavior_Sell. This allows us to reuse the
    code for the buy and sell pages. When a user buys/sells, the user database is updated in OwnedStocks and the users
    balance and profit is updated in the Customers database.
- Window_EmailNotification: This is simply a window used to simulate an email notification.
- Window_Forgot: This class is a JPanel that is used to reset a password. It has a text field to enter the email
    and a button to submit the request. This class extends JPanel so that it can be added to any JPanel or JFrame.
    This class occurs after the login page. When a user submits, the database is checked to see if the email exists
    and the associated password.
- Window_Funds: This class is a JPanel that allows a user to deposit or withdraw funds. It has a text field to
    enter the amount and a button to submit the transaction. This class extends JPanel so that it can be added
    to any JPanel or JFrame. This class occurs after the main user page. Using this window changes the Customers
    table and updates the Users balance.
- Window_Login: This class is a JPanel that allows a user to login. It has a text field to enter the username,
    a text field to enter the password, and a button to submit the login. This class extends JPanel so that it
    can be added to any JPanel or JFrame. This class occurs after the root page.  People using this page are checked
    in the database to see if they are a user with the proper credentials. If they are, they are taken to the
    appropriate page.
- Window_Manager: This class is a JPanel that allows the manager to interact with the system. It has buttons
    to add stocks, remove stocks, update stocks, view users, and blacklist users. This class extends JPanel so
    that it can be added to any JPanel or JFrame. This class occurs after the login page. When the manager
    interacts with the system, the database is updated accordingly and the observers are notified.
- Window_ManagerAddStock: This class is also a JPanel that allows a user to add a stock. When added, this stock
    is added to the database and the observers are notified.
- Window_ManagerApprove: This class is also a JPanel that allows a manager to approve or deny a user. When
    approved, the user is added to the database and the observers are notified. When denied, the user is
    removed from the database and the observers are notified.
- Window_ManagerProfitTable: This is a window that allows the manager to view each users profit real and unreal.
    As the manager view the table and a user makes a profit, this table is updated in real time.
- Window_ManagerRemoveStock: This class is also a JPanel that allows a manager to remove a stock. When removed,
    this stock is removed from the database and the observers are notified.
- Window_ManagerUpdateStock: This class is also a JPanel that allows a manager to update a stock. When updated,
    this stock is updated in the database and the observers are notified.
- Window_ManagerUsers: This class is also a JPanel that allows a manager to view all users. When a user is
    selected, the manager can choose to blacklist the user. When blacklisted, the user is added to the blacklist
    table and prevented from interacting. The manager can also choose to view the users portfolio. When selected,
    the manager is able to see live updates to the user page but without the ability to interact.
- Window_Root: This is the home page. This window will generate 1 of 2 windows, one for apply and one for login.
- Window_Settings: This is also a window that allows a person to change their password and update in the db.
- Window_Stock: This is also a window that shows a stocks relevant information such as how many owned, profit, and
    a graph of the stock's price over time. This window is updated in real time as the stock's price changes.
- Window_Trade: This extends a JPanel and allows a user to buy and sell stocks. This window is updated in real
    time as the stock's price changes.
- Windoww_User: This is the main user page. This window will generate 1 of 3 windows, one for funds, one for trade,
    and one for settings. This window is updated in real time as the stock's price changes.
### SQL:
#### Tables:
- Customers: This table stores all of the users information. This includes their id, name, username, password, email, balance,
    and profit. This table is used to login and to update the users balance and profit.
- EligibleSupers: This table stores all of the users that are eligible to become a super user. This table is used to
    determine if a user is a super or not. Includes username and if they are approved. If they are in the table,
    they are eligible and if in the table and approved, then they're super.
- Managers: This table stores the information for the manager. This includes email, lastName, username, password, and the minimum
    balance required to be a super user. There is only 1 manager and this table is used to login.
- OwnedStocks: This table stores all of the stocks that a user owns. This includes the symbol, ownerUsername, and purchasePrice.
    Any time a user buys or sells a stock, this table is updated. Each time a stock is added, we adjust the purchase price to 
    reflect the average purchase price of all of the stock owned by the user so that when we sell, we can calculate the average
    profit. Therefore, there are multiple similar entries in the table, so when we delete, we delete only one from the table.
- PendingCustomers: This table stores all of the users that are pending approval. This table is used to determine if a user
    is pending approval. Includes all fields of a Customer but the profit and balance. If they are in the table, they are
    pending approval. Once they are approved, they are removed and added to Customers table.
- Stocks: This table stores all of the stocks on the market. An entry has a name, symbol, whether it is available,
    the current price, and the price history for the last 10 days. This is implemented because we cannot add an array
    list to the table. 

### Interfaces and Helper Classes:
- Observer_Manager: This is an interface that specifies the methods that an observer must implement that is observing
    the manager. An observer must first register to the manager then will be updated when the manager changes. This is implemented
    by some manager windows.
- Observer_Stock: This is an interface that specifies the methods that an observer must implement that is observing
    a stock. An observer must first register to the manager then will be updated when the stock changes. This is implemented
    by users and windows.
- Observer_User: This is an interface that specifies the methods that an observer must implement that is observing
    a user. An observer must first register to the user then will be updated when the user changes. This is implemented
    by the manager and assorted windows of the user.
- TradeBehavior: This is an interface that specifies the methods that a trade behavior must implement. This is implemented
    by the buy and sell classes. Enables Strategy Pattern
- TradeBehavior_Buy: This is a class that implements TradeBehavior. This class is used to buy a stock. Enables Strategy Pattern
- TradeBehavior_Sell: This is a class that implements TradeBehavior. This class is used to sell a stock. Enables Strategy Pattern
- User_Account: This is an interface that specifies some of the required methods for a user account.
  This includes depositing and withdrawing funds, buying and selling stocks, and getting the
  current value of the portfolio.

## Class Structure

We designed our class structure to be as modular as possible. We have a class for each window and each window
is a JPanel. This allows us to easily add and remove windows on the same frame. We first mapped out how we wanted the program
to look and then designed the classes around that. As we were programming to an event driven model, a large
majority of our code comes from the windows. Therefore, we designed the windows to be as modular as possible so
that they can be reused in different scenarios. For example, we add a Window_BuySell class that implements
the strategy pattern so that we can reuse the same window for buying and selling. We also designed the windows
to be observers on the Manager, User, and or Stocks so that they update in real time. We also designed the 
manager to be a singleton because there is only one manager. Additionally, only the sql class can modify
the database which centralized our access. Something that we would add if more time is creating a window
factory class so that we can create windows more easily. 

## Scalability and Extendibility and Benfit

Scalability:
The design is scalable because it is easy to continue to add stocks and users. The entries
are just added to the database and the program will automatically update. This is done through 
the use of the observer pattern. The program will automatically update the windows when the
database is updated. The program is also scalable because it is easy to add new windows. All
you need to do is create a new class that inherits from JPanel and we can add them in the flow
of the program.

Extendibility:
The design is extendible because we can add new operations to the trade behavior
as long as they implement an execute and a getName method. So if we were to add a 
new type of stock such as options, we can easily add a new class that executes the 
trade behavior for it.

Benefit:
By designing the program focusing on the user interaction, we are able to create a system
that is coherent and has start and end points for the user. This allows us to model the real
world closer and gives us the freedom to change and add features as necessary. The sql database
is very critical to our design because it saves our state and data and allows us to generate 
the required objects quickly.