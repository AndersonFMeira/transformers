package ca.eaq.transformers.models;

public class BattleByCourage implements Battle {

	private final int MAX_COURAGE = 4;
	private final int MAX_STRENGTH = 3;
	
	@Override
	public void fight(Transformer autobot, Transformer decepticon, BattleField battleField)  {
		
		if (isVictoryByCourage(autobot, decepticon)) {
			if (isVictoryBySpecs(autobot, decepticon)
					&& isVictoryBySpecs(autobot, decepticon)) {
				battleField.setAutobotsVictories();
			} else if (isVictoryBySpecs(decepticon, autobot)
					&& isVictoryBySpecs(decepticon, autobot)) {
				battleField.setDecepticonVictories();
			}
		}
	}
	
	private boolean isVictoryByCourage(Transformer autobot, Transformer decepticon) {
		if ((autobot.getSpecs().getCourage() - decepticon.getSpecs().getCourage()) >= MAX_COURAGE
				&& (autobot.getSpecs().getStrength() - decepticon.getSpecs().getStrength()) >= MAX_STRENGTH) {
			return true;
		}
		return false;
	}
	
	private boolean isVictoryBySpecs(Transformer t1, Transformer t2){
		if(t1.getSpecs().getCourage() > t2.getSpecs().getCourage() && isSpecBigger(t1.getSpecs().getStrength(), t2.getSpecs().getStrength())){
			return true;
		}
		return false;
	}
	
	private boolean isSpecBigger(int spec1, int spec2){
		if(spec1 > spec2){
			return true;
		}
		return false;
	}

}
