A quick brainstorming session:

When you run, theres a login page with either login or create account

    Login:
        Login as user
            User needs state. Has name, password, actual balance, and projected balance. Maybe txt like in MH or SQL db
        Login as Comptroller
            Only 1 comptroller (Singleton at init)
    Create user:
        Creates account. Manually enter info. Makes sure no repeat. (Factory Pattern) Must be approved by comptroller
    
    Once Logged in:
        User:
            Add/Remove funds:
            Buy Sell Stocks:
        Comptroller:
            Add/Remove Stocks
            Approve/reject users
            See all users
            Notify special users (observer)
            Change Prices (select a stock, add new price)
                Update button? for all?

    Tradeables (buy and sell)

        Stock - Can use decorator and observer bc when update in Comptroller, all instances of stock should change
            Use composite interface because a user can have multiple of 1 stock, bought at different values
        Option- same as above. Maybe an interface? Or just implements interface
        Future - ""

        Stocks should track all past values and purchase value. Maybe can make this into a pretty graph