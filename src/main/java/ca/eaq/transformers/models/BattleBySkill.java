package ca.eaq.transformers.models;

public class BattleBySkill implements Battle {

	private final int MAX_SKILL = 3;
	
	@Override
	public void  fight(Transformer autobot, Transformer decepticon, BattleField battleField) {
		if (isVictoryBySkill(autobot, decepticon)) {
			if (autobot.getSpecs().getSkill() > decepticon.getSpecs().getSkill()) {
				battleField.setAutobotsVictories();
				battleField.getDecepticons().remove(decepticon);
			} else {
				battleField.setDecepticonVictories();
				battleField.getAutobots().remove(autobot);
			}
		}
	}

	private boolean isVictoryBySkill(Transformer autobot, Transformer decepticon) {

		if ((autobot.getSpecs().getSkill() - decepticon.getSpecs().getSkill()) >= MAX_SKILL) {
			return true;
		}

		return false;
	}

}
