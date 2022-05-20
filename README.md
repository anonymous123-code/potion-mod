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
 - Selection: returns the `max(min(n+1, param_length-1), 1)`th Element of the params, based on the first int param n [X]
 - Evaluation: Tries to evaluate the parameter, if multiple as sequence, returns result if multiple as list, `void` if error(such as non-executable, or no params (no params is cacheable)) [-]
 - Escaping: Returns the parameter as an executable value [-]
 - Retrieving: State with one param, returns the value stored at the index given by the param [-]
 - Storing: State with two params, stores the second param at the index given by the first param, returns the value [-]
 - void: execute with []
 - Amount: returns `1` if no param, else `sum of all params` [X]
 - Negativity: returns `-1` if no param, `-param` if 1 param, else `first param - sum of all other params` [X]

A way to express in written form is demonstrated by these examples; an interpreter is [potion_interpreter.py](src/potion_interpreter.py):

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
                        Evaluate
```
### A P interpreter:
(Brainfuck without io)

##### The structure:
```
[0]: The length of the input programm
[1 to [0] (inclusive)]: The programm, where 0 = <; 1 = >; 2 = -; 3 = +; 4 = [; 5 = ]
[[0]+1 to a([0]+1+5)]: The storage for the code
    pointer index (always >=0)
    pointer to code
    counter of closing brackets
    parsing code:
        handles <>-+[ and conditional part of ]
    bracket match searcher
[a until technical limit]: The "tape"
```
[See p_interpreter.potion](src/p_interpreter.potion)
