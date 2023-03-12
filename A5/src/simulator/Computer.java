package simulator;

import java.util.Arrays;

/**
 * The Computer class is composed of registers, memory, PC, IR, and CC.
 * The Computer can execute a program based on the the instructions in memory.
 *  
 * @author mmuppa
 * @author acfowler
 * @version 1.3
 */
public class Computer {

	private final static int MAX_MEMORY = 50;
	private final static int MAX_REGISTERS = 8;

	private BitString mRegisters[];
	private BitString mMemory[];
	private BitString mPC;
	private BitString mIR;
	private BitString mCC;

	/**
	 * Initialize all memory addresses to 0, registers to 0 to 7
	 * PC, IR to 16 bit 0s and CC to 000.
	 */
	public Computer() {
		mPC = new BitString();
		mPC.setUnsignedValue(0);
		mIR = new BitString();
		mIR.setUnsignedValue(0);
		mCC = new BitString();
		mCC.setBits(new char[] { '0', '0', '0' });
		
		mRegisters = new BitString[MAX_REGISTERS];
		for (int i = 0; i < MAX_REGISTERS; i++) {
			mRegisters[i] = new BitString();
			mRegisters[i].setUnsignedValue(i);
		}

		mMemory = new BitString[MAX_MEMORY];
		for (int i = 0; i < MAX_MEMORY; i++) {
			mMemory[i] = new BitString();
			mMemory[i].setUnsignedValue(0);
		}
	}
	
	// The public accessor methods shown below are useful for unit testing.
	// Do NOT add public mutator methods (setters)!
	
	/**
	 * @return the registers
	 */
	public BitString[] getRegisters() {
		return copyBitStringArray(mRegisters);
	}

	/**
	 * @return the memory
	 */
	public BitString[] getMemory() {
		return copyBitStringArray(mMemory);
	}

	/**
	 * @return the PC
	 */
	public BitString getPC() {
		return mPC.copy();
	}

	/**
	 * @return the IR
	 */
	public BitString getIR() {
		return mIR.copy();
	}

	/**
	 * @return the CC
	 */
	public BitString getCC() {
		return mCC.copy();
	}
	
	/**
	 * Safely copies a BitString array.
	 * @param theArray the array to copy.
	 * @return a copy of theArray.
	 */
	private BitString[] copyBitStringArray(final BitString[] theArray) {
		BitString[] bitStrings = new BitString[theArray.length];
		Arrays.setAll(bitStrings, n -> bitStrings[n] = theArray[n].copy());
		return bitStrings;
	}

	/**
	 * Loads a 16 bit word into memory at the given address. 
	 * @param address memory address
	 * @param word data or instruction or address to be loaded into memory
	 */
	private void loadWord(int address, BitString word) {
		if (address < 0 || address >= MAX_MEMORY) {
			throw new IllegalArgumentException("Invalid address");
		}
		mMemory[address] = word;
	}
	
	/**
	 * Loads a machine code program, as Strings.
	 * @param theWords the Strings that contain the instructions or data.
	 */
	public void loadMachineCode(final String ... theWords) {
		if (theWords.length == 0 || theWords.length >= MAX_MEMORY) {
			throw new IllegalArgumentException("Invalid words");
		}
		for (int i = 0; i < theWords.length; i++) {
			final BitString instruction = new BitString();
			instruction.setBits(theWords[i].toCharArray());
			loadWord(i, instruction);
		}
	}
	
	
	
	
	
	// The next 6 methods are used to execute the required instructions:
	// BR, ADD, LD, AND, NOT, TRAP
	
	/**
	 * op   nzp pc9offset
	 * 0000 000 000000000
	 * 
	 * The condition codes specified by bits [11:9] are tested.
	 * If bit [11] is 1, N is tested; if bit [11] is 0, N is not tested.
	 * If bit [10] is 1, Z is tested, etc.
	 * If any of the condition codes tested is 1, the program branches to the memory location specified by
	 * adding the sign-extended PCoffset9 field to the incremented PC.
	 */
	public void executeBranch() {
		System.out.println("BR"); // remove this print statement
		
		// TODO - implement the BR instruction here
		// 		  Based on condition codes, if they are met then the new location from offset is used.
		
	}
	
	/**
	 * op   dr  sr1      sr2
	 * 0001 000 000 0 00 000
	 * 
	 * OR
	 * 
	 * op   dr  sr1   imm5
	 * 0001 000 000 1 00000
	 * 
	 * If bit [5] is 0, the second source operand is obtained from SR2.
	 * If bit [5] is 1, the second source operand is obtained by sign-extending the imm5 field to 16 bits.
	 * In both cases, the second source operand is added to the contents of SR1 and the
	 * result stored in DR. The condition codes are set, based on whether the result is
	 * negative, zero, or positive.
	 */
	public void executeAdd() {
		BitString destBS = mIR.substring(4, 3);
		BitString sourceBS1 = mIR.substring(7, 3);
		BitString sourceBS2 = mIR.substring(13, 3);

		if (mIR.substring(10,1).getUnsignedValue() == 1) {
			sourceBS2 = mIR.substring(11, 5);
		}
		mRegisters[destBS.getUnsignedValue()].set2sCompValue(getSum(sourceBS1, sourceBS2));
		setConditionCodes(mRegisters[destBS.getUnsignedValue()]);
	}

	private int getSum(BitString sourceBS1, BitString sourceBS2) {
		return sourceBS1.get2sCompValue() + sourceBS2.get2sCompValue();
	}
	
	/**
	 * Performs the load operation by placing the data from PC
	 * + PC offset9 bits [8:0]
	 * into DR - bits [11:9]
	 * then sets CC.
	 */
	public void executeLoad() {
		System.out.println("LD");  // remove this print statement
		
		// TODO - Implement the LD instruction here
		// 		  LD will look at a specific memory location based on the offset and get the value
		// 	 	  from the memory location and put it in the destination register. It can get the memory from mMemory[].
		// 		  The program will be an isntruction with a 9 bit offset which should be some value in the range such that
		// 		  the memory located and loaded is within the max memory size.
	}


	/**
	 * op   dr  sr1      sr2
	 * 0101 000 000 0 00 000
	 * 
	 * OR
	 * 
	 * op   dr  sr1   imm5
	 * 0101 000 000 1 00000
	 * 
	 * If bit [5] is 0, the second source operand is obtained from SR2.
	 * If bit [5] is 1, the second source operand is obtained by sign-extending the imm5 field to 16 bits.
	 * In either case, the second source operand and the contents of SR1 are bitwise ANDed
	 * and the result stored in DR.
	 * The condition codes are set, based on whether the binary value produced, taken as a 2’s complement integer,
	 * is negative, zero, or positive.
	 */
	public void executeAnd() {
		BitString destBS = mIR.substring(4, 3);
		BitString sourceBS1 = mIR.substring(7, 3);
		BitString sourceBS2 = mIR.substring(13, 3);
		sourceBS1.set2sCompValue(mIR.substring(7, 3).getUnsignedValue());
		sourceBS2.set2sCompValue(mIR.substring(13, 3).getUnsignedValue());
		
		if (mIR.substring(10,1).getUnsignedValue() == 1) {
			sourceBS2.set2sCompValue(mIR.substring(11, 5).get2sCompValue());
		}

		int length = sourceBS2.getLength();
		mRegisters[destBS.getUnsignedValue()].setBits(getLogicalConjunction(sourceBS1, sourceBS2, length));
		setConditionCodes(mRegisters[destBS.getUnsignedValue()]);
	}

	public char[] getLogicalConjunction(BitString sourceBS1, BitString sourceBS2, int length) {
		char[] logicalConjunctionArray = new char[length];
		for (int i = 0; i < length; i++) {
			final char currentBitSR1 = sourceBS1.getBits()[i];
			final char currentBitSR2 = sourceBS2.getBits()[i];
			if (currentBitSR1 == currentBitSR2 && currentBitSR1 == '1') {
				logicalConjunctionArray[i] = '1';
			} else {
				logicalConjunctionArray[i] = '0';

			}
		}
		// If this doesn't work with little effort, re-engineer using loops.
		// Look at each bit in sr1 and sr2. If they are equal and 1 then add to the array '1' else '0'

		return logicalConjunctionArray;
	}

	/**
	 * Performs not operation by using the data from the source register (bits[8:6])
	 * and inverting and storing in the destination register (bits[11:9]).
	 * Then sets CC.
	 */
	public void executeNot() {
		BitString destBS = mIR.substring(4, 3);
		BitString sourceBS = mIR.substring(7, 3);
		mRegisters[destBS.getUnsignedValue()] = mRegisters[sourceBS.getUnsignedValue()].copy();
		mRegisters[destBS.getUnsignedValue()].invert();

		// add code here to set the condition code

		// This block of code sets the destination register.
		setConditionCodes(destBS);

	}

	/**
	 * Sets the condition codes N, Z or P when the function is called and
	 * a destination register is passed as an argument.
	 * @param destBS that sets the current condition codes.
	 */
	private void setConditionCodes(BitString destBS) {
		if (destBS.get2sCompValue() < 0) {
			mCC.setBits("100".toCharArray());
		} else if (destBS.get2sCompValue() > 0) {
			mCC.setBits("001".toCharArray());
		} else {
			mCC.setBits("010".toCharArray());
		}
	}


	/**
	 * Executes the trap operation by checking the vector (bits [7:0]
	 * 
	 * vector x21 - OUT
	 * vector x25 - HALT
	 * 
	 * @return true if this Trap is a HALT command; false otherwise.
	 */
	public boolean executeTrap() {
		boolean halt = true;

		// implement the TRAP instruction here
		// TODO - take a look at notes. Use substring to check for the correct bits 0->7
		//		  if
		return halt;
	}
	
	

	/**
	 * This method will execute all the instructions starting at address 0 
	 * until a HALT instruction is encountered. 
	 */
	public void execute() {
		BitString opCodeStr;
		int opCode;
		boolean halt = false;

		while (!halt) {
			// Fetch the next instruction
			mIR = mMemory[mPC.getUnsignedValue()];
			// increment the PC
			mPC.addOne();

			// Decode the instruction's first 4 bits 
			// to figure out the opcode
			opCodeStr = mIR.substring(0, 4);
			opCode = opCodeStr.getUnsignedValue();

			// What instruction is this?
			if (opCode == 0) { // BR
				executeBranch();
			} else if (opCode == 1) {  // ADD    0001
				executeAdd();
			} else if (opCode == 2) {  // LD     0010
				executeLoad();
			} else if (opCode == 5) {  // AND    0101
				executeAnd();
			} else if (opCode == 9) {  // NOT    1001
				executeNot();
			} else if (opCode == 15) { // TRAP   1111
				halt = executeTrap();
			} else {
				throw new UnsupportedOperationException("Illegal opCode: " + opCode);
			}
		}
	}

	/**
	 * Displays the computer's state
	 */
	public void display() {
		System.out.println();
		System.out.print("PC ");
		mPC.display(true);
		System.out.print("   ");

		System.out.print("IR ");
		mPC.display(true);
		System.out.print("   ");

		System.out.print("CC ");
		mCC.display(true);
		System.out.println("   ");
		for (int i = 0; i < MAX_REGISTERS; i++) {
			System.out.printf("R%d ", i);
			mRegisters[i].display(true);
			if (i % 3 == 2) {
				System.out.println();
			} else {
				System.out.print("   ");
			}
		}
		System.out.println();
		for (int i = 0; i < MAX_MEMORY; i++) {
			System.out.printf("%3d ", i);
			mMemory[i].display(true);
			if (i % 3 == 2) {
				System.out.println();
			} else {
				System.out.print("   ");
			}
		}
		System.out.println();
		System.out.println();
	}
}
