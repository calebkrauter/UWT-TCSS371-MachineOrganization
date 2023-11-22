        .ORIG       x3000 
        LD          R0, input
        ADD         R0, R0, #-6
        ADD         R0, R0, b000001
        OUT
        HALT
input   .FILL       x005A
        .END