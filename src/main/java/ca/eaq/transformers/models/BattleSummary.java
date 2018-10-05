package ca.eaq.transformers.models;

import java.util.ArrayList;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class representing the summary of the battle.")
public class BattleSummary {

	private final String AUTOBOTS = "AUTOBOTS";
	private final String DECEPTICONS = "DECEPTICONS";
	private final String NONE = "NONE";
	
    @ApiModelProperty(notes = "Numbers of battle that happened.", example = "3", required = true, position = 0)
	private int numberOfBattles;
    @ApiModelProperty(notes = "Winning team.", example = "AUTOBOTS", required = true, position = 1)
	private String winningTeam;
    @ApiModelProperty(notes = "Suvivers of the winning team.", example = "Bamblebee, Ironhide", required = true, position = 1)
	private List<String> winningTeamSurvivers;
    @ApiModelProperty(notes = "Suvivers of the losing team.", example = "Starscream", required = true, position = 1)
	private List<String> losingTeamSurvivers;
	
	public BattleSummary() {
		this.winningTeam = NONE;
		winningTeamSurvivers = new ArrayList<>();
		losingTeamSurvivers = new ArrayList<>();
	}

	public String getWinningTeam() {
		return winningTeam;
	}
	
	public int getNumberOfBattles() {
		return numberOfBattles;
	}

	public void setNumberOfBattles() {
		this.numberOfBattles++;
	}

	public void setWinningTeam(int autobotsVictories, int decepticonVictories) {
		if(autobotsVictories > decepticonVictories){
			this.winningTeam = AUTOBOTS;
		} else if(decepticonVictories > autobotsVictories) {
			this.winningTeam = DECEPTICONS;
		} 
	}
	
	public void setSuvivers(List<Transformer> autobots, List<Transformer> decepticons){
		if(!this.winningTeam.equals(NONE)){
			setWinningTeamSurvivers(getWinnersNames(autobots, decepticons));
			setLosingTeamSurvivers(getLosingNames(autobots, decepticons));
		} 
	}
	
	private List<String> getLosingNames(List<Transformer> autobots, List<Transformer> decepticons){
		if(this.winningTeam.equals(AUTOBOTS)){
			return getSuviversNames(decepticons);
		} else {
			return getSuviversNames(autobots);
		}
	}
	
	private List<String> getWinnersNames(List<Transformer> autobots, List<Transformer> decepticons){
		if(this.winningTeam.equals(AUTOBOTS)){
			return getSuviversNames(autobots);
		} else {
			return getSuviversNames(decepticons);
		}
	}
	
	private List<String> getSuviversNames(List<Transformer> transformers){
		List<String> names = new ArrayList<String>();
		transformers.forEach(t -> names.add(t.getName()));
		return names;
	}

	public List<String> getLosingTeamSurvivers() {
		return losingTeamSurvivers;
	}

	private void setLosingTeamSurvivers(List<String> losingTeamSurvivers) {
		this.losingTeamSurvivers = losingTeamSurvivers;
	}

	public List<String> getWinningTeamSurvivers() {
		return winningTeamSurvivers;
	}

	private void setWinningTeamSurvivers(List<String> winningTeamSurvivers) {
		this.winningTeamSurvivers = winningTeamSurvivers;
	}

}
