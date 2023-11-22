; Prompt the user to enter a number 1 to 9
; Echo the input to the monitor

        .ORIG        x3000
                                     ; keep in mind that IO commands use R0
        AND          R1, R1, #0      ; clear R1
        AND          R2, R2, #0      ; clear R2
LOOP    LEA          R0, PROMPT      ; load the start address of the prompt
        PUTS                         ; display the prompt
        GETC                         ; read a char into R0
        OUT                          ; write a char from R0 to the monitor
        LEA          R0, ENDMSG      ; load the end message
        PUTS                         ; display the end message
        HALT
PROMPT  .STRINGZ     "\nPlease enter a number 1 to 9: "
ENDMSG  .STRINGZ     "\nThanks!"
        .END