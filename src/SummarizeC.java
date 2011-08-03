import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.csvreader.CsvWriter;


public class SummarizeC implements Summarizer {

	public int numFile;
	public int numStruct;
	public int numTotalLine;
	public int numCommentLine;
	public int numBlankLine;
	public int numExecuteLine;
	public int numCall;
	public int numIf;
	public int numAssignment;
	public int numFunction;
	public int numFunctionDecl;
	public int numLoop;
	public int numTry;
	public int numSwitch;
	public int numDeclstmt;
	public int numDecl;
	public int numExprstmt;
	public int numExpr;
	public int numParamlist;
	public int numParam;
	public int numArgulist;
	public int numArgu;
	public int numBlock;
	public int numContinue;
	public int numBreak;
	public int numReturn;
	public int numFor;
	public int numElse;
	public int numWhile;
	public int numDo;
	public int numCase;
	public int numCatch;
	public int numGoto;
	public int numLabel;
	public int numLocalFunctionCall;
	public int numLibFunctionCall;

	String fileName;
	String fdir;
	int type;
	int index = 0;
	String number;
	Double percent;
	File folder;
	File[] listOfFiles;
	FileInputStream fstream; 
	DataInputStream in;
	BufferedReader br;
	String str;
	int proNum;
	boolean countPercent;
	int numProject;
	CsvWriter totalStatistics;
	CsvWriter stat1;
	CsvWriter stat2;
	public SummarizeC(String fileDir, int statType){
		resetStatementNumber();
		fdir = fileDir;
		type = statType;
		index = 0;
		fstream = null;
		br = null;
		proNum = 0;
		countPercent = false;
		folder = new File(fileDir);
		listOfFiles = folder.listFiles();
		numProject = listOfFiles.length;
	}
	private void resetStatementNumber() {
		numFile = 0;
		numStruct = 0;
		numTotalLine = 0;
		numCommentLine = 0;
		numBlankLine = 0;
		numIf = 0;
		numAssignment = 0;
		numFunction = 0;
		numFunctionDecl = 0;
		numLoop = 0;
		numTry = 0;
		numSwitch = 0;
		numDeclstmt = 0;
		numDecl = 0;
		numExprstmt = 0;
		numExpr = 0;
		numParamlist = 0;
		numParam = 0;
		numArgulist = 0;
		numArgu = 0;
		numBlock = 0;
		numContinue = 0;
		numBreak = 0;
		numReturn = 0;
		numFor = 0;
		numElse = 0;
		numWhile = 0;
		numDo = 0;
		numCase = 0;
		numCatch = 0;
		numGoto = 0;
		numLabel = 0;
	}

	public void getStatistics() {

		String name;
		int number;
		if(type==0){
			totalStatistics = new CsvWriter(fdir+"TotalStatistics_line.csv");
			totalStatistics.setComment('#');
			initializeLine();
		}
		else if(type == 1){
			totalStatistics = new CsvWriter(fdir+"TotalStatistics_stat.csv");
			totalStatistics.setComment('#');
			stat1 = new CsvWriter(fdir+"TotalStatistics_stat1.csv");
			stat2 = new CsvWriter(fdir+"TotalStatistics_stat2.csv");
			initializeStatement();
		}
		else{
			System.out.println("type should be 0 or 1");
			System.exit(0);
		}
		try {
			for (int i = 0; i < numProject; i++) {
				fileName = listOfFiles[i].getName();
				if (listOfFiles[i].isFile()&&fileName.endsWith(".txt")) {
					fstream = new FileInputStream(fdir+fileName);
					in = new DataInputStream(fstream);
					br = new BufferedReader(new InputStreamReader(in));
					str = br.readLine();
					while (!str.equals("-------------------------")) {
						index = str.indexOf(":");
						if(index >= 0){
							name = str.substring(0, index);
							number =Integer.valueOf(str.substring(index+2, str.length())); 
							getNumber(name, number);
						}
						str = br.readLine();
					}
					numExecuteLine = numTotalLine - numCommentLine - numBlankLine;
					numLoop = numFor + numWhile;
					if(type == 0){
						writeLine(proNum, fileName);
					}
					else{
						writeStatment(proNum, fileName);
					}
					proNum++;
					resetStatementNumber();
				}
			}
			if(in!=null){
				in.close();
			}
			totalStatistics.close();
			if(type==1){
				stat1.close();
				stat2.close();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}

	}
	private void writeStatment(int proNum, String filename) {
		try {
			totalStatistics.write(String.valueOf(proNum));
			totalStatistics.write(filename.substring(0, filename.indexOf(".")));
			totalStatistics.write(String.valueOf(numCall));
			totalStatistics.write(String.valueOf(numIf));
			totalStatistics.write(String.valueOf(numAssignment));
			totalStatistics.write(String.valueOf(numFunction));
			totalStatistics.write(String.valueOf(numFunctionDecl));
			totalStatistics.write(String.valueOf(numSwitch));
			totalStatistics.write(String.valueOf(numDecl));
			totalStatistics.write(String.valueOf(numContinue));
			totalStatistics.write(String.valueOf(numBreak));
			totalStatistics.write(String.valueOf(numReturn));
			totalStatistics.write(String.valueOf(numFor));
			totalStatistics.write(String.valueOf(numElse));
			totalStatistics.write(String.valueOf(numWhile));
			totalStatistics.write(String.valueOf(numCase));
			totalStatistics.write(String.valueOf(numDo));
			totalStatistics.endRecord();
/*
			stat1.write(String.valueOf(proNum));
			stat1.write(filename.substring(0, filename.indexOf(".")));
			stat1.write(String.valueOf(numMethodCall));
			stat1.write(String.valueOf(numIf));
			stat1.write(String.valueOf(numAssignment));
			stat1.write(String.valueOf(numFunction));
			stat1.write(String.valueOf(numDecl));
			stat1.write(String.valueOf(numParam));
			stat1.write(String.valueOf(numArgu));
			stat1.write(String.valueOf(numBlock));
			stat1.write(String.valueOf(numReturn));
			stat1.endRecord();

			stat2.write(String.valueOf(proNum));
			stat2.write(filename.substring(0, filename.indexOf(".")));
			stat2.write(String.valueOf(numFunctionDecl));
			stat2.write(String.valueOf(numTry));
			stat2.write(String.valueOf(numSwitch));
			stat2.write(String.valueOf(numConstructor));
			stat2.write(String.valueOf(numContinue));
			stat2.write(String.valueOf(numBreak));
			stat2.write(String.valueOf(numFor));
			stat2.write(String.valueOf(numElse));
			stat2.write(String.valueOf(numWhile));
			stat2.write(String.valueOf(numCase));
			stat2.write(String.valueOf(numCatch));
			stat2.endRecord();
			*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void writeLine(int proNum, String filename) {
		try{
			totalStatistics.write(String.valueOf(proNum));
			totalStatistics.write(filename.substring(0, filename.indexOf(".")));
			totalStatistics.write(String.valueOf(numFile));
			totalStatistics.write(String.valueOf(numStruct));
			totalStatistics.write(String.valueOf(numTotalLine));
			totalStatistics.write(String.valueOf(numExecuteLine));
			totalStatistics.write(String.valueOf(numCall));
			percent = (double)numCall/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numIf));
			percent = (double)numIf/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numAssignment));
			percent = (double)numAssignment/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numFunction));
			percent = (double)numFunction/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numFunctionDecl));
			percent = (double)numFunctionDecl/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numLoop));
			percent = (double)numLoop/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numDeclstmt));
			percent = (double)numDeclstmt/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numDecl));
			percent = (double)numDecl/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numExprstmt));
			percent = (double)numExprstmt/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numExpr));
			percent = (double)numExpr/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numParamlist));
			percent = (double)numParamlist/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numParam));
			percent = (double)numParam/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numArgulist));
			percent = (double)numArgulist/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numArgu));
			percent = (double)numArgu/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numBlock));
			percent = (double)numBlock/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numContinue));
			percent = (double)numContinue/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numBreak));
			percent = (double)numBreak/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numReturn));
			percent = (double)numReturn/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numFor));
			percent = (double)numFor/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numElse));
			percent = (double)numElse/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numWhile));
			percent = (double)numWhile/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numDo));
			percent = (double)numDo/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numSwitch));
			percent = (double)numSwitch/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numCase));
			percent = (double)numCase/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numGoto));
			percent = (double)numGoto/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numLabel));
			percent = (double)numLabel/numExecuteLine;
			totalStatistics.write(String.valueOf((double)Math.round(percent*10000)/10000));
			totalStatistics.write(String.valueOf(numLocalFunctionCall));
			totalStatistics.write(String.valueOf(numLibFunctionCall));
			totalStatistics.endRecord();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void getNumber(String name, int number) {

		if(name.equals("File")){
			numFile = number;
		}
		if(name.equals("Struct")){
			numStruct = number;
		}
		if(name.equals("Total line")){
			numTotalLine = number;
		}
		if(name.equals("Comment line")){
			numCommentLine = number;
		}
		if(name.equals("Blank line")){
			numBlankLine = number;
		}
		if(name.equals("Call")){
			numCall = number;
		}
		if(name.equals("If")){
			numIf = number;
		}
		if(name.equals("Assignment")){
			numAssignment = number;
		}
		if(name.equals("Function")){
			numFunction = number;
		}
		if(name.equals("Function declaration")){
			numFunctionDecl = number;
		}
		if(name.equals("Switch")){
			numSwitch = number;
		}
		if(name.equals("Declaration statement")){
			numDeclstmt = number;
		}
		if(name.equals("Declaration")){
			numDecl = number;
		}
		if(name.equals("Expression statement")){
			numExprstmt = number;
		}
		if(name.equals("Expression")){
			numExpr = number;
		}
		if(name.equals("Parameter list")){
			numParamlist = number;
		}
		if(name.equals("Parameter")){
			numParam = number;
		}
		if(name.equals("Argument list")){
			numArgulist = number;
		}
		if(name.equals("Argument")){
			numArgu = number;
		}
		if(name.equals("Block")){
			numBlock = number;
		}
		if(name.equals("Continue")){
			numContinue = number;
		}
		if(name.equals("Break")){
			numBreak = number;
		}
		if(name.equals("Return")){
			numReturn = number;
		}
		if(name.equals("For")){
			numFor = number;
		}
		if(name.equals("Else")){
			numElse = number;
		}
		if(name.equals("While")){
			numWhile = number;
		}
		if(name.equals("Case")){
			numCase = number;
		}
		if(name.equals("Do")){
			numDo = number;
		}
		if(name.equals("Goto")){
			numGoto = number;
		}
		if(name.equals("Label")){
			numLabel = number;
		}
		if(name.equals("Local function call")){
			numLocalFunctionCall = number;
		}
		if(name.equals("Library function call")){
			numLibFunctionCall = number;
		}
		
	}

	private void initializeLine() {
		try{
			//			totalStatistics.writeComment("Total Statistics with Line Percent for java project"+"\n");
			totalStatistics.write("Number");
			totalStatistics.write("Project");
			totalStatistics.write("File");
			totalStatistics.write("Struct");
			totalStatistics.write("TotalLines");
			totalStatistics.write("ExecuteLines");
			totalStatistics.write("Call");
			totalStatistics.write("Call_percent");
			totalStatistics.write("If");
			totalStatistics.write("If_percent");
			totalStatistics.write("Assignment");
			totalStatistics.write("Assignment_percent");
			totalStatistics.write("Function");
			totalStatistics.write("Function_percent");
			totalStatistics.write("FunctionDeclaration");
			totalStatistics.write("FunctionDeclaration_percent");
			totalStatistics.write("Loop");
			totalStatistics.write("Loop_percent");
			totalStatistics.write("DeclarationStatement");
			totalStatistics.write("DeclarationStatement_percent");
			totalStatistics.write("Declaration");
			totalStatistics.write("Declaration_percent");
			totalStatistics.write("ExpressionStatement");
			totalStatistics.write("ExpressionStatement_percent");
			totalStatistics.write("Expression");
			totalStatistics.write("Expression_percent");
			totalStatistics.write("ParameterList");
			totalStatistics.write("ParameterList_percent");
			totalStatistics.write("Parameter");
			totalStatistics.write("Parameter_percent");
			totalStatistics.write("ArgumentList");
			totalStatistics.write("ArgumentList_percent");
			totalStatistics.write("Argument");
			totalStatistics.write("Argument_percent");
			totalStatistics.write("Block");
			totalStatistics.write("Block_percent");
			totalStatistics.write("Continue");
			totalStatistics.write("Continue_percent");
			totalStatistics.write("Break");
			totalStatistics.write("Break_percent");
			totalStatistics.write("Return");
			totalStatistics.write("Return_percent");
			totalStatistics.write("For");
			totalStatistics.write("For_percent");
			totalStatistics.write("Else");
			totalStatistics.write("Else_percent");
			totalStatistics.write("While");
			totalStatistics.write("While_percent");
			totalStatistics.write("Do");
			totalStatistics.write("Do_percent");
			totalStatistics.write("Switch");
			totalStatistics.write("Switch_percent");
			totalStatistics.write("Case");
			totalStatistics.write("Case_percent");
			totalStatistics.write("Gogo");
			totalStatistics.write("Goto_percent");
			totalStatistics.write("Label");
			totalStatistics.write("Label_percent");
			totalStatistics.write("LocalFunctionCall");
			totalStatistics.write("LibFunctionCall");
			totalStatistics.endRecord();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initializeStatement() {
		try {
			//			totalStatistics.writeComment("Total Statistics with Statemetn Percent for java project"+"\n");
			totalStatistics.write("Number");
			totalStatistics.write("Project");
			totalStatistics.write("Call");
			totalStatistics.write("If");
			totalStatistics.write("Assignment");
			totalStatistics.write("Function");
			totalStatistics.write("FunctionDeclaration");
			totalStatistics.write("Switch");
			totalStatistics.write("Declaration");
			totalStatistics.write("Continue");
			totalStatistics.write("Break");
			totalStatistics.write("Return");
			totalStatistics.write("For");
			totalStatistics.write("Else");
			totalStatistics.write("While");
			totalStatistics.write("Case");
			totalStatistics.write("Do");
			totalStatistics.endRecord();
/*
			stat1.write("Number");
			stat1.write("Project");
			stat1.write("MethodCall");
			stat1.write("If");
			stat1.write("Assignment");
			stat1.write("Method");
			stat1.write("Declaration");
			stat1.write("Parameter");
			stat1.write("Argument");
			stat1.write("Block");
			stat1.write("Return");
			stat1.write("other");
			stat1.endRecord();

			stat2.write("Number");
			stat2.write("Project");
			stat2.write("MethodDeclaration");
			stat2.write("Try");
			stat2.write("Switch");
			stat2.write("Constructor");
			stat2.write("Continue");
			stat2.write("Break");
			stat2.write("For");
			stat2.write("Else");
			stat2.write("While");
			stat2.write("Case");
			stat2.write("Catch");
			stat2.endRecord();
			*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

