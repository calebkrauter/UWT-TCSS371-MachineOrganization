        .ORIG       X3000
        ; MISSING CONTENT
        
        
        
        JSR         ADDAndPRINT
        HALT
        
ADDAndPRINT
        Add         R4, R4, #2
        ST          R7, SAVER7
        JSR         PRINT
        LD          R7, SAVER7
        RET
        
PRINT
        LD          R0, ZERO
        ADD         R0, R4, R0
        OUT
        RET
        
ZERO    .FILL       #48
SAVER7  .BLKW       #1
        .END