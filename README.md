# Potion Mod
temporary name

aims to add turing complete potions to minecraft
I thhink with increment this is turing complete

Using the brewing stand, one can brew so-called primitive potions.
These have a low concentration of the elements.
While the vanilla potions are primitive potions, too,
there are other ingredients which might contain multiple different elements and are randomized between each world
(Mechanic inspired by the between lands).
There will probably be the following essentia:
 - light
 - electricity
 - heat / fire
 - coldness / ice
 - water
 - health
 - impermanence
 - time
 - motion
 - state
 - difference / equivalence / logic
 - messaging
 - amount
 - void
 - language

Most these elements can be combined to make other operators:
 - Logic + State = Select
 - Logic + Electricity = Evaluation
 - Logic + Void = Escaping
 - Amount + Coldness = Negativity

When making an advanced potion,the essentia are applied like this:
The first essence serves as an operator. The following operators are defined:
 - Selection: returns the `max(min(n+1, param_length-1), 1)`th Element of the params, based on the first int param n
 - Evaluation: Tries to evaluate the parameter, if multiple as sequence, returns result if multiple as list, `void` if error(such as non-executable, or no params (no params is cacheable))
 - Escaping: Returns the parameter as an executable value
 - Retrieving: State with one param, returns the value stored at the index given by the param
 - Storing: State with two params, stores the second param at the index given by the first param, returns the value
 - void: execute with
 - Amount: returns `1` if no param, else `sum of all params`
 - Negativity: returns `-1` if no param, `-param` if 1 param, else `first param - sum of all other params`

Examples:
While index[1] < 1 do Something:
```
Evaluate
    Store
        Amount 0
        Escape
            Evaluate
                Something
                Evaluate
                    Select
                        Retrieve
                            Amount 1
                        Retrieve
                            Amount 0
                        Execute
```
### A P interpreter:
(Brainfuck without io)

##### The structure:
```
[0]: The length of the input programm
[1 to [0] (inclusive)]: The programm, where 0 = <; 1 = >; 2 = -; 3 = +; 4 = [; 5 = ]
[[0]+1 to a(known when I wrote the code)]: The storage for the code
    pointer index (always >=0)
    pointer to code
    counter of closing brackets
    parsing code:
        handles <>-+[ and conditional part of ]
    bracket match searcher
[a until technical limit]: The "tape"
```
```
Evaluate
    Store
        Amount 0
        Amount 3
    // Code: [-]
    Store
        Amount 1
        Amount 4
    Store
        Amount 2
        Amount 2
    Store
        Amount 3
        Amount 5
    // Interpreter
    // index pointer
    Store
        Amount
            Amount 1
            Retrieve
                Amount 0
        Amount 0
    // code pointer
    Store
        Amount
            Amount 2
            Retrieve
                Amount 0
        Amount 0
    // brackets counter
    Store
        Amount
            Amount 3
            Retrieve
                Amount 0
        Amount 0
    Store
        Amount
            Amount 4
            Retrieve
                Amount 0
        Escape
            Evaluate
                Evaluate
                    Select
                        // retrieve char at pointer position
                        Retrieve
                            Amount
                                Amount 1
                                Retrieve
                                    Amount
                                        Amount 2
                                        Retrieve
                                            Amount 0
                        // If 0: <
                        Escape
                            Evaluate
                                Select
                                    // based on mem pointer
                                    Retrieve
                                        Amount
                                            Amount 1
                                            Retrieve
                                                Amount 0
                                    // if pointer is 0, do nothing
                                    Evaluate
                                    // Else
                                    Escape
                                        Store
                                            // at the mem pointer
                                            Amount
                                                Amount 1
                                                Retrieve
                                                    Amount 0
                                            Negativity
                                                // get mem pointer val
                                                Retrieve
                                                    Amount
                                                        Amount 1
                                                        Retrieve
                                                            Amount 0
                                                // And substract 1
                                                Amount 1

                        // If 1: >
                        Escape
                            Store
                                Amount
                                    Amount 1
                                    Retrieve
                                        Amount 0
                                Amount
                                    Amount 1
                                    // get mem pointer val
                                    Retrieve
                                        Amount
                                            Amount 1
                                            Retrieve
                                                Amount 0
                        // If 2: -
                        Escape
                        // If 3: +
                        Escape
                        // If 4 [
                        Escape
                        // If 5 ]
                        Escape


                Evaluate
                    Select
                        // 0 when code pointer == length, and evaluation is finished
                        Negativity
                            Retrieve
                                Amount 0
                            Retrieve
                                Amount
                                    Amount 2
                                    Retrieve
                                        Amount 0
                        // if finished, stop recursion
                        Evaluate
                        // This function
                        Retrieve
                            Amount
                                Amount 4
                                Retrieve
                                    Amount 0
```
