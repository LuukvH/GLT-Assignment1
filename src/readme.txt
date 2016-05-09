Luuk van Hulten 0720248
Nanne Wielinga  0852473

All regex test cases are located in the main.java file.
It will print the regex used and result to the console.

However we did change the structure of the project to support usability for the parser.
Regular expressions are located in the regexTestFactory.
RegexTest will return a result object containing the matching information.
We used also used unit test to validate the output.

The parser is located in parser.java and requires lexer.java and two enumerates,
one indicating state and one containing all tokens.
The parser also implements results, when an error occure while parsing it output the line, col and last seen token.
When correctly parsed it will return the results calculated inside the pico program for each variable.
The pico test programs are located in seperated pico files