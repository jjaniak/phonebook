This is a learning project making part of Spring course forked from [here](https://github.com/tbarabanov/phonebook)

##### Task description
It is required to complete PhoneBook application. Three commands have to be implemented:

* ADD — add a contact
* REMOVE_PHONE — remove the phone
* SHOW — return the content of the phonebook

Console input example:
* ADD Ivan 555-10-01, 555-10-03
* REMOVE_PHONE 555-10-03
* SHOW

All parameters have valid values. There is no need to validate them.
All “names” have only one word like “Ivan”, or “Peter”.
Command names are written in uppercase, parameter values are separated by space

REMOVE_PHONE command must throw an exception if there is no such phone in the PhoneBook. 
The exception must be handled and reason must be printed out. 
If in the result of REMOVE_PHONE command execution the set of phones will be empty the record must be completely removed from the PhoneBook.

ApplicationConfig.java class must be implemented. 
If PhoneBook.main(...) receives no parameters ApplicationConfig.java must be used as a source of configuration application-config.xml otherwise. 
The application should work the same way no matter what configuration source is provided.
