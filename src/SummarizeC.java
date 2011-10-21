import java.io.IOException;
import com.csvreader.CsvWriter;


public class SummarizeC extends Summarizer {

	public int numStruct;
	public int numGoto;
	public int numLabel;

	public SummarizeC(String folderdir, String type){
		fdir = folderdir;
		resulttype = type;
	}

	@Override
	public void createFile() {
		if(resulttype.equals("line")){
			totalWriter = new CsvWriter(fdir+"TotalStatistics_line_getset_c.csv");
		}
//		else{
//			totalWriter = new CsvWriter(fdir+"TotalStatistics_stat_getset_c.csv");
//		}
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

	@Override
	public void writeDiffStatColumnName() {
		try {
			totalWriter.write("Struct");
			totalWriter.write("Goto");
			totalWriter.write("Label");
			totalWriter.endRecord();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeDiffStatNumber(int proNum, String fileName) {
		try {
			totalWriter.write(String.valueOf(numStruct));
			totalWriter.write(String.valueOf(numGoto));
			totalWriter.write(String.valueOf(numLabel));
			totalWriter.endRecord();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

