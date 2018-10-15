package com.jpmorgan.tradeprocess;

import static com.jpmorgan.tradeprocess.Constant.FILE_DELIMITER;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.jpmorgan.tradeprocess.domain.InstructionData;

public class InstructionReader {

	public BufferedReader getInstructionReader() throws FileNotFoundException {
		File file = new File("resources/instructions.txt");

		FileReader fileReader = new FileReader(file);
		return new BufferedReader(fileReader);		
	}
	
	public InstructionData processInstructions(String instructions) {
		InstructionData data = new InstructionData();
		
		if (instructions.contains(FILE_DELIMITER)) {
			String[] sp = instructions.split(FILE_DELIMITER);	
			
			data.setEntity(sp[0]);
			data.setInstructionType(sp[1]);
			data.setFxRate(sp[2]);
			data.setCurrencyCode(sp[3]);
			data.setInstructionDate(sp[4]);
			data.setSettlementDate(sp[5]);
			data.setUnits(Integer.parseInt(sp[6]));
			data.setSharePrice(Double.parseDouble(sp[7]));
			
		} else {
			throw new IllegalArgumentException(instructions + "invalid format");
		}
		
		return data;
		
	}
}
