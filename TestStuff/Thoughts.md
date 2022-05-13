### Thougths 
I think that the program should have a profile based system.
Maybe it is too late to create something like this and I shold complete the original idea first?
I'll complete the original idea first :)
- Markdown thing here!

### Original Idea
1. The program is designed for a single user
2. That user enters a the **username**, **password** and **masterpassword** in order to add a new account to the
database.
3. The masterpassword must be validated before the record can be added to file.

### Inspiration
Use a seperate table to store user authentication for each profile. This will allow multiple users in a "clever" way

Schemas to be used:
## User Profile Table: 
CREATE TABLE USERPROFILES(
    ProfileName char (128),
    MasterPassword char (128);
)
## Notes on the userprofile table:
- This table will use its __rowid__ as the foreign key for the accounts table

## Accounts Table
CREATE TABLE ACCOUNTS(
    UserName char(128),
    Password char(128),
    AccountID integer; //This is the *foreign key* to map the appropriate user profile to the account
)