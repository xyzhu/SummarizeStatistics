import java.io.IOException;
import com.csvreader.CsvWriter;


public class SummarizeC extends Summarizer {

	public int numStruct;
	public int numGoto;
	public int numLabel;
	
	public SummarizeC(String folderdir){
		fdir = folderdir;
	}
	
	@Override
	public void createFile() {
		totalWriter = new CsvWriter(fdir+"TotalStatistics_c.csv");
	}

	@Override
	public void writeDiffColumnName() {
		try {
			totalWriter.write("Struct");
			totalWriter.write("Gogo");
			totalWriter.write("Goto_percent");
			totalWriter.write("Label");
			totalWriter.write("Label_percent");
			totalWriter.endRecord();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void writeDiffNumber(int i, String s) {
		double percent;
		try {
			totalWriter.write(String.valueOf(numStruct));
			totalWriter.write(String.valueOf(numGoto));
			percent = (double)numGoto/numExecuteLine;
			totalWriter.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalWriter.write(String.valueOf(numLabel));
			percent = (double)numLabel/numExecuteLine;
			totalWriter.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalWriter.endRecord();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getDiffNumber(String name, int number) {
		if(name.equals("Struct")){
			numStruct = number;
		}
		if(name.equals("Goto")){
			numGoto = number;
		}
		if(name.equals("Label")){
			numLabel = number;
		}
	}
}

