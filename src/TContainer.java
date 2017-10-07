public class TContainer {
	// EndDateTime_MST1_6, Mass_MST1_6, DRSMean_MST1_6,DRSMax_MST1_6,
	// alphaAct_MST1_6, betaAct_MST1_6, gammaAct_MST1_6, Operator_MST1_6
	String EndDateTime_MST1_6;
	String Mass_MST1_6;
	String DRSMean_MST1_6;
	String DRSMax_MST1_6;
	String alphaAct_MST1_6;
	String betaAct_MST1_6;
	String gammaAct_MST1_6;
	String Operator_MST1_6;

	public TContainer()
	{
		
	}
	
	public TContainer(String text) {
		EndDateTime_MST1_6 = text;
		Mass_MST1_6 = text;
		DRSMean_MST1_6 = text;
		DRSMax_MST1_6 = text;
		alphaAct_MST1_6 = text;
		betaAct_MST1_6 = text;
		gammaAct_MST1_6 = text;
		Operator_MST1_6 = text;
	}
}
