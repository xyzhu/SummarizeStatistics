
public class StartSummarize {
	Summarizer summarizer;
	public static void main(String args[]){
		StartSummarize starter = new StartSummarize();
		starter.initialize(args[0], args[1], Integer.valueOf(args[2]));
		starter.summarize();
	}

	public void initialize(String langType, String folddir, int type){
		if(langType.equals("java")){
			summarizer = new SummarizeJava(folddir, type);
		}
		else if(langType.equals("c++")){
			summarizer = new SummarizeCplusplus(folddir, type);
		}
	}

	public void summarize()
	{
		summarizer.getStatistics();
	}
}