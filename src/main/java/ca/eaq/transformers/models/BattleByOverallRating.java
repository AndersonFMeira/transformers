package ca.eaq.transformers.models;

public class BattleByOverallRating implements Battle {

	@Override
	public void fight(Transformer autobot, Transformer decepticon, BattleField battleField) {
		
		if (isVictoryByOverallRating(autobot, decepticon)) {
			battleField.setAutobotsVictories();
			battleField.getDecepticons().remove(decepticon);
		} else if (isVictoryByOverallRating(decepticon, autobot)) {
			battleField.setDecepticonVictories();
			battleField.getAutobots().remove(autobot);
		} else {
			battleField.getAutobots().remove(autobot);
			battleField.getDecepticons().remove(decepticon);			
		}
		
		
	}
	
	private boolean isVictoryByOverallRating(Transformer t1, Transformer t2){
		
		if (t1.getOverallRating() > t2.getOverallRating()) {
			return true;
		}
		
		return false;
	}
}
