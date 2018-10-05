package ca.eaq.transformers.models;

import java.util.ArrayList;
import java.util.List;

public class BattleField {

	private int autobotsVictories;
	private int decepticonVictories;
	private List<Transformer> autobots;
	private List<Transformer> decepticons;
	
	private boolean isRoundOver;
	
	private BattleSummary battleSummary;
	
	private List<Battle> battle;

	public BattleField(List<Transformer> autobots, List<Transformer> decepticons) {
		this.autobots = autobots;
		this.decepticons = decepticons;
		this.battle = new ArrayList<Battle>();
		this.battle.add(new BattleByName());
		this.battle.add(new BattleByCourage());
		this.battle.add(new BattleBySkill());
		this.battle.add(new BattleByOverallRating());
		this.battleSummary = new BattleSummary();
	}

	public void setAutobotsVictories() {
		this.isRoundOver = true;
		this.autobotsVictories++;
	}

	public void setDecepticonVictories() {
		this.isRoundOver = true;
		this.decepticonVictories++;
	}
	
	public void setGameOver() {
		this.isRoundOver = true;
	}

	public List<Transformer> getAutobots() {
		return autobots;
	}

	public List<Transformer> getDecepticons() {
		return decepticons;
	}
	
	public void startBattle(){
		List<Transformer> autobots = new ArrayList<Transformer>(this.autobots);
		List<Transformer> decepticons = new ArrayList<Transformer>(this.decepticons);
		int t = getSmallerListSize(autobots, decepticons);
		for(int i = 0; i < t; i++){
			this.isRoundOver = false;
			goBattle(autobots.get(i), decepticons.get(i));
		}
	}
	
	private void goBattle(Transformer autobot, Transformer decepticon){
		this.battleSummary.setNumberOfBattles();
		this.battle.stream().filter(i -> !this.isRoundOver).forEach(b -> b.fight(autobot, decepticon, this));
	}
	
	private int getSmallerListSize(List<Transformer> autobots, List<Transformer> decepticons){
		if(autobots.size() < decepticons.size()){
			return autobots.size();
		} else {
			return decepticons.size();
		}
	}
	
	public BattleSummary getBattleSummary(){
		this.battleSummary.setWinningTeam(this.autobotsVictories, this.decepticonVictories);
		this.battleSummary.setSuvivers(this.autobots, this.decepticons);
		return battleSummary;
	}
	

}
