import java.io.IOException;
import com.csvreader.CsvWriter;


public class SummarizeJava extends Summarizer {

	public int numLabel = 0;
	public int numClass = 0;
	public int numConstructor = 0;
	public int numTry = 0;
	public int numCatch = 0;
	public int numThrow = 0;
	public int numSynchronized = 0;
	
	public SummarizeJava(String folderdir){
		fdir = folderdir;
	}

	@Override
	public void createFile() {
		totalWriter = new CsvWriter(fdir+"TotalStatistics_java.csv");
	}
	@Override
	public void writeDiffColumnName() {
		try {
			totalWriter.write("Label");
			totalWriter.write("Label_percent");
			totalWriter.write("Class");
			totalWriter.write("Constructor");
			totalWriter.write("Constructor_percent");
			totalWriter.write("Try");
			totalWriter.write("Try_percent");
			totalWriter.write("Catch");
			totalWriter.write("Catch_percent");
			totalWriter.write("Throw");
			totalWriter.write("Throw_percent");
			totalWriter.write("Synchronized");
			totalWriter.write("Synch_percent");
			totalWriter.endRecord();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void writeDiffNumber(int i, String s) {
		double percent;
		try {
			totalWriter.write(String.valueOf(numLabel));
			percent = (double)numLabel/numExecuteLine;
			totalWriter.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalWriter.write(String.valueOf(numClass));totalWriter.write(String.valueOf(numConstructor));
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
			totalWriter.write(String.valueOf(numSynchronized));
			percent = (double)numSynchronized/numExecuteLine;
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
		if(name.equals("Class")){
			numClass = number;
		}
		if(name.equals("Class declaration")){
			numClassdecl = number;
		}
		if(name.equals("Constructor declaration")){
			numConstructordecl = number;
		}
		if(name.equals("Destructor declaration")){
			numDestructordecl = number;
		}
		if(name.equals("Constructor")){
			numConstructor = number;
		}
		if(name.equals("Destructor")){
			numDestructor = number;
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
		if(name.equals("Synchronized")){
			numSynchronized = number;
		}
	}
}

