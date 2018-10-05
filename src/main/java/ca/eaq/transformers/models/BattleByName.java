package ca.eaq.transformers.models;

public class BattleByName implements Battle{

	private final String OPTIMUS = "OPTIMUSPRIME";
	private final String PREDAKING = "PREDAKING";
	
	@Override
	public void fight(Transformer autobot, Transformer decepticon, BattleField battleField) {
		
		if(isOverPower(autobot) && isOverPower(decepticon)){
			gameOver(autobot, decepticon, battleField);
		} else if (isOverPower(autobot)){
			battleField.setAutobotsVictories();
			battleField.getDecepticons().remove(decepticon);
		} else if (isOverPower(decepticon)) {
			battleField.setDecepticonVictories();
			battleField.getAutobots().remove(autobot);
		}
		
	}
	
	private boolean isOverPower(Transformer transformer){
		if(transformer.getName().replaceAll("\\s", "").equals(OPTIMUS) || transformer.getName().trim().equals(PREDAKING)){
			return true;
		}
		return false;
	}
	
	private void gameOver(Transformer autobot, Transformer decepticon, BattleField battleField){
		battleField.setGameOver();
	}

}
