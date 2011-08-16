
public class StartSummarize {
	Summarizer summarizer;
	public static void main(String args[]){
		StartSummarize starter = new StartSummarize();
		starter.initialize(args[0], args[1]);
		starter.summarize();
	}

	public void initialize(String langType, String folddir){
		if(langType.equals("java")){
			summarizer = new SummarizeJava(folddir);
		}
		else if(langType.equals("c++")){
			summarizer = new SummarizeCplusplus(folddir);
		}
		else if(langType.equals("c")){
			summarizer = new SummarizeC(folddir);
		}
	}

	public void summarize()
	{
		summarizer.writeStatistics();
	}
}