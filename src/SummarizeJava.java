import java.io.IOException;
import com.csvreader.CsvWriter;


public class SummarizeJava extends Summarizer {

	public int numClass = 0;
	public int numConstructor = 0;
	public int numTry = 0;
	public int numCatch = 0;
	public int numThrow = 0;
	
	public SummarizeJava(String folderdir, String type){
		fdir = folderdir;
		resulttype = type;
	}

	@Override
	public void createFile() {
		if(resulttype.equals("line")){
			totalWriter = new CsvWriter(fdir+"TotalStatistics_line_java.csv");
		}
		else{
			totalWriter = new CsvWriter(fdir+"TotalStatistics_stat_java.csv");
		}
	}
	@Override
	public void writeDiffColumnName() {
		try {
			totalWriter.write("Class");
			totalWriter.write("Class_percent");
			totalWriter.write("Constructor");
			totalWriter.write("Constructor_percent");
			totalWriter.write("Try");
			totalWriter.write("Try_percent");
			totalWriter.write("Catch");
			totalWriter.write("Catch_percent");
			totalWriter.write("Throw");
			totalWriter.write("Throw_percent");
			totalWriter.endRecord();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void writeDiffNumber(int i, String s) {
		double percent;
		try {
			totalWriter.write(String.valueOf(numClass));
			percent = (double)numClass/numExecuteLine;
			totalWriter.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalWriter.write(String.valueOf(numConstructor));
			percent = (double)numConstructor/numExecuteLine;
			totalWriter.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalWriter.write(String.valueOf(numTry));
			percent = (double)numTry/numExecuteLine;
			totalWriter.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalWriter.write(String.valueOf(numCatch));
			percent = (double)numCatch/numExecuteLine;
			totalWriter.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalWriter.write(String.valueOf(numThrow));
			percent = (double)numThrow/numExecuteLine;
			totalWriter.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalWriter.endRecord();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getDiffNumber(String name, int number) {
		if(name.equals("Class")){
			numClass = number;
		}
		if(name.equals("Constructor")){
			numConstructor = number;
		}
		if(name.equals("Try")){
			numTry = number;
		}
		if(name.equals("Catch")){
			numCatch = number;
		}
		if(name.equals("Throw")){
			numThrow = number;
		}
	}

	@Override
	public void writeDiffStatColumnName() {
		try {
			totalWriter.write("Class");
			totalWriter.write("Constructor");
			totalWriter.write("Try");
			totalWriter.write("Catch");
			totalWriter.write("Throw");
			totalWriter.endRecord();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeDiffStatNumber(int proNum, String fileName) {
		try {
			totalWriter.write(String.valueOf(numClass));
			totalWriter.write(String.valueOf(numConstructor));
			totalWriter.write(String.valueOf(numTry));
			totalWriter.write(String.valueOf(numCatch));
			totalWriter.write(String.valueOf(numThrow));
			totalWriter.endRecord();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

