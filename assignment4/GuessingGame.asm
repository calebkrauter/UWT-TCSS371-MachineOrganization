; Caleb Krauter
; Collaborated with Elroy Mbabazi
; This program prompts a user for a value between 0 and 9 giving hints until
; the user enters the target value which in this case is 7.

                .ORIG               x3000               ; Start at orgin x3000.
                LD                  R4, StartCount      ; Initalize the counter to a valid ASCII value. (Value: within 0-9).
Loop            LD                  R0, x0              ; Load R0 with #0.
                ADD                 R4, R4, x1          ; Increment the counter.
                
                LEA                 R0, Prompt          ; Prompt for input.
                PUTS                                    ; Display the prompt.
                GETC                                    ; Read a char into R0.
                OUT                                     ; Write the char from R0 to the monitor.

MinBoundCheck   LD                  R1, MinValid        ; Load into R1 the MinValid value being X0030.
                NOT                 R3, R0              ; Negate R0's value.
                ADD                 R2, R3, R1          ; Subtract the input from the minimum valid value.
                BRz                 OutError            ; Check if input meets requirements for min bound check.
                BRp                 OutError            
                                                        ; If neither errors are output, continue (input value is greater/equal to MinValid).
MaxBoundCheck   LD                  R1, MaxValid        ; Load into R1 the MaxValid value being X003A.
                ADD                 R3, R3, x1          ; R3 is the negated input, add 1 to complete the 2's complement.
                ADD                 R2, R3, R1          ; Subtract the input from the maximum valid value.
                BRn                 OutError            ; Check if input meets requirements for max bound check.
                BRz                 OutError            ; If neither errors are output, continue (input value is less than MaxValid).
                
                LD                  R6, TargetValue     ; Load the target value 7.
                ADD                 R1, R3, R6          ; Add into R1 the sum of negated input with the target value.
                BRn                 OutGreater          ; Result is negative, target value is greater than the input, branch.
                BRp                 OutLesser           ; Result is positive, target value is less than the input, branch.
                BRz                 OutVictory          ; Result is zero, target value is found, branch

OutGreater      LEA                 R0, GreaterMsg      ; Load the effective address containing the message.
                PUTS                                    ; Output that target is larger.
                BR                  Loop                ; Return to top of the loop.
OutLesser       LEA                 R0, LesserMsg       ; Load the effective address containing the message.
                PUTS                                    ; Output that target is smaller.
                BR                  Loop                ; Return to top of the loop.
OutVictory      LEA                 R0, VictoryPart1    ; Load the effective address containing the message.
                PUTS                                    ; Output that target is found.
                ADD                 R0, R4, x0          ; Add into R0 R4's contents for outputting the amount of guesses.
                OUT                                     ; Output contents of R0.
                LEA                 R0, VictoryPart2    ; Finish output that target is found.
                PUTS                                    ; Output that target is found.
                BR                  END                 ; Branch to HALT.
OutError        LEA                 R0, Error           ; Load into R0 the Error message.
                PUTS                                    ; Output the error.
                BR                  Loop                ; Branch to the top of the loop.

END             HALT
MinValid        .FILL               x0030               ; The lower bound of the range of valid values.
MaxValid        .FILL               x003A               ; Completes the range of valid values. x003A is one larger than the max possible value. 
TargetValue     .FILL               x0037               ; The target value 7 to be found in order to win.
StartCount      .FILL               x0030               ; Start of the counter. For outputting 0 and to be incremented through representable decimals.
MaxCount        .FILL               x0039               ; The limiter of the counter, also the last representable decimal as a character.
Prompt          .STRINGZ            "\nGuess a number 0 to 9: "
Error           .STRINGZ            "\nInvalid input."            
GreaterMsg      .STRINGZ            "\nToo big."
LesserMsg       .STRINGZ            "\nToo small."
VictoryPart1    .STRINGZ            "\nCorrect! You took "
VictoryPart2    .STRINGZ            " guesses."
                .END                