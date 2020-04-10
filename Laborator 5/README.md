# FII JAVA LAB5
## Nistor Marian-Sergiu

* Compulsory

Create an object-oriented model of the problem. You should have at least the following classes: Catalog, Document. Consider creating a class responsible with external operations regarding a catalog.
Implement the following methods:
save: saves the catalog to an external file, using object serialization;
load: loads the catalog from an external file.
view: opens a document using the native operating system application (see the Desktop class);

* Optional

Implement the save and load methods.
Create a shell that allows reading commands from the keyboard, together with their arguments and implement the commands load, list, view.
Represent the commands using classes instead of methods (create the classes LoadCommand, ListCommand, etc.). Use an interface or an abstract class in order to desribe a generic command.
The application will signal invalid data (duplicate names, invalid paths or URLs, etc.) or invalid commands using custom exceptions.
The final form of the application will be an executable JAR archive. Identify the generated archive and launch the application from the console, using the JAR.