import java.io.IOException;
import com.csvreader.CsvWriter;


public class SummarizeCplusplus extends Summarizer {

	public int numClass = 0;;
    public int numStruct = 0;
    public int numClassdecl = 0;
    public int numConstructordecl = 0;
    public int numDestructordecl = 0;
    public int numConstructor = 0;
    public int numDestructor = 0;
    public int numMacro = 0;
    public int numUnion = 0;
    public int numTry = 0;
    public int numCatch = 0;
    public int numThrow = 0;
    
    public SummarizeCplusplus(String folderdir){
		fdir = folderdir;
	}

	@Override
	public void createFile() {
		totalWriter = new CsvWriter(fdir+"TotalStatistics_cplusplus.csv");
	}
	@Override
	public void writeDiffColumnName() {
		try {
			totalWriter.write("Class");
			totalWriter.write("Struct");
		    totalWriter.write("Classdecl");
		    totalWriter.write("Constructordecl");
		    totalWriter.write("Constructordecl_percent");
		    totalWriter.write("Destructordecl");
		    totalWriter.write("Destructordecl_percent");
		    totalWriter.write("Constructor");
		    totalWriter.write("Constructor_percent");
		    totalWriter.write("Destructor");
		    totalWriter.write("Destructor_percent");
		    totalWriter.write("Macro");
		    totalWriter.write("Macro_percent");
		    totalWriter.write("Union");
		    totalWriter.write("Union_percent");
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
			totalWriter.write(String.valueOf(numStruct));
			totalWriter.write(String.valueOf(numClassdecl));
			totalWriter.write(String.valueOf(numConstructordecl));
			percent = (double)numConstructordecl/numExecuteLine;
			totalWriter.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalWriter.write(String.valueOf(numDestructordecl));
			percent = (double)numDestructordecl/numExecuteLine;
			totalWriter.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalWriter.write(String.valueOf(numConstructor));
			percent = (double)numConstructor/numExecuteLine;
			totalWriter.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalWriter.write(String.valueOf(numDestructor));
			percent = (double)numDestructor/numExecuteLine;
			totalWriter.write(String.valueOf((double)Math.round(percent*10000)/10000));
		    totalWriter.write(String.valueOf(numMacro));
		    percent = (double)numMacro/numExecuteLine;
			totalWriter.write(String.valueOf((double)Math.round(percent*10000)/10000));
		    totalWriter.write(String.valueOf(numUnion));
		    percent = (double)numUnion/numExecuteLine;
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
		if(name.equals("Macro")){
			numMacro = number;
		}
		if(name.equals("Union")){
			numUnion = number;
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
}

