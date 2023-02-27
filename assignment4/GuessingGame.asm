; Caleb Krauter
; Collaborated with Elroy Mbabazi

                .ORIG               x3000               ; Start at orgin x3000
Loop            LD                  R0, #0
                LEA                 R0, Prompt          ; Ask for input    
                PUTS                                    ; display the prompt
                GETC                                    ; read a char into R0
                OUT                                     ; write the char from R0 to the monitor
                ; Check if input is greater or equal to MinValid.
                ; if MinValid + (-input) = 0 or is neg, then it meets the MinValid requirement.

                
MinBoundCheck   LD                  R1, MinValid ; LD into R1 the minValid value
                NOT                 R3, R0          ; Negate R0's value         
                ADD                 R2, R3, R1      ; R1 - R0 (ex: min(x0030 = 0) - in(x002F = some value) = pos, if in is x0030 then it's zero, if it is greater than x0030 it is neg, (valid range for min is when input sum = nz))      
                BRz                 END ;  Check if input meets requirements for min bound check.
                BRp                 END

MaxBoundCheck   LD                  R4, MaxValid ; LD into R4, maxV
                ADD                 R3, R3, #1      ; Add 1 to complete the 2's complement 

                ADD                 R5, R3, R4   ; R0's value is already negated, Add max with -INPUT 
                BRn                 END          ; Check if input meets requirements for max bound check.
                BRz                 END
                LD                  R6, TargetValue              ; Value to search for.
                ADD                 R1, R3, R6
                BRn                 OutGreater
                BRp                 OutLesser
                BRz                 OutVictory
                BR                  END
OutGreater      LEA                 R0, GreaterMsg
                PUTS
                BR                  Loop
OutLesser       LEA                 R0, LesserMsg
                PUTS
                BR                  Loop
OutVictory      LEA                 R0, VictoryMsg
                PUTS
                BR                  END

END             HALT
MinValid          .FILL               x0030         
MaxValid          .FILL               x003A  
TargetValue     .FILL               x0037
Prompt          .STRINGZ            "\nGuess a number 0 to 9: "
Error           .STRINGZ            "\nInvalid input."            
GreaterMsg         .STRINGZ            "\nToo big."
LesserMsg          .STRINGZ            "\nToo small."
VictoryMsg         .STRINGZ            "\nCorrect! You took n guesses."
                .END                