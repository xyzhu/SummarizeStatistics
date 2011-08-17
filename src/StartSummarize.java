
public class StartSummarize {
	Summarizer summarizer;
	public static void main(String args[]){
		StartSummarize starter = new StartSummarize();
		starter.initialize(args[0], args[1], args[2]);
		starter.summarize();
	}

	public void initialize(String langType, String folddir, String type){
		if(langType.equals("java")){
			summarizer = new SummarizeJava(folddir, type);
		}
		else if(langType.equals("c++")){
			summarizer = new SummarizeCplusplus(folddir, type);
		}
		else if(langType.equals("c")){
			summarizer = new SummarizeC(folddir, type);
		}
	}

	public void summarize()
	{
		summarizer.writeStatistics();
	}
}